package org.minutenwerk.mimic4j.api.attribute;

import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationImage;

/**
 * MmImage is a mimic for an editable attribute of type {@link String}.
 *
 * @author  Olaf Kossak
 */
public class MmImage extends MmBaseAttributeDeclaration<MmImplementationImage, String, String> {

  /**
   * Type of image.
   */
  public enum MmImageType {
    PNG_FILE,
    SVG_FILE,
    SVG_INLINE
  }

  /**
   * Creates a new MmImage instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImage(MmDeclarationMimic pParent) {
    super(new MmImplementationImage(pParent));
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
  public String callbackMmConvertDataModelToViewModel(String pDataModelValue) throws MmDataModelConverterException {
    if (pDataModelValue == null) {
      return ATTRIBUTE_STRING_VIEW_NULL_VALUE;
    } else {
      return pDataModelValue;
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
  public String callbackMmConvertViewModelToDataModel(String pViewModelValue) throws MmViewModelConverterException {
    if (isMmEmpty()) {
      return null;
    } else {
      return pViewModelValue;
    }
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(String pDataModelValue) throws MmValidatorException {
  }

  /**
   * Returns type of image.
   *
   * @return  type of image.
   */
  public MmImage.MmImageType getMmImageType() {
    return implementation.getMmImageType();
  }

  /**
   * Returns source URI of image as String.
   *
   * @return  source URI of image as String.
   */
  public String getMmSrc() {
    return implementation.getMmSrc();
  }

}
