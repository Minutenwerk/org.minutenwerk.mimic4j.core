package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;

/**
 * MmContainerMimic is the basic interface of all mimic types containing other mimics of type MmEditableMimic.
 *
 * @param   <MODEL>  The type of the model, containing business data.
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
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public MmComponentAccessor<?, MODEL> getMmModelAccessor();

  /**
   * Returns the Java type of the model.
   *
   * @return  The Java type of the model.
   */
  public Class<MODEL> getMmModelType();

}
