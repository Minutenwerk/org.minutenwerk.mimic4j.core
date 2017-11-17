package org.minutenwerk.mimic4j.api.exception;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * MmViewsideConverterException is a checked exception which occurs if the conversion of a mimic's value between viewside and modelside has
 * failed.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmViewsideConverterException extends MmException {

  /** Default serial id. */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new MmViewsideConverterException instance.
   *
   * @param  pMm    The mimic this exception relates to, may be null.
   * @param  pArgs  The arguments to be inserted into message text, may be null.
   */
  public MmViewsideConverterException(MmMimic pMm, Object... pArgs) {
    this(pMm, null, pArgs);
  }

  /**
   * Creates a new MmViewsideConverterException instance.
   *
   * @param  pMm     The mimic this exception relates to, may be null.
   * @param  pCause  The causing exception, may be null.
   * @param  pArgs   The arguments to be inserted into message text, may be null.
   */
  public MmViewsideConverterException(MmMimic pMm, Throwable pCause, Object... pArgs) {
    super(pMm, pCause, pArgs);
  }

}
