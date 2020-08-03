package org.minutenwerk.mimic4j.impl.attribute;

import java.time.ZonedDateTime;

import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTimeAnnotation;

/**
 * MmConfigurationZonedDateTime contains configuration information for mimics of type {@link MmZonedDateTime}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationZonedDateTime extends MmBaseAttributeConfiguration<ZonedDateTime> {

  /** Constant for default value of format pattern for parsing user input and formatting view value. */
  public static final String DEFAULT_FORMAT_PATTERN    = "dd.MM.yyyy HH:mm:ss";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int    DEFAULT_FORMAT_MAX_LENGTH = 19;

  /** Format pattern for parsing user input and formatting view value. */
  protected String           formatPattern;

  /** Maximum length of formatted input string. */
  protected int              formatMaxLength;

  /**
   * Creates a new MmConfigurationDateTime instance of default values.
   */
  public MmConfigurationZonedDateTime() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
    formatPattern   = DEFAULT_FORMAT_PATTERN;
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationDateTime instance from annotation.
   *
   * @param  pDateTimeAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationZonedDateTime(MmZonedDateTimeAnnotation pDateTimeAnnotation) {
    super(pDateTimeAnnotation.id(), pDateTimeAnnotation.visible(), pDateTimeAnnotation.referenceEnabled(), pDateTimeAnnotation.enabled(),
      pDateTimeAnnotation.required(), pDateTimeAnnotation.transientDataModel(), pDateTimeAnnotation.styleClasses());
    formatPattern   = pDateTimeAnnotation.formatPattern();
    formatMaxLength = pDateTimeAnnotation.formatMaxLength();
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
