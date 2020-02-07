package org.minutenwerk.mimic4j.impl.command;

import org.minutenwerk.mimic4j.api.mimic.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.link.MmLinkCallback;

/**
 * MmCommandCallback defines a set of override-able methods common to all command mimics. Callback methods are part of the declaration API of mimics. Callback
 * methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts and view text label of link.
 *
 * @author  Olaf Kossak
 */
public interface MmCommandCallback<DATA_MODEL extends MmReferencableModel> extends MmLinkCallback<DATA_MODEL, DATA_MODEL> {

  /**
   * Returns command button submit parameter.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  command button submit parameter.
   */
  public String callbackMmGetSubmitParam(String passThroughValue);

}
