package org.minutenwerk.mimic4j.impl.message;

/**
 * MmErrorMessageType indicates whether an error can be viewed as technical error or an error in execution of business logic.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public enum MmErrorMessageType {

  /** The error is a technical error. */
  TECHNICAL_ERROR,

  /** The error is an error in execution of business logic. */
  BUSINESS_LOGIC_ERROR;

}
