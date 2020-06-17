package org.minutenwerk.mimic4j.impl.container;

import java.lang.annotation.Annotation;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelChangeListener;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.message.MmMessage;
import org.minutenwerk.mimic4j.api.message.MmMessageList;
import org.minutenwerk.mimic4j.api.mimic.MmContainerMimic;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmEditableMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmEditableMimicImpl;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;

/**
 * MmBaseContainerImplementation is the abstract base class for the implementation part of all container mimic classes.
 *
 * @param               <DECLARATION>    Type of configuration, holding state of mimic configuration.
 * @param               <MODEL>          Type of the model, containing business data.
 * @param               <CONFIGURATION>  Type of configuration base class for containers.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-lifecycle, group-do, group-get, group-clear, group-changed-front, group-required, group-valid
 */
public abstract class MmBaseContainerImplementation<DECLARATION extends MmBaseContainerDeclaration<?, MODEL>,
  MODEL, CONFIGURATION extends MmBaseConfiguration, ANNOTATION extends Annotation> extends MmBaseImplementation<DECLARATION, CONFIGURATION, ANNOTATION>
  implements MmContainerMimic<MODEL>, MmEditableMimicImpl, MmModelChangeListener {

  /** Constant for index of generic type for model. */
  private static final int GENERIC_PARAMETER_INDEX_MODEL = 2;

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
  protected MmContainerErrorState     errorstate;

  /** The list of messages. */
  protected final MmMessageList       messageList;

  /** This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root accessor. */
  protected final MmRootAccessor<?>   rootAccessor;

  /** This component has a model of type MODEL. The model has a model accessor. Its first generic, the type of the parent model, is undefined. */
  protected MmModelAccessor<?, MODEL> modelAccessor;

  /**
   * Creates a new MmBaseContainerImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmBaseContainerImplementation(final MmDeclarationMimic pParent) {
    this(pParent, NULL_RUNTIME_INDEX);
  }

  /**
   * Creates a new MmBaseContainerImplementation instance at runtime.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRuntimeIndex  The index of this mimic when created at runtime.
   */
  public MmBaseContainerImplementation(final MmDeclarationMimic pParent, final Integer pRuntimeIndex) {
    super(pParent, NULL_NAME, pRuntimeIndex);
    messageList  = new MmMessageList();
    errorstate   = MmContainerErrorState.SUCCESS;
    rootAccessor = onConstructRootAccessor();
  }

  /**
   * Creates a new MmBaseContainerImplementation instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pName          Specified name of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root
   *                        accessor.
   */
  @SuppressWarnings("unchecked")
  public MmBaseContainerImplementation(final MmDeclarationMimic pParent, final String pName, final MmRootAccessor<MODEL> pRootAccessor) {
    super(pParent, pName, NULL_RUNTIME_INDEX);
    messageList   = new MmMessageList();
    errorstate    = MmContainerErrorState.SUCCESS;
    rootAccessor  = pRootAccessor;
    modelAccessor = (MmModelAccessor<?, MODEL>)rootAccessor;
  }

  /**
   * Returns accessor of model.
   *
   * @return        The accessor of model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, MODEL> getMmModelAccessor() {
    ensureInitialization();

    return modelAccessor;
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
    ensureInitialization();

    return rootAccessor;
  }

  /**
   * Returns view model value.
   *
   * @return        The view model value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MODEL getMmViewModel() {
    ensureInitialization();

    return getMmModel();
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
    ensureInitialization();

    return modelAccessor.isPresent();
  }

  /**
   * Returns data model for self reference. The data model delivers parameters of the target URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return        The data model for self reference.
   *
   * @jalopy.group  group-override
   */
  @Override
  protected MmReferenceableModel getMmReferenceableModel() {
    final MODEL dataModel = getMmModel();
    return ((dataModel != null) && (dataModel instanceof MmReferenceableModel)) ? (MmReferenceableModel)dataModel : null;
  }

  /**
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call super.onInitialization()!
   *
   * @throws        IllegalStateException  In case of root accessor or model accessor is not defined.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void onInitialization() {
    super.onInitialization();

    if (modelAccessor == null) {

      // initialize parentAccessor
      final MmModelAccessor<?, ?> parentAccessor = onInitializeParentAccessor();

      // initialize modelAccessor
      modelAccessor = declaration.callbackMmGetModelAccessor(parentAccessor);
      if (modelAccessor == null) {
        throw new IllegalStateException("no definition of callbackMmGetModelAccessor() for " + parentPath + "." + name);
      }
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
    for (MmBaseContainerImplementation<?, ?, ?, ?> containerAncestor = getMmImplementationAncestorOfType(MmBaseContainerImplementation.class);
        containerAncestor != null; containerAncestor = containerAncestor.getMmImplementationAncestorOfType(MmBaseContainerImplementation.class)) {
      if (containerAncestor.rootAccessor != null) {
        return containerAncestor.rootAccessor;
      }
    }
    throw new IllegalStateException("no definition of rootAccessor for " + parentPath + "." + name);
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
    MmBaseContainerImplementation<?, ?, ?, ?> parentContainer = getMmImplementationAncestorOfType(MmBaseContainerImplementation.class);
    if (parentContainer == null) {
      throw new IllegalStateException("no ancestor of type MmContainerMimic for " + parentPath + "." + name);
    } else {
      MmModelAccessor<?, ?> parentContainerAccessor = parentContainer.onInitializeGetMmModelAccessor();
      if (parentContainerAccessor == null) {
        throw new IllegalStateException("no definition of parentAccessor for " + parentPath + "." + name);
      }
      return parentContainerAccessor;
    }
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>converting view model value to data model type</li>
   *   <li>passing converted value into data model value</li>
   *   <li>validating data model value</li>
   * </ol>
   *
   * @throws        MmValidatorException  in case of semantic validation of container or one of its children failed.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void doMmValidate() throws MmValidatorException {
    ensureInitialization();

    clearMmMessageList();
    passViewModelToDataModel();
    validateDataModel();
    logSubtree(this, "");
    if (!isMmValid()) {
      throw new MmValidatorException(this);
    }
  }

  /**
   * Event of model change.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void onModelChange() {
    ensureInitialization();

    passDataModelToViewModel();
  }

  /**
   * Converts and formats data from data model to view model type and transfers converted data into view model.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passDataModelToViewModel() {
    for (MmEditableMimicImpl child : getDirectImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.passDataModelToViewModel();
    }
  }

  /**
   * Validates and converts data from view model to data model type and transfers converted data into data model.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passViewModelToDataModel() {
    for (MmEditableMimicImpl child : getDirectImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.passViewModelToDataModel();
    }
  }

  /**
   * Semantic validation of data model value.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void validateDataModel() {
    // validate this container
    try {

      // invoke callback method for semantic validation on mimic's declaration part
      declaration.callbackMmValidateModel(modelAccessor.get());
      errorstate = MmContainerErrorState.SUCCESS;

    } catch (MmValidatorException validatorException) {
      errorstate = MmContainerErrorState.ERROR_IN_SEMANTIC_VALIDATION;

      MmMessage message = new MmMessage(validatorException);
      messageList.addMessage(message);
    }

    // validate all children of type MmEditableMimicImpl
    for (MmEditableMimicImpl child : getDirectImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.validateDataModel();
    }
  }

  /**
   * Returns message list of this container and all descendants of type attribute or container.
   *
   * @return        The message list of this container and all descendants of type attribute or container.
   *
   * @jalopy.group  group-get
   */
  @Override
  public MmMessageList getMmMessageList() {
    ensureInitialization();

    MmMessageList returnMessageList = new MmMessageList();
    returnMessageList.addMessages(messageList);

    for (MmEditableMimicImpl child : getDirectImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      returnMessageList.addMessages(child.getMmMessageList());
    }
    return returnMessageList;
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
    ensureInitialization();

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
    ensureInitialization();

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
    for (MmEditableMimicImpl child : getDirectImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.clearMmMessageList();
    }
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from view model. If a mimic is changed, all ancestors of type MmEditableMimic are marked as
   * being changed as well.
   *
   * @return        <code>True</code>, if mimic has been changed from view model.
   *
   * @jalopy.group  group-changed-front
   */
  @Override
  public boolean isMmChangedFromView() {
    ensureInitialization();

    // iterate over children of type MmEditableMimic
    for (MmEditableMimic child : getDirectImplementationChildrenOfType(MmEditableMimic.class)) {
      if (child.isMmChangedFromView()) {
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
    ensureInitialization();

    // iterate over children of type MmEditableMimic
    for (MmEditableMimic child : getDirectImplementationChildrenOfType(MmEditableMimic.class)) {
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
    ensureInitialization();

    // if this container is invalid, return immediately
    if (errorstate != MmContainerErrorState.SUCCESS) {
      return false;
    }

    // iterate over children of type MmEditableMimic
    for (MmEditableMimic child : getDirectImplementationChildrenOfType(MmEditableMimic.class)) {
      if (!child.isMmValid()) {
        return false;
      }
    }
    return true;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public MmModelAccessor<?, MODEL> onInitializeGetMmModelAccessor() {
    return modelAccessor;
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
