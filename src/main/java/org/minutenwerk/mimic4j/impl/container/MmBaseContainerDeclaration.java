package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelChangeListener;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.message.MmMessage;
import org.minutenwerk.mimic4j.api.message.MmMessageList;
import org.minutenwerk.mimic4j.api.mimic.MmContainerMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseContainerDeclaration is the abstract base class for container declaration classes, where a container contains editable mimics like attributes or
 * other containers.
 *
 * @param   <MODEL>           The type of the model, containing business data.
 * @param   <IMPLEMENTATION>  The type of the corresponding class implementing this declaration api.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseContainerDeclaration<IMPLEMENTATION extends MmBaseContainerImplementation<?, MODEL, ?, ?>, MODEL>
  extends MmBaseDeclaration<MmContainerMimic<MODEL>, IMPLEMENTATION> implements MmContainerMimic<MODEL>, MmContainerCallback<MODEL>, MmModelChangeListener {

  /**
   * Creates a new MmBaseContainerDeclaration instance.
   *
   * @param  pImplementation  The implementation part of this mimic.
   */
  protected MmBaseContainerDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Adds a message of type {@link MmMessage} to this root.
   *
   * @param  pMessage  The specified message to add.
   */
  public void addMmMessage(MmMessage pMessage) {
    implementation.getMmMessageList().addMessage(pMessage);
  }

  /**
   * Returns the container's accessor to corresponding model, derived from specified parent accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The container's accessor.
   *
   * @throws  ClassCastException  IllegalStateException In case of model accessor is not defined.
   */
  @Override
  public MmModelAccessor<?, MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      @SuppressWarnings("unchecked")
      MmModelAccessor<?, MODEL> modelAccessor = (MmModelAccessor<?, MODEL>)pParentAccessor;
      return modelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to containerAccessor. You must redefine callbackMmGetModelAccessor() for " + this);
    }
  }

  /**
   * Semantic validation of model.
   *
   * @param   pModel  The model to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateModel(MODEL pModel) throws MmValidatorException {
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
   * @throws  MmValidatorException  in case of semantic validation of container or one of its children failed.
   */
  @Override
  public final void doMmValidate() throws MmValidatorException {
    implementation.doMmValidate();
  }

  /**
   * Returns message list of this container and all descendants of type attribute or container.
   *
   * @return  The message list of this container and all descendants of type attribute or container.
   */
  @Override
  public final MmMessageList getMmMessageList() {
    return implementation.getMmMessageList();
  }

  /**
   * Returns the model.
   *
   * @return  The model.
   */
  @Override
  public final MODEL getMmModel() {
    return implementation.getMmModel();
  }

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public MmModelAccessor<?, MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns the Java type of the model.
   *
   * @return  The Java type of the model.
   */
  @Override
  public final Class<MODEL> getMmModelType() {
    return implementation.getMmModelType();
  }

  /**
   * Returns accessor of root component of model.
   *
   * @return  The accessor of root component of model.
   */
  @Override
  public final MmRootAccessor<?> getMmRootAccessor() {
    return implementation.getMmRootAccessor();
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from view model. If a mimic is changed, all ancestors of type MmEditableMimic are marked as
   * being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from view model.
   */
  @Override
  public final boolean isMmChangedFromView() {
    return implementation.isMmChangedFromView();
  }

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return  <code>True</code> if a value from view has to be set.
   */
  @Override
  public final boolean isMmRequired() {
    return implementation.isMmRequired();
  }

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return  <code>True</code> if the mimic has been validated without any errors.
   */
  @Override
  public final boolean isMmValid() {
    return implementation.isMmValid();
  }

  /**
   * Event of model change.
   */
  @Override
  public void onModelChange() {
    implementation.onModelChange();
  }
}
