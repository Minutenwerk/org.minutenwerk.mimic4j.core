package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;

/**
 * MmConfigurationString contains configuration information for mimics of type {@link MmString}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationString extends MmBaseAttributeConfiguration<String> {

  /** Constant for default value of maximum length of formatted input string. */
  public static final int DEFAULT_FORMAT_MAX_LENGTH = 255;

  /** Constant for default value of columns of multi line text field. */
  public static final int DEFAULT_COLS              = 85;

  /** Constant for default value of rows of multi line text field. */
  public static final int DEFAULT_ROWS              = 3;

  /** Maximum length of formatted input string. */
  protected int           formatMaxLength;

  /** Columns of multi line text field. */
  protected int           cols;

  /** Rows of multi line text field. */
  protected int           rows;

  /**
   * Creates a new MmConfigurationString instance of default values.
   */
  public MmConfigurationString() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
    formatMaxLength = DEFAULT_FORMAT_MAX_LENGTH;
    cols            = DEFAULT_COLS;
    rows            = DEFAULT_ROWS;
  }

  /**
   * Creates a new MmConfigurationString instance from annotation.
   *
   * @param  pStringAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationString(MmStringAnnotation pStringAnnotation) {
    super(pStringAnnotation.id(), pStringAnnotation.visible(), pStringAnnotation.readOnly(), pStringAnnotation.enabled(), pStringAnnotation.required(),
      pStringAnnotation.styleClasses());
    formatMaxLength = pStringAnnotation.formatMaxLength();
    cols            = pStringAnnotation.cols();
    rows            = pStringAnnotation.rows();
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
   * Returns the configuration of maximum length of formatted input string.
   *
   * @return  The configuration of maximum length of formatted input string.
   */
  public int getFormatMaxLength() {
    return formatMaxLength;
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
