package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationEnum;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmEnum is a mimic for an editable attribute of type {@link Enum}.
 *
 * @author  Olaf Kossak
 */
public class MmEnum<ENUM_TYPE extends Enum<ENUM_TYPE>> extends MmBaseAttributeDeclaration<MmImplementationEnum<ENUM_TYPE>, ENUM_TYPE, String> {

  /**
   * Creates a new MmEnum instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmEnum(final MmDeclarationMimic pParent) {
    super(new MmImplementationEnum<ENUM_TYPE>(pParent));
  }

  /**
   * Converts data model value of type DATA_MODEL to value of type VIEW_TYPE.
   *
   * @param   pDataModelValue  The data model value to be converted.
   *
   * @return  The converted value of type VIEW_TYPE.
   *
   * @throws  MmDataModelConverterException  In case of the conversion failed.
   */
  @Override
  public String callbackMmConvertDataModelToViewModel(ENUM_TYPE pDataModelValue) throws MmDataModelConverterException {
    if (pDataModelValue == null) {
      return ATTRIBUTE_STRING_VIEW_NULL_VALUE;
    } else {
      if (isMmEnabled()) {
        return pDataModelValue.name();
      } else {
        return implementation.getMmI18nEnumShortDescription(pDataModelValue);
      }
    }
  }

  /**
   * Converts view value of type VIEW_TYPE to value of type DATA_MODEL.
   *
   * @param   pViewModelValue  The view value to be converted.
   *
   * @return  The converted value of type DATA_MODEL.
   *
   * @throws  MmViewModelConverterException  In case of the conversion failed.
   */
  @Override
  public ENUM_TYPE callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    ENUM_TYPE returnEnumType = null;
    if (!isMmEmpty()) {
      try {
        Class<ENUM_TYPE> enumType = implementation.getMmEnumType();
        returnEnumType = Enum.valueOf(enumType, pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmViewModelConverterException(this, "Cannot format " + this + ", view value: " + pViewModelValue);
      }
    }
    return returnEnumType;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<MmSelectOption<ENUM_TYPE>> callbackMmGetSelectOptions() {
    final List<MmSelectOption<ENUM_TYPE>> returnList = new ArrayList<>();

    final MmSelectOption<ENUM_TYPE>       nullOption = new MmSelectOption<>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    Class<ENUM_TYPE> enumType = implementation.getMmEnumType();
    for (ENUM_TYPE enumInstance : enumType.getEnumConstants()) {
      returnList.add(new MmSelectOption<>(enumInstance.name(), implementation.getMmI18nEnumShortDescription(enumInstance),
          implementation.getMmI18nEnumLongDescription(enumInstance), enumInstance));
    }
    return returnList;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(ENUM_TYPE pDataModelValue) throws MmValidatorException {
  }

}
