package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class MmImplementationBigInteger extends MmBaseAttributeImplementation<MmBigInteger, MmConfigurationBigInteger, BigInteger, String> {

  /** The logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationBigInteger.class);

  /**
   * Creates a new MmImplementationBigInteger instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationBigInteger(final MmDeclarationMimic pParent) {
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
    if (LOGGER.isDebugEnabled()) {
      checkForIllegalAnnotationsOtherThan(declaration, MmBigIntegerAnnotation.class);
    }

    MmBigIntegerAnnotation annotation = findAnnotation(declaration, MmBigIntegerAnnotation.class);
    if (annotation != null) {
      configuration = new MmConfigurationBigInteger(annotation);
    } else {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationBigInteger();
    }
  }

}
