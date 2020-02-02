package org.minutenwerk.mimic4j.api.attribute;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationInstant;

/**
 * MmDate is a mimic for an editable attribute of type {@link Date}.
 *
 * @author  Olaf Kossak
 */
public class MmInstant extends MmBaseAttributeDeclaration<MmImplementationInstant, Instant, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationInstant.class);

  /**
   * Creates a new MmDate instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmInstant(final MmDeclarationMimic pParent) {
    super(new MmImplementationInstant(pParent));
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
  public String callbackMmConvertDataModelToViewModel(Instant pDataModelValue) throws MmDataModelConverterException {
    try {
      if (pDataModelValue == null) {
        return ATTRIBUTE_STRING_VIEW_NULL_VALUE;
      } else {
        DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
        String            returnString      = dateTimeFormatter.format(pDataModelValue);
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
  public Instant callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    Instant returnInstant;
    if (isMmEmpty()) {
      returnInstant = null;
    } else {
      try {
        DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
        TemporalAccessor  temporalAccessor  = dateTimeFormatter.parse(pViewModelValue);
        LocalDateTime     localDateTime     = LocalDateTime.from(temporalAccessor);
        ZonedDateTime     zonedDateTime     = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
        returnInstant = Instant.from(zonedDateTime);
      } catch (DateTimeParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + this + ", view value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
      }
    }
    return returnInstant;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(Instant pDataModelValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return  The initialized date formatter of this mimic.
   *
   * @throws  IllegalStateException  In case of getMmFormatPattern returns an invalid format pattern.
   */
  protected DateTimeFormatter getMmDateTimeFormatter() {
    final String formatPattern = getMmFormatPattern();
    if (LOGGER.isDebugEnabled()) {
      if (formatPattern == null) {
        throw new IllegalStateException("getMmFormatPattern() must return valid format pattern");
      }
    }

    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(formatPattern).withZone(ZoneId.of("UTC"));
    return returnDateFormatter;
  }

}
