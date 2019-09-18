package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.link.MmLink;
import org.minutenwerk.mimic4j.api.link.MmLinkAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLink;

/**
 * MmImplementationLink is the specific class for the implementation part of link mimics.
 *
 * @param   <MODELSIDE_VALUE>  Modelside value delivers dynamic parts of URL.
 * @param   <LINK_MODEL>       Link model delivers text of link.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLink<MODELSIDE_VALUE, LINK_MODEL>
  extends MmBaseLinkImplementation<MmLink<MODELSIDE_VALUE, LINK_MODEL>, MODELSIDE_VALUE, LINK_MODEL, MmConfigurationLink, MmLinkAnnotation> {

  /**
   * Creates a new MmImplementationLink instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLink(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationLink onConstructConfiguration(MmLinkAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLink(pAnnotation);
    } else {
      return new MmConfigurationLink();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeLink(this);
  }

}
