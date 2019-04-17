package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.impl.command.MmImplementationCommand;

/**
 * MmJsfBridgeCommand connects a command mimic and a JSF command view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeCommand extends MmJsfBridge<MmImplementationCommand, String, String> {

  /**
   * Creates a new MmJsfBridgeCommand instance.
   *
   * @param  pDeclaration  The implementation part of connected mimic.
   */
  public MmJsfBridgeCommand(MmImplementationCommand pDeclaration) {
    super(pDeclaration);
  }

  /**
   * Returns an EL expression of type String, which triggers JSF to execute a Java method, usually invoked by a HTML button.
   *
   * @return  An EL expression of type String.
   */
  @Override
  public String action() {
    return this.implementation.doMmIt();
  }

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator. May be used in combination with
   * getMmTargetRefParams().
   *
   * @return  A reference to some target.
   */
  @Override
  public MmReference getTargetReference() {
    return this.implementation.getMmTargetReference();
  }

  /**
   * Returns the value of type String, which is the short description of mimic.
   *
   * @return  The value of type String, which is the short description of mimic.
   */
  @Override
  public String getValue() {
    return this.implementation.getMmShortDescription();
  }

}
