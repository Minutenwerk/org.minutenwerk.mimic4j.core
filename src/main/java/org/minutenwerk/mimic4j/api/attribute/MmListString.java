package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationListString;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmListString is a mimic for an editable attribute of type {@link List} of type {@link String}.
 *
 * @author  Olaf Kossak
 *
 *          <p>, group-getter</p>
 */
public class MmListString extends MmBaseAttributeDeclaration<MmImplementationListString, List<String>, List<String>> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmListStringJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmListStringJsfTag {

    SelectManyListbox,

    SelectManyCheckbox;
  }

  /**
   * Creates a new MmListString instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmListString(final MmDeclarationMimic pParent) {
    super(new MmImplementationListString(pParent));
  }

  /**
   * Converts data model value of type DATA_MODEL to value of type VIEW_MODEL.
   *
   * @param   pDataModelValue  The data model value to be converted.
   *
   * @return  The converted value of type VIEW_MODEL.
   *
   * @throws  MmDataModelConverterException  In case of the conversion failed.
   */
  @Override
  public List<String> callbackMmConvertDataModelToViewModel(List<String> pDataModelValue) throws MmDataModelConverterException {
    final List<String> returnList = new ArrayList<>();
    if (pDataModelValue == null) {
      returnList.add(ATTRIBUTE_STRING_VIEW_MODEL_NULL_VALUE);
    } else {
      Collections.copy(pDataModelValue, returnList);
    }
    return returnList;
  }

  /**
   * Converts view model value of type VIEW_MODEL to value of type DATA_MODEL.
   *
   * @param   pViewModelValue  The view model value to be converted.
   *
   * @return  The converted value of type DATA_MODEL.
   *
   * @throws  MmViewModelConverterException  In case of the conversion failed.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<String> callbackMmConvertViewModelToDataModel(List<String> pViewModelValue) throws MmViewModelConverterException {
    List<String> returnList;
    if (isMmEmpty()) {
      returnList = (List<String>)Collections.EMPTY_LIST;
    } else {
      returnList = new ArrayList<>();
      Collections.copy(pViewModelValue, returnList);
    }
    return returnList;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<MmSelectOption<String>> callbackMmGetSelectOptions() {
    List<MmSelectOption<String>> returnList = new ArrayList<>();
    MmSelectOption<String>       nullOption = new MmSelectOption<>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    List<String> currentValues = getMmModel();
    if ((currentValues != null) && !currentValues.isEmpty()) {
      for (String currentValue : currentValues) {
        MmSelectOption<String> valueOption = new MmSelectOption<>("currentValue", "currentValue", "currentValue", currentValue);
        returnList.add(valueOption);
      }
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
  public void callbackMmValidateDataModel(List<String> pDataModelValue) throws MmValidatorException {
  }

}
