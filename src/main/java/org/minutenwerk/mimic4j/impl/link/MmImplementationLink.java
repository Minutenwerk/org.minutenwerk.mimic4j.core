package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.link.MmLink;
import org.minutenwerk.mimic4j.api.link.MmLinkAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLink;

/**
 * MmImplementationLink is the specific class for the implementation part of link mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLink extends MmBaseLinkImplementation<MmLink, MmConfigurationLink> {

  /**
   * Creates a new MmImplementationLink instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLink(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeLink(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmLinkAnnotation.class);

    MmLinkAnnotation annotation = this.findAnnotation(this.declaration, MmLinkAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationLink();
    } else {
      this.configuration = new MmConfigurationLink(annotation);
    }
  }

}
