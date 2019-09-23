package org.minutenwerk.mimic4j.impl.view;

import java.net.URI;

import org.minutenwerk.mimic4j.impl.link.MmImplementationLeporelloTab;

/**
 * MmJsfBridgeLeporelloTab connects a leporello panel tab mimic and a JSF tab view component. Corresponding tag is leporelloTab.xhtml.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeLeporelloTab extends MmJsfBridge<MmImplementationLeporelloTab<?, ?>, String, String> {

  /**
   * Creates a new MmJsfBridgeLeporelloTab instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeLeporelloTab(MmImplementationLeporelloTab<?, ?> pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the CSS styleclass if this leporello tab is active.
   *
   * @return  The CSS styleclass if this leporello tab is active.
   */
  public String getStyleClassActive() {
    return implementation.getMmStyleClassActive();
  }

  /**
   * Returns a reference to some target, either an URL or an outcome. May be used in combination with getMmTargetRefParams().
   *
   * @return  A reference to some target.
   */
  @Override
  public URI getTargetReference() {
    return implementation.getMmTargetReference();
  }

  /**
   * Returns the value of this leporello tab.
   *
   * @return  The value of this leporello tab.
   */
  @Override
  public String getValue() {
    return implementation.getMmViewsideValue();
  }

  /**
   * Returns true, if the HTML tag shall be displayed in disabled state.
   *
   * @return  True, if the HTML tag shall be displayed in disabled state.
   */
  @Override
  public boolean isDisabled() {
    return !implementation.isMmEnabled();
  }

}
