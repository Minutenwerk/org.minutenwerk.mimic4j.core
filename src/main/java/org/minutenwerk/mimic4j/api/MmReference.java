package org.minutenwerk.mimic4j.api;

import java.util.List;

/**
 * A reference or URL is a hypertext link to some object, consisting of parts for path, file, anchor and query parameters.
 *
 * @author  Olaf Kossak
 */
public interface MmReference {

  /**
   * Returns the anchor part of the URL including the #, like "#plz" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The anchor part of the URL including the #.
   */
  public String getMmAnchor();

  /**
   * Returns the file part of the URL without slashes, like "display.html" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The file part of the URL without slashes.
   */
  public String getMmFile();

  /**
   * If this reference has an outcome string like "gotoPageLogin", it returns the outcome, otherwise its URL path, file and anchor.
   *
   * @return  An outcome string like "gotoPageLogin", or its URL path, file and anchor.
   */
  public String getMmOutcome();

  /**
   * Returns name of link parameter 1, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 1.
   */
  public String getMmParam1Name();

  /**
   * Returns value of link parameter 1, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 1.
   */
  public String getMmParam1Value();

  /**
   * Returns name of link parameter 2, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 2.
   */
  public String getMmParam2Name();

  /**
   * Returns value of link parameter 2, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 2.
   */
  public String getMmParam2Value();

  /**
   * Returns name of link parameter 3, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 3.
   */
  public String getMmParam3Name();

  /**
   * Returns value of link parameter 3, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 3.
   */
  public String getMmParam3Value();

  /**
   * Returns name of link parameter 4, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 4.
   */
  public String getMmParam4Name();

  /**
   * Returns value of link parameter 4, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 4.
   */
  public String getMmParam4Value();

  /**
   * Returns name of link parameter 5, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 5.
   */
  public String getMmParam5Name();

  /**
   * Returns value of link parameter 5, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 5.
   */
  public String getMmParam5Value();

  /**
   * Returns name of link parameter 6, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 6.
   */
  public String getMmParam6Name();

  /**
   * Returns value of link parameter 6, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 6.
   */
  public String getMmParam6Value();

  /**
   * Returns name of link parameter 7, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 7.
   */
  public String getMmParam7Name();

  /**
   * Returns value of link parameter 7, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 7.
   */
  public String getMmParam7Value();

  /**
   * Returns name of link parameter 8, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 8.
   */
  public String getMmParam8Name();

  /**
   * Returns value of link parameter 8, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 8.
   */
  public String getMmParam8Value();

  /**
   * Returns name of link parameter 9, if exists, empty string otherwise.
   *
   * @return  The name of link parameter 9.
   */
  public String getMmParam9Name();

  /**
   * Returns value of link parameter 9, if exists, empty string otherwise.
   *
   * @return  The value of link parameter 9.
   */
  public String getMmParam9Value();

  /**
   * Returns the list of query parameters of the URL, like "rootId 1", "subId 2" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The list of query parameters of the URL.
   */
  public List<MmNameValue> getMmParams();

  /**
   * Returns the count of reference parameters having a name and value.
   *
   * @return  The count of reference parameters having a name and value.
   */
  public int getMmParamsCount();

  /**
   * Returns the path part of the URL including trailing slash but without base part, like "person/wohnort/" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The path part of the URL including trailing slash but without base part.
   */
  public String getMmPath();

  /**
   * Returns the concatenated URL parts of path, file and anchor, like "person/wohnort/display.html#plz" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The concatenated URL parts of path, file and anchor.
   */
  public String getMmPathFileAnchor();

  /**
   * Returns the concatenated URL parts of path, file, anchor and query string, like "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The concatenated URL parts of path, file, anchor and query string.
   */
  public String getMmPathFileAnchorQuery();

  /**
   * Returns the query string part of the URL without the ?, like "rootId=1&subId=2" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The query string part of the URL without the ?.
   */
  public String getMmQuery();

  /**
   * Returns true, if this reference returns an outcome string like "gotoPageLogin", and not a concrete URL.
   *
   * @return  True, if this reference returns an outcome string like "gotoPageLogin", and not a concrete URL.
   */
  public boolean isMmJsfOutcome();

  /**
   * Returns true, if this reference returns a concrete URL, and not an outcome string like "gotoPageLogin".
   *
   * @return  True, if this reference returns a concrete URL, and not an outcome string like "gotoPageLogin".
   */
  public boolean isMmUrl();

}
