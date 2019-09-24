package org.minutenwerk.mimic4j.impl.command;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfTag;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

import org.springframework.web.util.UriComponents;

/**
 * MmCommandCallback defines a set of override-able methods common to all command mimics. Callback methods are part of the declaration API
 * of mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmCommandCallback extends MmBaseCallback {

  /**
   * Executes an action.
   *
   * @return  A control string, most times used as outcome string for JSF.
   */
  public String callbackMmDoIt();

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The current JSF tag of this mimic.
   */
  public MmCommandJsfTag callbackMmGetJsfTag(MmCommandJsfTag pPassThroughValue);

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A mimic, which is the target reference of this link mimic.
   */
  public MmMimic callbackMmGetTargetMimic(MmMimic pPassThroughValue);

  /**
   * Returns a string referencing a target, either an URL or an outcome.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A string referencing a target, either an URL or an outcome
   */
  public UriComponents callbackMmGetTargetOutcome(UriComponents pPassThroughValue);

  /**
   * Returns a list of URL parameters to be concatenated to target reference. May be used in combination with callbackMmGetTargetOutcome().
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             A referencable data model providing a list of reference values.
   *
   * @return  A list of URL parameters to be concatenated to target reference.
   */
  public List<String> callbackMmGetTargetReferenceValues(List<String> pPassThroughValue, MmReferencableModel pModel);

}
