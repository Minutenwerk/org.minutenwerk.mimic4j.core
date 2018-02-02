package org.minutenwerk.mimic4j.impl.attribute;

import java.time.ZonedDateTime;

import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime.MmDateTimeJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime.MmDateTimeJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTimeAnnotation;

/**
 * MmConfigurationZonedDateTime contains static configuration information for mimics of type {@link MmZonedDateTime}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationZonedDateTime extends MmBaseAttributeConfiguration<ZonedDateTime> {

  /** Constant for default value of format pattern for parsing user input and formatting viewside value. */
  public static final String                DEFAULT_FORMAT_PATTERN    = "dd.MM.yyyy HH:mm:ss";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                   DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of default value. */
  public static final ZonedDateTime         DEFAULT_DEFAULT_VALUE     = null;

  /** Redundant to {@link MmDateTimeAnnotation.jsfTag()}. */
  public static final MmDateTimeJsfTag      DEFAULT_JSF_TAG           = MmDateTimeJsfTag.TextField;

  /** Redundant to {@link MmDateTimeAnnotation.jsfTagDisabled()}. */
  public static final MmDateTimeJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmDateTimeJsfDisabled.SameAsEnabled;

  /** Format pattern for parsing user input and formatting viewside value. */
  protected String                          formatPattern;

  /** The default value. */
  protected ZonedDateTime                   defaultValue;

  /** Maximum length of formatted input string. */
  protected int                             formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmDateTimeJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmDateTimeJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationDateTime instance of default values.
   */
  public MmConfigurationZonedDateTime() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatPattern   = DEFAULT_FORMAT_PATTERN;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDateTime instance from annotation.
   *
   * @param  pDateTimeAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationZonedDateTime(MmZonedDateTimeAnnotation pDateTimeAnnotation) {
    super(pDateTimeAnnotation.id(), pDateTimeAnnotation.visible(), pDateTimeAnnotation.readOnly(), pDateTimeAnnotation.enabled(),
      pDateTimeAnnotation.required());
    this.formatPattern   = pDateTimeAnnotation.formatPattern();

    // there is no default value for date in annotation
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = pDateTimeAnnotation.formatMaxLength();
    this.jsfTag          = pDateTimeAnnotation.jsfTag();
    this.jsfTagDisabled  = pDateTimeAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public ZonedDateTime getDefaultValue() {
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
  public void setDefaultValue(ZonedDateTime pDefaultValue) {
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
  public void setJsfTag(MmDateTimeJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
