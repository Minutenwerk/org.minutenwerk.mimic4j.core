package org.minutenwerk.mimic4j.api.attribute;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationBigDecimal;

/**
 * MmBigDecimal is a mimic for an editable attribute of type {@link BigDecimal}.
 *
 * @author  Olaf Kossak
 */
public class MmBigDecimal extends MmBaseAttributeDeclaration<MmImplementationBigDecimal, BigDecimal, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationBigDecimal.class);

  /**
   * Creates a new MmBigDecimal instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmBigDecimal(final MmDeclarationMimic pParent) {
    super(new MmImplementationBigDecimal(pParent));
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
  public String callbackMmConvertDataModelToViewModel(BigDecimal pDataModelValue) throws MmDataModelConverterException {
    String returnString;
    if (pDataModelValue == null) {
      returnString = ATTRIBUTE_STRING_VIEW_NULL_VALUE;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        returnString = numberFormatter.format(pDataModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this, "Cannot format data model value: " + pDataModelValue + " by pattern >" + getMmFormatPattern() + "<");
      }
    }
    return returnString;
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
  public BigDecimal callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    BigDecimal returnBigDecimal;
    if (isMmEmpty()) {
      returnBigDecimal = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();

        Number       parsedNumber    = numberFormatter.parse(pViewModelValue);
        returnBigDecimal = (BigDecimal)parsedNumber;
      } catch (ParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + this + ", view value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
      }
    }
    return returnBigDecimal;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(BigDecimal pDataModelValue) throws MmValidatorException {
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

    final Locale        locale                = implementation.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(true);
    returnNumberFormatter.applyPattern(formatPattern);
    return returnNumberFormatter;
  }

}
