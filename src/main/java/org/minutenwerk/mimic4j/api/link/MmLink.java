package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLink;

/**
 * MmLink is a composite mimic offering a display link.
 *
 * @param   <MODELSIDE_VALUE>  Modelside value delivers dynamic parts of URL.
 * @param   <LINK_MODEL>       Link model delivers text of link.
 *
 * @author  Olaf Kossak
 */
public class MmLink<MODELSIDE_VALUE, LINK_MODEL>
  extends MmBaseLinkDeclaration<MmImplementationLink<MODELSIDE_VALUE, LINK_MODEL>, MODELSIDE_VALUE, LINK_MODEL> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
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
    super(new MmImplementationLink<>(pParent));
  }

}
