package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmLong;
import org.minutenwerk.mimic4j.api.attribute.MmLong.MmLongJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLong.MmLongJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmLongAnnotation;

/**
 * MmConfigurationLong contains configuration information for mimics of type {@link MmLong}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLong extends MmBaseAttributeConfiguration<Long> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int               DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Redundant to {@link MmLongAnnotation.jsfTag()}. */
  public static final MmLongJsfTag      DEFAULT_JSF_TAG           = MmLongJsfTag.TextField;

  /** Redundant to {@link MmLongAnnotation.jsfTagDisabled()}. */
  public static final MmLongJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmLongJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                         formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmLongJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmLongJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationLong instance of default values.
   */
  public MmConfigurationLong() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    jsfTag          = DEFAULT_JSF_TAG;
    jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationLong instance from annotation.
   *
   * @param  pLongAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLong(MmLongAnnotation pLongAnnotation) {
    super(pLongAnnotation.id(), pLongAnnotation.visible(), pLongAnnotation.readOnly(), pLongAnnotation.enabled(),
      pLongAnnotation.required(), pLongAnnotation.styleClasses());
    formatMaxLength = pLongAnnotation.formatMaxLength();
    jsfTag          = pLongAnnotation.jsfTag();
    jsfTagDisabled  = pLongAnnotation.jsfTagDisabled();
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
  public void setJsfTag(MmLongJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
