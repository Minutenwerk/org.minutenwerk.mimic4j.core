package org.minutenwerk.mimic4j.impl.command;

import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmCommandCallback defines a set of override-able methods common to all command mimics. Callback methods are part of the declaration API
 * of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmCommandCallback extends MmBaseCallback {

  /**
   * Returns command button submit parameter.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  command button submit parameter.
   */
  public String callbackMmGetSubmitParam(String pPassThroughValue);

}
