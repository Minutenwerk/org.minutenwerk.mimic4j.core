package org.minutenwerk.mimic4j.impl.attribute;

import java.math.BigInteger;

import org.minutenwerk.mimic4j.api.attribute.MmBigInteger;
import org.minutenwerk.mimic4j.api.attribute.MmBigInteger.MmBigIntegerJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBigInteger.MmBigIntegerJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmBigIntegerAnnotation;

/**
 * MmConfigurationBigInteger contains configuration information for mimics of type {@link MmBigInteger}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBigInteger extends MmBaseAttributeConfiguration<BigInteger> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                     DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Redundant to {@link MmBigIntegerAnnotation.jsfTag()}. */
  public static final MmBigIntegerJsfTag      DEFAULT_JSF_TAG           = MmBigIntegerJsfTag.TextField;

  /** Redundant to {@link MmBigIntegerAnnotation.jsfTagDisabled()}. */
  public static final MmBigIntegerJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmBigIntegerJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                               formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmBigIntegerJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmBigIntegerJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationBigInteger instance of default values.
   */
  public MmConfigurationBigInteger() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationBigInteger instance from annotation.
   *
   * @param  pBigIntegerAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBigInteger(MmBigIntegerAnnotation pBigIntegerAnnotation) {
    super(pBigIntegerAnnotation.id(), pBigIntegerAnnotation.visible(), pBigIntegerAnnotation.readOnly(), pBigIntegerAnnotation.enabled(),
      pBigIntegerAnnotation.required());

    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = pBigIntegerAnnotation.jsfTag();
    jsfTagDisabled  = pBigIntegerAnnotation.jsfTagDisabled();
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
  public void setJsfTag(MmBigIntegerJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
