package org.minutenwerk.mimic4j.impl.link;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmLinkCallback defines a set of override-able methods common to all link mimics. Callback methods are part of the declaration API of
 * mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @param   <MODELSIDE_VALUE>  Modelside value delivers dynamic parts of URL.
 * @param   <LINK_MODEL>       Link model delivers text of link.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkCallback<MODELSIDE_VALUE, LINK_MODEL> extends MmBaseCallback {

  /**
   * Returns the link's model accessor to corresponding model. The modelside value delivers dynamic parts of URL. The link accessor can be
   * derived from specified parent component accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The link's model accessor.
   */
  public MmModelAccessor<?, MODELSIDE_VALUE> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor);

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   */
  public String callbackMmGetFormatPattern(String pPassThroughValue);

  /**
   * Returns the link's model accessor to corresponding link model. The link model delivers text of link. The link accessor can be derived
   * from specified parent component accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The link's model accessor.
   */
  public MmModelAccessor<?, LINK_MODEL> callbackMmGetLinkModelAccessor(MmModelAccessor<?, ?> pParentAccessor);

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
  public String callbackMmGetTargetOutcome(String pPassThroughValue);

  /**
   * Returns a list of URL parameters to be concatenated to target reference. May be used in combination with callbackMmGetTargetOutcome().
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The link's data model providing the list of URL parameters.
   *
   * @return  A list of URL parameters to be concatenated to target reference.
   */
  public List<MmNameValue> callbackMmGetTargetReferenceParams(List<MmNameValue> pPassThroughValue, MODELSIDE_VALUE pModel);

}
