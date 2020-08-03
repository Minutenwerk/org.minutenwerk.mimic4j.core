package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmInteger;
import org.minutenwerk.mimic4j.api.attribute.MmIntegerAnnotation;

/**
 * MmConfigurationInteger contains configuration information for mimics of type {@link MmInteger}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationInteger extends MmBaseAttributeConfiguration<Integer> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /**
   * Creates a new MmConfigurationInteger instance of default values.
   */
  public MmConfigurationInteger() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
  }

  /**
   * Creates a new MmConfigurationInteger instance from annotation.
   *
   * @param  pIntegerAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationInteger(MmIntegerAnnotation pIntegerAnnotation) {
    super(pIntegerAnnotation.id(), pIntegerAnnotation.visible(), pIntegerAnnotation.referenceEnabled(), pIntegerAnnotation.enabled(),
      pIntegerAnnotation.required(), pIntegerAnnotation.transientDataModel(), pIntegerAnnotation.styleClasses());
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
