package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;

/**
 * MmJsfBridgeLeporello connects a leporello mimic and a JSF div and bootstrap leporello view component. Corresponding tag is
 * leporello.xhtml.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeLeporello extends MmJsfBridge<MmImplementationLeporello<?, ?>, String, String> {

  /**
   * Creates a new MmJsfBridgeLeporello instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeLeporello(MmImplementationLeporello<?, ?> pImplementation) {
    super(pImplementation);
  }

}
