package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmDouble;
import org.minutenwerk.mimic4j.api.attribute.MmDouble.MmDoubleJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmDouble.MmDoubleJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmDoubleAnnotation;

/**
 * MmConfigurationDouble contains configuration information for mimics of type {@link MmDouble}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationDouble extends MmBaseAttributeConfiguration<Double> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                 DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Redundant to {@link MmDoubleAnnotation.jsfTag()}. */
  public static final MmDoubleJsfTag      DEFAULT_JSF_TAG           = MmDoubleJsfTag.TextField;

  /** Redundant to {@link MmDoubleAnnotation.jsfTagDisabled()}. */
  public static final MmDoubleJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmDoubleJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                           formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmDoubleJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmDoubleJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationDouble instance of default values.
   */
  public MmConfigurationDouble() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDouble instance from annotation.
   *
   * @param  pDoubleAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationDouble(MmDoubleAnnotation pDoubleAnnotation) {
    super(pDoubleAnnotation.id(), pDoubleAnnotation.visible(), pDoubleAnnotation.readOnly(), pDoubleAnnotation.enabled(),
      pDoubleAnnotation.required(), pDoubleAnnotation.styleClasses());

    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = pDoubleAnnotation.jsfTag();
    jsfTagDisabled  = pDoubleAnnotation.jsfTagDisabled();
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
  public void setJsfTag(MmDoubleJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
