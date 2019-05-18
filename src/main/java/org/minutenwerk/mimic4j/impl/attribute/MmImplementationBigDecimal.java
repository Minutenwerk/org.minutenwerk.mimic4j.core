package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class MmImplementationBigDecimal extends MmBaseAttributeImplementation<MmBigDecimal, MmConfigurationBigDecimal, BigDecimal, String> {

  /** The logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationBigDecimal.class);

  /**
   * Creates a new MmImplementationBigDecimal instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationBigDecimal(final MmDeclarationMimic pParent) {
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
      checkForIllegalAnnotationsOtherThan(declaration, MmBigDecimalAnnotation.class);
    }

    MmBigDecimalAnnotation annotation = findAnnotation(declaration, MmBigDecimalAnnotation.class);
    if (annotation != null) {
      configuration = new MmConfigurationBigDecimal(annotation);
    } else {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationBigDecimal();
    }
  }

}
