package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmDouble;
import org.minutenwerk.mimic4j.api.attribute.MmDoubleAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationDouble is the implementation part of a mimic for {@link Double}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationDouble extends MmBaseAttributeImplementation<MmDouble, MmConfigurationDouble, Double, String> {

  /**
   * Creates a new MmImplementationDouble instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationDouble(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewsideValue == null) || viewsideValue.trim().isEmpty());
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeAttribute<String>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmDoubleAnnotation.class);

    MmDoubleAnnotation annotation = findAnnotation(declaration, MmDoubleAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationDouble();
    } else {
      configuration = new MmConfigurationDouble(annotation);
    }
  }

}
