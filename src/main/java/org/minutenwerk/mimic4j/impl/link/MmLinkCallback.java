package org.minutenwerk.mimic4j.impl.link;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

import org.springframework.web.util.UriComponents;

/**
 * MmLinkCallback defines a set of override-able methods common to all link mimics. Callback methods are part of the declaration API of
 * mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param   <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkCallback<DATA_MODEL, VIEW_MODEL> extends MmBaseCallback {

  /**
   * Returns the link's model accessor to corresponding data model. The data model delivers dynamic parts of URL. The data model accessor
   * can be derived from specified parent component accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The data model accessor.
   */
  public MmModelAccessor<?, DATA_MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor);

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  A mimic, which is the target reference of this link mimic.
   */
  public MmMimic callbackMmGetTargetReferenceMimic(MmMimic pPassThroughValue);

  /**
   * Returns the path part of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The path part of the target URL.
   */
  public UriComponents callbackMmGetTargetReferencePath(UriComponents pPassThroughValue);

  /**
   * Returns a list of path or query parameter values of the target URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The model data, which may control the query string.
   *
   * @return  A list of path or query parameter values of the target URL. Usually this is a list of ids starting by id of root dto.
   */
  public List<String> callbackMmGetTargetReferenceValues(List<String> pPassThroughValue, DATA_MODEL pModel);

  /**
   * Returns the link's format pattern for displaying view value. It is used during conversion from view model to view value and vice versa.
   * It is dependent on the user's locale.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's format pattern for displaying view value.
   */
  public String callbackMmGetViewFormatPattern(String pPassThroughValue);

  /**
   * Returns the link's model accessor to corresponding view model. The view model delivers dynamic parts of URL. The view model accessor
   * can be derived from specified parent component accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The view model accessor.
   */
  public MmModelAccessor<?, VIEW_MODEL> callbackMmGetViewModelAccessor(MmModelAccessor<?, ?> pParentAccessor);

}
