package org.minutenwerk.mimic4j.impl.referencable;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmCallbackTarget defines a set of override-able methods of mimics which have an URL and can be the target of a reference. These callback
 * methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 */
public interface MmReferencableCallback<MODEL extends MmReferencableModel> extends MmBaseCallback {

  /**
   * Returns the file part of the URL without slashes, like "display.html" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The file part of the URL without slashes.
   */
  public String callbackMmGetReferenceFile(String pPassThroughValue);

  /**
   * Returns the list of query parameters of the URL, like "rootId 1", "subId 2" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The model data, which may control the query string.
   *
   * @return  The list of query parameters of the URL.
   */
  public List<MmNameValue> callbackMmGetReferenceParams(List<MmNameValue> pPassThroughValue, MODEL pModel);

  /**
   * Returns the path part of the URL including trailing slash but without base part, like "person/wohnort/" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The path part of the URL including trailing slash but without base part.
   */
  public String callbackMmGetReferencePath(String pPassThroughValue);

}
