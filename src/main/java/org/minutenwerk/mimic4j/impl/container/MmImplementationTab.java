package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLeporelloTab;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeTab;

/**
 * MmImplementationTab is the specific class for the implementation part of tab mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationTab<MODEL> extends MmBaseContainerImplementation<MmTab<MODEL>, MODEL, MmConfigurationTab> {

  /** The parent tab set this tab belongs to. */
  protected MmImplementationLeporelloTab parentTabSet;

  /**
   * Creates a new MmImplementationTab instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationTab(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeTab<MODEL>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmTabAnnotation.class);

    MmTabAnnotation annotation = findAnnotation(declaration, MmTabAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationTab();
    } else {
      configuration = new MmConfigurationTab(annotation);
    }
  }

}
