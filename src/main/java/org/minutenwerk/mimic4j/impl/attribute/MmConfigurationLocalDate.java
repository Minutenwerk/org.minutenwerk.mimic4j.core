package org.minutenwerk.mimic4j.impl.attribute;

import java.time.LocalDate;

import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate.MmDateJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate.MmDateJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;

/**
 * MmConfigurationLocalDate contains static configuration information for mimics of type {@link MmLocalDate}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLocalDate extends MmBaseAttributeConfiguration<LocalDate> {

  /** Constant for default value of format pattern for parsing user input and formatting view value. */
  public static final String            DEFAULT_FORMAT_PATTERN    = "dd.MM.yyyy";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int               DEFAULT_FORMAT_MAX_LENGTH = 10;

  /** Redundant to {@link MmDateAnnotation.jsfTag()}. */
  public static final MmDateJsfTag      DEFAULT_JSF_TAG           = MmDateJsfTag.TextField;

  /** Redundant to {@link MmDateAnnotation.jsfTagDisabled()}. */
  public static final MmDateJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmDateJsfDisabled.SameAsEnabled;

  /** Format pattern for parsing user input and formatting view value. */
  protected String                      formatPattern;

  /** Maximum length of formatted input string. */
  protected int                         formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmDateJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmDateJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationDate instance of default values.
   */
  public MmConfigurationLocalDate() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    formatPattern   = DEFAULT_FORMAT_PATTERN;
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDate instance from annotation.
   *
   * @param  pDateAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLocalDate(MmLocalDateAnnotation pDateAnnotation) {
    super(pDateAnnotation.id(), pDateAnnotation.visible(), pDateAnnotation.readOnly(), pDateAnnotation.enabled(),
      pDateAnnotation.required());
    formatPattern   = pDateAnnotation.formatPattern();
    formatMaxLength = pDateAnnotation.formatMaxLength();
    jsfTag          = pDateAnnotation.jsfTag();
    jsfTagDisabled  = pDateAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of maximum length of formatted input string.
   *
   * @return  The configuration of maximum length of formatted input string.
   */
  public int getFormatMaxLength() {
    return formatMaxLength;
  }

  /**
   * Returns the configuration of format pattern for parsing user input and formatting view value.
   *
   * @return  The configuration of format pattern for parsing user input and formatting view value.
   */
  public String getFormatPattern() {
    return formatPattern;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return jsfTag.name();
  }

  /**
   * Sets the configuration of maximum length of formatted input string.
   *
   * @param  pFormatMaxLength  The specified configuration of maximum length of formatted input string.
   */
  public void setFormatMaxLength(int pFormatMaxLength) {
    formatMaxLength = pFormatMaxLength;
  }

  /**
   * Sets the configuration of format pattern for parsing user input and formatting view value.
   *
   * @param  pFormatPattern  The specified configuration of format pattern for parsing user input and formatting view value.
   */
  public void setFormatPattern(String pFormatPattern) {
    formatPattern = pFormatPattern;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmDateJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
