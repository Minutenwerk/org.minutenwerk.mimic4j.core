package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigInteger;

import org.minutenwerk.mimic4j.api.attribute.MmBigInteger;
import org.minutenwerk.mimic4j.api.attribute.MmBigInteger.MmBigIntegerJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBigInteger.MmBigIntegerJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmBigIntegerAnnotation;

/**
 * MmConfigurationBigInteger contains static configuration information for mimics of type {@link MmBigInteger}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBigInteger extends MmBaseAttributeConfiguration<BigInteger> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                     DEFAULT_FORMAT_MAX_LENGTH     = 255;

  /** Constant for default value of default value. */
  public static final BigInteger              DEFAULT_DEFAULT_VALUE         = BigInteger.ZERO;

  /** Constant for default value of default value of type long. */
  public static final long                    DEFAULT_DEFAULT_VALUE_AS_LONG = 0L;

  /** Redundant to {@link MmBigIntegerAnnotation.jsfTag()}. */
  public static final MmBigIntegerJsfTag      DEFAULT_JSF_TAG               = MmBigIntegerJsfTag.TextField;

  /** Redundant to {@link MmBigIntegerAnnotation.jsfTagDisabled()}. */
  public static final MmBigIntegerJsfDisabled DEFAULT_JSF_TAG_DISABLED      = MmBigIntegerJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                               formatMaxLength;

  /** The default value. */
  protected BigInteger                        defaultValue;

  /** The JSF tag in enabled state. */
  protected MmBigIntegerJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmBigIntegerJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationBigInteger instance of default values.
   */
  public MmConfigurationBigInteger() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationBigInteger instance from annotation.
   *
   * @param  pBigIntegerAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBigInteger(MmBigIntegerAnnotation pBigIntegerAnnotation) {
    super(pBigIntegerAnnotation.id(), pBigIntegerAnnotation.visible(), pBigIntegerAnnotation.readOnly(), pBigIntegerAnnotation.enabled(),
      pBigIntegerAnnotation.required());

    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = BigInteger.valueOf(pBigIntegerAnnotation.defaultValue());
    this.jsfTag          = pBigIntegerAnnotation.jsfTag();
    this.jsfTagDisabled  = pBigIntegerAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public BigInteger getDefaultValue() {
    return this.defaultValue;
  }

  /**
   * Returns the configuration of maximum length of formatted input string.
   *
   * @return  The configuration of maximum length of formatted input string.
   */
  public int getFormatMaxLength() {
    return this.formatMaxLength;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Sets the configuration of default value.
   *
   * @param  pDefaultValue  The specified configuration of default value.
   */
  public void setDefaultValue(BigInteger pDefaultValue) {
    this.defaultValue = pDefaultValue;
  }

  /**
   * Sets the configuration of maximum length of formatted input string.
   *
   * @param  pFormatMaxLength  The specified configuration of maximum length of formatted input string.
   */
  public void setFormatMaxLength(int pFormatMaxLength) {
    this.formatMaxLength = pFormatMaxLength;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmBigIntegerJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
