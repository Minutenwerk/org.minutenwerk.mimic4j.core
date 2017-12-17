package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

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
public interface MmAttributeCallback<MODELSIDE_VALUE, VIEWSIDE_VALUE> extends MmBaseCallback {

  /**
   * Converts modelside value of type MODELSIDE_VALUE to value of type VIEWSIDE_VALUE.
   *
   * @param   pModelsideValue  The modelside value to be converted.
   *
   * @return  The converted value of type VIEWSIDE_VALUE.
   *
   * @throws  MmModelsideConverterException  In case of the conversion failed.
   */
  public VIEWSIDE_VALUE callbackMmConvertModelsideToViewsideValue(MODELSIDE_VALUE pModelsideValue) throws MmModelsideConverterException;

  /**
   * Converts viewside value of type VIEWSIDE_VALUE to value of type MODELSIDE_VALUE.
   *
   * @param   pViewsideValue  The viewside value to be converted.
   *
   * @return  The converted value of type MODELSIDE_VALUE.
   *
   * @throws  MmViewsideConverterException  In case of the conversion failed.
   */
  public MODELSIDE_VALUE callbackMmConvertViewsideToModelsideValue(VIEWSIDE_VALUE pViewsideValue) throws MmViewsideConverterException;

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's default value of type MODELSIDE_VALUE.
   */
  public MODELSIDE_VALUE callbackMmGetDefaultValue(MODELSIDE_VALUE pPassThroughValue);

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
   * Semantic validation of modelside value of type MODELSIDE_VALUE.
   *
   * @param   pModelsideValue  The modelside value to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  public void callbackMmValidateModelsideValue(MODELSIDE_VALUE pModelsideValue) throws MmValidatorException;

}
