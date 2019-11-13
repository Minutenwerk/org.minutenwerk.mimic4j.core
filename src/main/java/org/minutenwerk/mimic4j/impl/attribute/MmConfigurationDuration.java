package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Duration;

import org.minutenwerk.mimic4j.api.attribute.MmDuration;
import org.minutenwerk.mimic4j.api.attribute.MmDuration.MmDurationJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmDuration.MmDurationJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmDurationAnnotation;

/**
 * MmConfigurationDuration contains configuration information for mimics of type {@link MmDuration}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationDuration extends MmBaseAttributeConfiguration<Duration> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                   DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Redundant to {@link MmDurationAnnotation.jsfTag()}. */
  public static final MmDurationJsfTag      DEFAULT_JSF_TAG           = MmDurationJsfTag.TextField;

  /** Redundant to {@link MmDurationAnnotation.jsfTagDisabled()}. */
  public static final MmDurationJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmDurationJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                             formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmDurationJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmDurationJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationDuration instance of default values.
   */
  public MmConfigurationDuration() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDuration instance from annotation.
   *
   * @param  pDurationAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationDuration(MmDurationAnnotation pDurationAnnotation) {
    super(pDurationAnnotation.id(), pDurationAnnotation.visible(), pDurationAnnotation.readOnly(), pDurationAnnotation.enabled(),
      pDurationAnnotation.required(), pDurationAnnotation.styleClasses());

    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = pDurationAnnotation.jsfTag();
    jsfTagDisabled  = pDurationAnnotation.jsfTagDisabled();
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
  public void setJsfTag(MmDurationJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
