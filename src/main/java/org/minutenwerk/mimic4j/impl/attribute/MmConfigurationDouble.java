package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmDouble;
import org.minutenwerk.mimic4j.api.attribute.MmDoubleAnnotation;

/**
 * MmConfigurationDouble contains configuration information for mimics of type {@link MmDouble}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationDouble extends MmBaseAttributeConfiguration<Double> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationDouble instance of default values.
   */
  public MmConfigurationDouble() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationDouble instance from annotation.
   *
   * @param  pDoubleAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationDouble(MmDoubleAnnotation pDoubleAnnotation) {
    super(pDoubleAnnotation.id(), pDoubleAnnotation.visible(), pDoubleAnnotation.readOnly(), pDoubleAnnotation.enabled(), pDoubleAnnotation.required(),
      pDoubleAnnotation.styleClasses());

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
