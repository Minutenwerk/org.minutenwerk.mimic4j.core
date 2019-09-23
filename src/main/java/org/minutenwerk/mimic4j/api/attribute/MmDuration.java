package org.minutenwerk.mimic4j.api.attribute;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.time.Duration;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationDuration;

/**
 * MmDuration is a mimic for an editable attribute of type {@link Duration}.
 *
 * @author  Olaf Kossak
 */
public class MmDuration extends MmBaseAttributeDeclaration<MmImplementationDuration, Duration, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationDuration.class);

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDurationJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDurationJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmDuration instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmDuration(final MmDeclarationMimic pParent) {
    super(new MmImplementationDuration(pParent));
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
  public String callbackMmConvertDataModelToViewModel(Duration pDataModelValue) throws MmDataModelConverterException {
    String returnString;
    if (pDataModelValue == null) {
      returnString = ATTRIBUTE_STRING_VIEW_MODEL_NULL_VALUE;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        returnString = numberFormatter.format(pDataModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", data model value: " + pDataModelValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnString;
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
  public Duration callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    Duration returnDuration;
    if (isMmEmpty()) {
      returnDuration = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        Number       parsedNumber    = numberFormatter.parse(pViewModelValue);
        returnDuration = Duration.ofMillis(parsedNumber.longValue());
      } catch (ParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", view model value: " + pViewModelValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnDuration;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(Duration pDataModelValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized number formatter of this mimic.
   *
   * @return  The initialized number formatter of this mimic.
   *
   * @throws  IllegalStateException  In case of getMmFormatPattern returns an invalid format pattern.
   */
  @Override
  protected NumberFormat getMmNumberFormatter() {
    final String formatPattern = getMmFormatPattern();
    if (LOGGER.isDebugEnabled()) {
      if (formatPattern == null) {
        throw new IllegalStateException("getMmFormatPattern() must return valid format pattern");
      }
    }

    final Locale        locale                = getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(true);
    returnNumberFormatter.applyPattern(formatPattern);
    return returnNumberFormatter;
  }

}
