package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Duration;

import org.minutenwerk.mimic4j.api.attribute.MmDuration;
import org.minutenwerk.mimic4j.api.attribute.MmDuration.MmDurationJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmDuration.MmDurationJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmDurationAnnotation;

/**
 * MmConfigurationDuration contains static configuration information for mimics of type {@link MmDuration}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationDuration extends MmBaseAttributeConfiguration<Duration> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                   DEFAULT_FORMAT_MAX_LENGTH     = 255;

  /** Constant for default value of default value. */
  public static final Duration              DEFAULT_DEFAULT_VALUE         = Duration.ZERO;

  /** Constant for default value of default value of type long. */
  public static final long                  DEFAULT_DEFAULT_VALUE_AS_LONG = 0L;

  /** Redundant to {@link MmDurationAnnotation.jsfTag()}. */
  public static final MmDurationJsfTag      DEFAULT_JSF_TAG               = MmDurationJsfTag.TextField;

  /** Redundant to {@link MmDurationAnnotation.jsfTagDisabled()}. */
  public static final MmDurationJsfDisabled DEFAULT_JSF_TAG_DISABLED      = MmDurationJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                             formatMaxLength;

  /** The default value. */
  protected Duration                        defaultValue;

  /** The JSF tag in enabled state. */
  protected MmDurationJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmDurationJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationDuration instance of default values.
   */
  public MmConfigurationDuration() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDuration instance from annotation.
   *
   * @param  pDurationAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationDuration(MmDurationAnnotation pDurationAnnotation) {
    super(pDurationAnnotation.id(), pDurationAnnotation.visible(), pDurationAnnotation.readOnly(), pDurationAnnotation.enabled(),
      pDurationAnnotation.required());

    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = Duration.ofMillis(pDurationAnnotation.defaultValue());
    this.jsfTag          = pDurationAnnotation.jsfTag();
    this.jsfTagDisabled  = pDurationAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public Duration getDefaultValue() {
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
  public void setDefaultValue(Duration pDefaultValue) {
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
  public void setJsfTag(MmDurationJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
