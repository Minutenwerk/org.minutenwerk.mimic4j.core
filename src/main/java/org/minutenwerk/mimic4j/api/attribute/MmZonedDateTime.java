package org.minutenwerk.mimic4j.api.attribute;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Date;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationZonedDateTime;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmDate is a mimic for an editable attribute of type {@link Date}.
 *
 * @author  Olaf Kossak
 */
public class MmZonedDateTime extends MmBaseAttributeDeclaration<MmImplementationZonedDateTime, ZonedDateTime, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDateTimeJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDateTimeJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmDate instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmZonedDateTime(final MmDeclarationMimic pParent) {
    super(new MmImplementationZonedDateTime(pParent));
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
  public String callbackMmConvertModelsideToViewsideValue(ZonedDateTime pModelsideValue) throws MmModelsideConverterException {
    try {
      if (pModelsideValue == null) {
        return ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
      } else {
        String returnString = pModelsideValue.format(getMmDateTimeFormatter());
        return returnString;
      }
    } catch (Exception e) {
      throw new MmModelsideConverterException(this,
        "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
        + getMmFormatPattern() + "<");
    }
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
  public ZonedDateTime callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    ZonedDateTime returnDateTime;
    if (isMmEmpty()) {
      returnDateTime = null;
    } else {
      try {
        DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
        returnDateTime = ZonedDateTime.parse(pViewsideValue, dateTimeFormatter);
      } catch (DateTimeParseException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", viewside value: " + pViewsideValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnDateTime;
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
  public void callbackMmValidateModelsideValue(ZonedDateTime pModelsideValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return  The initialized date formatter of this mimic.
   */
  protected DateTimeFormatter getMmDateTimeFormatter() {
    final String formatPattern = getMmFormatPattern();
    assert formatPattern != null : "getMmFormatPattern() must return valid format pattern";

    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(formatPattern);
    return returnDateFormatter;
  }

}
