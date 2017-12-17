package org.minutenwerk.mimic4j.api.exception;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * MmValidatorException is a checked exception which occurs if the validation of a mimic failed.
 *
 * @author  Olaf Kossak
 */
public class MmValidatorException extends MmException {

  /** Default serial id. */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new MmValidatorException instance.
   *
   * @param  pMm    The mimic this exception relates to, may be null.
   * @param  pArgs  The arguments to be inserted into message text, may be null.
   */
  public MmValidatorException(MmMimic pMm, Object... pArgs) {
    this(pMm, null, pArgs);
  }

  /**
   * Creates a new MmValidatorException instance.
   *
   * @param  pMm     The mimic this exception relates to, may be null.
   * @param  pCause  The causing exception, may be null.
   * @param  pArgs   The arguments to be inserted into message text, may be null.
   */
  public MmValidatorException(MmMimic pMm, Throwable pCause, Object... pArgs) {
    super(pMm, pCause, pArgs);
  }

}
