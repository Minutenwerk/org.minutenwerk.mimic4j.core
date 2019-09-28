package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.command.MmImplementationCommand;

/**
 * MmJsfBridgeCommand connects a command mimic and a JSF command view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeCommand extends MmJsfBridge<MmImplementationCommand<?>, String, String> {

  /**
   * Creates a new MmJsfBridgeCommand instance.
   *
   * @param  pDeclaration  The implementation part of connected mimic.
   */
  public MmJsfBridgeCommand(MmImplementationCommand<?> pDeclaration) {
    super(pDeclaration);
  }

  /**
   * Returns the value of type String, which is the short description of mimic.
   *
   * @return  The value of type String, which is the short description of mimic.
   */
  @Override
  public String getValue() {
    return implementation.getMmShortDescription();
  }

}
