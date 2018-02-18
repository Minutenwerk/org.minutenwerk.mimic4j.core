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

  /** Constant for default value of format pattern for parsing user input and formatting viewside value. */
  public static final String               DEFAULT_FORMAT_PATTERN    = "dd.MM.yyyy HH:mm:ss";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                  DEFAULT_FORMAT_MAX_LENGTH = 19;

  /** Constant for default value of default value. */
  public static final Instant              DEFAULT_DEFAULT_VALUE     = null;

  /** Redundant to {@link MmDateAnnotation.jsfTag()}. */
  public static final MmInstantJsfTag      DEFAULT_JSF_TAG           = MmInstantJsfTag.TextField;

  /** Redundant to {@link MmDateAnnotation.jsfTagDisabled()}. */
  public static final MmInstantJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmInstantJsfDisabled.SameAsEnabled;

  /** Format pattern for parsing user input and formatting viewside value. */
  protected String                         formatPattern;

  /** The default value. */
  protected Instant                        defaultValue;

  /** Maximum length of formatted input string. */
  protected int                            formatMaxLength;

  /** The JSF tag in enabled state. */
  protected MmInstantJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmInstantJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationDate instance of default values.
   */
  public MmConfigurationInstant() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.formatPattern   = DEFAULT_FORMAT_PATTERN;
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationDate instance from annotation.
   *
   * @param  pDateAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationInstant(MmInstantAnnotation pDateAnnotation) {
    super(pDateAnnotation.id(), pDateAnnotation.visible(), pDateAnnotation.readOnly(), pDateAnnotation.enabled(),
      pDateAnnotation.required());
    this.formatPattern   = pDateAnnotation.formatPattern();

    // there is no default value for date in annotation
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = pDateAnnotation.formatMaxLength();
    this.jsfTag          = pDateAnnotation.jsfTag();
    this.jsfTagDisabled  = pDateAnnotation.jsfTagDisabled();
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
   * Returns the configuration of format pattern for parsing user input and formatting viewside value.
   *
   * @return  The configuration of format pattern for parsing user input and formatting viewside value.
   */
  public String getFormatPattern() {
    return this.formatPattern;
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
   * Sets the configuration of format pattern for parsing user input and formatting viewside value.
   *
   * @param  pFormatPattern  The specified configuration of format pattern for parsing user input and formatting viewside value.
   */
  public void setFormatPattern(String pFormatPattern) {
    this.formatPattern = pFormatPattern;
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
