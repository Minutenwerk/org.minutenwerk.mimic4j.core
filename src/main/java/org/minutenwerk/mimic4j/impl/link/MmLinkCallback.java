package org.minutenwerk.mimic4j.impl.link;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmLinkCallback defines a set of override-able methods common to all link mimics. Callback methods are part of the declaration API of
 * mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmLinkCallback extends MmBaseCallback {

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   *
   * @since   $maven.project.version$
   */
  public String callbackMmGetFormatPattern(String pPassThroughValue);

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A mimic, which is the target reference of this link mimic.
   *
   * @since   $maven.project.version$
   */
  public MmMimic callbackMmGetTargetMimic(MmMimic pPassThroughValue);

  /**
   * Returns a string referencing a target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A string referencing a target, either an URL or an outcome
   *
   * @since   $maven.project.version$
   */
  public String callbackMmGetTargetOutcome(String pPassThroughValue);

  /**
   * Returns a list of URL parameters to be concatenated to target reference. May be used in combination with callbackMmGetTargetOutcome().
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             A referencable data model providing a list of reference values.
   *
   * @return  A list of URL parameters to be concatenated to target reference.
   *
   * @since   $maven.project.version$
   */
  public List<MmNameValue> callbackMmGetTargetReferenceParams(List<MmNameValue> pPassThroughValue, MmReferencableModel pModel);

}
