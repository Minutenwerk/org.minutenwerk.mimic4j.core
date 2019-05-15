package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;

/**
 * MmContainerMimic is the basic interface of all mimic types containing other mimics of type MmEditableMimic.
 *
 * @author  Olaf Kossak
 */
public interface MmContainerMimic<MODEL> extends MmEditableMimic {

  /**
   * Returns the model.
   *
   * @return  The model.
   */
  public MODEL getMmModel();

  /**
   * Returns the Java type of the model.
   *
   * @return  The Java type of the model.
   */
  public Class<?> getMmModelType();

  /**
   * Returns accessor of root component of model.
   *
   * @return  The accessor of root component of model.
   */
  public MmComponentAccessor<?, ?> getMmRootAccessor();

  /**
   * Sets the model.
   *
   * @param  pModel  The model to set.
   */
  public void setMmModel(MODEL pModel);

}
