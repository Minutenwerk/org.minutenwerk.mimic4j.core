package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Instant;

import org.minutenwerk.mimic4j.api.attribute.MmInstant;
import org.minutenwerk.mimic4j.api.attribute.MmInstant.MmInstantJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmInstant.MmInstantJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmInstantAnnotation;

/**
 * MmConfigurationInstant contains static configuration information for mimics of type {@link MmInstant}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationInstant extends MmBaseAttributeConfiguration<Instant> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                  DEFAULT_FORMAT_MAX_LENGTH     = 255;

  /** Constant for default value of default value. */
  public static final Instant              DEFAULT_DEFAULT_VALUE         = null;

  /** Constant for default value of default value of type long. */
  public static final long                 DEFAULT_DEFAULT_VALUE_AS_LONG = 0L;

  /** Redundant to {@link MmInstantAnnotation.jsfTag()}. */
  public static final MmInstantJsfTag      DEFAULT_JSF_TAG               = MmInstantJsfTag.TextField;

  /** Redundant to {@link MmInstantAnnotation.jsfTagDisabled()}. */
  public static final MmInstantJsfDisabled DEFAULT_JSF_TAG_DISABLED      = MmInstantJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                            formatMaxLength;

  /** The default value. */
  protected Instant                        defaultValue;

  /** The JSF tag in enabled state. */
  protected MmInstantJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmInstantJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationInstant instance of default values.
   */
  public MmConfigurationInstant() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationInstant instance from annotation.
   *
   * @param  pInstantAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationInstant(MmInstantAnnotation pInstantAnnotation) {
    super(pInstantAnnotation.id(), pInstantAnnotation.visible(), pInstantAnnotation.readOnly(), pInstantAnnotation.enabled(),
      pInstantAnnotation.required());

    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = Instant.ofEpochMilli(pInstantAnnotation.defaultValue());
    this.jsfTag          = pInstantAnnotation.jsfTag();
    this.jsfTagDisabled  = pInstantAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public Instant getDefaultValue() {
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
  public void setDefaultValue(Instant pDefaultValue) {
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
  public void setJsfTag(MmInstantJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
