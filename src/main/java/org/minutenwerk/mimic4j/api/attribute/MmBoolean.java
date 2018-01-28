package org.minutenwerk.mimic4j.api.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationBoolean;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmBoolean is a mimic for an editable attribute of type {@link Boolean}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-getter
 */
public class MmBoolean extends MmBaseAttributeDeclaration<MmImplementationBoolean, Boolean, Boolean> {

  /** Constant for value to be displayed in case of the viewside value is null. */
  public static final Boolean ATTRIBUTE_BOOLEAN_VIEWSIDE_NULL_VALUE = Boolean.FALSE;

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmBooleanJsfDisabled {

    IconOutput,

    TextOutput,

    TextField,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmBooleanJsfTag {

    SelectOneListbox,

    SelectOneMenu,

    SelectOneRadio,

    SelectOneCheckbox;
  }

  /**
   * Creates a new MmBoolean instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBoolean(MmDeclarationMimic pParent) {
    super(new MmImplementationBoolean(pParent));
  }

  /**
   * Converts modelside value of type MODELSIDE_VALUE to value of type VIEWSIDE_VALUE.
   *
   * @param         pModelsideValue  The modelside value to be converted.
   *
   * @return        The converted value of type VIEWSIDE_VALUE.
   *
   * @throws        MmModelsideConverterException  In case of the conversion failed.
   *
   * @jalopy.group  group-callback
   */
  @Override public Boolean callbackMmConvertModelsideToViewsideValue(Boolean pModelsideValue) throws MmModelsideConverterException {
    if (pModelsideValue == null) {
      return ATTRIBUTE_BOOLEAN_VIEWSIDE_NULL_VALUE;
    } else {
      return pModelsideValue;
    }
  }

  /**
   * Converts viewside value of type VIEWSIDE_VALUE to value of type MODELSIDE_VALUE.
   *
   * @param         pViewsideValue  The viewside value to be converted.
   *
   * @return        The converted value of type MODELSIDE_VALUE.
   *
   * @throws        MmViewsideConverterException  In case of the conversion failed.
   *
   * @jalopy.group  group-callback
   */
  @Override public Boolean callbackMmConvertViewsideToModelsideValue(Boolean pViewsideValue) throws MmViewsideConverterException {
    return pViewsideValue;
  }

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's default value of type MODELSIDE_VALUE.
   *
   * @jalopy.group  group-callback
   */
  @Override public Boolean callbackMmGetDefaultValue(Boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return        A list of options.
   *
   * @jalopy.group  group-callback
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
   * Semantic validation of modelside value of type MODELSIDE_VALUE. If validation succeeds:
   *
   * @param         pModelsideValue  The modelside value to be validated.
   *
   * @throws        MmValidatorException  In case of validation fails.
   *
   * @jalopy.group  group-callback
   */
  @Override public void callbackMmValidateModelsideValue(Boolean pModelsideValue) throws MmValidatorException {
  }

}
