package org.minutenwerk.mimic4j.impl.attribute;

import java.time.LocalTime;

import org.minutenwerk.mimic4j.api.attribute.MmLocalTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTimeAnnotation;

/**
 * MmConfigurationLocalTime contains configuration information for mimics of type {@link MmLocalTime}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLocalTime extends MmBaseAttributeConfiguration<LocalTime> {

  /** Constant for default value of format pattern for parsing user input and formatting view value. */
  public static final String DEFAULT_FORMAT_PATTERN    = "HH:mm:ss";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int    DEFAULT_FORMAT_MAX_LENGTH = 8;

  /** Format pattern for parsing user input and formatting view value. */
  protected String           formatPattern;

  /** Maximum length of formatted input string. */
  protected int              formatMaxLength;

  /**
   * Creates a new MmConfigurationLocalTime instance of default values.
   */
  public MmConfigurationLocalTime() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatPattern   = DEFAULT_FORMAT_PATTERN;
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationLocalTime instance from annotation.
   *
   * @param  pTimeAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLocalTime(MmLocalTimeAnnotation pTimeAnnotation) {
    super(pTimeAnnotation.id(), pTimeAnnotation.visible(), pTimeAnnotation.readOnly(), pTimeAnnotation.enabled(), pTimeAnnotation.required(),
      pTimeAnnotation.styleClasses());
    formatPattern   = pTimeAnnotation.formatPattern();
    formatMaxLength = pTimeAnnotation.formatMaxLength();
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

}
