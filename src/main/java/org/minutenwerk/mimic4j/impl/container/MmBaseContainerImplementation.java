package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmContainerMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmEditableMimic;
import org.minutenwerk.mimic4j.api.MmEditableMimicImpl;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.message.MmMessage;
import org.minutenwerk.mimic4j.impl.message.MmMessageList;

/**
 * MmBaseContainerImplementation is the abstract base class for the implementation part of all container mimic classes.
 *
 * @param               <DECLARATION>    Type of configuration, holding state of mimic configuration.
 * @param               <MODEL>          Type of the model, containing business data.
 * @param               <CONFIGURATION>  Type of configuration base class for containers.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-lifecycle, group-do, group-get, group-clear, group-changed-front, group-required,
 *                      group-valid
 */
public abstract class MmBaseContainerImplementation<DECLARATION extends MmBaseContainerDeclaration<MODEL, ?>,
  MODEL, CONFIGURATION extends MmBaseConfiguration> extends MmBaseImplementation<DECLARATION, CONFIGURATION>
  implements MmContainerMimic<MODEL>, MmEditableMimicImpl {

  /** Constant for index of generic type for model. */
  public static final int GENERIC_PARAMETER_INDEX_MODEL         = 2;

  /** Constant for index of generic type for configuration. */
  public static final int GENERIC_PARAMETER_INDEX_CONFIGURATION = 3;

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
  protected MmContainerErrorState         errorstate;

  /** The list of messages. */
  protected final MmMessageList           messageList;

  /** This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root accessor. */
  protected MmRootAccessor<?>             rootAccessor;

  /**
   * This component has a model of type MODEL. The model has a model accessor. Its first generic, the type of the parent model, is
   * undefined.
   */
  protected MmComponentAccessor<?, MODEL> modelAccessor;

  /**
   * Creates a new MmBaseContainerImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseContainerImplementation(final MmDeclarationMimic pParent) {
    super(pParent);
    messageList = new MmMessageList();
    errorstate  = MmContainerErrorState.SUCCESS;
  }

  /**
   * Creates a new MmBaseContainerImplementation instance.
   *
   * @param  pParent        The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmBaseContainerImplementation(final MmDeclarationMimic pParent, final MmRootAccessor<MODEL> pRootAccessor) {
    this(pParent);
    rootAccessor = pRootAccessor;
    rootAccessor.setMmContainer(this);
  }

  /**
   * Returns accessor of model.
   *
   * @return        The accessor of model.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  public MmComponentAccessor<?, MODEL> getMmModelAccessor() {
    assureInitialization();

    return modelAccessor;
  }

  /**
   * Returns accessor of root component of model.
   *
   * @return        The accessor of root component of model.
   *
   * @throws        IllegalStateException  In case of there is no definition of a root accessor.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  public MmRootAccessor<?> getMmRootAccessor() {
    if (rootAccessor == null) {
      for (MmBaseContainerImplementation<?, ?, ?> containerAncestor = getImplementationAncestorOfType(MmBaseContainerImplementation.class); //
          ((rootAccessor == null) && (containerAncestor != null)); //


          containerAncestor = containerAncestor.getImplementationAncestorOfType(MmBaseContainerImplementation.class)) {
        rootAccessor = containerAncestor.rootAccessor;
      }
      if (rootAccessor == null) {
        throw new IllegalStateException("no definition of rootAccessor for " + getMmFullName());
      }
    }
    return rootAccessor;
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
  @SuppressWarnings("unchecked")
  protected void initialize() {
    super.initialize();

    // initialize rootAccessor
    if (rootAccessor != null) {

      // this container is the root
      modelAccessor = (MmComponentAccessor<?, MODEL>)rootAccessor;

    } else {

      // this container is not the root, so evaluate the root from ancestors
      rootAccessor  = getMmRootAccessor();

      // initialize modelAccessor
      modelAccessor = declaration.callbackMmGetAccessor(rootAccessor);
      if (modelAccessor == null) {
        throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + getMmFullName());
      }
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
   * @throws        MmValidatorException  in case of semantic validation of container or one of its children failed.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void doMmValidate() throws MmValidatorException {
    assureInitialization();

    clearMmMessageList();
    passViewsideToModelside();
    validateModelside();
    logSubtree(this, "");
    if (!isMmValid()) {
      throw new MmValidatorException(this);
    }
  }

  /**
   * TODOC.
   *
   * @jalopy.group  group-lifecycle
   */
  public void onModelChange() {
    assureInitialization();

    passModelsideToViewside();
  }

  /**
   * TODOC.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passModelsideToViewside() {
    for (MmEditableMimicImpl child : getImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.passModelsideToViewside();
    }
  }

  /**
   * TODOC.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passViewsideToModelside() {
    for (MmEditableMimicImpl child : getImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.passViewsideToModelside();
    }
  }

  /**
   * TODOC.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void validateModelside() {
    // validate this container
    try {

      // invoke callback method for semantic validation on mimic's declaration part
      declaration.callbackMmValidateModel(modelAccessor.get());
      errorstate = MmContainerErrorState.SUCCESS;

    } catch (MmValidatorException validatorException) {
      errorstate = MmContainerErrorState.ERROR_IN_SEMANTIC_VALIDATION;

      MmMessage message = new MmMessage(validatorException);
      getMmMessageList().addMessage(message);
    }

    // validate all children of type MmEditableMimicImpl
    for (MmEditableMimicImpl child : getImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.validateModelside();
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
    assureInitialization();

    return messageList;
  }

  /**
   * Returns the model.
   *
   * @return        The model.
   *
   * @jalopy.group  group-get
   */
  @Override
  public MODEL getMmModel() {
    assureInitialization();

    return modelAccessor.get();
  }

  /**
   * Returns the Java type of the model.
   *
   * @return        The Java type of the model.
   *
   * @jalopy.group  group-get
   */
  @Override
  public Class<MODEL> getMmModelType() {
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseContainerImplementation.class, GENERIC_PARAMETER_INDEX_MODEL);
  }

  /**
   * Clears message lists of specified mimic and all descendants of type attribute or container.
   *
   * @jalopy.group  group-clear
   */
  @Override
  public void clearMmMessageList() {
    messageList.clear();
    for (MmEditableMimic child : getImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      ((MmEditableMimicImpl)child).clearMmMessageList();
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
  @Override
  public boolean isMmChangedFromViewside() {
    assureInitialization();

    // iterate over children of type MmEditableMimic
    for (MmEditableMimic child : getImplementationChildrenOfType(MmEditableMimic.class)) {
      if (child.isMmChangedFromViewside()) {
        return true;
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
  @Override
  public boolean isMmRequired() {
    assureInitialization();

    // iterate over children of type MmEditableMimic
    for (MmEditableMimic child : getImplementationChildrenOfType(MmEditableMimic.class)) {
      if (child.isMmRequired()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return        <code>True</code> if the mimic has been validated without any errors.
   *
   * @jalopy.group  group-valid
   */
  @Override
  public boolean isMmValid() {
    assureInitialization();

    // if this container is invalid, return immediately
    if (errorstate != MmContainerErrorState.SUCCESS) {
      return false;
    }

    // iterate over children of type MmEditableMimic
    for (MmEditableMimic child : getImplementationChildrenOfType(MmEditableMimic.class)) {
      if (!child.isMmValid()) {
        return false;
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
    switch (errorstate) {
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
