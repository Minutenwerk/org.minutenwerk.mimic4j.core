package org.minutenwerk.mimic4j.impl.message;

/**
 * MmMessageType is an enumeration of all types of messages in mimic4j.
 *
 * @author  Olaf Kossak
 */
public enum MmMessageType {

  /** Some text itself. */
  TEXT                  ("text"),

  /** Short description of mimic. */
  SHORT                 ("label"),

  /** Long description of mimic. */
  LONG                  ("hover"),

  /** Number or date format of mimic. */
  FORMAT                ("format"),

  /** Message in case of input is required. */
  ERROR_REQUIRED        ("error_required"),

  /** Message in case of error in conversion from viewside to modelside. */
  ERROR_CONVERSION_MODEL("error_conversion_model"),

  /** Message in case of error in conversion from modelside to viewside. */
  ERROR_CONVERSION_VIEW ("error_conversion_view"),

  /** Message in case of error in validation of modelside value. */
  ERROR_VALIDATION      ("error_validation");

  /** TODOC. */
  private final String suffix;

  /**
   * Creates a new MmMessageType instance.
   *
   * @param  pSuffix  TODOC
   */
  private MmMessageType(String pSuffix) {
    suffix = pSuffix;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public String getSuffix() {
    return suffix;
  }
}
