package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmContainerCallback defines a set of override-able methods common to all container mimics. Callback methods are part of the declaration
 * API of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration
 * part.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmContainerCallback<MODEL> extends MmBaseCallback {

  /**
   * Sets values from modelside of mimic into model.
   *
   * @param  pModel  The model to set values into.
   *
   * @since  $maven.project.version$
   */
  public void callbackMmSetModelFromModelside(MODEL pModel);

  /**
   * Sets values from model into modelside of mimic.
   *
   * @param  pModel  The model containing the values to be set.
   *
   * @since  $maven.project.version$
   */
  public void callbackMmSetModelsideFromModel(MODEL pModel);

  /**
   * Semantic validation of model.
   *
   * @param   pModel  The model to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   *
   * @since   $maven.project.version$
   */
  public void callbackMmValidateModel(MODEL pModel) throws MmValidatorException;

}
