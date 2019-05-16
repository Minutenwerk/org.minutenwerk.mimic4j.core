package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloAnnotation;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLeporello;

/**
 * MmImplementationLeporello is the specific class for the implementation part of leporello mimics.
 *
 * @param   MODEL      The model containing the values to be set, cannot be null.
 * @param   SUB_MODEL  The sub model containing the values to be set, can be null.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLeporello<MODEL, SUB_MODEL>
  extends MmBaseContainerImplementation<MmLeporello<MODEL, SUB_MODEL>, MODEL, MmConfigurationLeporello> {

  /**
   * Creates a new MmImplementationLeporello instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLeporello(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the currently selected leporello tab, may be null.
   *
   * @return  the currently selected leporello tab, may be null.
   */
  public MmLeporelloTab getMmSelectedTab() {
    assureInitialization();

    return declaration.callbackMmGetSelectedTab();
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeLeporello(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmLeporelloAnnotation.class);

    MmLeporelloAnnotation annotation = findAnnotation(declaration, MmLeporelloAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationLeporello();
    } else {
      configuration = new MmConfigurationLeporello(annotation);
    }
  }

}
