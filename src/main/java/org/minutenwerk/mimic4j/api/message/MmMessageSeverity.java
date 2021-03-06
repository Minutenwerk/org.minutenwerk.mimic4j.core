package org.minutenwerk.mimic4j.api.message;

/**
 * MmMessageSeverity is an enumeration of possible severity levels of a MmMessage.
 *
 * @author  Olaf Kossak
 */
public enum MmMessageSeverity {

  /** The message is an info message. This is the least important severity. */
  INFO,

  /** The message informs the user about successful execution of last action. */
  SUCCESS,

  /** The message warns the user about a state, which might cause harm but might be ignored. */
  WARNING,

  /** The message inform the user about an error in execution of an action. */
  USER_ERROR,

  /** The message informs the user about an error in the system's state. */
  SYSTEM_ERROR;

}
