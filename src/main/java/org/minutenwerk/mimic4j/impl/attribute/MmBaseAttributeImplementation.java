package org.minutenwerk.mimic4j.impl.attribute;

import java.lang.annotation.Annotation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmEditableMimicImpl;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;
import org.minutenwerk.mimic4j.impl.message.MmErrorMessageType;
import org.minutenwerk.mimic4j.impl.message.MmMessage;
import org.minutenwerk.mimic4j.impl.message.MmMessageList;
import org.minutenwerk.mimic4j.impl.message.MmMessageSeverity;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmBaseAttributeImplementation is the abstract base class for all mimics of editable attributes. This class implements the mimic of setting a value into an
 * attribute either by a attribute accessor on a model or from view model, converting the value between model and view model type and vice versa, validating a
 * value entered from view model and controlling change events and the mimic's state.
 *
 * @param               <CALLBACK>        Interface defining callback methods, extending {@link MmBaseCallback}.
 * @param               <CONFIGURATION>   Type of configuration, holding state of mimic configuration.
 * @param               <ANNOTATION>      Annotation type for configuration of this mimic.
 * @param               <ATTRIBUTE_TYPE>  Type of attribute of model.
 * @param               <VIEW_TYPE>       Type of view value of attribute, passed to HTML tag.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-lifecycle, group-override, group-helper
 */
public abstract class MmBaseAttributeImplementation<CALLBACK extends MmBaseCallback,
  CONFIGURATION extends MmBaseAttributeConfiguration<ATTRIBUTE_TYPE>, ANNOTATION extends Annotation, ATTRIBUTE_TYPE, VIEW_TYPE>
  extends MmBaseImplementation<MmBaseAttributeDeclaration<?, ATTRIBUTE_TYPE, VIEW_TYPE>, CONFIGURATION, ANNOTATION>
  implements MmAttributeMimic<ATTRIBUTE_TYPE, VIEW_TYPE>, MmEditableMimicImpl {

  /** Class internal constant to describe index of generic type ATTRIBUTE_TYPE. */
  private static final int    GENERIC_PARAMETER_INDEX_ATTRIBUTE_TYPE = 4;

  /** Class internal constant to describe index of generic type VIEW_TYPE. */
  private static final int    GENERIC_PARAMETER_INDEX_VIEW_TYPE      = 5;

  /** The logger of this class. */
  private static final Logger LOGGER                                 = LogManager.getLogger(MmBaseAttributeImplementation.class);

  /**
   * MmAttributeErrorState is an enumeration of values regarding an attribute's error state during conversion and validation.
   *
   * @author  Olaf Kossak
   */
  protected enum MmAttributeErrorState {

    /** No error occurred during last conversion or validation. */
    SUCCESS,

    /** Error occurred during conversion from data model value to view value. */
    ERROR_UNCONVERTABLE_DATA_MODEL_TO_VIEW_TYPE,

    /** Error occurred during conversion from view value to data model value. */
    ERROR_UNCONVERTABLE_VIEW_TYPE_TO_DATA_MODEL,

    /** Error occurred because required value in view model is missing. */
    ERROR_REQUIRED_VALUE_IN_VIEW_TYPE,

    /** Error occurred during validation of data model value. */
    ERROR_INVALID_VALUE_IN_DATA_MODEL;

    /**
     * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change at any time.
     *
     * @return  Some information about this object for development purposes like debugging and logging.
     */
    @Override
    public String toString() {
      return name();
    }
  }

  /**
   * MmValueState is an enumeration of values regarding the state of an attribute's value. If validation fails, the attribute state remains unchanged, but the
   * error state will be changed.
   *
   * @author  Olaf Kossak
   */
  protected enum MmValueState {

    /** The state of the attribute is undefined. */
    UNDEFINED,

    /** The attribute is in the state, that data model value is converted to view model type and passed to view value. */
    CONVERTED_DATA_MODEL_TO_VIEW_TYPE,

    /** The attribute is in the state, that view value is passed to view value. */
    SET_FROM_VIEW_TO_VIEW_TYPE,

    /** The attribute is in the state, that view value is converted to data model type and passed to data model value. */
    CONVERTED_VIEW_TYPE_TO_DATA_MODEL,

    /** The attribute is in the state, that data model value has validated successfully. */
    VALID_VALUE_IN_DATA_MODEL;

    /**
     * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change at any time.
     *
     * @return  Some information about this object for development purposes like debugging and logging.
     */
    @Override
    public String toString() {
      return name();
    }
  }

  /** The list of messages related to this attribute. */
  protected final MmMessageList                    messageList;

  /** This attribute has a model of type ATTRIBUTE_TYPE. The model has a model accessor. Its first generic, the type of the parent model, is undefined. */
  protected MmAttributeAccessor<?, ATTRIBUTE_TYPE> modelAccessor;

  /** The attribute's view value of type VIEW_TYPE. */
  protected VIEW_TYPE                              viewModelValue;

  /** True, if attribute's view value has been changed. */
  protected boolean                                isChangedFromView;

  /** The state of the attribute regarding its view value and data model value. Initially the state is {@link MmValueState.UNDEFINED}. */
  protected MmValueState                           valueState;

  /** The state regarding errors during conversion and validation. Initially the state is {@link MmErrorState.UNDEFINED}. */
  protected MmAttributeErrorState                  errorState;

  /**
   * Creates a new MmBaseAttributeImplementation instance.
   *
   * @param  parent  The parent mimic of type MmBaseDeclaration.
   */
  public MmBaseAttributeImplementation(final MmDeclarationMimic parent) {
    super(parent);
    messageList = new MmMessageList();
    valueState  = MmValueState.UNDEFINED;
    errorState  = MmAttributeErrorState.SUCCESS;
  }

  /**
   * Returns <code>true</code>, if the mimic has a model, which delivers data for this model, and a model instance is currently present.
   *
   * @return        <code>True</code>, if a model instance is currently present.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public final boolean isMmModelPresent() {
    assureInitialization();

    if (modelAccessor != null) {
      return modelAccessor.isPresent();
    } else {
      LOGGER.debug("no definition of callbackMmGetModelAccessor() for {}.{}.", parentPath, name);
      return false;
    }
  }

  /**
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call super.onInitialization()!
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void onInitialization() {
    super.onInitialization();

    // initialize parentAccessor
    final MmModelAccessor<?, ?> parentAccessor = onInitializeParentAccessor();

    // initialize modelAccessor
    modelAccessor = declaration.callbackMmGetModelAccessor(parentAccessor);
    if (modelAccessor == null) {
      LOGGER.debug("no definition of callbackMmGetModelAccessor() for {}.{}.", parentPath, name);
    }
  }

  /**
   * Evaluates accessor of component of parent container mimic.
   *
   * @return        The parent accessor.
   *
   * @throws        IllegalStateException  In case of there is no definition of a parent accessor.
   *
   * @jalopy.group  group-initialization
   */
  private MmModelAccessor<?, ?> onInitializeParentAccessor() {
    MmBaseContainerImplementation<?, ?, ?, ?> containerAncestor = getMmImplementationAncestorOfType(MmBaseContainerImplementation.class);
    if (containerAncestor == null) {
      throw new IllegalStateException("no ancestor of type MmContainerMimic for " + parentPath + "." + name);
    } else {
      MmModelAccessor<?, ?> containerAccessor = containerAncestor.onInitializeGetMmModelAccessor();
      if (containerAccessor == null) {
        throw new IllegalStateException("no definition of parentAccessor for " + parentPath + "." + name);
      }
      return containerAccessor;
    }
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>converting view value to data model type</li>
   *   <li>passing converted value into data model value</li>
   *   <li>validating data model value</li>
   * </ol>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void doMmValidate() {
    assureInitialization();

    passViewModelToDataModel();
    validateDataModel();
  }

  /**
   * Converts and passes data model value of type ATTRIBUTE_TYPE to view value of type VIEW_TYPE. If conversion succeeds:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.CONVERTED_DATA_MODEL_TO_VIEW_TYPE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * <p>If conversion fails:</p>
   *
   * <ul>
   *   <li>valueState remains unchanged</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_UNCONVERTABLE_DATA_MODEL_TO_VIEW_TYPE}</li>
   *   <li>a {@link MmDataModelConverterException} is thrown</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passDataModelToViewModel() {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    try {
      ATTRIBUTE_TYPE dataModelValue = null;
      if (modelAccessor != null) {
        dataModelValue = modelAccessor.get();
      } else {
        LOGGER.debug("no definition of callbackMmGetModelAccessor() for {}.{}.", parentPath, name);
      }
      viewModelValue = declaration.callbackMmConvertDataModelToViewModel(dataModelValue);
      valueState     = MmValueState.CONVERTED_DATA_MODEL_TO_VIEW_TYPE;
      errorState     = MmAttributeErrorState.SUCCESS;
    } catch (MmDataModelConverterException converterException) {
      errorState = MmAttributeErrorState.ERROR_UNCONVERTABLE_DATA_MODEL_TO_VIEW_TYPE;
      throw converterException;
    }

    logDebugChange(originalDebugState);
  }

  /**
   * Validates syntactically, converts and passes view value of type VIEW_TYPE to data model value of type ATTRIBUTE_TYPE.
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.CONVERTED_VIEW_TYPE_TO_DATA_MODEL}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * <p>If conversion fails:</p>
   *
   * <ul>
   *   <li>valueState remains unchanged</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_REQUIRED_VALUE_IN_VIEW_TYPE}, or</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_UNCONVERTABLE_VIEW_TYPE_TO_DATA_MODEL}</li>
   *   <li>an error message is produced and added to MmRoot</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passViewModelToDataModel() {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    // syntactic validation of view model
    if (isMmRequired() && isMmEmpty()) {
      errorState = MmAttributeErrorState.ERROR_REQUIRED_VALUE_IN_VIEW_TYPE;

      MmMessage message = new MmMessage(MmErrorMessageType.BUSINESS_LOGIC_ERROR, MmMessageSeverity.USER_ERROR, this, getMmId(), MmMessageType.ERROR_REQUIRED);
      messageList.addMessage(message);

    } else {

      // conversion to data model
      try {
        if (isChangedFromView) {
          ATTRIBUTE_TYPE dataModelValue = declaration.callbackMmConvertViewModelToDataModel(viewModelValue);
          modelAccessor.set(dataModelValue);
        }
        valueState = MmValueState.CONVERTED_VIEW_TYPE_TO_DATA_MODEL;
        errorState = MmAttributeErrorState.SUCCESS;
      } catch (MmViewModelConverterException converterException) {
        errorState = MmAttributeErrorState.ERROR_UNCONVERTABLE_VIEW_TYPE_TO_DATA_MODEL;

        MmMessage message = new MmMessage(converterException);
        messageList.addMessage(message);
      }
    }

    logDebugChange(originalDebugState);
  }

  /**
   * Sets view value of mimic to specified value. Post action state is:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.SET_FROM_VIEW_TO_VIEW_TYPE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * @param         pViewModelValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void setMmViewValue(VIEW_TYPE pViewModelValue) {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    // set changed flag just in case it has changed
    if ((viewModelValue != null) && (!viewModelValue.equals(pViewModelValue))) {
      isChangedFromView = true;
    }

    viewModelValue = pViewModelValue;
    valueState     = MmValueState.SET_FROM_VIEW_TO_VIEW_TYPE;
    errorState     = MmAttributeErrorState.SUCCESS;

    logDebugChange(originalDebugState);
  }

  /**
   * Semantic validation of data model value of type ATTRIBUTE_TYPE. If validation succeeds:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.VALID_VALUE_IN_DATA_MODEL}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * <p>If validation fails:</p>
   *
   * <ul>
   *   <li>valueState remains unchanged</li>
   *   <li>errorState is {@link MmAttributeErrorState.ERROR_INVALID_VALUE_IN_DATA_MODEL}</li>
   *   <li>an error message is produced and added to MmRoot</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void validateDataModel() {
    assureInitialization();

    String originalDebugState = toStringTraceState();

    if (isValidationEnabled()) {
      try {
        if (modelAccessor != null) {
          declaration.callbackMmValidateDataModel(modelAccessor.get());
        } else {
          LOGGER.debug("no definition of callbackMmGetModelAccessor() for {}.{}.", parentPath, name);
          declaration.callbackMmValidateDataModel(null);
        }
        valueState = MmValueState.VALID_VALUE_IN_DATA_MODEL;
        errorState = MmAttributeErrorState.SUCCESS;
      } catch (MmValidatorException validatorException) {
        errorState = MmAttributeErrorState.ERROR_INVALID_VALUE_IN_DATA_MODEL;

        MmMessage message = new MmMessage(validatorException);
        messageList.addMessage(message);
      }
    }

    logDebugChange(originalDebugState);
  }

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.validateDataModel()} is executable.
   *
   * @return        <code>true</code> if the action {@link MmEditableMimic.validateDataModel()} is executable.
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

      // if view model is changed from view (but not converted yet), do not validate
      case SET_FROM_VIEW_TO_VIEW_TYPE: {
        return false;
      }

      // if converted to view model, validate
      case CONVERTED_DATA_MODEL_TO_VIEW_TYPE: {
        return (errorState == MmAttributeErrorState.SUCCESS);
      }

      // or converted from view model, validate
      case CONVERTED_VIEW_TYPE_TO_DATA_MODEL: {
        return (errorState == MmAttributeErrorState.SUCCESS);
      }

      // or valid already, validate again
      case VALID_VALUE_IN_DATA_MODEL: {
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
   * Returns the attribute's format pattern for displaying view value in view. It is used during conversion from data model to view model value and vice
   * versa. It is dependent on the user's locale.
   *
   * @return        The attribute's format pattern for displaying view value.
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
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If <code>
   * callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @throws        IllegalStateException  In case of callbackMmGetLongDescription returns null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmLongDescription() {
    assureInitialization();

    final String i18nLongDescription = getMmI18nText(MmMessageType.LONG);
    final String returnString        = declaration.callbackMmGetLongDescription(i18nLongDescription);
    if (LOGGER.isDebugEnabled()) {
      if (returnString == null) {
        throw new IllegalStateException("callbackMmGetLongDescription cannot return null for " + this);
      }
    }
    return returnString;
  }

  /**
   * Returns the data model value of the mimic. The data model value is exchanged between model and mimic.
   *
   * @return        The data model value of the mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public ATTRIBUTE_TYPE getMmModel() {
    assureInitialization();

    if (modelAccessor != null) {
      return modelAccessor.get();
    } else {
      LOGGER.debug("no definition of callbackMmGetModelAccessor() for {}.{}.", parentPath, name);
      return null;
    }
  }

  /**
   * Returns accessor of attribute of model.
   *
   * @return        The accessor of attribute of model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_TYPE> getMmModelAccessor() {
    assureInitialization();

    return modelAccessor;
  }

  /**
   * Returns the attribute's type of data model value (ATTRIBUTE_TYPE).
   *
   * @return        The attribute's type of data model value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<ATTRIBUTE_TYPE> getMmModelType() {
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_ATTRIBUTE_TYPE);
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
   * Returns a short description. The short description is evaluated from declaration method <code>callbackMmGetShortDescription</code>. If <code>
   * callbackMmGetShortDescription</code> is not overridden, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @throws        IllegalStateException  TODOC
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmShortDescription() {
    assureInitialization();

    final String i18nShortDescription = getMmI18nText(MmMessageType.SHORT);
    String       returnString         = declaration.callbackMmGetShortDescription(i18nShortDescription);
    if (LOGGER.isDebugEnabled()) {
      if (returnString == null) {
        throw new IllegalStateException("callbackMmGetShortDescription cannot return null for " + this);
      }
    }
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
   * Returns the attribute's type of view value (VIEW_TYPE).
   *
   * @return        The attribute's type of view value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<VIEW_TYPE> getMmViewType() {
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_VIEW_TYPE);
  }

  /**
   * Returns the attribute's view value of type VIEW_TYPE.
   *
   * @return        The attribute's view value of type VIEW_TYPE.
   *
   * @jalopy.group  group-override
   */
  @Override
  public VIEW_TYPE getMmViewValue() {
    assureInitialization();

    return viewModelValue;
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from view model. If a mimic is changed, all ancestors of type MmEditableMimic are marked as
   * being changed as well.
   *
   * @return        <code>True</code>, if mimic has been changed from view model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final boolean isMmChangedFromView() {
    assureInitialization();

    return isChangedFromView;
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return        <code>True</code> if the view value of this mimic is empty.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return viewModelValue == null;
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

    return valueState == MmValueState.VALID_VALUE_IN_DATA_MODEL;
  }

  /**
   * Returns data model for self reference. The data model delivers parameters of the target URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return        The data model for self reference.
   *
   * @jalopy.group  group-override
   */
  @Override
  protected MmReferencableModel getMmReferencableModel() {
    final MmAttributeAccessor<?, ATTRIBUTE_TYPE> modelAccessor = getMmModelAccessor();
    if (modelAccessor == null) {
      return null;
    }

    final Object dataModel = modelAccessor.get();
    if (dataModel == null) {
      return null;
    }
    if (!(dataModel instanceof MmReferencableModel)) {
      return null;
    }
    return (MmReferencableModel)dataModel;
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
      case CONVERTED_DATA_MODEL_TO_VIEW_TYPE: {
        sb.append("     M>V  ");
        break;
      }
      case SET_FROM_VIEW_TO_VIEW_TYPE: {
        sb.append("       V<V");
        break;
      }
      case CONVERTED_VIEW_TYPE_TO_DATA_MODEL: {
        sb.append("     M<V  ");
        break;
      }
      case VALID_VALUE_IN_DATA_MODEL: {
        sb.append(" VAL<M    ");
      }
    }
    switch (errorState) {
      case SUCCESS: {
        sb.append("         ");
        break;
      }
      case ERROR_REQUIRED_VALUE_IN_VIEW_TYPE: {
        sb.append(" REQUIRED");
        break;
      }
      case ERROR_UNCONVERTABLE_DATA_MODEL_TO_VIEW_TYPE:
      case ERROR_UNCONVERTABLE_VIEW_TYPE_TO_DATA_MODEL: {
        sb.append(" UNCONVRT");
        break;
      }
      case ERROR_INVALID_VALUE_IN_DATA_MODEL: {
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

    String viewModelValueAsString = "----                ";
    if (viewModelValue != null) {
      viewModelValueAsString = viewModelValue.toString();
      viewModelValueAsString += "                    ";
      viewModelValueAsString = viewModelValueAsString.substring(0, 20);
    }
    sb.append(viewModelValueAsString);
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
