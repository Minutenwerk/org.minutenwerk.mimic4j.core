package org.minutenwerk.mimic4j.api.message;

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

  /** Message in case of error in conversion from view model to data model. */
  ERROR_CONVERSION_MODEL("error_conversion_model"),

  /** Message in case of error in conversion from data model to view model. */
  ERROR_CONVERSION_VIEW ("error_conversion_view"),

  /** Message in case of error in validation of data model value. */
  ERROR_VALIDATION      ("error_validation");

  /** Suffix of message key. */
  private final String suffix;

  /**
   * Creates a new MmMessageType instance.
   *
   * @param  pSuffix  Suffix of message key
   */
  private MmMessageType(String pSuffix) {
    suffix = pSuffix;
  }

  /**
   * Returns suffix of message key.
   *
   * @return  Suffix of message key
   */
  public String getSuffix() {
    return suffix;
  }
}
