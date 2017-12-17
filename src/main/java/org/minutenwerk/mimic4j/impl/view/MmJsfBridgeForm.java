package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.container.MmImplementationForm;

/**
 * MmJsfBridgeForm connects a form mimic and a JSF form view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeForm<MODEL> extends MmJsfBridge<MmImplementationForm<MODEL>, String, MODEL> {

  /**
   * Creates a new MmJsfBridgeForm instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeForm(MmImplementationForm<MODEL> pImplementation) {
    super(pImplementation);
  }

}
