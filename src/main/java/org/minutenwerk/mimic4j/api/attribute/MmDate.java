package org.minutenwerk.mimic4j.api.attribute;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.exception.MmViewsideConverterException;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeDeclaration;
import org.minutenwerk.mimic4j.impl.attribute.MmImplementationDate;
import org.minutenwerk.mimic4j.impl.attribute.MmSelectOption;

/**
 * MmDate is a mimic for an editable attribute of type {@link Date}.
 *
 * @author              Olaf Kossak
 * @see                 $HeadURL: $$maven.project.version$
 *
 * @jalopy.group-order  group-callback
 */
public class MmDate extends MmBaseAttributeDeclaration<MmImplementationDate, LocalDate, String> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/attribute/MmDate.java\$
   */
  public enum MmDateJsfDisabled {

    TextOutput,

    TextPlain,

    SameAsEnabled;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/attribute/MmDate.java\$
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
  public MmDate(MmDeclarationMimic pParent) {
    super(new MmImplementationDate(pParent));
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
   * @since         $maven.project.version$
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmConvertModelsideToViewsideValue(LocalDate pModelsideValue) throws MmModelsideConverterException {
    try {
      if (pModelsideValue == null) {
        return ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE;
      } else {
        String returnString = this.getMmDateTimeFormatter().print(pModelsideValue);
        return returnString;
      }
    } catch (Exception e) {
      throw new MmModelsideConverterException(this,
        "Cannot format " + this.getClass().getSimpleName() + " " + this.getMmId() + ", modelside value: " + pModelsideValue
        + " by pattern >" + this.getMmFormatPattern() + "<");
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
   * @since         $maven.project.version$
   *
   * @jalopy.group  group-callback
   */
  @Override public LocalDate callbackMmConvertViewsideToModelsideValue(String pViewsideValue) throws MmViewsideConverterException {
    LocalDate returnDate;
    if (this.isMmEmpty()) {
      returnDate = null;
    } else {
      try {
        DateTimeFormatter dateTimeFormatter = this.getMmDateTimeFormatter();
        returnDate = dateTimeFormatter.parseLocalDate(pViewsideValue);
      } catch (IllegalArgumentException e) {
        throw new MmViewsideConverterException(this,
          "Cannot format " + this.getClass().getSimpleName() + " " + this.getMmId() + ", viewside value: " + pViewsideValue
          + " by pattern >" + this.getMmFormatPattern() + "<");
      }
    }
    return returnDate;
  }

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's default value of type MODELSIDE_VALUE.
   *
   * @since         $maven.project.version$
   *
   * @jalopy.group  group-callback
   */
  @Override public LocalDate callbackMmGetDefaultValue(LocalDate pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return        A list of options.
   *
   * @since         $maven.project.version$
   *
   * @jalopy.group  group-callback
   */
  @Override public <OPTION_VALUE_TYPE> List<MmSelectOption<OPTION_VALUE_TYPE>> callbackMmGetSelectOptions() {
    return null;
  }

  /**
   * Semantic validation of modelside value of type MODELSIDE_VALUE. If validation succeeds:
   *
   * @param         pModelsideValue  The modelside value to be validated.
   *
   * @throws        MmValidatorException  In case of validation fails.
   *
   * @since         $maven.project.version$
   *
   * @jalopy.group  group-callback
   */
  @Override public void callbackMmValidateModelsideValue(LocalDate pModelsideValue) throws MmValidatorException {
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return  The initialized date formatter of this mimic.
   *
   * @since   $maven.project.version$
   */
  protected DateTimeFormatter getMmDateTimeFormatter() {
    final String formatPattern = this.getMmFormatPattern();
    assert formatPattern != null : "getMmFormatPattern() must return valid format pattern";

    final DateTimeFormatter returnDateFormatter = DateTimeFormat.forPattern(formatPattern);
    return returnDateFormatter;
  }

}
