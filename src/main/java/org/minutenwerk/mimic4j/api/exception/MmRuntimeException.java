package org.minutenwerk.mimic4j.api.exception;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * MmRuntimeException is the base class for all runtime exceptions in mimic4j.
 *
 * @author  Olaf Kossak
 */
public class MmRuntimeException extends RuntimeException {

  /** Default serial id. */
  private static final long serialVersionUID = 1L;

  /** The mimic this exception relates to. */
  protected final MmMimic   mimic;

  /**
   * Creates a new MmRuntimeException instance.
   *
   * @param  pMm       The mimic this exception relates to.
   * @param  pMessage  pArgs The arguments to be inserted into message text, may be null.
   */
  public MmRuntimeException(MmMimic pMm, String pMessage) {
    this(pMm, pMessage, null);
  }

  /**
   * Creates a new MmRuntimeException instance.
   *
   * @param  pMimic    The mimic this exception relates to.
   * @param  pMessage  pArgs The arguments to be inserted into message text, may be null.
   * @param  pCause    The causing exception, may be null.
   */
  public MmRuntimeException(MmMimic pMimic, String pMessage, Throwable pCause) {
    super(pMimic.toString() + ": " + pMessage, pCause);
    mimic = pMimic;
  }

  /**
   * Returns the mimic this exception relates to.
   *
   * @return  The mimic this exception relates to.
   */
  public MmMimic getMimic() {
    return mimic;
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
   * at any time.
   *
   * @return  Some information about this object for development purposes like debugging and logging.
   */
  @Override
  public String toString() {
    StringBuilder snippet = new StringBuilder();
    if (getCause() != null) {
      snippet.append(getCause().getClass().getSimpleName());
      snippet.append(": ");
    }
    if (getMessage() != null) {
      snippet.append(getMessage());
      snippet.append(": ");
    }
    if (mimic != null) {
      snippet.append(mimic.toString());
    }
    return snippet.toString();
  }

}
