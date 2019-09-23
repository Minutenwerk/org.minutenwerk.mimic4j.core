package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporelloPanel;

/**
 * MmJsfBridgeLeporello connects a leporello panel mimic and a JSF tabset view component. Corresponding tag is leporelloPanel.xhtml.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeLeporelloPanel extends MmJsfBridge<MmImplementationLeporelloPanel<?>, String, String> {

  /**
   * Creates a new MmJsfBridgeLeporelloPanel instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeLeporelloPanel(MmImplementationLeporelloPanel<?> pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns CSS selector for data parents of leporello panel, like in (data-toggle="collapse" data-target="#target1,#target2,#target3").
   *
   * @return  The CSS selector for data parents of leporello panel.
   */
  @Override
  public String getDataParents() {
    return implementation.getMmDataParents();
  }

  /**
   * Returns the leporello id, which is used as data parent.
   *
   * @return  The leporello id.
   */
  @Override
  public String getLeporelloId() {
    return implementation.getMmParent().getMmId();
  }

  /**
   * Returns the CSS style class for initial opening of leporello panel.
   *
   * @return  The CSS style class for initial opening of leporello panel.
   */
  @Override
  public String getStyleInitiallyOpen() {
    return implementation.getMmStyleInitiallyOpen();
  }

  /**
   * Returns the value of this leporello panel.
   *
   * @return  The value of this leporello panel.
   */
  @Override
  public String getValue() {
    return implementation.getMmViewModelValue();
  }

}
