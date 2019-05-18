package org.minutenwerk.mimic4j.impl.attribute;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttributeEnum;

/**
 * MmImplementationEnum is the implementation part of a mimic for enumerations.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationEnum<ENUM_TYPE extends Enum<ENUM_TYPE>>
  extends MmBaseAttributeImplementation<MmEnum<ENUM_TYPE>, MmConfigurationEnum<ENUM_TYPE>, ENUM_TYPE, String> {

  /** The logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationEnum.class);

  /**
   * Creates a new MmImplementationEnum instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationEnum(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the Java type of the Java enumeration of this mimic.
   *
   * @return        The Java type of the Java enumeration of this mimic.
   *
   * @jalopy.group  group-override
   */
  @SuppressWarnings("unchecked")
  public Class<ENUM_TYPE> getMmEnumType() {
    return (Class<ENUM_TYPE>)typeOfFirstGenericParameter;
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewsideValue == null) || viewsideValue.trim().isEmpty() || viewsideValue.equals("UNDEFINED"));
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeAttributeEnum<ENUM_TYPE>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    if (LOGGER.isDebugEnabled()) {
      checkForIllegalAnnotationsOtherThan(declaration, MmEnumAnnotation.class);
    }

    MmEnumAnnotation annotation = findAnnotation(declaration, MmEnumAnnotation.class);
    if (annotation != null) {
      configuration = new MmConfigurationEnum<ENUM_TYPE>(annotation, getMmEnumType());
    } else {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationEnum<ENUM_TYPE>();
    }
  }

}
