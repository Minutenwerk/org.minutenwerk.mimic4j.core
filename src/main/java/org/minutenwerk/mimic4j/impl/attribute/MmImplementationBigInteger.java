package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigInteger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmBigInteger;
import org.minutenwerk.mimic4j.api.attribute.MmBigIntegerAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationBigInteger is the implementation part of a mimic for {@link BigInteger}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationBigInteger
  extends MmBaseAttributeImplementation<MmBigInteger, MmConfigurationBigInteger, MmBigIntegerAnnotation, BigInteger, String> {

  /**
   * Creates a new MmImplementationBigInteger instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationBigInteger(final MmDeclarationMimic pParent) {
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
  protected MmConfigurationBigInteger onConstructConfiguration(MmBigIntegerAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationBigInteger(pAnnotation);
    } else {
      return new MmConfigurationBigInteger();
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
