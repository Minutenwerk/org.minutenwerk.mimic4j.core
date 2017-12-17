package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmLong;
import org.minutenwerk.mimic4j.api.attribute.MmLong.MmLongJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLong.MmLongJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmLongAnnotation;

/**
 * MmConfigurationLong contains static configuration information for mimics of type {@link MmLong}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLong extends MmBaseAttributeConfiguration<Long> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int               DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of default value. */
  public static final long              DEFAULT_DEFAULT_VALUE     = 0L;

  /** Redundant to {@link MmLongAnnotation.jsfTag()}. */
  public static final MmLongJsfTag      DEFAULT_JSF_TAG           = MmLongJsfTag.TextField;

  /** Redundant to {@link MmLongAnnotation.jsfTagDisabled()}. */
  public static final MmLongJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmLongJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                         formatMaxLength;

  /** The default value. */
  protected Long                        defaultValue;

  /** The JSF tag in enabled state. */
  protected MmLongJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmLongJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationLong instance of default values.
   */
  public MmConfigurationLong() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationLong instance from annotation.
   *
   * @param  pLongAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLong(MmLongAnnotation pLongAnnotation) {
    super(pLongAnnotation.id(), pLongAnnotation.visible(), pLongAnnotation.readOnly(), pLongAnnotation.enabled(),
      pLongAnnotation.required());
    this.formatMaxLength = pLongAnnotation.formatMaxLength();
    this.defaultValue    = pLongAnnotation.defaultValue();
    this.jsfTag          = pLongAnnotation.jsfTag();
    this.jsfTagDisabled  = pLongAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public Long getDefaultValue() {
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
  public void setDefaultValue(Long pDefaultValue) {
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
  public void setJsfTag(MmLongJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
