package org.minutenwerk.mimic4j.api.attribute;

import java.text.NumberFormat;
import java.text.ParseException;

import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationFloat;

/**
 * MmFloat is a mimic for an editable attribute of type {@link Float}.
 *
 * @author  Olaf Kossak
 */
public class MmFloat extends MmBaseAttributeDeclaration<MmImplementationFloat, Float, String> {

  /**
   * Creates a new MmFloat instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmFloat(final MmDeclarationMimic pParent) {
    super(new MmImplementationFloat(pParent));
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
  public String callbackMmConvertDataModelToViewModel(Float pDataModelValue) throws MmDataModelConverterException {
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
  public Float callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    Float returnFloat;
    if (isMmEmpty()) {
      returnFloat = null;
    } else {
      try {
        NumberFormat numberFormatter = getMmNumberFormatter();
        Number       parsedNumber    = numberFormatter.parse(pViewModelValue);
        returnFloat = parsedNumber.floatValue();
      } catch (ParseException e) {
        throw new MmViewModelConverterException(this,
          "Cannot format " + this + ", view value: " + pViewModelValue + " by pattern >" + getMmFormatPattern() + "<");
      }
    }
    return returnFloat;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(Float pDataModelValue) throws MmValidatorException {
  }

}
