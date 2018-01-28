package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationString;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmString is a mimic for an editable attribute of type {@link String}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-getter
 */
public class MmString extends MmBaseAttributeDeclaration<MmImplementationString, String, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmStringJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmStringJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmString instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmString(MmDeclarationMimic pParent) {
    super(new MmImplementationString(pParent));
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
  @Override public String callbackMmConvertModelsideToViewsideValue(String pModelsideValue) throws MmModelsideConverterException {
    if (pModelsideValue == null) {
      return ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
    } else {
      return pModelsideValue;
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
  @Override public String callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    String returnString;
    if (this.isMmEmpty()) {
      returnString = null;
    } else {
      returnString = pViewsideValue;
    }
    return returnString;
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
  @Override public String callbackMmGetDefaultValue(String pPassThroughValue) {
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
  public List<MmSelectOption<String>> callbackMmGetSelectOptions() {
    final List<MmSelectOption<String>> returnList = new ArrayList<>();

    final MmSelectOption<String>       nullOption = new MmSelectOption<>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    final String currentValue = this.getMmModelsideValue();
    if ((currentValue != null) && !currentValue.isEmpty()) {
      final MmSelectOption<String> valueOption = new MmSelectOption<>(currentValue, currentValue, currentValue, currentValue);
      returnList.add(valueOption);
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
  @Override public void callbackMmValidateModelsideValue(String pModelsideValue) throws MmValidatorException {
  }

}
