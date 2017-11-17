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
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmConfigurationBigDecimal extends MmBaseAttributeConfiguration<BigDecimal> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                     DEFAULT_FORMAT_MAX_LENGTH       = 255;

  /** Constant for default value of default value. */
  public static final BigDecimal              DEFAULT_DEFAULT_VALUE           = BigDecimal.ZERO;

  /** Constant for default value of default value of type double. */
  public static final double                  DEFAULT_DEFAULT_VALUE_AS_DOUBLE = 0.0D;

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmBigDecimalAnnotation.jsfTag()}. */
  public static final MmBigDecimalJsfTag      DEFAULT_JSF_TAG                 = MmBigDecimalJsfTag.TextField;

  /** Constant for default value of JSF tag in disabled state. Redundant to {@link MmBigDecimalAnnotation.jsfTagDisabled()}. */
  public static final MmBigDecimalJsfDisabled DEFAULT_JSF_TAG_DISABLED        = MmBigDecimalJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                               formatMaxLength;

  /** The default value. */
  protected BigDecimal                        defaultValue;

  /** The JSF tag in enabled state. */
  protected MmBigDecimalJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmBigDecimalJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationBigDecimal instance of default values.
   */
  public MmConfigurationBigDecimal() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationBigDecimal instance from annotation.
   *
   * @param  pBigDecimalAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBigDecimal(MmBigDecimalAnnotation pBigDecimalAnnotation) {
    super(pBigDecimalAnnotation.id(), pBigDecimalAnnotation.visible(), pBigDecimalAnnotation.readOnly(), pBigDecimalAnnotation.enabled(),
      pBigDecimalAnnotation.required());

    this.formatMaxLength = pBigDecimalAnnotation.formatMaxLength();
    this.defaultValue    = BigDecimal.valueOf(pBigDecimalAnnotation.defaultValue());
    this.jsfTag          = pBigDecimalAnnotation.jsfTag();
    this.jsfTagDisabled  = pBigDecimalAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   *
   * @since   $maven.project.version$
   */
  @Override public BigDecimal getDefaultValue() {
    return this.defaultValue;
  }

  /**
   * Returns the configuration of maximum length of formatted input string.
   *
   * @return  The configuration of maximum length of formatted input string.
   *
   * @since   $maven.project.version$
   */
  public int getFormatMaxLength() {
    return this.formatMaxLength;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Sets the configuration of default value.
   *
   * @param  pDefaultValue  The specified configuration of default value.
   *
   * @since  $maven.project.version$
   */
  public void setDefaultValue(BigDecimal pDefaultValue) {
    this.defaultValue = pDefaultValue;
  }

  /**
   * Sets the configuration of maximum length of formatted input string.
   *
   * @param  pFormatMaxLength  The specified configuration of maximum length of formatted input string.
   *
   * @since  $maven.project.version$
   */
  public void setFormatMaxLength(int pFormatMaxLength) {
    this.formatMaxLength = pFormatMaxLength;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   *
   * @since  $maven.project.version$
   */
  public void setJsfTag(MmBigDecimalJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
