package org.minutenwerk.mimic4j.api.exception;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * MmModelsideConverterException is a checked exception which occurs if the conversion of a mimic's value from modelside to viewside has
 * failed.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmModelsideConverterException extends MmRuntimeException {

  /** Default serial id. */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new MmModelsideConverterException instance.
   *
   * @param  pMm    The mimic this exception relates to, may be null.
   * @param  pArgs  The arguments to be inserted into message text, may be null.
   */
  public MmModelsideConverterException(MmMimic pMm, Object... pArgs) {
    this(pMm, null, pArgs);
  }

  /**
   * Creates a new MmModelsideConverterException instance.
   *
   * @param  pMm     The mimic this exception relates to, may be null.
   * @param  pCause  The causing exception, may be null.
   * @param  pArgs   The arguments to be inserted into message text, may be null.
   */
  public MmModelsideConverterException(MmMimic pMm, Throwable pCause, Object... pArgs) {
    super(pMm, pCause, pArgs);
  }

}
