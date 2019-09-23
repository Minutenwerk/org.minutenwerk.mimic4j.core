package org.minutenwerk.mimic4j.api.exception;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * MmDataModelConverterException is a checked exception which occurs if the conversion of a mimic's value from data model to view model has
 * failed.
 *
 * @author  Olaf Kossak
 */
public class MmDataModelConverterException extends MmRuntimeException {

  /** Default serial id. */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new MmDataModelConverterException instance.
   *
   * @param  pMm    The mimic this exception relates to, may be null.
   * @param  pArgs  The arguments to be inserted into message text, may be null.
   */
  public MmDataModelConverterException(MmMimic pMm, Object... pArgs) {
    this(pMm, null, pArgs);
  }

  /**
   * Creates a new MmDataModelConverterException instance.
   *
   * @param  pMm     The mimic this exception relates to, may be null.
   * @param  pCause  The causing exception, may be null.
   * @param  pArgs   The arguments to be inserted into message text, may be null.
   */
  public MmDataModelConverterException(MmMimic pMm, Throwable pCause, Object... pArgs) {
    super(pMm, pCause, pArgs);
  }

}
