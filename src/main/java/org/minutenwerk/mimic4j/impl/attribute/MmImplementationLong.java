package org.minutenwerk.mimic4j.impl.attribute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmLong;
import org.minutenwerk.mimic4j.api.attribute.MmLongAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationLong is the implementation part of a mimic for {@link Long}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLong extends MmBaseAttributeImplementation<MmLong, MmConfigurationLong, MmLongAnnotation, Long, String> {

  /** The logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationLong.class);

  /**
   * Creates a new MmImplementationLong instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLong(final MmDeclarationMimic pParent) {
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
      checkForIllegalAnnotationsOtherThan(declaration, MmLongAnnotation.class);
    }

    MmLongAnnotation annotation = findAnnotation(declaration, MmLongAnnotation.class);
    if (annotation != null) {
      configuration = new MmConfigurationLong(annotation);
    } else {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationLong();
    }
  }

}
