package org.minutenwerk.mimic4j.api;

/**
 * MmNameValue can deliver a string name and value pair, which will be used for URL query strings.
 *
 * @author  olaf
 */
public interface MmNameValue {

  /**
   * Returns the name of this query parameter.
   *
   * @return  The name of this query parameter.
   */
  public String getMmName();

  /**
   * Returns the value of this query parameter.
   *
   * @return  The value of this query parameter.
   */
  public String getMmValue();

}
