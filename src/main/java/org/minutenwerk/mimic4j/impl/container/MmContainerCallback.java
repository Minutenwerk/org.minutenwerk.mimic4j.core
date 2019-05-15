package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmModelAccessor;

/**
 * MmContainerCallback defines a set of override-able methods common to all container mimics. Callback methods are part of the declaration
 * API of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration
 * part.
 *
 * @author  Olaf Kossak
 */
public interface MmContainerCallback<MODEL> extends MmBaseCallback {

  /**
   * Returns the container's accessor to corresponding model. The container accessor can be derived from specified root component accessor.
   *
   * @param   pRootAccessor  The specified root component accessor.
   *
   * @return  The container's accessor.
   */
  public MmComponentAccessor<?, MODEL> callbackMmGetAccessor(MmModelAccessor<?, ?> pRootAccessor);

  /**
   * Semantic validation of model.
   *
   * @param   pModel  The model to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  public void callbackMmValidateModel(MODEL pModel) throws MmValidatorException;

}
