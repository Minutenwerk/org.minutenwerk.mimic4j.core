package org.minutenwerk.mimic4j.impl.attribute;

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

  /**
   * Creates a new MmImplementationEnum instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationEnum(MmDeclarationMimic pParent) {
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
    return (Class<ENUM_TYPE>)this.typeOfFirstGenericParameter;
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    this.ensureInitialization();

    return ((this.viewsideValue == null) || this.viewsideValue.trim().isEmpty() || this.viewsideValue.equals("UNDEFINED"));
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
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmEnumAnnotation.class);

    MmEnumAnnotation annotation = this.findAnnotation(this.declaration, MmEnumAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationEnum<ENUM_TYPE>();
    } else {
      this.configuration = new MmConfigurationEnum<ENUM_TYPE>(annotation, this.getMmEnumType());
    }
  }

}
