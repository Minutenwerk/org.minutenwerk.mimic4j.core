package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewModelConverterException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmAttributeCallback defines a set of override-able methods common to all attribute mimics. Callback methods are part of the declaration
 * API of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration
 * part.
 *
 * @author  Olaf Kossak
 */
public interface MmAttributeCallback<ATTRIBUTE_TYPE, VIEW_TYPE> extends MmBaseCallback {

  /**
   * Converts data model value of type ATTRIBUTE_TYPE to value of type VIEW_TYPE.
   *
   * @param   pDataModelValue  The data model value to be converted.
   *
   * @return  The converted value of type VIEW_TYPE.
   *
   * @throws  MmDataModelConverterException  In case of the conversion failed.
   */
  public VIEW_TYPE callbackMmConvertDataModelToViewModel(ATTRIBUTE_TYPE pDataModelValue) throws MmDataModelConverterException;

  /**
   * Converts view value of type VIEW_TYPE to value of type ATTRIBUTE_TYPE.
   *
   * @param   pViewModelValue  The view value to be converted.
   *
   * @return  The converted value of type ATTRIBUTE_TYPE.
   *
   * @throws  MmViewModelConverterException  In case of the conversion failed.
   */
  public ATTRIBUTE_TYPE callbackMmConvertViewModelToDataModel(VIEW_TYPE pViewModelValue) throws MmViewModelConverterException;

  /**
   * Returns the attribute's format pattern for displaying view value in view. It is used during conversion from data model to view model
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's format pattern for displaying view value.
   */
  public String callbackMmGetFormatPattern(String pPassThroughValue);

  /**
   * Returns the configuration of maximum length of formatted input string.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The configuration of maximum length of formatted input string.
   */
  public int callbackMmGetMaxLength(int pPassThroughValue);

  /**
   * Returns the attribute's accessor to corresponding model. The attribute accessor can be derived from specified parent component
   * accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The attribute's accessor.
   */
  public MmAttributeAccessor<?, ATTRIBUTE_TYPE> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor);

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return  A list of options.
   */
  public <OPTION_VALUE_TYPE> List<MmSelectOption<OPTION_VALUE_TYPE>> callbackMmGetSelectOptions();

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  <code>True</code> if a value from view has to be set.
   */
  public boolean callbackMmIsRequired(boolean pPassThroughValue);

  /**
   * Semantic validation of data model value of type ATTRIBUTE_TYPE.
   *
   * @param   pDataModelValue  The data model value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  public void callbackMmValidateDataModel(ATTRIBUTE_TYPE pDataModelValue) throws MmValidatorException;

}
