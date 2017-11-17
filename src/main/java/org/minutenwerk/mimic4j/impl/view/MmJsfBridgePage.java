package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.referencable.MmImplementationPage;

/**
 * MmJsfBridgePage connects a tab mimic and a JSF tab view component.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmJsfBridgePage<MODEL extends MmReferencableModel> extends MmJsfBridge<MmImplementationPage<MODEL>, String, MODEL> {

  /**
   * Creates a new MmJsfBridgePage instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgePage(MmImplementationPage<MODEL> pImplementation) {
    super(pImplementation);
  }

}
