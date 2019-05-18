package org.minutenwerk.mimic4j.api;

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
  public Class<MODEL> getMmModelType();

  /**
   * Sets the model.
   *
   * @param  pModel  The model to set.
   */
  public void setMmModel(MODEL pModel);
}
