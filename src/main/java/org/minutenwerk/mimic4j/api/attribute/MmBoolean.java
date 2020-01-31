package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationBoolean;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmBoolean is a mimic for an editable attribute of type {@link Boolean}.
 *
 * @author  Olaf Kossak
 *
 *          <p>, group-getter</p>
 */
public class MmBoolean extends MmBaseAttributeDeclaration<MmImplementationBoolean, Boolean, Boolean> {

  /** Constant for value to be displayed in case of the view value is null. */
  public static final Boolean ATTRIBUTE_BOOLEAN_VIEW_TYPE_NULL_VALUE = Boolean.FALSE;

  /**
   * Creates a new MmBoolean instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmBoolean(final MmDeclarationMimic pParent) {
    super(new MmImplementationBoolean(pParent));
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
  public Boolean callbackMmConvertDataModelToViewModel(Boolean pDataModelValue) throws MmDataModelConverterException {
    if (pDataModelValue == null) {
      return ATTRIBUTE_BOOLEAN_VIEW_TYPE_NULL_VALUE;
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
  public Boolean callbackMmConvertViewModelToDataModel(Boolean pViewModelValue) throws MmViewModelConverterException {
    return pViewModelValue;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<MmSelectOption<Boolean>> callbackMmGetSelectOptions() {
    List<MmSelectOption<Boolean>> returnList = new ArrayList<>();

    MmSelectOption<Boolean>       nullOption = new MmSelectOption<>("UNDEFINED", "", "", null);
    returnList.add(nullOption);

    MmSelectOption<Boolean> trueOption = new MmSelectOption<>("True", "true", "true", Boolean.TRUE);
    returnList.add(trueOption);

    MmSelectOption<Boolean> falseOption = new MmSelectOption<>("False", "false", "false", Boolean.FALSE);
    returnList.add(falseOption);

    return returnList;
  }

  /**
   * Semantic validation of data model value of type DATA_MODEL. If validation succeeds:
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateDataModel(Boolean pDataModelValue) throws MmValidatorException {
  }

}
