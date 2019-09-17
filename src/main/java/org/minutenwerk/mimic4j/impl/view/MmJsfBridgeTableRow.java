package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.container.MmImplementationTableRow;

/**
 * MmJsfBridgeTableRow connects a table Row mimic and a JSF table row view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeTableRow extends MmJsfBridge<MmImplementationTableRow<?>, String, String> {

  /**
   * Creates a new MmJsfBridgeTableRow instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeTableRow(MmImplementationTableRow<?> pImplementation) {
    super(pImplementation);
  }
}
