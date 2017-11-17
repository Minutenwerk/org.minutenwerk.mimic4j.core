package org.minutenwerk.mimic4j.api.command;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.command.MmBaseCommandDeclaration;

/**
 * MmCommand is the base class for mimics which execute an action. Commands are usually related to view buttons or view links.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmCommand extends MmBaseCommandDeclaration {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/command/MmCommand.java\$
   */
  public enum MmCommandJsfDisabled {

    SameAsEnabled,

    TextOutput,

    TextPlain;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/command/MmCommand.java\$
   */
  public enum MmCommandJsfTag {

    CommandButton,

    Link;
  }

  /**
   * Creates a new MmCommand instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmCommand(MmDeclarationMimic pParent) {
    super(pParent);
  }

}
