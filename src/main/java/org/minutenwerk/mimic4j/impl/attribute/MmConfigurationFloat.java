package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmFloat;
import org.minutenwerk.mimic4j.api.attribute.MmFloat.MmFloatJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmFloat.MmFloatJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmFloatAnnotation;

/**
 * MmConfigurationFloat contains static configuration information for mimics of type {@link MmFloat}.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmConfigurationFloat extends MmBaseAttributeConfiguration<Float> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of default value. */
  public static final float              DEFAULT_DEFAULT_VALUE     = 0F;

  /** Redundant to {@link MmFloatAnnotation.jsfTag()}. */
  public static final MmFloatJsfTag      DEFAULT_JSF_TAG           = MmFloatJsfTag.TextField;

  /** Redundant to {@link MmFloatAnnotation.jsfTagDisabled()}. */
  public static final MmFloatJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmFloatJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                          formatMaxLength;

  /** The default value. */
  protected Float                        defaultValue;

  /** The JSF tag in enabled state. */
  protected MmFloatJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmFloatJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationFloat instance of default values.
   */
  public MmConfigurationFloat() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationFloat instance from annotation.
   *
   * @param  pFloatAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationFloat(MmFloatAnnotation pFloatAnnotation) {
    super(pFloatAnnotation.id(), pFloatAnnotation.visible(), pFloatAnnotation.readOnly(), pFloatAnnotation.enabled(),
      pFloatAnnotation.required());

    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.defaultValue    = pFloatAnnotation.defaultValue();
    this.jsfTag          = pFloatAnnotation.jsfTag();
    this.jsfTagDisabled  = pFloatAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   *
   * @since   $maven.project.version$
   */
  @Override public Float getDefaultValue() {
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
  public void setDefaultValue(Float pDefaultValue) {
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
  public void setJsfTag(MmFloatJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
