package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationEnum;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmEnum is a mimic for an editable attribute of type {@link Enum}.
 *
 * @author  Olaf Kossak
 */
public class MmEnum<ENUM_TYPE extends Enum<ENUM_TYPE>>
  extends MmBaseAttributeDeclaration<MmImplementationEnum<ENUM_TYPE>, ENUM_TYPE, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author   Olaf Kossak
   */
  public enum MmEnumJsfDisabled {

    TextOutput,

    TextField,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   */
  public enum MmEnumJsfTag {

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmEnum instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmEnum(MmDeclarationMimic pParent) {
    super(new MmImplementationEnum<ENUM_TYPE>(pParent));
  }

  /**
   * Converts modelside value of type MODELSIDE_VALUE to value of type VIEWSIDE_VALUE.
   *
   * @param         pModelsideValue  The modelside value to be converted.
   *
   * @return        The converted value of type VIEWSIDE_VALUE.
   *
   * @throws        MmModelsideConverterException  In case of the conversion failed.
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmConvertModelsideToViewsideValue(ENUM_TYPE pModelsideValue) throws MmModelsideConverterException {
    if (pModelsideValue == null) {
      return ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
    } else {
      if (this.isMmEnabled()) {
        return pModelsideValue.name();
      } else {
        MmRoot           root         = MmRelationshipApi.getMmRoot(this);
        Class<ENUM_TYPE> enumType     = this.implementation.getMmEnumType();
        String           enumTypeName = enumType.getSimpleName();
        String           enumLabel    = root.getMmI18nText(enumTypeName + "." + pModelsideValue.name(), MmMessageType.SHORT);
        return enumLabel;
      }
    }
  }

  /**
   * Converts viewside value of type VIEWSIDE_VALUE to value of type MODELSIDE_VALUE.
   *
   * @param         pViewsideValue  The viewside value to be converted.
   *
   * @return        The converted value of type MODELSIDE_VALUE.
   *
   * @throws        MmViewsideConverterException  In case of the conversion failed.
   *
   * @jalopy.group  group-callback
   */
  @Override public ENUM_TYPE callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    ENUM_TYPE returnEnumType = null;
    if (!this.isMmEmpty()) {
      try {
        Class<ENUM_TYPE> enumType = this.implementation.getMmEnumType();
        returnEnumType = Enum.valueOf(enumType, pViewsideValue);
      } catch (IllegalArgumentException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + this.getClass().getSimpleName() + " " + this.getMmId() + ", viewside value: " + pViewsideValue);
      }
    }
    return returnEnumType;
  }

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's default value of type MODELSIDE_VALUE.
   *
   * @jalopy.group  group-callback
   */
  @Override public ENUM_TYPE callbackMmGetDefaultValue(ENUM_TYPE pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return        A list of options.
   *
   * @jalopy.group  group-callback
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<MmSelectOption<ENUM_TYPE>> callbackMmGetSelectOptions() {
    final List<MmSelectOption<ENUM_TYPE>> returnList = new ArrayList<MmSelectOption<ENUM_TYPE>>();
    final MmSelectOption<ENUM_TYPE>       nullOption = new MmSelectOption<ENUM_TYPE>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    final MmRoot     root     = MmRelationshipApi.getMmRoot(this);
    Class<ENUM_TYPE> enumType = this.implementation.getMmEnumType();
    for (ENUM_TYPE enumInstance : enumType.getEnumConstants()) {
      final String                    messageId       = enumType.getSimpleName() + "." + enumInstance.name();
      final String                    enumLabel       = root.getMmI18nText(messageId, MmMessageType.SHORT);
      final String                    enumDescription = root.getMmI18nText(messageId, MmMessageType.LONG);
      final MmSelectOption<ENUM_TYPE> option          = new MmSelectOption<ENUM_TYPE>(enumInstance.name(), enumLabel, enumDescription,
          enumInstance);
      returnList.add(option);
    }
    return returnList;
  }

  /**
   * Semantic validation of modelside value of type MODELSIDE_VALUE. If validation succeeds:
   *
   * @param         pModelsideValue  The modelside value to be validated.
   *
   * @throws        MmValidatorException  In case of validation fails.
   *
   * @jalopy.group  group-callback
   */
  @Override public void callbackMmValidateModelsideValue(ENUM_TYPE pModelsideValue) throws MmValidatorException {
  }

}
