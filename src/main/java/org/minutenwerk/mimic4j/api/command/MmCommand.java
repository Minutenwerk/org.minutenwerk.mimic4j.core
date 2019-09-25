package org.minutenwerk.mimic4j.api.command;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.command.MmBaseCommandDeclaration;

/**
 * MmCommand is the base class for mimics which execute an action. Commands are usually related to view buttons or view links.
 *
 * @author  Olaf Kossak
 */
public class MmCommand extends MmBaseCommandDeclaration {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmCommandJsfDisabled {

    SameAsEnabled,

    TextOutput,

    TextPlain;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmCommandJsfTag {

    CommandButton,

    Link;
  }

  /**
   * Creates a new MmCommand instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmCommand(MmDeclarationMimic pParent) {
    super(pParent);
  }

}
