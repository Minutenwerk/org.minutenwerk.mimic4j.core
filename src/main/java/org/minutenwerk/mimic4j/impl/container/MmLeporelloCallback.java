package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;

/**
 * MmLeporelloCallback defines a set of override-able methods for a leporello mimic. Callback methods are part of the declaration API of
 * mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
// TODO SUB_MODEL ist wahrscheinlich nicht mehr notwendig
public interface MmLeporelloCallback<MODEL, SUB_MODEL> extends MmContainerCallback<MODEL> {

  /**
   * Returns the currently selected tab of the leporello.
   *
   * @return  The currently selected tab of the leporello.
   */
  public MmLeporelloTab<?, ?> callbackMmGetSelectedTab();

}
