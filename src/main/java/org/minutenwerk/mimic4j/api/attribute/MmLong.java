package org.minutenwerk.mimic4j.api.attribute;

import java.text.NumberFormat;
import java.text.ParseException;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationLong;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmLong is a mimic for an editable attribute of type {@link Long}.
 *
 * @author  Olaf Kossak
 */
public class MmLong extends MmBaseAttributeDeclaration<MmImplementationLong, Long, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmLongJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmLongJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmLong instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLong(final MmDeclarationMimic pParent) {
    super(new MmImplementationLong(pParent));
  }

  /**
   * Converts modelside value of type MODELSIDE_VALUE to value of type VIEWSIDE_VALUE.
   *
   * @param   pModelsideValue  The modelside value to be converted.
   *
   * @return  The converted value of type VIEWSIDE_VALUE.
   *
   * @throws  MmModelsideConverterException  In case of the conversion failed.
   */
  @Override
  public String callbackMmConvertModelsideToViewsideValue(Long pModelsideValue) throws MmModelsideConverterException {
    String returnString;
    if (pModelsideValue == null) {
      returnString = ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        returnString = numberFormatter.format(pModelsideValue);
      } catch (IllegalArgumentException e) {
        throw new MmModelsideConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnString;
  }

  /**
   * Converts viewside value of type VIEWSIDE_VALUE to value of type MODELSIDE_VALUE.
   *
   * @param   pViewsideValue  The viewside value to be converted.
   *
   * @return  The converted value of type MODELSIDE_VALUE.
   *
   * @throws  MmViewsideConverterException  In case of the conversion failed.
   */
  @Override
  public Long callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    Long returnLong;
    if (isMmEmpty()) {
      returnLong = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        Number       parsedNumber    = numberFormatter.parse(pViewsideValue);
        returnLong = parsedNumber.longValue();
      } catch (ParseException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", viewside value: " + pViewsideValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnLong;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  @Override
  public <OPTION_VALUE_TYPE> List<MmSelectOption<OPTION_VALUE_TYPE>> callbackMmGetSelectOptions() {
    return null;
  }

  /**
   * Semantic validation of modelside value of type MODELSIDE_VALUE. If validation succeeds:
   *
   * @param   pModelsideValue  The modelside value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateModelsideValue(Long pModelsideValue) throws MmValidatorException {
  }

}
