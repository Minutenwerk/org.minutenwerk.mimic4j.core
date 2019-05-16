package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigDecimal;

import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal.MmBigDecimalJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal.MmBigDecimalJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimalAnnotation;

/**
 * MmConfigurationBigDecimal contains static configuration information for mimics of type {@link MmBigDecimal}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBigDecimal extends MmBaseAttributeConfiguration<BigDecimal> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                     DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmBigDecimalAnnotation.jsfTag()}. */
  public static final MmBigDecimalJsfTag      DEFAULT_JSF_TAG           = MmBigDecimalJsfTag.TextField;

  /** Constant for default value of JSF tag in disabled state. Redundant to {@link MmBigDecimalAnnotation.jsfTagDisabled()}. */
  public static final MmBigDecimalJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmBigDecimalJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                               formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmBigDecimalJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmBigDecimalJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationBigDecimal instance of default values.
   */
  public MmConfigurationBigDecimal() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationBigDecimal instance from annotation.
   *
   * @param  pBigDecimalAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBigDecimal(MmBigDecimalAnnotation pBigDecimalAnnotation) {
    super(pBigDecimalAnnotation.id(), pBigDecimalAnnotation.visible(), pBigDecimalAnnotation.readOnly(), pBigDecimalAnnotation.enabled(),
      pBigDecimalAnnotation.required());

    formatMaxLength = pBigDecimalAnnotation.formatMaxLength();
    jsfTag          = pBigDecimalAnnotation.jsfTag();
    jsfTagDisabled  = pBigDecimalAnnotation.jsfTagDisabled();
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
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return jsfTag.name();
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
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmBigDecimalJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
