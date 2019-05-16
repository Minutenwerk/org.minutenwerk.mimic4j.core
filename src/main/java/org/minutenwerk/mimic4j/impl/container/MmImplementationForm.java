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
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeForm<MODEL>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmFormAnnotation.class);

    MmFormAnnotation annotation = findAnnotation(declaration, MmFormAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationForm();
    } else {
      configuration = new MmConfigurationForm(annotation);
    }
  }

}
