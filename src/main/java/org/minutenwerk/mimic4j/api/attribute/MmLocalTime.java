package org.minutenwerk.mimic4j.api.attribute;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationLocalTime;

/**
 * MmLocalTime is a mimic for an editable attribute of type {@link LocalTime}.
 *
 * @author  Olaf Kossak
 */
public class MmLocalTime extends MmBaseAttributeDeclaration<MmImplementationLocalTime, LocalTime, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationLocalTime.class);

  /**
   * Creates a new MmTime instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmLocalTime(final MmDeclarationMimic pParent) {
    super(new MmImplementationLocalTime(pParent));
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
  public String callbackMmConvertDataModelToViewModel(LocalTime pDataModelValue) throws MmDataModelConverterException {
    try {
      if (pDataModelValue == null) {
        return ATTRIBUTE_STRING_VIEW_NULL_VALUE;
      } else {
        String returnString = pDataModelValue.format(getMmTimeFormatter());
        return returnString;
      }
    } catch (Exception e) {
      throw new MmDataModelConverterException(this, "Cannot format data model value: " + pDataModelValue + " by pattern >" + getMmFormatPattern() + "<");
    }
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
  public LocalTime callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    LocalTime returnDate;
    if (isMmEmpty()) {
      returnDate = null;
    } else {
      try {
        DateTimeFormatter dateTimeFormatter = getMmTimeFormatter();
        returnDate = LocalTime.parse(pViewModelValue, dateTimeFormatter);
      } catch (DateTimeParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + this + ", view value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
      }
    }
    return returnDate;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(LocalTime pDataModelValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return  The initialized date formatter of this mimic.
   *
   * @throws  IllegalStateException  In case of getMmFormatPattern returns an invalid format pattern.
   */
  protected DateTimeFormatter getMmTimeFormatter() {
    final String formatPattern = getMmFormatPattern();
    if (LOGGER.isDebugEnabled()) {
      if (formatPattern == null) {
        throw new IllegalStateException("getMmFormatPattern() must return valid format pattern");
      }
    }

    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(formatPattern);
    return returnDateFormatter;
  }

}
