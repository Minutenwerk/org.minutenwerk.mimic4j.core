package org.minutenwerk.mimic4j.impl.composite;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.composite.MmDiv;
import org.minutenwerk.mimic4j.api.composite.MmDivAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeComposite;

/**
 * MmImplementationDiv is the specific class for the implementation part of div mimics.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmImplementationDiv extends MmBaseCompositeImplementation<MmDiv, MmConfigurationDiv> {

  /**
   * Creates a new MmImplementationDiv instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationDiv(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   *
   * @since   $maven.project.version$
   */
  @Override protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeComposite(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   *
   * @since  $maven.project.version$
   */
  @Override protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmDivAnnotation.class);

    MmDivAnnotation annotation = this.findAnnotation(this.declaration, MmDivAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationDiv();
    } else {
      this.configuration = new MmConfigurationDiv(annotation);
    }
  }

}
