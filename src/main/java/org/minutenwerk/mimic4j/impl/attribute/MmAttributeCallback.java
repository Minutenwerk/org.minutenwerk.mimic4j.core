package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmAttributeCallback defines a set of override-able methods common to all attribute mimics. Callback methods are part of the declaration
 * API of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration
 * part.
 *
 * @author  Olaf Kossak
 */
public interface MmAttributeCallback<ATTRIBUTE_MODEL, VIEWSIDE_VALUE> extends MmBaseCallback {

  /**
   * Converts modelside value of type ATTRIBUTE_MODEL to value of type VIEWSIDE_VALUE.
   *
   * @param   pModelsideValue  The modelside value to be converted.
   *
   * @return  The converted value of type VIEWSIDE_VALUE.
   *
   * @throws  MmModelsideConverterException  In case of the conversion failed.
   */
  public VIEWSIDE_VALUE callbackMmConvertModelsideToViewsideValue(ATTRIBUTE_MODEL pModelsideValue) throws MmModelsideConverterException;

  /**
   * Converts viewside value of type VIEWSIDE_VALUE to value of type ATTRIBUTE_MODEL.
   *
   * @param   pViewsideValue  The viewside value to be converted.
   *
   * @return  The converted value of type ATTRIBUTE_MODEL.
   *
   * @throws  MmViewsideConverterException  In case of the conversion failed.
   */
  public ATTRIBUTE_MODEL callbackMmConvertViewsideToModelsideValue(VIEWSIDE_VALUE pViewsideValue) throws MmViewsideConverterException;

  /**
   * Returns the attribute's accessor to corresponding model field. The attribute accessor can be derived from specified root component
   * accessor.
   *
   * @param   pRootAccessor  The specified root component accessor.
   *
   * @return  The attribute's accessor.
   */
  public MmAttributeAccessor<?, ATTRIBUTE_MODEL> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor);

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's format pattern for displaying viewside value.
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
   * Semantic validation of modelside value of type ATTRIBUTE_MODEL.
   *
   * @param   pModelsideValue  The modelside value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  public void callbackMmValidateModelsideValue(ATTRIBUTE_MODEL pModelsideValue) throws MmValidatorException;

}
