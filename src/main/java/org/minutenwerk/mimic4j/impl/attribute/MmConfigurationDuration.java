package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Duration;

import org.minutenwerk.mimic4j.api.attribute.MmDuration;
import org.minutenwerk.mimic4j.api.attribute.MmDurationAnnotation;

/**
 * MmConfigurationDuration contains configuration information for mimics of type {@link MmDuration}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationDuration extends MmBaseAttributeConfiguration<Duration> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationDuration instance of default values.
   */
  public MmConfigurationDuration() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationDuration instance from annotation.
   *
   * @param  pDurationAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationDuration(MmDurationAnnotation pDurationAnnotation) {
    super(pDurationAnnotation.id(), pDurationAnnotation.visible(), pDurationAnnotation.referenceEnabled(), pDurationAnnotation.enabled(),
      pDurationAnnotation.required(), pDurationAnnotation.transientDataModel(), pDurationAnnotation.styleClasses());

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
