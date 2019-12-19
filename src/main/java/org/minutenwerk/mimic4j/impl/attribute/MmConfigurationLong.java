package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmLong;
import org.minutenwerk.mimic4j.api.attribute.MmLongAnnotation;

/**
 * MmConfigurationLong contains configuration information for mimics of type {@link MmLong}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLong extends MmBaseAttributeConfiguration<Long> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationLong instance of default values.
   */
  public MmConfigurationLong() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationLong instance from annotation.
   *
   * @param  pLongAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLong(MmLongAnnotation pLongAnnotation) {
    super(pLongAnnotation.id(), pLongAnnotation.visible(), pLongAnnotation.readOnly(), pLongAnnotation.enabled(), pLongAnnotation.required(),
      pLongAnnotation.styleClasses());
    formatMaxLength = pLongAnnotation.formatMaxLength();
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
