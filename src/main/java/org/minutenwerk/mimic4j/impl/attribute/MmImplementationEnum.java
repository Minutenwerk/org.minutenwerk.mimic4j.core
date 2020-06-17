package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.message.MmMessageType;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

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
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
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
   * Returns i18n long description of specified enum value.
   *
   * @param   pEnumValue  Specified enum value.
   *
   * @return  I18n long description of specified enum value.
   */
  public String getMmI18nEnumLongDescription(final ENUM_TYPE pEnumValue) {
    final String enumTypeName = pEnumValue.getClass().getSimpleName();
    return root.getMmI18nText(enumTypeName + "." + pEnumValue.name(), MmMessageType.LONG);
  }

  /**
   * Returns i18n short description of specified enum value.
   *
   * @param   pEnumValue  Specified enum value.
   *
   * @return  I18n short description of specified enum value.
   */
  public String getMmI18nEnumShortDescription(final ENUM_TYPE pEnumValue) {
    final String enumTypeName = pEnumValue.getClass().getSimpleName();
    return root.getMmI18nText(enumTypeName + "." + pEnumValue.name(), MmMessageType.SHORT);
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    ensureInitialization();

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

}
