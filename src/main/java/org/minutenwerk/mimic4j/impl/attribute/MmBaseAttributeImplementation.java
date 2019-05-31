package org.minutenwerk.mimic4j.impl.attribute;

import java.lang.annotation.Annotation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmEditableMimicImpl;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;
import org.minutenwerk.mimic4j.impl.message.MmErrorMessageType;
import org.minutenwerk.mimic4j.impl.message.MmMessage;
import org.minutenwerk.mimic4j.impl.message.MmMessageList;
import org.minutenwerk.mimic4j.impl.message.MmMessageSeverity;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmBaseAttributeImplementation is the abstract base class for all mimics of editable attributes. This class implements the mimic of
 * setting a value into an attribute either by a attribute accessor on a model or from viewside, converting the value between model and
 * viewside type and vice versa, validating a value entered from viewside and controlling change events and the mimic's state.
 *
 * @param               <CALLBACK>         Interface defining callback methods, extending {@link MmBaseCallback}.
 * @param               <CONFIGURATION>    Type of configuration, holding state of mimic configuration.
 * @param               <ATTRIBUTE_MODEL>  Type of attribute of model.
 * @param               <VIEWSIDE_VALUE>   Type of viewside value of attribute, passed to JSF tag.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-lifecycle, group-override, group-helper
 */
public abstract class MmBaseAttributeImplementation<CALLBACK extends MmBaseCallback,
  CONFIGURATION extends MmBaseAttributeConfiguration<ATTRIBUTE_MODEL>, ANNOTATION extends Annotation, ATTRIBUTE_MODEL, VIEWSIDE_VALUE>
  extends MmBaseImplementation<MmBaseAttributeDeclaration<?, ATTRIBUTE_MODEL, VIEWSIDE_VALUE>, CONFIGURATION, ANNOTATION>
  implements MmAttributeMimic<ATTRIBUTE_MODEL, VIEWSIDE_VALUE>, MmEditableMimicImpl {

  /** Class internal constant to control index of generic type ATTRIBUTE_MODEL. */
  private static final int    GENERIC_PARAMETER_INDEX_ATTRIBUTE_MODEL = 3;

  /** Class internal constant to control index of generic type VIEWSIDE_VALUE. */
  private static final int    GENERIC_PARAMETER_INDEX_VIEWSIDE_VALUE  = 4;

  /** The logger of this class. */
  private static final Logger LOGGER                                  = LogManager.getLogger(MmBaseAttributeImplementation.class);

  /**
   * MmAttributeErrorState is an enumeration of values regarding an attribute's error state during conversion and validation.
   *
   * @author  Olaf Kossak
   */
  protected enum MmAttributeErrorState {

    /** No error occurred during last conversion or validation. */
    SUCCESS,

    /** Error occurred during conversion from modelside value to viewside value. */
    ERROR_UNCONVERTABLE_MODELSIDE_TO_VIEWSIDE,

    /** Error occurred during conversion from viewside value to modelside value. */
    ERROR_UNCONVERTABLE_VIEWSIDE_TO_MODELSIDE,

    /** Error occurred because required value in viewside is missing. */
    ERROR_REQUIRED_VALUE_IN_VIEWSIDE,

    /** Error occurred during validation of modelside value. */
    ERROR_INVALID_VALUE_IN_MODELSIDE;

    /**
     * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
     * at any time.
     *
     * @return  Some information about this object for development purposes like debugging and logging.
     */
    @Override
    public String toString() {
      return name();
    }
  }

  /**
   * MmValueState is an enumeration of values regarding the state of an attribute's value. If validation fails, the attribute state remains
   * unchanged, but the error state will be changed.
   *
   * @author  Olaf Kossak
   */
  protected enum MmValueState {

    /** The state of the attribute is undefined. */
    UNDEFINED,

    /** The attribute is in the state, that modelside value is converted to viewside type and passed to viewside value. */
    CONVERTED_MODELSIDE_TO_VIEWSIDE,

    /** The attribute is in the state, that view value is passed to viewside value. */
    SET_FROM_VIEW_TO_VIEWSIDE,

    /** The attribute is in the state, that viewside value is converted to modelside type and passed to modelside value. */
    CONVERTED_VIEWSIDE_TO_MODELSIDE,

    /** The attribute is in the state, that modelside value has validated successfully. */
    VALID_VALUE_IN_MODELSIDE;

    /**
     * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
     * at any time.
     *
     * @return  Some information about this object for development purposes like debugging and logging.
     */
    @Override
    public String toString() {
      return name();
    }
  }

  /** The list of messages related to this attribute. */
  protected final MmMessageList                     messageList;

  /** This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root accessor. */
  protected final MmRootAccessor<?>                 rootAccessor;

  /**
   * This attribute has a model of type ATTRIBUTE_MODEL. The model has a model accessor. Its first generic, the type of the parent model, is
   * undefined.
   */
  protected MmAttributeAccessor<?, ATTRIBUTE_MODEL> modelAccessor;

  /** The attribute's viewside value of type VIEWSIDE_VALUE. */
  protected VIEWSIDE_VALUE                          viewsideValue;

  /** True, if attribute's viewside value has been changed. */
  protected boolean                                 isChangedFromViewside;

  /** The state of the attribute regarding its viewside value and modelside value. Initially the state is {@link MmValueState.UNDEFINED}. */
  protected MmValueState                            valueState;

  /** The state regarding errors during conversion and validation. Initially the state is {@link MmErrorState.UNDEFINED}. */
  protected MmAttributeErrorState                   errorState;

  /**
   * Creates a new MmBaseAttributeImplementation instance.
   *
   * @param  parent  The parent mimic of type MmBaseDeclaration.
   */
  public MmBaseAttributeImplementation(final MmDeclarationMimic parent) {
    super(parent);
    messageList  = new MmMessageList();
    rootAccessor = onConstructRootAccessor();
    valueState   = MmValueState.UNDEFINED;
    errorState   = MmAttributeErrorState.SUCCESS;
  }

  /**
   * Initializes this mimic after constructor phase, calls super.initialize(), if you override this method, you must call
   * super.initialize()!
   *
   * @throws        IllegalStateException  In case of root accessor or model accessor is not defined.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void initialize() {
    super.initialize();

    // initialize modelAccessor
    modelAccessor = declaration.callbackMmGetAccessor(rootAccessor);
    if (modelAccessor == null) {
      throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + getMmFullName());
    }
  }

  /**
   * Evaluates accessor of root component of model.
   *
   * @return        The accessor of root component of model.
   *
   * @throws        IllegalStateException  In case of there is no definition of a root accessor.
   *
   * @jalopy.group  group-initialization
   */
  private MmRootAccessor<?> onConstructRootAccessor() {
    MmBaseContainerImplementation<?, ?, ?, ?> containerAncestor = getMmImplementationAncestorOfType(MmBaseContainerImplementation.class);
    if (containerAncestor == null) {
      throw new IllegalStateException("no ancestor of type MmContainerMimic for " + getMmFullName());
    } else {
      MmRootAccessor<?> containerRootAccessor = containerAncestor.onInitializeGetRootAccessor();
      if (containerRootAccessor == null) {
        throw new IllegalStateException("no definition of rootAccessor for " + getMmFullName());
      }
      return containerRootAccessor;
    }
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>converting viewside value to modelside type</li>
   *   <li>passing converted value into modelside value</li>
   *   <li>validating modelside value</li>
   * </ol>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void doMmValidate() {
    assureInitialization();

    passViewsideToModelside();
    validateModelside();
  }

  /**
   * Converts and passes modelside value of type ATTRIBUTE_MODEL to viewside value of type VIEWSIDE_VALUE. If conversion succeeds:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.CONVERTED_MODELSIDE_TO_VIEWSIDE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * <p>If conversion fails:</p>
   *
   * <ul>
   *   <li>valueState remains unchanged</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_UNCONVERTABLE_MODELSIDE_TO_VIEWSIDE}</li>
   *   <li>a {@link MmModelsideConverterException} is thrown</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passModelsideToViewside() {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    try {
      ATTRIBUTE_MODEL modelsideVALUE = modelAccessor.get();
      viewsideValue = declaration.callbackMmConvertModelsideToViewsideValue(modelsideVALUE);
      valueState    = MmValueState.CONVERTED_MODELSIDE_TO_VIEWSIDE;
      errorState    = MmAttributeErrorState.SUCCESS;
    } catch (MmModelsideConverterException converterException) {
      errorState = MmAttributeErrorState.ERROR_UNCONVERTABLE_MODELSIDE_TO_VIEWSIDE;
      throw converterException;
    }

    logDebugChange(originalDebugState);
  }

  /**
   * Validates syntactically, converts and passes viewside value of type VIEWSIDE_VALUE to modelside value of type ATTRIBUTE_MODEL.
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.CONVERTED_VIEWSIDE_TO_MODELSIDE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * <p>If conversion fails:</p>
   *
   * <ul>
   *   <li>valueState remains unchanged</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_REQUIRED_VALUE_IN_VIEWSIDE}, or</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_UNCONVERTABLE_VIEWSIDE_TO_MODELSIDE}</li>
   *   <li>an error message is produced and added to MmRoot</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passViewsideToModelside() {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    // syntactic validation of viewside
    if (isMmRequired() && isMmEmpty()) {
      errorState = MmAttributeErrorState.ERROR_REQUIRED_VALUE_IN_VIEWSIDE;

      MmMessage message = new MmMessage(MmErrorMessageType.BUSINESS_LOGIC_ERROR, MmMessageSeverity.USER_ERROR, this, getMmId(),
          MmMessageType.ERROR_REQUIRED);
      messageList.addMessage(message);

    } else {

      // conversion to modelside
      try {
        if (isChangedFromViewside) {
          ATTRIBUTE_MODEL modelsideValue = declaration.callbackMmConvertViewsideToModelsideValue(viewsideValue);
          modelAccessor.set(modelsideValue);
        }
        valueState = MmValueState.CONVERTED_VIEWSIDE_TO_MODELSIDE;
        errorState = MmAttributeErrorState.SUCCESS;
      } catch (MmViewsideConverterException converterException) {
        errorState = MmAttributeErrorState.ERROR_UNCONVERTABLE_VIEWSIDE_TO_MODELSIDE;

        MmMessage message = new MmMessage(converterException);
        messageList.addMessage(message);
      }
    }

    logDebugChange(originalDebugState);
  }

  /**
   * Sets viewside value of mimic to specified value. Post action state is:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.SET_FROM_VIEW_TO_VIEWSIDE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * @param         pViewsideValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void setMmViewsideValue(VIEWSIDE_VALUE pViewsideValue) {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    // set changed flag just in case it has changed
    if ((viewsideValue != null) && (!viewsideValue.equals(pViewsideValue))) {
      isChangedFromViewside = true;
    }

    viewsideValue = pViewsideValue;
    valueState    = MmValueState.SET_FROM_VIEW_TO_VIEWSIDE;
    errorState    = MmAttributeErrorState.SUCCESS;

    logDebugChange(originalDebugState);
  }

  /**
   * Semantic validation of modelside value of type ATTRIBUTE_MODEL. If validation succeeds:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.VALID_VALUE_IN_MODELSIDE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * <p>If validation fails:</p>
   *
   * <ul>
   *   <li>valueState remains unchanged</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_INVALID_VALUE_IN_MODELSIDE}</li>
   *   <li>an error message is produced and added to MmRoot</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void validateModelside() {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    if (isValidationEnabled()) {
      try {
        declaration.callbackMmValidateModelsideValue(modelAccessor.get());
        valueState = MmValueState.VALID_VALUE_IN_MODELSIDE;
        errorState = MmAttributeErrorState.SUCCESS;
      } catch (MmValidatorException validatorException) {
        errorState = MmAttributeErrorState.ERROR_INVALID_VALUE_IN_MODELSIDE;

        MmMessage message = new MmMessage(validatorException);
        messageList.addMessage(message);
      }
    }

    logDebugChange(originalDebugState);
  }

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.validateModelside()} is executable.
   *
   * @return        <code>true</code> if the action {@link MmEditableMimic.validateModelside()} is executable.
   *
   * @throws        IllegalStateException  in case of unconsidered value state
   *
   * @jalopy.group  group-lifecycle
   */
  private boolean isValidationEnabled() {
    assureInitialization();

    switch (valueState) {
      case UNDEFINED: {
        return false;
      }

      // if viewside is changed from view (but not converted yet), do not validate
      case SET_FROM_VIEW_TO_VIEWSIDE: {
        return false;
      }

      // if converted to viewside, validate
      case CONVERTED_MODELSIDE_TO_VIEWSIDE: {
        return (errorState == MmAttributeErrorState.SUCCESS);
      }

      // or converted from viewside, validate
      case CONVERTED_VIEWSIDE_TO_MODELSIDE: {
        return (errorState == MmAttributeErrorState.SUCCESS);
      }

      // or valid already, validate again
      case VALID_VALUE_IN_MODELSIDE: {
        return true;
      }

      default: {
        throw new IllegalStateException("illegal value state " + valueState);
      }
    }
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return        The attribute's number of columns.
   *
   * @jalopy.group  group-override
   */
  @Override
  public int getMmCols() {
    assureInitialization();

    return 1;
  }

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return        The attribute's maximum number of characters for input.
   *
   * @throws        IllegalStateException  In case of callbackMmGetMaxLength returns 0.
   *
   * @jalopy.group  group-override
   */
  @Override
  public int getMmFormatMaxLength() {
    assureInitialization();

    final int returnInt = declaration.callbackMmGetMaxLength(MmBaseAttributeDeclaration.EDITABLE_DEFAULT_MAX_LENGTH);
    if (LOGGER.isDebugEnabled()) {
      if (returnInt == 0) {
        throw new IllegalStateException("callbackMmGetMaxLength cannot return 0");
      }
    }
    return returnInt;
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return        The attribute's format pattern for displaying viewside value.
   *
   * @throws        IllegalStateException  In case of callbackMmGetFormatPattern returns an invalid format pattern.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmFormatPattern() {
    assureInitialization();

    final String i18nFormatPattern = getMmI18nText(MmMessageType.FORMAT);
    final String returnString      = declaration.callbackMmGetFormatPattern(i18nFormatPattern);
    if (LOGGER.isDebugEnabled()) {
      if (returnString == null) {
        throw new IllegalStateException("callbackMmGetFormatPattern() must return valid format pattern");
      }
    }
    return returnString;
  }

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return        The attribute's layout direction.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmBooleanLayout getMmLayout() {
    assureInitialization();

    return MmBooleanLayout.PAGE_DIRECTION;
  }

  /**
   * Returns accessor of attribute of model.
   *
   * @return        The accessor of attribute of model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_MODEL> getMmModelAccessor() {
    assureInitialization();

    return modelAccessor;
  }

  /**
   * Returns the attribute's type of modelside value (ATTRIBUTE_MODEL).
   *
   * @return        The attribute's type of modelside value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<ATTRIBUTE_MODEL> getMmModelsideType() {
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_ATTRIBUTE_MODEL);
  }

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return        The modelside value of the mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public ATTRIBUTE_MODEL getMmModelsideValue() {
    assureInitialization();

    return modelAccessor.get();
  }

  /**
   * Returns accessor of root component of model.
   *
   * @return        The accessor of root component of model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmRootAccessor<?> getMmRootAccessor() {
    assureInitialization();

    return rootAccessor;
  }

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return        The attribute's number of rows.
   *
   * @jalopy.group  group-override
   */
  @Override
  public int getMmRows() {
    assureInitialization();

    return 1;
  }

  /**
   * Returns a short description. The short description is evaluated from declaration method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> is not overridden, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmShortDescription() {
    assureInitialization();

    String returnString = super.getMmShortDescription();
    if (isMmRequired()) {
      returnString = returnString + " *";
    }
    return returnString;
  }

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return        The attribute's row size of option list.
   *
   * @jalopy.group  group-override
   */
  @Override
  public int getMmSize() {
    assureInitialization();

    return 3;
  }

  /**
   * Returns the attribute's type of viewside value (VIEWSIDE_VALUE).
   *
   * @return        The attribute's type of viewside value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<VIEWSIDE_VALUE> getMmViewsideType() {
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_VIEWSIDE_VALUE);
  }

  /**
   * Returns the attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @return        The attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @jalopy.group  group-override
   */
  @Override
  public VIEWSIDE_VALUE getMmViewsideValue() {
    assureInitialization();

    return viewsideValue;
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return        <code>True</code>, if mimic has been changed from viewside.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmChangedFromViewside() {
    assureInitialization();

    return isChangedFromViewside;
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return        <code>True</code> if the viewside value of this mimic is empty.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return viewsideValue == null;
  }

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return        <code>True</code> if a value from view has to be set.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmRequired() {
    assureInitialization();

    return declaration.callbackMmIsRequired(configuration.isRequired());
  }

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return        <code>True</code> if the mimic has been validated without any errors.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmValid() {
    assureInitialization();

    return valueState == MmValueState.VALID_VALUE_IN_MODELSIDE;
  }

  /**
   * Clears list of messages of this mimic.
   *
   * @jalopy.group  group-helper
   */
  @Override
  public void clearMmMessageList() {
    assureInitialization();

    messageList.clear();
  }

  /**
   * Returns the highest severity of error message of this mimic, returns null in case of no messages.
   *
   * @return        The highest severity of error message of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public MmMessageSeverity getMmMaximumSeverity() {
    assureInitialization();

    return messageList.getMaximumSeverity();
  }

  /**
   * Returns a list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   *
   * @return        A list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public List<MmMessage> getMmMessages() {
    assureInitialization();

    return messageList.getMessages();
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return        A list of options.
   *
   * @jalopy.group  group-helper
   */
  public List<MmSelectOption<Object>> getMmSelectOptions() {
    assureInitialization();

    return declaration.callbackMmGetSelectOptions();
  }

  /**
   * Logs the attribute's state before and after an action, if TRACE level is switched on.
   *
   * @param  pOriginalDebugState  The attribute's state before an action.
   */
  protected void logDebugChange(String pOriginalDebugState) {
    if (LOGGER.isTraceEnabled()) {
      final String trimmedName = ("(" + parentPath + "." + name + "                              ").substring(0, 29) + ")";
      LOGGER.trace(trimmedName + " was " + pOriginalDebugState);
      LOGGER.trace(trimmedName + "  is " + toStringTraceState());
    }
  }

  /**
   * Returns state information about this mimic.
   *
   * @return  State information about this mimic.
   */
  protected String toStringState() {
    StringBuilder sb = new StringBuilder();
    switch (valueState) {
      case UNDEFINED: {
        sb.append("U         ");
        break;
      }
      case CONVERTED_MODELSIDE_TO_VIEWSIDE: {
        sb.append("     M>V  ");
        break;
      }
      case SET_FROM_VIEW_TO_VIEWSIDE: {
        sb.append("       V<V");
        break;
      }
      case CONVERTED_VIEWSIDE_TO_MODELSIDE: {
        sb.append("     M<V  ");
        break;
      }
      case VALID_VALUE_IN_MODELSIDE: {
        sb.append(" VAL<M    ");
      }
    }
    switch (errorState) {
      case SUCCESS: {
        sb.append("         ");
        break;
      }
      case ERROR_REQUIRED_VALUE_IN_VIEWSIDE: {
        sb.append(" REQUIRED");
        break;
      }
      case ERROR_UNCONVERTABLE_MODELSIDE_TO_VIEWSIDE:
      case ERROR_UNCONVERTABLE_VIEWSIDE_TO_MODELSIDE: {
        sb.append(" UNCONVRT");
        break;
      }
      case ERROR_INVALID_VALUE_IN_MODELSIDE: {
        sb.append(" INVALID ");
        break;
      }
    }
    sb.append(" (");

    if (modelAccessor == null) {
      sb.append("NO AC");
    } else if (modelAccessor.isPresent()) {
      if (modelAccessor.get() == null) {
        sb.append("NULL ");
      } else {
        sb.append("MODEL");
      }
    } else {
      sb.append("NO M ");
    }
    sb.append("/");

    String viewsideValueAsString = "----                ";
    if (viewsideValue != null) {
      viewsideValueAsString = viewsideValue.toString();
      viewsideValueAsString += "                    ";
      viewsideValueAsString = viewsideValueAsString.substring(0, 20);
    }
    sb.append(viewsideValueAsString);
    sb.append(")");
    return sb.toString();
  }

  /**
   * Returns a string representation of the attribute's state and value, doesn't have side effects, for debugging purposes only.
   *
   * @return  A string representation of the attribute's state and value.
   */
  protected String toStringTraceState() {
    if (LOGGER.isTraceEnabled()) {
      return toStringState();
    } else {
      return "";
    }
  }

}
