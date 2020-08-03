package org.minutenwerk.mimic4j.impl.attribute;

import java.time.LocalDate;

import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;

/**
 * MmConfigurationLocalDate contains configuration information for mimics of type {@link MmLocalDate}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLocalDate extends MmBaseAttributeConfiguration<LocalDate> {

  /** Constant for default value of format pattern for parsing user input and formatting view value. */
  public static final String DEFAULT_FORMAT_PATTERN    = "dd.MM.yyyy";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int    DEFAULT_FORMAT_MAX_LENGTH = 10;

  /** Format pattern for parsing user input and formatting view value. */
  protected String           formatPattern;

  /** Maximum length of formatted input string. */
  protected int              formatMaxLength;

  /**
   * Creates a new MmConfigurationDate instance of default values.
   */
  public MmConfigurationLocalDate() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
    formatPattern   = DEFAULT_FORMAT_PATTERN;
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationDate instance from annotation.
   *
   * @param  pDateAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLocalDate(MmLocalDateAnnotation pDateAnnotation) {
    super(pDateAnnotation.id(), pDateAnnotation.visible(), pDateAnnotation.referenceEnabled(), pDateAnnotation.enabled(), pDateAnnotation.required(),
      pDateAnnotation.transientDataModel(), pDateAnnotation.styleClasses());
    formatPattern   = pDateAnnotation.formatPattern();
    formatMaxLength = pDateAnnotation.formatMaxLength();
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
