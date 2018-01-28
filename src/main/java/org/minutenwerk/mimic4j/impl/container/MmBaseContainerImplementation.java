package org.minutenwerk.mimic4j.impl.container;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmContainerMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmEditableMimic;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessage;
import org.minutenwerk.mimic4j.impl.message.MmMessageList;

/**
 * MmBaseContainerImplementation is the abstract base class for the implementation part of all container mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-do, group-get, group-clear, group-changed-front, group-required, group-is-reset, group-valid
 */
public abstract class MmBaseContainerImplementation<DECLARATION extends MmBaseContainerDeclaration<MODEL, ?>,
  MODEL, CONFIGURATION extends MmBaseConfiguration> extends MmBaseImplementation<DECLARATION, CONFIGURATION>
  implements MmContainerMimic<MODEL> {

  /** Constant for index of generic type for model. */
  public static final int     GENERIC_PARAMETER_INDEX_MODEL         = 2;

  /** Constant for index of generic type for configuration. */
  public static final int     GENERIC_PARAMETER_INDEX_CONFIGURATION = 3;

  /** The logger of this class. */
  private static final Logger LOGGER                                = LogManager.getLogger(MmBaseContainerImplementation.class);

  /**
   * MmContainerErrorState is an enumeration of values regarding an container's error state during conversion and validation.
   *
   * @author  Olaf Kossak
   */
  protected enum MmContainerErrorState {

    /** No error occurred during last conversion or validation. */
    SUCCESS,

    /** Error occurred during semantic validation of model. */
    ERROR_IN_SEMANTIC_VALIDATION;
  }

  /** The error state of this container. */
  protected MmContainerErrorState errorstate;

  /** The list of messages. */
  protected final MmMessageList   messageList;

  /** The model of this mimic. */
  protected MODEL                 model;

  /**
   * Creates a new MmBaseContainerImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseContainerImplementation(MmDeclarationMimic pParent) {
    super(pParent);
    this.messageList = new MmMessageList();
    this.errorstate  = MmContainerErrorState.SUCCESS;
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
   * @jalopy.group  group-do
   */
  @Override public void doMmReset() {
    this.ensureInitialization();

    this.clearMessageListRecursively(this);
    this.doPassResetToModelsideRecursively(this);
    this.doPassModelsideToViewsideRecursively(this);
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
   * @jalopy.group  group-do
   */
  @Override public void doMmSetDefaults() {
    this.ensureInitialization();

    this.clearMessageListRecursively(this);
    this.doPassDefaultToModelsideRecursively(this);
    this.doPassModelsideToViewsideRecursively(this);
  }

  /**
   * Sets the values from model into modelside of mimic.
   *
   * @jalopy.group  group-do
   */
  @Override public void doMmSetModelFromModelside() {
    this.ensureInitialization();

    this.clearMessageListRecursively(this);
    this.declaration.callbackMmSetModelFromModelside(this.model);

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(this)) {

      // if child is attribute, invoke attribute child
      if (MmBaseContainerImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseContainerImplementation<?, ?, ?> containerChild = (MmBaseContainerImplementation<?, ?, ?>)child;

        // ask each container child, but do NOT iterate over child's children
        containerChild.doMmSetModelFromModelside();
      }
    }
  }

  /**
   * Sets the values from model to modelside of mimic.
   *
   * @param         pModel  The model to set.
   *
   * @jalopy.group  group-do
   */
  @Override public void doMmSetModelsideFromModel(MODEL pModel) {
    this.ensureInitialization();

    this.clearMessageListRecursively(this);

    // store modelside reference to model
    this.model = pModel;

    // invoke callback method to pass values from model to modelside value
    this.declaration.callbackMmSetModelsideFromModel(this.model);

    this.doPassModelsideToViewside(this);
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>validating viewside value syntactically and converting to modelside type</li>
   *   <li>passing converted viewside value into modelside value</li>
   *   <li>validating modelside value semantically</li>
   * </ol>
   *
   * @throws        MmValidatorException  in case of semantic validation of container or one of its children failed.
   *
   * @jalopy.group  group-do
   */
  @Override public void doMmValidate() throws MmValidatorException {
    this.ensureInitialization();

    this.clearMessageListRecursively(this);
    this.doPassViewsideToModelsideRecursively(this);
    this.doValidateModelsideRecursively(this);
    logSubtree(this, "");
    if (!this.isMmValid()) {
      throw new MmValidatorException(this);
    }
  }

  /**
   * Passes default values to mimic's modelside for all children of specified mimic.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-do
   */
  protected void doPassDefaultToModelsideRecursively(MmBaseImplementation<?, ?> pMm) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("doPassDefaultToModelsideRecursively()");
    }

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // ask each attribute child, but do NOT iterate over attribute's children
        attributeChild.doPassDefaultToModelsideValue();

      } else {

        // in any other case iterate recursively over child's children
        this.doPassDefaultToModelsideRecursively(child);
      }
    }
  }

  /**
   * Passes values from mimic's modelside to mimic's viewside just for attribute children of specified mimic, but NOT recursively for all
   * children.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-do
   */
  protected void doPassModelsideToViewside(MmBaseImplementation<?, ?> pMm) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("doPassModelsideToViewside()");
    }

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // ask each attribute child, but do NOT iterate over attribute's children
        attributeChild.doPassModelsideToViewsideValue();
      }
    }
  }

  /**
   * Passes values from mimic's modelside to mimic's viewside for all children of specified mimic.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-do
   */
  protected void doPassModelsideToViewsideRecursively(MmBaseImplementation<?, ?> pMm) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("doPassModelsideToViewsideRecursively()");
    }

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // ask each attribute child, but do NOT iterate over attribute's children
        attributeChild.doPassModelsideToViewsideValue();

      } else {

        // in any other case iterate recursively over child's children
        this.doPassModelsideToViewsideRecursively(child);
      }
    }
  }

  /**
   * Passes reset values to mimic's modelside for all children of specified mimic.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-do
   */
  protected void doPassResetToModelsideRecursively(MmBaseImplementation<?, ?> pMm) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("doPassResetToModelsideRecursively()");
    }

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // ask each attribute child, but do NOT iterate over attribute's children
        attributeChild.doPassResetToModelsideValue();

      } else {

        // in any other case iterate recursively over child's children
        this.doPassResetToModelsideRecursively(child);
      }
    }
  }

  /**
   * Passes values from mimic's viewside to mimic's modelside for all children of specified mimic.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-do
   */
  protected void doPassViewsideToModelsideRecursively(MmBaseImplementation<?, ?> pMm) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("doPassViewsideToModelsideRecursively()");
    }

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // ask each attribute child, but do NOT iterate over attribute's children
        attributeChild.doPassViewsideToModelsideValue();

      } else {

        // in any other case iterate recursively over child's children
        this.doPassViewsideToModelsideRecursively(child);
      }
    }
  }

  /**
   * Validates at first all children of specified mimic, and at second the mimic itself. In detail {@code doValidateModelsideRecursively}
   * iterates over all children, if child is of type {@code MmBaseAttributeImplementation} it invokes {@code doValidateModelsideValue},
   * which invokes {@code callbackMmValidateModelsideValue} on each attribute, if child is not an attribute it invokes recursively
   * {@code doValidateModelsideRecursively}. After validation of all children it validates the mimic itself by calling
   * {@code callbackMmValidateModel} on container.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-do
   */
  protected void doValidateModelsideRecursively(MmBaseImplementation<?, ?> pMm) {
    if (LOGGER.isTraceEnabled()) {
      LOGGER.trace("doValidateModelsideRecursively()");
    }

    this.errorstate = MmContainerErrorState.SUCCESS;

    // iterate over container's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // ask each attribute child, whether conversion succeeded and validation is possible
        if (attributeChild.isDoValidateModelsideValueEnabled()) {

          // validate attribute child, but do NOT iterate over attribute's children
          attributeChild.doValidateModelsideValue();
        }

      } else {

        // in any other case iterate recursively over child's children
        this.doValidateModelsideRecursively(child);
      }
    }

    // if validation of children succeeded, validate container
    if (this.isMmValid()) {
      try {

        // invoke callback method for semantic validation on mimic's declaration part
        this.declaration.callbackMmValidateModel(this.model);

      } catch (MmValidatorException validatorException) {
        this.errorstate = MmContainerErrorState.ERROR_IN_SEMANTIC_VALIDATION;

        MmMessage message = new MmMessage(validatorException);
        this.getMmMessageList().addMessage(message);
      }
    }
  }

  /**
   * Returns message list of this container.
   *
   * @return        The message list of this container.
   *
   * @jalopy.group  group-get
   */
  public MmMessageList getMmMessageList() {
    this.ensureInitialization();

    return this.messageList;
  }

  /**
   * Returns the model.
   *
   * @return        The model.
   *
   * @jalopy.group  group-get
   */
  @Override public MODEL getMmModel() {
    this.ensureInitialization();

    return this.model;
  }

  /**
   * Returns the Java type of the model.
   *
   * @return        The Java type of the model.
   *
   * @jalopy.group  group-get
   */
  @Override public Class<MODEL> getMmModelType() {
    this.ensureInitialization();

    return findGenericsParameterType(this.getClass(), MmBaseContainerImplementation.class, GENERIC_PARAMETER_INDEX_MODEL);
  }

  /**
   * Sets the model.
   *
   * @param         pModel  The model to set.
   *
   * @jalopy.group  group-get
   */
  @Override public void setMmModel(MODEL pModel) {
    this.ensureInitialization();

    this.model = pModel;
  }

  /**
   * Clears message lists of specified mimic and all descendants of type attribute or container.
   *
   * @param         pMm  The specified mimic.
   *
   * @jalopy.group  group-clear
   */
  protected void clearMessageListRecursively(MmBaseImplementation<?, ?> pMm) {
    // clear container's message list
    if (MmBaseContainerImplementation.class.isAssignableFrom(pMm.getClass())) {
      MmBaseContainerImplementation<?, ?, ?> container = (MmBaseContainerImplementation<?, ?, ?>)pMm;
      container.messageList.clear();
    }

    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is attribute, invoke attribute child
      if (MmBaseAttributeImplementation.class.isAssignableFrom(child.getClass())) {
        MmBaseAttributeImplementation<?, ?, ?, ?> attributeChild = (MmBaseAttributeImplementation<?, ?, ?, ?>)child;

        // clear attribute's message list
        attributeChild.clearMmMessageList();

      } else {

        // in any other case iterate recursively over child's children
        this.clearMessageListRecursively(child);
      }
    }
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return        <code>True</code>, if mimic has been changed from viewside.
   *
   * @jalopy.group  group-changed-front
   */
  @Override public boolean isMmChangedFromViewside() {
    this.ensureInitialization();

    return isChangedFromViewsideRecursively(this);
  }

  /**
   * Returns true, if any child of specified mimic is changed from viewside.
   *
   * @param         pMm  The specified mimic.
   *
   * @return        True, if any child of specified mimic is changed from viewside.
   *
   * @jalopy.group  group-changed-front
   */
  protected boolean isChangedFromViewsideRecursively(MmBaseImplementation<?, ?> pMm) {
    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is editable, ask child
      if (MmEditableMimic.class.isAssignableFrom(child.getClass())) {
        MmEditableMimic editableChild = (MmEditableMimic)child;

        // if child is editable attribute, this will NOT iterate over child's children
        // if child is editable container, container is responsible for its children
        if (editableChild.isMmChangedFromViewside()) {
          return true;
        }

      } else {

        // in any other case iterate recursively over child's children
        if (isChangedFromViewsideRecursively(child)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return        <code>True</code> if a value from view has to be set.
   *
   * @jalopy.group  group-required
   */
  @Override public boolean isMmRequired() {
    this.ensureInitialization();

    return isRequiredRecursively(this);
  }

  /**
   * Returns true, if any child of specified mimic is required.
   *
   * @param         pMm  The specified mimic.
   *
   * @return        True, if any child of specified mimic is required.
   *
   * @jalopy.group  group-required
   */
  protected boolean isRequiredRecursively(MmBaseImplementation<?, ?> pMm) {
    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is editable, ask child
      if (MmEditableMimic.class.isAssignableFrom(child.getClass())) {
        MmEditableMimic editableChild = (MmEditableMimic)child;

        // if child is editable attribute, this will NOT iterate over child's children
        // if child is editable container, container is responsible for its children
        if (!editableChild.isMmRequired()) {
          return false;
        }

      } else {

        // in any other case iterate recursively over child's children
        if (!isRequiredRecursively(child)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.doMmReset} is executable.
   *
   * @return        <code>true</code> if the action {@link MmEditableMimic.doMmReset} is executable.
   *
   * @jalopy.group  group-is-reset
   */
  @Override public boolean isMmResetEnabled() {
    this.ensureInitialization();

    return isResetEnabledRecursively(this);
  }

  /**
   * Returns true, if for all children of specified mimic reset is enabled.
   *
   * @param         pMm  The specified mimic.
   *
   * @return        True, if for all children of specified mimic reset is enabled.
   *
   * @jalopy.group  group-is-reset
   */
  protected boolean isResetEnabledRecursively(MmBaseImplementation<?, ?> pMm) {
    // iterate over mimic's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is editable, ask child
      if (MmEditableMimic.class.isAssignableFrom(child.getClass())) {
        MmEditableMimic editableChild = (MmEditableMimic)child;

        // if child is editable attribute, this will NOT iterate over child's children
        // if child is editable container, container is responsible for its children
        if (!editableChild.isMmResetEnabled()) {
          return false;
        }

      } else {

        // in any other case iterate recursively over child's children
        if (!isResetEnabledRecursively(child)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return        <code>True</code> if the mimic has been validated without any errors.
   *
   * @jalopy.group  group-valid
   */
  @Override public boolean isMmValid() {
    this.ensureInitialization();

    return isValidRecursively(this);
  }

  /**
   * Returns true, if all children of specified mimic are validated successfully.
   *
   * @param         pMm  The specified mimic.
   *
   * @return        True, if all children of specified mimic are validated successfully.
   *
   * @jalopy.group  group-valid
   */
  protected boolean isValidRecursively(MmBaseImplementation<?, ?> pMm) {
    // if this container is invalid, return immediately
    if (this.errorstate != MmContainerErrorState.SUCCESS) {
      return false;
    }

    // otherwise iterate over container's children
    for (MmBaseImplementation<?, ?> child : getImplementationChildrenOf(pMm)) {

      // if child is editable, ask child
      if (MmEditableMimic.class.isAssignableFrom(child.getClass())) {
        MmEditableMimic editableChild = (MmEditableMimic)child;

        // if child is editable attribute, this will NOT iterate over child's children
        // if child is editable container, container is responsible for its children
        if (!editableChild.isMmValid()) {
          return false;
        }

      } else {

        // in any other case iterate recursively over child's children
        if (!isValidRecursively(child)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns state information about this mimic.
   *
   * @return  State information about this mimic.
   */
  protected String toStringState() {
    StringBuilder sb = new StringBuilder();
    switch (this.errorstate) {
      case SUCCESS: {
        sb.append("        ");
        break;
      }
      case ERROR_IN_SEMANTIC_VALIDATION: {
        sb.append(" INVALID");
        break;
      }
    }
    return sb.toString();
  }

}
