package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigDecimal;

import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimalAnnotation;

/**
 * MmConfigurationBigDecimal contains configuration information for mimics of type {@link MmBigDecimal}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBigDecimal extends MmBaseAttributeConfiguration<BigDecimal> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationBigDecimal instance of default values.
   */
  public MmConfigurationBigDecimal() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationBigDecimal instance from annotation.
   *
   * @param  pBigDecimalAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBigDecimal(MmBigDecimalAnnotation pBigDecimalAnnotation) {
    super(pBigDecimalAnnotation.id(), pBigDecimalAnnotation.visible(), pBigDecimalAnnotation.referenceEnabled(), pBigDecimalAnnotation.enabled(),
      pBigDecimalAnnotation.required(), pBigDecimalAnnotation.styleClasses());

    formatMaxLength = pBigDecimalAnnotation.formatMaxLength();
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
   * Sets the configuration of maximum length of formatted input string.
   *
   * @param  pFormatMaxLength  The specified configuration of maximum length of formatted input string.
   */
  public void setFormatMaxLength(int pFormatMaxLength) {
    formatMaxLength = pFormatMaxLength;
  }

}
