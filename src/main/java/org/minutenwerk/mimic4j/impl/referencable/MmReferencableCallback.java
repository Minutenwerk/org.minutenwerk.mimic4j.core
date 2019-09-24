package org.minutenwerk.mimic4j.impl.referencable;

import java.util.List;

import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

import org.springframework.web.util.UriComponents;

/**
 * MmCallbackTarget defines a set of override-able methods of mimics which have an URL and can be the target of a reference. These callback
 * methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencableCallback<MODEL extends MmReferencableModel> extends MmBaseCallback {

  /**
   * Returns the path part of the URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The path part of the URL.
   */
  public UriComponents callbackMmGetReferencePath(UriComponents pPassThroughValue);

  /**
   * Returns a list of path or query parameter values of the URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The model data, which may control the query string.
   *
   * @return  A list of path or query parameter values of the URL. Usually this is a list of ids starting by id of root dto.
   */
  public List<String> callbackMmGetReferenceValues(List<String> pPassThroughValue, MODEL pModel);

}
