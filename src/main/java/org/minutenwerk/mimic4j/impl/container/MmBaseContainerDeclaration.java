package org.minutenwerk.mimic4j.impl.container;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmContainerMimic;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.impl.message.MmMessage;

/**
 * MmBaseContainerDeclaration is the abstract base class for container declaration classes, where a container contains editable mimics like
 * attributes or other containers.
 *
 * @param   <MODEL>           The type of the model, containing business data.
 * @param   <IMPLEMENTATION>  The type of the corresponding class implementing this declaration api.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseContainerDeclaration<MODEL, IMPLEMENTATION extends MmBaseContainerImplementation<?, MODEL, ?>>
  extends MmBaseDeclaration<MmContainerMimic<MODEL>, IMPLEMENTATION> implements MmContainerMimic<MODEL>, MmContainerCallback<MODEL> {

  /** The logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmBaseContainerDeclaration.class);

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
   * Returns the container's accessor to corresponding model. The container accessor can be derived from specified root component accessor.
   *
   * @param   pRootAccessor  The specified root component accessor.
   *
   * @return  The container's accessor.
   */
  @Override
  public MmComponentAccessor<?, MODEL> callbackMmGetAccessor(MmComponentAccessor<?, ?> pRootAccessor) {
    LOGGER.warn("no definition of callbackMmGetAccessor() for {}", this::getMmFullName);
    return null;
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
   *   <li>passing viewside value into modelside value</li>
   *   <li>converting viewside value to modelside type</li>
   *   <li>passing converted value into modelside value</li>
   *   <li>validating modelside value</li>
   * </ol>
   *
   * @throws  MmValidatorException  in case of semantic validation of container or one of its children failed.
   */
  @Override
  public final void doMmValidate() throws MmValidatorException {
    implementation.doMmValidate();
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
   * Returns the Java type of the model.
   *
   * @return  The Java type of the model.
   */
  @Override
  public final Class<MODEL> getMmModelType() {
    return implementation.getMmModelType();
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from viewside.
   */
  @Override
  public final boolean isMmChangedFromViewside() {
    return implementation.isMmChangedFromViewside();
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
   * Sets the model.
   *
   * @param  pModel  The model to set.
   */
  @Override
  public final void setMmModel(MODEL pModel) {
    implementation.setMmModel(pModel);
  }

}
