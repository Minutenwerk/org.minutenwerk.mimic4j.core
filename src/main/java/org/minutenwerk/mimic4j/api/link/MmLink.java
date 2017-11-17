package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLink;

/**
 * MmLink is a composite mimic offering a display link.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmLink extends MmBaseLinkDeclaration<MmImplementationLink> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/link/MmLink.java\$
   */
  public enum MmLinkJsfTag {

    Link;
  }

  /**
   * Creates a new MmLink instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLink(MmDeclarationMimic pParent) {
    super(new MmImplementationLink(pParent));
  }

}
