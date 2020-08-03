package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmFloat;
import org.minutenwerk.mimic4j.api.attribute.MmFloatAnnotation;

/**
 * MmConfigurationFloat contains configuration information for mimics of type {@link MmFloat}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationFloat extends MmBaseAttributeConfiguration<Float> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationFloat instance of default values.
   */
  public MmConfigurationFloat() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationFloat instance from annotation.
   *
   * @param  pFloatAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationFloat(MmFloatAnnotation pFloatAnnotation) {
    super(pFloatAnnotation.id(), pFloatAnnotation.visible(), pFloatAnnotation.referenceEnabled(), pFloatAnnotation.enabled(), pFloatAnnotation.required(),
      pFloatAnnotation.transientDataModel(), pFloatAnnotation.styleClasses());

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
