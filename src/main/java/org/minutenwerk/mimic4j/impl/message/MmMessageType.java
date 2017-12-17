package org.minutenwerk.mimic4j.impl.message;

/**
 * MmMessageType is an enumeration of all types of messages in mimic4j.
 *
 * @author  Olaf Kossak
 */
public enum MmMessageType {

  /** Short description of mimic. */
  SHORT,

  /** Long description of mimic. */
  LONG,

  /** Number or date format of mimic. */
  FORMAT,

  /** Message in case of input is required. */
  ERROR_REQUIRED,

  /** Message in case of error in conversion from viewside to modelside. */
  ERROR_CONVERSION_MODEL,

  /** Message in case of error in conversion from modelside to viewside. */
  ERROR_CONVERSION_VIEW,

  /** Message in case of error in validation of modelside value. */
  ERROR_VALIDATION;

}
