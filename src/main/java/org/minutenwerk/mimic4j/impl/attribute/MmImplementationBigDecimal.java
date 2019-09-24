package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigDecimal;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimalAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationBigDecimal is the implementation part of a mimic for {@link BigDecimal}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationBigDecimal
  extends MmBaseAttributeImplementation<MmBigDecimal, MmConfigurationBigDecimal, MmBigDecimalAnnotation, BigDecimal, String> {

  /**
   * Creates a new MmImplementationBigDecimal instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationBigDecimal(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewModelValue == null) || viewModelValue.trim().isEmpty());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationBigDecimal onConstructConfiguration(MmBigDecimalAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationBigDecimal(pAnnotation);
    } else {
      return new MmConfigurationBigDecimal();
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
