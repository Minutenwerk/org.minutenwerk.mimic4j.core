package org.minutenwerk.mimic4j.api.attribute;

import java.text.NumberFormat;
import java.text.ParseException;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationDouble;

/**
 * MmDouble is a mimic for an editable attribute of type {@link Double}.
 *
 * @author  Olaf Kossak
 */
public class MmDouble extends MmBaseAttributeDeclaration<MmImplementationDouble, Double, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDoubleJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmDoubleJsfTag {

    TextField,

    TextArea,

    TextSecret,

    TextHidden,

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio;
  }

  /**
   * Creates a new MmDouble instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmDouble(final MmDeclarationMimic pParent) {
    super(new MmImplementationDouble(pParent));
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
  public String callbackMmConvertDataModelToViewModel(Double pDataModelValue) throws MmDataModelConverterException {
    String returnString;
    if (pDataModelValue == null) {
      returnString = ATTRIBUTE_STRING_VIEW_NULL_VALUE;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        returnString = numberFormatter.format(pDataModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format data model value: " + pDataModelValue + " by pattern >" + getMmFormatPattern() + "<");
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
  public Double callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    Double returnDouble;
    if (isMmEmpty()) {
      returnDouble = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        Number       parsedNumber    = numberFormatter.parse(pViewModelValue);
        returnDouble = parsedNumber.doubleValue();
      } catch (ParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + this + ", view value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
      }
    }
    return returnDouble;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(Double pDataModelValue) throws MmValidatorException {
  }

}
