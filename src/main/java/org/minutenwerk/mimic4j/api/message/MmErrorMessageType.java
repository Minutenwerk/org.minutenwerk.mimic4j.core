package org.minutenwerk.mimic4j.api.message;

/**
 * MmErrorMessageType indicates whether an error can be viewed as technical error or an error in execution of business logic.
 *
 * @author  Olaf Kossak
 */
public enum MmErrorMessageType {

  /** The error is a technical error. */
  TECHNICAL_ERROR,

  /** The error is an error in execution of business logic. */
  BUSINESS_LOGIC_ERROR;

}
