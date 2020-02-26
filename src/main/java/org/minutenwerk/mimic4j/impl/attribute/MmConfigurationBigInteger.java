package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigInteger;

import org.minutenwerk.mimic4j.api.attribute.MmBigInteger;
import org.minutenwerk.mimic4j.api.attribute.MmBigIntegerAnnotation;

/**
 * MmConfigurationBigInteger contains configuration information for mimics of type {@link MmBigInteger}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBigInteger extends MmBaseAttributeConfiguration<BigInteger> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationBigInteger instance of default values.
   */
  public MmConfigurationBigInteger() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationBigInteger instance from annotation.
   *
   * @param  pBigIntegerAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBigInteger(MmBigIntegerAnnotation pBigIntegerAnnotation) {
    super(pBigIntegerAnnotation.id(), pBigIntegerAnnotation.visible(), pBigIntegerAnnotation.referenceEnabled(), pBigIntegerAnnotation.enabled(),
      pBigIntegerAnnotation.required(), pBigIntegerAnnotation.styleClasses());

    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
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
