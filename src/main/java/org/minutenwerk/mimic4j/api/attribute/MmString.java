package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationString;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmString is a mimic for an editable attribute of type {@link String}.
 *
 * @author  Olaf Kossak
 */
public class MmString extends MmBaseAttributeDeclaration<MmImplementationString, String, String> {

  /**
   * Creates a new MmString instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmString(MmDeclarationMimic pParent) {
    super(new MmImplementationString(pParent));
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
  public String callbackMmConvertDataModelToViewModel(String pDataModelValue) throws MmDataModelConverterException {
    if (pDataModelValue == null) {
      return ATTRIBUTE_STRING_VIEW_NULL_VALUE;
    } else {
      return pDataModelValue;
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
  public String callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    if (isMmEmpty()) {
      return null;
    } else {
      return pViewModelValue;
    }
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<MmSelectOption<String>> callbackMmGetSelectOptions() {
    final List<MmSelectOption<String>> returnList = new ArrayList<>();

    final MmSelectOption<String>       nullOption = new MmSelectOption<>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    final String currentValue = getMmModel();
    if ((currentValue != null) && !currentValue.isEmpty()) {
      final MmSelectOption<String> valueOption = new MmSelectOption<>(currentValue, currentValue, currentValue, currentValue);
      returnList.add(valueOption);
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
  public void callbackMmValidateDataModel(String pDataModelValue) throws MmValidatorException {
  }

}
