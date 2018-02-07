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

  /** Constant for default value of format pattern for parsing user input and formatting viewside value. */
  public static final String            DEFAULT_FORMAT_PATTERN    = "dd.MM.yyyy";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int               DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of default value. */
  public static final LocalDate         DEFAULT_DEFAULT_VALUE     = null;

  /** Redundant to {@link MmDateAnnotation.jsfTag()}. */
  public static final MmDateJsfTag      DEFAULT_JSF_TAG           = MmDateJsfTag.TextField;

  /** Redundant to {@link MmDateAnnotation.jsfTagDisabled()}. */
  public static final MmDateJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmDateJsfDisabled.SameAsEnabled;

  /** Format pattern for parsing user input and formatting viewside value. */
  protected String                      formatPattern;

  /** The default value. */
  protected LocalDate                   defaultValue;

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
    this.formatPattern   = DEFAULT_FORMAT_PATTERN;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDate instance from annotation.
   *
   * @param  pDateAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLocalDate(MmLocalDateAnnotation pDateAnnotation) {
    super(pDateAnnotation.id(), pDateAnnotation.visible(), pDateAnnotation.readOnly(), pDateAnnotation.enabled(),
      pDateAnnotation.required());
    this.formatPattern   = pDateAnnotation.formatPattern();

    // there is no default value for date in annotation
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = pDateAnnotation.formatMaxLength();
    this.jsfTag          = pDateAnnotation.jsfTag();
    this.jsfTagDisabled  = pDateAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public LocalDate getDefaultValue() {
    return this.defaultValue;
  }

  /**
   * Returns the configuration of maximum length of formatted input string.
   *
   * @return  The configuration of maximum length of formatted input string.
   */
  public int getFormatMaxLength() {
    return this.formatMaxLength;
  }

  /**
   * Returns the configuration of format pattern for parsing user input and formatting viewside value.
   *
   * @return  The configuration of format pattern for parsing user input and formatting viewside value.
   */
  public String getFormatPattern() {
    return this.formatPattern;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Sets the configuration of default value.
   *
   * @param  pDefaultValue  The specified configuration of default value.
   */
  public void setDefaultValue(LocalDate pDefaultValue) {
    this.defaultValue = pDefaultValue;
  }

  /**
   * Sets the configuration of maximum length of formatted input string.
   *
   * @param  pFormatMaxLength  The specified configuration of maximum length of formatted input string.
   */
  public void setFormatMaxLength(int pFormatMaxLength) {
    this.formatMaxLength = pFormatMaxLength;
  }

  /**
   * Sets the configuration of format pattern for parsing user input and formatting viewside value.
   *
   * @param  pFormatPattern  The specified configuration of format pattern for parsing user input and formatting viewside value.
   */
  public void setFormatPattern(String pFormatPattern) {
    this.formatPattern = pFormatPattern;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmDateJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
