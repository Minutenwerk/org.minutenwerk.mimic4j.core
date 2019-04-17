package org.minutenwerk.mimic4j.impl.referencable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReference;

/**
 * Immutable implementation of a reference, consisting of parts for path, file, anchor and query parameters.
 *
 * @author  Olaf Kossak
 */
public class MmReferenceImplementation implements MmReference {

  /** Current maximum of supported parameters. */
  private static final int          MAX_COUNT           = 9;

  /** The path part of the URL, like "person/wohnort/". */
  protected final String            path;

  /** The file part of the URL, like "display.html". */
  protected final String            file;

  /** The anchor part of the URL including the #, like "#plz". */
  protected final String            anchor;

  /** The concatenated URL parts of path, file and anchor. */
  protected final String            pathFileAnchor;

  /** An outcome string like "gotoPageLogin". */
  protected final String            outcome;

  /** A set of reference parameters for URL query string. */
  protected final List<MmNameValue> params;

  /** The count of reference parameters having a name and value. */
  protected final int               paramsCount;

  /** Internal and redundant array of parameter names. */
  protected final String[]          paramName           = new String[MAX_COUNT];

  /** Internal and redundant array of parameter values. */
  protected final String[]          paramValue          = new String[MAX_COUNT];

  /** The query string part of the URL without the ?, like "rootId=1&subId=2". */
  protected final String            query;

  /** The concatenated URL parts of path, file, anchor and query string. */
  protected final String            pathFileAnchorQuery;

  /**
   * Creates a new MmReferenceImplementation instance.
   *
   * @param   pReference  A reference containing a path, a file and params, but no outcome.
   * @param   pAnchor     The anchor part of the URL.
   *
   * @throws  IllegalArgumentException  In case of pReference is from outcome.
   */
  public MmReferenceImplementation(MmReference pReference, String pAnchor) {
    if (pReference.isMmJsfOutcome()) {
      throw new IllegalArgumentException("constructor for anchor cannot be called for reference from outcome");
    }
    if (pReference instanceof MmReferenceImplementation) {
      final MmReferenceImplementation pReferenceImpl = (MmReferenceImplementation)pReference;
      this.path           = pReferenceImpl.path;
      this.file           = pReferenceImpl.file;
      this.anchor         = pAnchor;
      this.pathFileAnchor = this.path + this.file + this.anchor;
      this.outcome        = null;
      this.params         = pReferenceImpl.params;
      this.paramsCount    = pReferenceImpl.paramsCount;
      for (int i = 0; i < MAX_COUNT; i++) {
        this.paramName[i]  = pReferenceImpl.paramName[i];
        this.paramValue[i] = pReferenceImpl.paramValue[i];
      }
      this.query = pReferenceImpl.query;
      if (this.query.isEmpty()) {
        this.pathFileAnchorQuery = this.pathFileAnchor;
      } else {
        this.pathFileAnchorQuery = this.pathFileAnchor + "?" + this.query;
      }

    } else {
      this.path           = pReference.getMmPath();
      this.file           = pReference.getMmFile();
      this.anchor         = pAnchor;
      this.pathFileAnchor = this.path + this.file + this.anchor;
      this.outcome        = null;
      this.params         = pReference.getMmParams();

      int index           = 0;
      for (MmNameValue param : this.params) {
        this.paramName[index]  = param.getMmName();
        this.paramValue[index] = param.getMmValue();
        index++;
      }
      this.paramsCount = index;
      for (; index < MAX_COUNT; index++) {
        this.paramName[index]  = "";
        this.paramValue[index] = "";
      }

      this.query = pReference.getMmQuery();
      if (this.query.isEmpty()) {
        this.pathFileAnchorQuery = this.pathFileAnchor;
      } else {
        this.pathFileAnchorQuery = this.pathFileAnchor + "?" + this.query;
      }
    }
  }

  /**
   * Creates a new MmReferenceImplementation instance.
   *
   * @param  pOutcome  An outcome string like "gotoPageLogin".
   * @param  pParams   A list of reference parameters for URL query string.
   */
  public MmReferenceImplementation(String pOutcome, List<MmNameValue> pParams) {
    this(null, null, pOutcome, pParams);
  }

  /**
   * Creates a new MmReferenceImplementation instance.
   *
   * @param  pPath    The path part of the URL.
   * @param  pFile    The file part of the URL.
   * @param  pParams  A list of reference parameters for URL query string.
   */
  public MmReferenceImplementation(String pPath, String pFile, List<MmNameValue> pParams) {
    this(pPath, pFile, null, pParams);
  }

  /**
   * Creates a new MmReferenceImplementation instance.
   *
   * @param   pPath     The path part of the URL.
   * @param   pFile     The file part of the URL.
   * @param   pOutcome  An outcome string like "gotoPageLogin".
   * @param   pParams   A list of reference parameters for URL query string.
   *
   * @throws  IllegalArgumentException  If path and outcome is null or parameter size exceeds maximum count.
   */
  protected MmReferenceImplementation(String pPath, String pFile, String pOutcome, List<MmNameValue> pParams) {
    if ((pPath == null) && (pOutcome == null)) {
      throw new IllegalArgumentException("pPath or pOutcome must have a value");
    } else if ((pParams != null) && (pParams.size() >= MAX_COUNT)) {
      throw new IllegalArgumentException("count of pParams " + pParams.size() + " exceeds maximum of " + MAX_COUNT);
    }
    if (pPath != null) {
      this.path           = pPath;
      this.file           = pFile;
      this.anchor         = null;
      this.pathFileAnchor = this.path + this.file;
      this.outcome        = null;

      // if pOutcome != null
    } else {
      this.path           = null;
      this.file           = null;
      this.anchor         = null;
      this.pathFileAnchor = null;
      this.outcome        = pOutcome;
    }

    // evaluate query, parameter list and parameter array
    int                     index        = 0;
    final StringBuilder     queryBuilder = new StringBuilder();
    final List<MmNameValue> nameValues   = new ArrayList<>();
    for (MmNameValue nameValue : pParams) {

      // check each name value for null or empty
      if (notNullOrEmpty(nameValue)) {

        // build query
        if (queryBuilder.length() > 0) {
          queryBuilder.append("&");
        }
        queryBuilder.append(nameValue.getMmName());
        queryBuilder.append("=");
        queryBuilder.append(nameValue.getMmValue());

        // build parameter list
        nameValues.add(nameValue);

        // build parameter array
        this.paramName[index]  = nameValue.getMmName();
        this.paramValue[index] = nameValue.getMmValue();
        index++;
      }
    }

    // if there are no parameters
    if (index == 0) {
      this.query  = "";
      this.params = Collections.emptyList();
      if (pPath != null) {
        this.pathFileAnchorQuery = this.pathFileAnchor;
      } else {
        this.pathFileAnchorQuery = null;
      }

      // if there are parameters
    } else {
      this.query  = queryBuilder.toString();
      this.params = Collections.unmodifiableList(nameValues);
      if (pPath != null) {
        this.pathFileAnchorQuery = this.pathFileAnchor + "?" + this.query;
      } else {
        this.pathFileAnchorQuery = null;
      }
    }

    // set remaining parameter array to empty string
    this.paramsCount = index;
    for (; index < MAX_COUNT; index++) {
      this.paramName[index]  = "";
      this.paramValue[index] = "";
    }
  }

  /**
   * Returns the anchor part of the URL including the #, like "#plz" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The anchor part of the URL including the #.
   */
  @Override
  public String getMmAnchor() {
    return this.anchor;
  }

  /**
   * Returns the file part of the URL without slashes, like "display.html" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The file part of the URL without slashes.
   */
  @Override
  public String getMmFile() {
    return this.file;
  }

  /**
   * If this reference has an outcome string like "gotoPageLogin", it returns the outcome, otherwise its URL path, file and anchor.
   *
   * @return  An outcome string like "gotoPageLogin", or its URL path, file and anchor.
   */
  @Override
  public String getMmOutcome() {
    if (this.isMmJsfOutcome()) {
      return this.outcome;
    } else {
      return this.getMmPathFileAnchor();
    }
  }

  /**
   * Returns name of reference parameter 1, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 1.
   */
  @Override
  public String getMmParam1Name() {
    return this.paramName[0];
  }

  /**
   * Returns value of reference parameter 1, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 1.
   */
  @Override
  public String getMmParam1Value() {
    return this.paramValue[0];
  }

  /**
   * Returns name of reference parameter 2, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 2.
   */
  @Override
  public String getMmParam2Name() {
    return this.paramName[1];
  }

  /**
   * Returns value of reference parameter 2, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 2.
   */
  @Override
  public String getMmParam2Value() {
    return this.paramValue[1];
  }

  /**
   * Returns name of reference parameter 3, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 3.
   */
  @Override
  public String getMmParam3Name() {
    return this.paramName[2];
  }

  /**
   * Returns value of reference parameter 3, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 3.
   */
  @Override
  public String getMmParam3Value() {
    return this.paramValue[2];
  }

  /**
   * Returns name of reference parameter 4, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 4.
   */
  @Override
  public String getMmParam4Name() {
    return this.paramName[3];
  }

  /**
   * Returns value of reference parameter 4, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 4.
   */
  @Override
  public String getMmParam4Value() {
    return this.paramValue[3];
  }

  /**
   * Returns name of reference parameter 5, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 5.
   */
  @Override
  public String getMmParam5Name() {
    return this.paramName[4];
  }

  /**
   * Returns value of reference parameter 5, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 5.
   */
  @Override
  public String getMmParam5Value() {
    return this.paramValue[4];
  }

  /**
   * Returns name of reference parameter 6, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 6.
   */
  @Override
  public String getMmParam6Name() {
    return this.paramName[5];
  }

  /**
   * Returns value of reference parameter 6, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 6.
   */
  @Override
  public String getMmParam6Value() {
    return this.paramValue[5];
  }

  /**
   * Returns name of reference parameter 7, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 7.
   */
  @Override
  public String getMmParam7Name() {
    return this.paramName[6];
  }

  /**
   * Returns value of reference parameter 7, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 7.
   */
  @Override
  public String getMmParam7Value() {
    return this.paramValue[6];
  }

  /**
   * Returns name of reference parameter 8, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 8.
   */
  @Override
  public String getMmParam8Name() {
    return this.paramName[7];
  }

  /**
   * Returns value of reference parameter 8, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 8.
   */
  @Override
  public String getMmParam8Value() {
    return this.paramValue[7];
  }

  /**
   * Returns name of reference parameter 9, if exists, empty string otherwise.
   *
   * @return  The name of reference parameter 9.
   */
  @Override
  public String getMmParam9Name() {
    return this.paramName[8];
  }

  /**
   * Returns value of reference parameter 9, if exists, empty string otherwise.
   *
   * @return  The value of reference parameter 9.
   */
  @Override
  public String getMmParam9Value() {
    return this.paramValue[8];
  }

  /**
   * Returns the list of query parameters of the URL, like "rootId 1", "subId 2" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The list of query parameters of the URL.
   */
  @Override
  public List<MmNameValue> getMmParams() {
    return this.params;
  }

  /**
   * Returns the count of reference parameters having a name and value.
   *
   * @return  The count of reference parameters having a name and value.
   */
  @Override
  public int getMmParamsCount() {
    return this.paramsCount;
  }

  /**
   * Returns the path part of the URL including trailing slash but without base part, like "person/wohnort/" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The path part of the URL including trailing slash but without base part.
   */
  @Override
  public String getMmPath() {
    return this.path;
  }

  /**
   * Returns the concatenated URL parts of path, file and anchor, like "person/wohnort/display.html#plz" in
   * "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The concatenated URL parts of path, file and anchor.
   */
  @Override
  public String getMmPathFileAnchor() {
    return this.pathFileAnchor;
  }

  /**
   * Returns the concatenated URL parts of path, file, anchor and query string, like "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The concatenated URL parts of path, file, anchor and query string.
   */
  @Override
  public String getMmPathFileAnchorQuery() {
    return this.pathFileAnchorQuery;
  }

  /**
   * Returns the query string part of the URL without the ?, like "rootId=1&subId=2" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @return  The query string part of the URL without the ?.
   */
  @Override
  public String getMmQuery() {
    return this.query;
  }

  /**
   * Returns true, if this reference returns an outcome string like "gotoPageLogin", and not a concrete URL.
   *
   * @return  True, if this reference returns an outcome string like "gotoPageLogin", and not a concrete URL.
   */
  @Override
  public boolean isMmJsfOutcome() {
    return this.outcome != null;
  }

  /**
   * Returns true, if this reference returns a concrete URL, and not an outcome string like "gotoPageLogin".
   *
   * @return  True, if this reference returns a concrete URL, and not an outcome string like "gotoPageLogin".
   */
  @Override
  public boolean isMmUrl() {
    return this.pathFileAnchor != null;
  }

  /**
   * Returns true, if specified string is null or empty.
   *
   * @param   pString  The specified string.
   *
   * @return  True, if specified string is null or empty.
   */
  private boolean notNullOrEmpty(String pString) {
    return (pString != null) && !pString.isEmpty();
  }

  /**
   * Returns true, if specified reference parameter's name and value are null or empty.
   *
   * @param   pNameValue  The specified reference parameter.
   *
   * @return  True, if specified reference parameter's name and value are null or empty.
   */
  private boolean notNullOrEmpty(MmNameValue pNameValue) {
    return notNullOrEmpty(pNameValue.getMmName()) && notNullOrEmpty(pNameValue.getMmValue());
  }

}
