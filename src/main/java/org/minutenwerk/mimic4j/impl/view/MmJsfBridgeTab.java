package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.container.MmImplementationTab;

/**
 * MmJsfBridgeTab connects a tab mimic and a JSF tab view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeTab<MODEL> extends MmJsfBridge<MmImplementationTab<MODEL>, String, MODEL> {

  /**
   * Creates a new MmJsfBridgeTab instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeTab(MmImplementationTab<MODEL> pImplementation) {
    super(pImplementation);
  }

}
