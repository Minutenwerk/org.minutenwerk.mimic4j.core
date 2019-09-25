package org.minutenwerk.mimic4j.api.attribute;

import java.text.NumberFormat;
import java.text.ParseException;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationLong;

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
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmLong(final MmDeclarationMimic pParent) {
    super(new MmImplementationLong(pParent));
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
  public String callbackMmConvertDataModelToViewModel(Long pDataModelValue) throws MmDataModelConverterException {
    String returnString;
    if (pDataModelValue == null) {
      returnString = ATTRIBUTE_STRING_VIEW_NULL_VALUE;
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
   * Converts view value of type VIEW_TYPE to value of type DATA_MODEL.
   *
   * @param   pViewModelValue  The view value to be converted.
   *
   * @return  The converted value of type DATA_MODEL.
   *
   * @throws  MmViewModelConverterException  In case of the conversion failed.
   */
  @Override
  public Long callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    Long returnLong;
    if (isMmEmpty()) {
      returnLong = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        Number       parsedNumber    = numberFormatter.parse(pViewModelValue);
        returnLong = parsedNumber.longValue();
      } catch (ParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", view value: " + pViewModelValue + " by pattern >"
          + getMmFormatPattern() + "<");
      }
    }
    return returnLong;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(Long pDataModelValue) throws MmValidatorException {
  }

}
