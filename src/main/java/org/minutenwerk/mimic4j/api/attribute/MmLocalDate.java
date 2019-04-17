package org.minutenwerk.mimic4j.api.attribute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationLocalDate;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmLocalDate is a mimic for an editable attribute of type {@link LocalDate}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback
 */
public class MmLocalDate extends MmBaseAttributeDeclaration<MmImplementationLocalDate, LocalDate, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDateJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDateJsfTag {

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
  public MmLocalDate(MmDeclarationMimic pParent) {
    super(new MmImplementationLocalDate(pParent));
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
  public String callbackMmConvertModelsideToViewsideValue(LocalDate pModelsideValue) throws MmModelsideConverterException {
    String formatPattern = null;
    try {
      if (pModelsideValue == null) {
        return ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
      } else {
        formatPattern = this.getMmFormatPattern();
        assert formatPattern != null : "getMmFormatPattern() must return valid format pattern";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        String            returnString  = pModelsideValue.format(dateFormatter);
        return returnString;
      }
    } catch (IllegalArgumentException | DateTimeParseException e) {
      throw new MmModelsideConverterException(this,
        "Cannot format " + this.getClass().getSimpleName() + ", modelside value: " + pModelsideValue + " by pattern >" + formatPattern
        + "< " + e.getMessage());
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
  public LocalDate callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    LocalDate returnDate;
    String    formatPattern = null;
    if (this.isMmEmpty()) {
      returnDate = null;
    } else {
      try {
        formatPattern = this.getMmFormatPattern();
        assert formatPattern != null : "getMmFormatPattern() must return valid format pattern";

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        returnDate = LocalDate.parse(pViewsideValue, dateFormatter);
      } catch (DateTimeParseException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + this.getClass().getSimpleName() + " " + this.getMmId() + ", viewside value: " + pViewsideValue
          + " by pattern >" + formatPattern + "< " + e.getMessage());
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
  public LocalDate callbackMmGetDefaultValue(LocalDate pPassThroughValue) {
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
  public void callbackMmValidateModelsideValue(LocalDate pModelsideValue) throws MmValidatorException {
  }

}
