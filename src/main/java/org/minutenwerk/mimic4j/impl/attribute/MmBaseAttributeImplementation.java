package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.message.MmErrorMessageType;
import org.minutenwerk.mimic4j.impl.message.MmMessage;
import org.minutenwerk.mimic4j.impl.message.MmMessageList;
import org.minutenwerk.mimic4j.impl.message.MmMessageSeverity;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmBaseAttributeImplementation is the abstract base class for all mimics of editable attributes. This class implements the mimic of
 * setting a value into an attribute either from modelside or from viewside, converting the value between modelside and viewside type and
 * vice versa, validating a value entered from viewside, resetting the value, and controlling change events and the mimic state.
 *
 * @param               <CALLBACK>         Interface defining callback methods, extending {@link MmBaseCallback}.
 * @param               <CONFIGURATION>    Type of configuration base class for attributes.
 * @param               <MODELSIDE_VALUE>  Type of modelside value of attribute, passed to model.
 * @param               <VIEWSIDE_VALUE>   Type of viewside value of attribute, passed to JSF tag.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-lifecycle, group-override
 */
public abstract class MmBaseAttributeImplementation<CALLBACK extends MmBaseCallback,
  CONFIGURATION extends MmBaseAttributeConfiguration<MODELSIDE_VALUE>, MODELSIDE_VALUE, VIEWSIDE_VALUE>
  extends MmBaseImplementation<MmBaseAttributeDeclaration<?, MODELSIDE_VALUE, VIEWSIDE_VALUE>, CONFIGURATION>
  implements MmAttributeMimic<MODELSIDE_VALUE, VIEWSIDE_VALUE> {

  /** Class internal constant to control index of generic type MODELSIDE_VALUE. */
  private static final int    GENERIC_PARAMETER_INDEX_MODELSIDE_VALUE = 3;

  /** Class internal constant to control index of generic type VIEWSIDE_VALUE. */
  private static final int    GENERIC_PARAMETER_INDEX_VIEWSIDE_VALUE  = 4;

  /** The logger of this class. */
  private static final Logger LOGGER                                  = LogManager.getLogger(MmBaseAttributeImplementation.class);

  /**
   * MmEditableErrorState is an enumeration of values regarding an attribute's error state during conversion and validation.
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
      return this.name();
    }
  }

  /**
   * MmEditableState is an enumeration of values regarding an attribute's value state.
   *
   * @author  Olaf Kossak
   */
  protected enum MmValueState {

    /** The state of the attribute is undefined. */
    UNDEFINED,

    /** The attribute is in the state, that default value is passed to modelside value. */
    SET_FROM_DEFAULT_TO_MODELSIDE,

    /** The attribute is in the state, that model value is passed to modelside value. */
    SET_FROM_MODEL_TO_MODELSIDE,

    /** The attribute is in the state, that modelside value is converted to viewside type and passed to viewside value. */
    CONVERTED_MODELSIDE_TO_VIEWSIDE,

    /** The attribute is in the state, that view value is passed to viewside value. */
    SET_FROM_VIEW_TO_VIEWSIDE,

    /** The attribute is in the state, that viewside value is converted to modelside type and passed to modelside value. */
    CONVERTED_VIEWSIDE_TO_MODELSIDE,

    /**
     * The attribute is in the state, that modelside value has validated successfully. If validation fails, the attribute state remains
     * unchanged, but the error state will be changed.
     */
    VALID_VALUE_IN_MODELSIDE;

    /**
     * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
     * at any time.
     *
     * @return  Some information about this object for development purposes like debugging and logging.
     */
    @Override
    public String toString() {
      return this.name();
    }
  }

  /** The list of messages. */
  protected final MmMessageList   messageList;

  /** The state of the attribute regarding its viewside value and modelside value. Initially the state is {@link MmValueState.UNDEFINED}. */
  protected MmValueState          valueState;

  /** The state of the attribute regarding its reset value. Initially the state is {@link MmValueState.UNDEFINED}. */
  protected MmValueState          resetState;

  /**
   * The state of the attribute regarding errors during conversion and validation. Initially the state is {@link MmErrorState.UNDEFINED}.
   */
  protected MmAttributeErrorState errorState;

  /** The attribute's reset value of type MODELSIDE_VALUE. */
  protected MODELSIDE_VALUE       resetValue;

  /** The attribute's modelside value of type MODELSIDE_VALUE. */
  protected MODELSIDE_VALUE       modelsideValue;

  /** The attribute's viewside value of type VIEWSIDE_VALUE. */
  protected VIEWSIDE_VALUE        viewsideValue;

  /** True, if attribute's viewside value has been changed. */
  protected boolean               isChangedFromViewside;

  /**
   * Creates a new MmBaseAttributeImplementation instance.
   *
   * @param  pParent  The parent mimic of type MmBaseDeclaration.
   */
  public MmBaseAttributeImplementation(MmDeclarationMimic pParent) {
    super(pParent);
    this.messageList = new MmMessageList();
    this.valueState  = MmValueState.UNDEFINED;
    this.resetState  = MmValueState.UNDEFINED;
    this.errorState  = MmAttributeErrorState.SUCCESS;
  }

  /**
   * Initializes this mimic after constructor phase, calls super.initialize(), if you override this method, you must call
   * super.initialize()!
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void initialize() {
    super.initialize();
    this.doMmSetDefaults();
  }

  /**
   * Passes default value to modelside value of mimic. The state is:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.SET_FROM_DEFAULT_TO_MODELSIDE}</li>
   *   <li>resetState is {@link MmValueState.SET_FROM_DEFAULT_TO_MODELSIDE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * @jalopy.group  group-lifecycle
   */
  public void doPassDefaultToModelsideValue() {
    this.ensureInitialization();

    String originalDebugState = this.toStringTraceState();

    this.modelsideValue        = this.declaration.getMmDefaultValue();
    this.resetValue            = this.modelsideValue;
    this.valueState            = MmValueState.SET_FROM_DEFAULT_TO_MODELSIDE;
    this.resetState            = MmValueState.SET_FROM_DEFAULT_TO_MODELSIDE;
    this.errorState            = MmAttributeErrorState.SUCCESS;
    this.isChangedFromViewside = false;

    this.logDebugChange(originalDebugState);
  }

  /**
   * Converts and passes modelside value of type MODELSIDE_VALUE to viewside value of type VIEWSIDE_VALUE. If conversion succeeds:
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
  public void doPassModelsideToViewsideValue() {
    this.ensureInitialization();

    String originalDebugState = this.toStringTraceState();

    try {
      this.viewsideValue = this.declaration.callbackMmConvertModelsideToViewsideValue(this.modelsideValue);
      this.valueState    = MmValueState.CONVERTED_MODELSIDE_TO_VIEWSIDE;
      this.errorState    = MmAttributeErrorState.SUCCESS;
    } catch (MmModelsideConverterException converterException) {
      this.errorState = MmAttributeErrorState.ERROR_UNCONVERTABLE_MODELSIDE_TO_VIEWSIDE;
      throw converterException;
    }

    this.logDebugChange(originalDebugState);
  }

  /**
   * Passes reset value to modelside value of mimic. The post state is:
   *
   * <ul>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * @throws        IllegalStateException  if reset action is not enabled.
   *
   * @jalopy.group  group-lifecycle
   */
  public void doPassResetToModelsideValue() {
    this.ensureInitialization();

    if (!this.isMmResetEnabled()) {
      throw new IllegalStateException("reset not possible, reset state = " + this.resetState);
    }

    String originalDebugState = this.toStringTraceState();

    this.modelsideValue        = this.resetValue;
    this.valueState            = this.resetState;
    this.errorState            = MmAttributeErrorState.SUCCESS;
    this.isChangedFromViewside = false;

    this.logDebugChange(originalDebugState);
  }

  /**
   * Validates syntactically, converts and passes viewside value of type VIEWSIDE_VALUE to modelside value of type MODELSIDE_VALUE.
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
  public void doPassViewsideToModelsideValue() {
    this.ensureInitialization();

    String originalDebugState = this.toStringTraceState();

    // syntactic validation of viewside
    if (this.isMmRequired() && this.isMmEmpty()) {
      this.errorState = MmAttributeErrorState.ERROR_REQUIRED_VALUE_IN_VIEWSIDE;

      MmMessage message = new MmMessage(MmErrorMessageType.BUSINESS_LOGIC_ERROR, MmMessageSeverity.USER_ERROR, this, this.getMmId(),
          MmMessageType.ERROR_REQUIRED);
      this.messageList.addMessage(message);

    } else {

      // conversion to modelside
      try {
        if (this.isChangedFromViewside) {
          this.modelsideValue = this.declaration.callbackMmConvertViewsideToModelsideValue(this.viewsideValue);
        }
        this.valueState = MmValueState.CONVERTED_VIEWSIDE_TO_MODELSIDE;
        this.errorState = MmAttributeErrorState.SUCCESS;
      } catch (MmViewsideConverterException converterException) {
        this.errorState = MmAttributeErrorState.ERROR_UNCONVERTABLE_VIEWSIDE_TO_MODELSIDE;

        MmMessage message = new MmMessage(converterException);
        this.messageList.addMessage(message);
      }
    }

    this.logDebugChange(originalDebugState);
  }

  /**
   * Semantic validation of modelside value of type MODELSIDE_VALUE. If validation succeeds:
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
  public void doValidateModelsideValue() {
    this.ensureInitialization();

    String originalDebugState = this.toStringTraceState();

    try {
      this.declaration.callbackMmValidateModelsideValue(this.modelsideValue);
      this.valueState = MmValueState.VALID_VALUE_IN_MODELSIDE;
      this.errorState = MmAttributeErrorState.SUCCESS;
    } catch (MmValidatorException validatorException) {
      this.errorState = MmAttributeErrorState.ERROR_INVALID_VALUE_IN_MODELSIDE;

      MmMessage message = new MmMessage(validatorException);
      this.messageList.addMessage(message);
    }

    this.logDebugChange(originalDebugState);
  }

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.doValidateModelsideValue()} is
   * executable.
   *
   * @return        <code>true</code> if the action {@link MmEditableMimic.doValidateModelsideValue()} is executable.
   *
   * @throws        IllegalStateException  in case of unconsidered value state
   *
   * @jalopy.group  group-lifecycle
   */
  public boolean isDoValidateModelsideValueEnabled() {
    this.ensureInitialization();

    switch (this.valueState) {
      case UNDEFINED: {
        return false;
      }

      // if viewside is changed from view (but not converted yet), do not validate
      case SET_FROM_VIEW_TO_VIEWSIDE: {
        return false;
      }

      // if modelside is set from default, validate
      case SET_FROM_DEFAULT_TO_MODELSIDE: {
        return true;
      }

      // or from model, validate
      case SET_FROM_MODEL_TO_MODELSIDE: {
        return true;
      }

      // or converted to viewside, validate
      case CONVERTED_MODELSIDE_TO_VIEWSIDE: {
        return (this.errorState == MmAttributeErrorState.SUCCESS);
      }

      // or converted from viewside, validate
      case CONVERTED_VIEWSIDE_TO_MODELSIDE: {
        return (this.errorState == MmAttributeErrorState.SUCCESS);
      }

      // or valid already, validate again
      case VALID_VALUE_IN_MODELSIDE: {
        return true;
      }

      default: {
        throw new IllegalStateException("illegal value state " + this.valueState);
      }
    }
  }

  /**
   * Sets modelside value of mimic to specified value. Sets reset value as well to specified value. Post action state is:
   *
   * <ul>
   *   <li>valueState is {@link MmValueState.SET_FROM_MODEL_TO_MODELSIDE}</li>
   *   <li>resetState is {@link MmValueState.SET_FROM_MODEL_TO_MODELSIDE}</li>
   *   <li>errorState is {@link MmAttributeErrorState.NO_ERROR}</li>
   * </ul>
   *
   * @param         pModelsideValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void setMmModelsideValue(MODELSIDE_VALUE pModelsideValue) {
    this.ensureInitialization();

    String originalDebugState = this.toStringTraceState();

    this.modelsideValue        = pModelsideValue;
    this.resetValue            = this.modelsideValue;
    this.valueState            = MmValueState.SET_FROM_MODEL_TO_MODELSIDE;
    this.resetState            = MmValueState.SET_FROM_MODEL_TO_MODELSIDE;
    this.errorState            = MmAttributeErrorState.SUCCESS;
    this.isChangedFromViewside = false;

    this.logDebugChange(originalDebugState);
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
    this.ensureInitialization();

    String originalDebugState = this.toStringTraceState();

    // set changed flag just in case it has changed
    if ((this.viewsideValue != null) && (!this.viewsideValue.equals(pViewsideValue))) {
      this.isChangedFromViewside = true;
    }

    this.viewsideValue = pViewsideValue;
    this.valueState    = MmValueState.SET_FROM_VIEW_TO_VIEWSIDE;
    this.errorState    = MmAttributeErrorState.SUCCESS;

    this.logDebugChange(originalDebugState);
  }

  /**
   * Resets the attribute to its reset value, by:
   *
   * <ol>
   *   <li>passing reset value into modelside value</li>
   *   <li>converting modelside value to viewside type</li>
   *   <li>passing converted value into viewside value</li>
   * </ol>
   *
   * @jalopy.group  group-override
   */
  @Override
  public void doMmReset() {
    this.ensureInitialization();

    this.doPassResetToModelsideValue();
    this.doPassModelsideToViewsideValue();
  }

  /**
   * Sets the attribute to its default value, by:
   *
   * <ol>
   *   <li>passing default value into modelside value</li>
   *   <li>converting modelside value to viewside type</li>
   *   <li>passing converted value into viewside value</li>
   * </ol>
   *
   * @jalopy.group  group-override
   */
  @Override
  public void doMmSetDefaults() {
    this.ensureInitialization();

    this.doPassDefaultToModelsideValue();
    this.doPassModelsideToViewsideValue();
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>passing viewside value into modelside value</li>
   *   <li>converting viewside value to modelside type</li>
   *   <li>passing converted value into modelside value</li>
   *   <li>validating modelside value</li>
   * </ol>
   *
   * @jalopy.group  group-override
   */
  @Override
  public void doMmValidate() {
    this.ensureInitialization();

    this.doPassViewsideToModelsideValue();
    this.doValidateModelsideValue();
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
    this.ensureInitialization();

    return 1;
  }

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @return        The attribute's default value of type MODELSIDE_VALUE.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MODELSIDE_VALUE getMmDefaultValue() {
    this.ensureInitialization();

    final MODELSIDE_VALUE defaultValue = this.configuration.getDefaultValue();
    return this.declaration.callbackMmGetDefaultValue(defaultValue);
  }

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return        The attribute's maximum number of characters for input.
   *
   * @jalopy.group  group-override
   */
  @Override
  public int getMmFormatMaxLength() {
    this.ensureInitialization();

    final int returnInt = this.declaration.callbackMmGetMaxLength(MmBaseAttributeDeclaration.EDITABLE_DEFAULT_MAX_LENGTH);
    assert returnInt != 0 : "callbackMmGetMaxLength cannot return 0";
    return returnInt;
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return        The attribute's format pattern for displaying viewside value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmFormatPattern() {
    this.ensureInitialization();

    final String i18nFormatPattern = this.getMmI18nText(MmMessageType.FORMAT);
    final String returnString      = this.declaration.callbackMmGetFormatPattern(i18nFormatPattern);
    assert returnString != null : "callbackMmGetFormatPattern cannot return null";
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
    this.ensureInitialization();

    return MmBooleanLayout.PAGE_DIRECTION;
  }

  /**
   * Returns the attribute's type of modelside value (MODELSIDE_VALUE).
   *
   * @return        The attribute's type of modelside value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<MODELSIDE_VALUE> getMmModelsideType() {
    this.ensureInitialization();

    return findGenericsParameterType(this.getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_MODELSIDE_VALUE);
  }

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return        The modelside value of the mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MODELSIDE_VALUE getMmModelsideValue() {
    this.ensureInitialization();

    return this.modelsideValue;
  }

  /**
   * Returns the attribute's reset value of type MODELSIDE_VALUE.
   *
   * @return        The attribute's reset value of type MODELSIDE_VALUE.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MODELSIDE_VALUE getMmResetValue() {
    this.ensureInitialization();

    return this.resetValue;
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
    this.ensureInitialization();

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
    this.ensureInitialization();

    String returnString = super.getMmShortDescription();
    if (this.isMmRequired()) {
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
    this.ensureInitialization();

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
    this.ensureInitialization();

    return findGenericsParameterType(this.getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_VIEWSIDE_VALUE);
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
    this.ensureInitialization();

    return this.viewsideValue;
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
    this.ensureInitialization();

    return this.viewsideValue == null;
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
    this.ensureInitialization();

    return this.declaration.callbackMmIsRequired(this.configuration.isRequired());
  }

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.doMmReset} is executable.
   *
   * @return        <code>true</code> if the action {@link MmEditableMimic.doMmReset} is executable.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmResetEnabled() {
    this.ensureInitialization();

    return ((this.resetState == MmValueState.SET_FROM_MODEL_TO_MODELSIDE)
      || (this.resetState == MmValueState.SET_FROM_DEFAULT_TO_MODELSIDE));
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
    this.ensureInitialization();

    return this.valueState == MmValueState.VALID_VALUE_IN_MODELSIDE;
  }

  /**
   * Clears list of messages of this mimic.
   */
  public void clearMmMessageList() {
    this.ensureInitialization();

    this.messageList.clear();
  }

  /**
   * Returns the highest severity of error message of this mimic, returns null in case of no messages.
   *
   * @return  The highest severity of error message of this mimic.
   */
  public MmMessageSeverity getMmMaximumSeverity() {
    this.ensureInitialization();

    return this.messageList.getMaximumSeverity();
  }

  /**
   * Returns a list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   *
   * @return  A list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   */
  public List<MmMessage> getMmMessages() {
    this.ensureInitialization();

    return this.messageList.getMessages();
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  public List<MmSelectOption<Object>> getMmSelectOptions() {
    this.ensureInitialization();

    return this.declaration.callbackMmGetSelectOptions();
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from viewside.
   */
  @Override
  public final boolean isMmChangedFromViewside() {
    this.ensureInitialization();

    return this.isChangedFromViewside;
  }

  /**
   * Logs the attribute's state before and after an action, if TRACE level is switched on.
   *
   * @param  pOriginalDebugState  The attribute's state before an action.
   */
  protected void logDebugChange(String pOriginalDebugState) {
    if (LOGGER.isTraceEnabled()) {
      final String trimmedName = ("(" + this.name + "                              ").substring(0, 29) + ")";
      LOGGER.trace(trimmedName + " was " + pOriginalDebugState);
      LOGGER.trace(trimmedName + "  is " + this.toStringTraceState());
    }
  }

  /**
   * Returns state information about this mimic.
   *
   * @return  State information about this mimic.
   */
  protected String toStringState() {
    StringBuilder sb = new StringBuilder();
    switch (this.valueState) {
      case UNDEFINED: {
        sb.append("U         ");
        break;
      }
      case SET_FROM_DEFAULT_TO_MODELSIDE: {
        sb.append("   D>M    ");
        break;
      }
      case SET_FROM_MODEL_TO_MODELSIDE: {
        sb.append("   M>M    ");
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
    switch (this.errorState) {
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

    String modelsideValueAsString = "----                ";
    if (this.modelsideValue != null) {
      modelsideValueAsString = this.modelsideValue.toString();
      modelsideValueAsString += "                    ";
      modelsideValueAsString = modelsideValueAsString.substring(0, 20);
    }
    sb.append(modelsideValueAsString);
    sb.append("/");

    String viewsideValueAsString = "----                ";
    if (this.viewsideValue != null) {
      viewsideValueAsString = this.viewsideValue.toString();
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
      return this.toStringState();
    } else {
      return "";
    }
  }

}
