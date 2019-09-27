package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmContainerCallback defines a set of override-able methods common to all container mimics. Callback methods are part of the declaration
 * API of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration
 * part.
 *
 * @author  Olaf Kossak
 */
public interface MmContainerCallback<MODEL> extends MmBaseCallback {

  /**
   * Returns the container's accessor to corresponding model, derived from specified parent accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The container's accessor.
   */
  public MmModelAccessor<?, MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor);

  /**
   * Semantic validation of model.
   *
   * @param   pModel  The model to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  public void callbackMmValidateModel(MODEL pModel) throws MmValidatorException;

}
