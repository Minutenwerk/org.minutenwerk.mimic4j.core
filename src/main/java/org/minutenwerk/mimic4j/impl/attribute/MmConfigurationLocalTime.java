package org.minutenwerk.mimic4j.impl.attribute;

import java.time.LocalTime;

import org.minutenwerk.mimic4j.api.attribute.MmLocalTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTime.MmTimeJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTime.MmTimeJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTimeAnnotation;

/**
 * MmConfigurationLocalTime contains static configuration information for mimics of type {@link MmLocalTime}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLocalTime extends MmBaseAttributeConfiguration<LocalTime> {

  /** Constant for default value of format pattern for parsing user input and formatting viewside value. */
  public static final String            DEFAULT_FORMAT_PATTERN    = "HH:mm:ss";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int               DEFAULT_FORMAT_MAX_LENGTH = 8;

  /** Redundant to {@link MmAnnotation.jsfTag()}. */
  public static final MmTimeJsfTag      DEFAULT_JSF_TAG           = MmTimeJsfTag.TextField;

  /** Redundant to {@link MmLocalTimeAnnotation.jsfTagDisabled()}. */
  public static final MmTimeJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmTimeJsfDisabled.SameAsEnabled;

  /** Format pattern for parsing user input and formatting viewside value. */
  protected String                      formatPattern;

  /** Maximum length of formatted input string. */
  protected int                         formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmTimeJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmTimeJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationLocalTime instance of default values.
   */
  public MmConfigurationLocalTime() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    formatPattern   = DEFAULT_FORMAT_PATTERN;
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationLocalTime instance from annotation.
   *
   * @param  pTimeAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLocalTime(MmLocalTimeAnnotation pTimeAnnotation) {
    super(pTimeAnnotation.id(), pTimeAnnotation.visible(), pTimeAnnotation.readOnly(), pTimeAnnotation.enabled(),
      pTimeAnnotation.required());
    formatPattern   = pTimeAnnotation.formatPattern();
    formatMaxLength = pTimeAnnotation.formatMaxLength();
    jsfTag          = pTimeAnnotation.jsfTag();
    jsfTagDisabled  = pTimeAnnotation.jsfTagDisabled();
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
   * Returns the configuration of format pattern for parsing user input and formatting viewside value.
   *
   * @return  The configuration of format pattern for parsing user input and formatting viewside value.
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
   * Sets the configuration of format pattern for parsing user input and formatting viewside value.
   *
   * @param  pFormatPattern  The specified configuration of format pattern for parsing user input and formatting viewside value.
   */
  public void setFormatPattern(String pFormatPattern) {
    formatPattern = pFormatPattern;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmTimeJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
