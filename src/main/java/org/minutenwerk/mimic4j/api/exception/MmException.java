package org.minutenwerk.mimic4j.api.exception;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * MmException is the base class for all checked exceptions in mimic4j.
 *
 * @author  Olaf Kossak
 */
public class MmException extends Exception {

  /** Default serial id. */
  private static final long serialVersionUID = 1L;

  /** The mimic this exception relates to. */
  protected final MmMimic   mimic;

  /** The arguments to be inserted into message text, may be null. */
  protected final Object[]  args;

  /**
   * Creates a new MmException instance.
   *
   * @param  pMm    The mimic this exception relates to.
   * @param  pArgs  The arguments to be inserted into message text, may be null.
   */
  public MmException(MmMimic pMm, Object... pArgs) {
    this(pMm, null, pArgs);
  }

  /**
   * Creates a new MmException instance.
   *
   * @param  pMimic  The mimic this exception relates to.
   * @param  pCause  The causing exception, may be null.
   * @param  pArgs   The arguments to be inserted into message text, may be null.
   */
  public MmException(MmMimic pMimic, Throwable pCause, Object... pArgs) {
    super(pMimic.getMmId(), pCause);
    this.mimic = pMimic;
    this.args  = pArgs;
  }

  /**
   * Returns the arguments to be inserted into message text, may be null.
   *
   * @return  The arguments to be inserted into message text, may be null.
   */
  public final Object[] getArgs() {
    return this.args;
  }

  /**
   * Returns the mimic this exception relates to.
   *
   * @return  The mimic this exception relates to.
   */
  public MmMimic getMimic() {
    return this.mimic;
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
    if (this.getCause() != null) {
      snippet.append(this.getCause().getClass().getSimpleName());
      snippet.append(": ");
    }
    if (this.getMessage() != null) {
      snippet.append(this.getMessage());
      snippet.append(": ");
    }
    if (this.mimic != null) {
      snippet.append(this.mimic.toString());
    }
    return snippet.toString();
  }

}
