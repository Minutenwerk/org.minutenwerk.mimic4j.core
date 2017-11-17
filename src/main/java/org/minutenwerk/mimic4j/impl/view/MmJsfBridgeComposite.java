package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.composite.MmBaseCompositeImplementation;

/**
 * MmJsfBridgeComposite connects a composite mimic and a JSF composite view component.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmJsfBridgeComposite extends MmJsfBridge<MmBaseCompositeImplementation<?, ?>, String, String> {

  /**
   * Creates a new MmJsfBridgeComposite instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeComposite(MmBaseCompositeImplementation<?, ?> pImplementation) {
    super(pImplementation);
  }

}
