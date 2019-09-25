package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmInteger;
import org.minutenwerk.mimic4j.api.attribute.MmInteger.MmIntegerJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmInteger.MmIntegerJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmIntegerAnnotation;

/**
 * MmConfigurationInteger contains fixed configuration information for mimics of type {@link MmInteger}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationInteger extends MmBaseAttributeConfiguration<Integer> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                  DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Redundant to {@link MmIntegerAnnotation.jsfTag()}. */
  public static final MmIntegerJsfTag      DEFAULT_JSF_TAG           = MmIntegerJsfTag.TextField;

  /** Redundant to {@link MmIntegerAnnotation.jsfTagDisabled()}. */
  public static final MmIntegerJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmIntegerJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                            formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmIntegerJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmIntegerJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationInteger instance of default values.
   */
  public MmConfigurationInteger() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationInteger instance from annotation.
   *
   * @param  pIntegerAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationInteger(MmIntegerAnnotation pIntegerAnnotation) {
    super(pIntegerAnnotation.id(), pIntegerAnnotation.visible(), pIntegerAnnotation.readOnly(), pIntegerAnnotation.enabled(),
      pIntegerAnnotation.required());
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = pIntegerAnnotation.jsfTag();
    jsfTagDisabled  = pIntegerAnnotation.jsfTagDisabled();
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
  public void setJsfTag(MmIntegerJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
