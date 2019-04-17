package org.minutenwerk.mimic4j.api.attribute;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationLocalTime;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmLocalTime is a mimic for an editable attribute of type {@link LocalTime}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback
 */
public class MmLocalTime extends MmBaseAttributeDeclaration<MmImplementationLocalTime, LocalTime, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmTimeJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmTimeJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmTime instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLocalTime(MmDeclarationMimic pParent) {
    super(new MmImplementationLocalTime(pParent));
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
  public String callbackMmConvertModelsideToViewsideValue(LocalTime pModelsideValue) throws MmModelsideConverterException {
    try {
      if (pModelsideValue == null) {
        return ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
      } else {
        String returnString = pModelsideValue.format(this.getMmTimeTimeFormatter());
        return returnString;
      }
    } catch (Exception e) {
      throw new MmModelsideConverterException(this,
        "Cannot format " + this.getClass().getSimpleName() + " " + this.getMmId() + ", modelside value: " + pModelsideValue
        + " by pattern >" + this.getMmFormatPattern() + "<");
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
  @Override
  public LocalTime callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    LocalTime returnDate;
    if (this.isMmEmpty()) {
      returnDate = null;
    } else {
      try {
        DateTimeFormatter dateTimeFormatter = this.getMmTimeTimeFormatter();
        returnDate = LocalTime.parse(pViewsideValue, dateTimeFormatter);
      } catch (DateTimeParseException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + this.getClass().getSimpleName() + " " + this.getMmId() + ", viewside value: " + pViewsideValue
          + " by pattern >" + this.getMmFormatPattern() + "<");
      }
    }
    return returnDate;
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
  @Override
  public LocalTime callbackMmGetDefaultValue(LocalTime pPassThroughValue) {
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
  public <OPTION_VALUE_TYPE> List<MmSelectOption<OPTION_VALUE_TYPE>> callbackMmGetSelectOptions() {
    return null;
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
  public void callbackMmValidateModelsideValue(LocalTime pModelsideValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return  The initialized date formatter of this mimic.
   */
  protected DateTimeFormatter getMmTimeTimeFormatter() {
    final String formatPattern = this.getMmFormatPattern();
    assert formatPattern != null : "getMmFormatPattern() must return valid format pattern";

    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(formatPattern);
    return returnDateFormatter;
  }

}
