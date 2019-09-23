package org.minutenwerk.mimic4j.impl.view;

import java.net.URI;

import org.minutenwerk.mimic4j.impl.link.MmImplementationLink;

/**
 * MmJsfBridgeLink connects a link mimic and a JSF link view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeLink extends MmJsfBridge<MmImplementationLink<?, ?>, String, String> {

  /**
   * Creates a new MmJsfBridgeLink instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeLink(MmImplementationLink<?, ?> pImplementation) {
    super(pImplementation);
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
   * Returns the value of this link.
   *
   * @return  The value of this link.
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
