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
  extends MmBaseAttributeImplementation<MmEnum<ENUM_TYPE>, MmConfigurationEnum<ENUM_TYPE>, MmEnumAnnotation, ENUM_TYPE, String> {

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
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewModelValue == null) || viewModelValue.trim().isEmpty() || viewModelValue.equals("UNDEFINED"));
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationEnum<ENUM_TYPE> onConstructConfiguration(MmEnumAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationEnum<ENUM_TYPE>(pAnnotation, getMmEnumType());
    } else {
      return new MmConfigurationEnum<ENUM_TYPE>();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeAttributeEnum<ENUM_TYPE>(this);
  }

}
