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
 */
public class MmImplementationDiv extends MmBaseCompositeImplementation<MmDiv, MmConfigurationDiv, MmDivAnnotation> {

  /**
   * Creates a new MmImplementationDiv instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationDiv(MmDeclarationMimic pParent) {
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
  protected MmConfigurationDiv onConstructConfiguration(MmDivAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationDiv(pAnnotation);
    } else {
      return new MmConfigurationDiv();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeComposite(this);
  }

}
