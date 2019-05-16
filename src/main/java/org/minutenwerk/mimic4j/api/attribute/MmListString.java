package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationListString;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmListString is a mimic for an editable attribute of type {@link List} of type {@link String}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-getter
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
  @Override
  public List<String> callbackMmConvertModelsideToViewsideValue(List<String> pModelsideValue) throws MmModelsideConverterException {
    final List<String> returnList = new ArrayList<>();
    if (pModelsideValue == null) {
      returnList.add(ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE);
    } else {
      Collections.copy(pModelsideValue, returnList);
    }
    return returnList;
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
  @Override
  @SuppressWarnings("unchecked")
  public List<String> callbackMmConvertViewsideToModelsideValue(List<String> pViewsideValue) throws MmViewsideConverterException {
    List<String> returnList;
    if (isMmEmpty()) {
      returnList = (List<String>)Collections.EMPTY_LIST;
    } else {
      returnList = new ArrayList<>();
      Collections.copy(pViewsideValue, returnList);
    }
    return returnList;
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
  public List<MmSelectOption<String>> callbackMmGetSelectOptions() {
    List<MmSelectOption<String>> returnList = new ArrayList<>();
    MmSelectOption<String>       nullOption = new MmSelectOption<>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    List<String> currentValues = getMmModelsideValue();
    if ((currentValues != null) && !currentValues.isEmpty()) {
      for (String currentValue : currentValues) {
        MmSelectOption<String> valueOption = new MmSelectOption<>("currentValue", "currentValue", "currentValue", currentValue);
        returnList.add(valueOption);
      }
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
  @Override
  public void callbackMmValidateModelsideValue(List<String> pModelsideValue) throws MmValidatorException {
  }

}
