package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmString.MmStringJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmString.MmStringJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;

/**
 * MmConfigurationString contains static configuration information for mimics of type {@link MmString}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationString extends MmBaseAttributeConfiguration<String> {

  /** Constant for default value of default value. */
  public static final String              DEFAULT_DEFAULT_VALUE     = "";

  /** Constant for default value of maximum length of formatted input string. */
  public static final int                 DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of columns of multi line text field. */
  public static final int                 DEFAULT_COLS              = 85;

  /** Constant for default value of rows of multi line text field. */
  public static final int                 DEFAULT_ROWS              = 3;

  /** Redundant to {@link MmStringAnnotation.jsfTag()}. */
  public static final MmStringJsfTag      DEFAULT_JSF_TAG           = MmStringJsfTag.TextField;

  /** Redundant to {@link MmStringAnnotation.jsfTagDisabled()}. */
  public static final MmStringJsfDisabled DEFAULT_JSF_TAG_DISABLED  = MmStringJsfDisabled.SameAsEnabled;

  /** Maximum length of formatted input string. */
  protected int                           formatMaxLength;

  /** The default value. */
  protected String                        defaultValue;

  /** Columns of multi line text field. */
  protected int                           cols;

  /** Rows of multi line text field. */
  protected int                           rows;

  /** The JSF tag in enabled state. */
  protected MmStringJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmStringJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationString instance of default values.
   */
  public MmConfigurationString() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.defaultValue    = DEFAULT_DEFAULT_VALUE;
    this.formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    this.cols            = DEFAULT_COLS;
    this.rows            = DEFAULT_ROWS;
    this.jsfTag          = DEFAULT_JSF_TAG;
    this.jsfTagDisabled  = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationString instance from annotation.
   *
   * @param  pStringAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationString(MmStringAnnotation pStringAnnotation) {
    super(pStringAnnotation.id(), pStringAnnotation.visible(), pStringAnnotation.readOnly(), pStringAnnotation.enabled(),
      pStringAnnotation.required());
    this.defaultValue    = pStringAnnotation.defaultValue();
    this.formatMaxLength = pStringAnnotation.formatMaxLength();
    this.cols            = pStringAnnotation.cols();
    this.rows            = pStringAnnotation.rows();
    this.jsfTag          = pStringAnnotation.jsfTag();
    this.jsfTagDisabled  = pStringAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of number of columns for multi line input field.
   *
   * @return  The configuration of number of columns for multi line input field.
   */
  public int getCols() {
    return cols;
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   */
  @Override public String getDefaultValue() {
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
   * Returns the configuration of number of rows for multi line input field.
   *
   * @return  The configuration of number of rows for multi line input field.
   */
  public int getRows() {
    return rows;
  }

}
