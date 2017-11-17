package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.container.MmForm;
import org.minutenwerk.mimic4j.api.container.MmFormAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeForm;

/**
 * MmImplementationForm is the specific class for the implementation part of form mimics.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmImplementationForm<MODEL> extends MmBaseContainerImplementation<MmForm<MODEL>, MODEL, MmConfigurationForm> {

  /**
   * Creates a new MmImplementationForm instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationForm(MmDeclarationMimic pParent) {
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
    return new MmJsfBridgeForm<MODEL>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   *
   * @since  $maven.project.version$
   */
  @Override protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmFormAnnotation.class);

    MmFormAnnotation annotation = this.findAnnotation(this.declaration, MmFormAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationForm();
    } else {
      this.configuration = new MmConfigurationForm(annotation);
    }
  }

}
