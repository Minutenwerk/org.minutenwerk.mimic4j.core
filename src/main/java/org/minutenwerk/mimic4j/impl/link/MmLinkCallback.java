package org.minutenwerk.mimic4j.impl.link;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

import org.springframework.web.util.UriComponents;

/**
 * MmLinkCallback defines a set of override-able methods common to all link mimics. Callback methods are part of the declaration API of mimics. Callback
 * methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param   <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkCallback<DATA_MODEL, VIEW_MODEL> extends MmBaseCallback {

  /**
   * Returns the link's model accessor to corresponding data model. The data model delivers dynamic parts of URL. The data model accessor can be derived from
   * specified parent component accessor.
   *
   * @param   parentAccessor  The specified parent component accessor.
   *
   * @return  The data model accessor.
   */
  public MmModelAccessor<?, DATA_MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> parentAccessor);

  /**
   * Returns the target URL of this mimic.
   *
   * @param   targetReferencePath    The path of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   * @param   dataModel              The data model, which delivers the target reference parameters.
   * @param   targetReferenceParams  The parameters of the target URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return  The target URL of this mimic.
   */
  public URI callbackMmGetTargetReference(UriComponents targetReferencePath, DATA_MODEL dataModel, List<String> targetReferenceParams);

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  A mimic, which is the target reference of this link mimic.
   */
  public MmMimic callbackMmGetTargetReferenceMimic(MmMimic passThroughValue);

  /**
   * Returns the link's format pattern for displaying view value. It is used during conversion from view model to view value and vice versa. It is dependent
   * on the user's locale.
   *
   * @param   passThroughValue  By default this parameter value will be returned.
   *
   * @return  The attribute's format pattern for displaying view value.
   */
  public String callbackMmGetViewFormatPattern(String passThroughValue);

  /**
   * Returns the link's model accessor to corresponding view model. The view model delivers dynamic parts of URL. The view model accessor can be derived from
   * specified parent component accessor.
   *
   * @param   parentAccessor  The specified parent component accessor.
   *
   * @return  The view model accessor.
   */
  public MmModelAccessor<?, VIEW_MODEL> callbackMmGetViewModelAccessor(MmModelAccessor<?, ?> parentAccessor);

}
