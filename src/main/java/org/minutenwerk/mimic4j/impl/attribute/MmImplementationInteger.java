package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmInteger;
import org.minutenwerk.mimic4j.api.attribute.MmIntegerAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationInteger is the implementation part of a mimic for {@link Integer}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationInteger
  extends MmBaseAttributeImplementation<MmInteger, MmConfigurationInteger, MmIntegerAnnotation, Integer, String> {

  /**
   * Creates a new MmImplementationInteger instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationInteger(final MmDeclarationMimic pParent) {
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
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationInteger onConstructConfiguration(MmIntegerAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationInteger(pAnnotation);
    } else {
      return new MmConfigurationInteger();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeAttribute<String>(this);
  }

}
