package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;

/**
 * MmLeporelloCallback defines a set of override-able methods for a leporello mimic. Callback methods are part of the declaration API of
 * mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmLeporelloCallback<MODEL, SUB_MODEL> extends MmContainerCallback<MODEL> {

  /**
   * Returns the currently selected tab of the leporello.
   *
   * @return  The currently selected tab of the leporello.
   *
   * @since   $maven.project.version$
   */
  public MmLeporelloTab callbackMmGetSelectedTab();

  /**
   * Sets values from model into modelside of mimic.
   *
   * @param  pModel     The model containing the values to be set, cannot be null.
   * @param  pSubModel  The sub model containing the values to be set, can be null.
   *
   * @since  $maven.project.version$
   */
  public void callbackMmSetModelsideFromModel(MODEL pModel, SUB_MODEL pSubModel);

}
