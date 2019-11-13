package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmFloat;
import org.minutenwerk.mimic4j.api.attribute.MmFloat.MmFloatJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmFloat.MmFloatJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmFloatAnnotation;

/**
 * MmConfigurationFloat contains configuration information for mimics of type {@link MmFloat}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationFloat extends MmBaseAttributeConfiguration<Float> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Redundant to {@link MmFloatAnnotation.jsfTag()}. */
  public static final MmFloatJsfTag      DEFAULT_JSF_TAG           = MmFloatJsfTag.TextField;

  /** Redundant to {@link MmFloatAnnotation.jsfTagDisabled()}. */
  public static final MmFloatJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmFloatJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                          formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmFloatJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmFloatJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationFloat instance of default values.
   */
  public MmConfigurationFloat() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationFloat instance from annotation.
   *
   * @param  pFloatAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationFloat(MmFloatAnnotation pFloatAnnotation) {
    super(pFloatAnnotation.id(), pFloatAnnotation.visible(), pFloatAnnotation.readOnly(), pFloatAnnotation.enabled(),
      pFloatAnnotation.required(), pFloatAnnotation.styleClasses());

    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = pFloatAnnotation.jsfTag();
    jsfTagDisabled  = pFloatAnnotation.jsfTagDisabled();
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
  public void setJsfTag(MmFloatJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
