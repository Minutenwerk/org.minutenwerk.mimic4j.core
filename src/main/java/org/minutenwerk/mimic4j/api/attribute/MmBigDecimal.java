package org.minutenwerk.mimic4j.api.attribute;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.List;
import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationBigDecimal;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmBigDecimal is a mimic for an editable attribute of type {@link BigDecimal}.
 *
 * @author  Olaf Kossak
 */
public class MmBigDecimal extends MmBaseAttributeDeclaration<MmImplementationBigDecimal, BigDecimal, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmBigDecimalJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmBigDecimalJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmBigDecimal instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBigDecimal(final MmDeclarationMimic pParent) {
    super(new MmImplementationBigDecimal(pParent));
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
  public String callbackMmConvertModelsideToViewsideValue(BigDecimal pModelsideValue) throws MmModelsideConverterException {
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
  public BigDecimal callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    BigDecimal returnBigDecimal;
    if (isMmEmpty()) {
      returnBigDecimal = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();

        Number       parsedNumber    = numberFormatter.parse(pViewsideValue);
        returnBigDecimal = (BigDecimal)parsedNumber;
      } catch (ParseException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", viewside value: " + pViewsideValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnBigDecimal;
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
  public void callbackMmValidateModelsideValue(BigDecimal pModelsideValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized number formatter of this mimic.
   *
   * @return  The initialized number formatter of this mimic.
   */
  @Override
  protected NumberFormat getMmNumberFormatter() {
    final String formatPattern = getMmFormatPattern();
    assert formatPattern != null : "getMmFormatPattern() must return valid format pattern";

    final MmRoot        root                  = MmRelationshipApi.getMmRoot(this);
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(true);
    returnNumberFormatter.applyPattern(formatPattern);
    return returnNumberFormatter;
  }

}
