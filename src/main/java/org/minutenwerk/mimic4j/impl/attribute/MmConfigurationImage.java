package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmImage;
import org.minutenwerk.mimic4j.api.attribute.MmImageAnnotation;

/**
 * MmConfigurationImage contains configuration information for mimics of type {@link MmImage}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationImage extends MmBaseAttributeConfiguration<String> {

  /** Constant for default value of maximum length of formatted input String. */
  public static final int    DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of fixed image src. */
  public static final String DEFAULT_FIXED_SRC         = "";

  /** Maximum length of formatted input String. */
  protected int              formatMaxLength;

  /** Fixed value of image src. */
  protected String           fixedSrc;

  /**
   * Creates a new MmConfigurationImage instance of default values.
   */
  public MmConfigurationImage() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    fixedSrc        = DEFAULT_FIXED_SRC;
  }

  /**
   * Creates a new MmConfigurationImage instance from annotation.
   *
   * @param  pImageAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationImage(MmImageAnnotation pImageAnnotation) {
    super(pImageAnnotation.id(), pImageAnnotation.visible(), pImageAnnotation.referenceEnabled(), pImageAnnotation.enabled(), pImageAnnotation.required(),
      pImageAnnotation.styleClasses());
    formatMaxLength = pImageAnnotation.formatMaxLength();
    fixedSrc        = pImageAnnotation.fixedSrc();
  }

  /**
   * Returns the configuration of fixed value of image src.
   *
   * @return  The configuration of fixed value of image src.
   */
  public String getFixedSrc() {
    return fixedSrc;
  }

  /**
   * Returns the configuration of maximum length of formatted input String.
   *
   * @return  The configuration of maximum length of formatted input String.
   */
  public int getFormatMaxLength() {
    return formatMaxLength;
  }

}
