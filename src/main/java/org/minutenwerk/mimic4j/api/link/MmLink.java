package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLink;

/**
 * MmLink is a composite mimic offering a display link.
 *
 * @author  Olaf Kossak
 */
public class MmLink extends MmBaseLinkDeclaration<MmImplementationLink> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
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
