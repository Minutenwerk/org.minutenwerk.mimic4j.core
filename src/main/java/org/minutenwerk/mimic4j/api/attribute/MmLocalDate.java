package org.minutenwerk.mimic4j.api.attribute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationLocalDate;

/**
 * MmLocalDate is a mimic for an editable attribute of type {@link LocalDate}.
 *
 * @author  Olaf Kossak
 */
public class MmLocalDate extends MmBaseAttributeDeclaration<MmImplementationLocalDate, LocalDate, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationLocalDate.class);

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
  public MmLocalDate(final MmDeclarationMimic pParent) {
    super(new MmImplementationLocalDate(pParent));
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
  public String callbackMmConvertDataModelToViewModel(LocalDate pDataModelValue) throws MmDataModelConverterException {
    String formatPattern = null;
    try {
      if (pDataModelValue == null) {
        return ATTRIBUTE_STRING_VIEW_NULL_VALUE;
      } else {
        formatPattern = getMmFormatPattern();
        if (LOGGER.isDebugEnabled()) {
          if (formatPattern == null) {
            throw new IllegalStateException("getMmFormatPattern() must return valid format pattern");
          }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        String            returnString  = pDataModelValue.format(dateFormatter);
        return returnString;
      }
    } catch (IllegalArgumentException | DateTimeParseException e) {
      throw new MmDataModelConverterException(this,
        "Cannot format " + getClass().getSimpleName() + ", data model value: " + pDataModelValue + " by pattern >" + formatPattern + "< "
        + e.getMessage());
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
  public LocalDate callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    LocalDate returnDate;
    String    formatPattern = null;
    if (isMmEmpty()) {
      returnDate = null;
    } else {
      try {
        formatPattern = getMmFormatPattern();
        if (LOGGER.isDebugEnabled()) {
          if (formatPattern == null) {
            throw new IllegalStateException("getMmFormatPattern() must return valid format pattern");
          }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        returnDate = LocalDate.parse(pViewModelValue, dateFormatter);
      } catch (DateTimeParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", view value: " + pViewModelValue + " by pattern >"
          + formatPattern + "< " + e.getMessage());
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
  public void callbackMmValidateDataModel(LocalDate pDataModelValue) throws MmValidatorException {
  }

}
