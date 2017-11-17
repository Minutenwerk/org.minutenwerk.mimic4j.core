package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmNameValue;

/**
 * MmReferenceParam is an immutable helper class for a name/ value pair, which serves as a parameter of an URL query string.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmReferenceParam implements MmNameValue {

  /** The name part. */
  protected final String name;

  /** The value part. */
  protected final String value;

  /**
   * Creates a new MmReferenceParam instance.
   *
   * @param  pName   The specified name of this link parameter, cannot be changed.
   * @param  pValue  The initial value of this link parameter.
   */
  public MmReferenceParam(String pName, Number pValue) {
    this.name = pName;
    if (pValue == null) {
      this.value = "";
    } else {
      this.value = "" + pValue;
    }
  }

  /**
   * Creates a new MmReferenceParam instance.
   *
   * @param  pName   The specified name of this link parameter, cannot be changed.
   * @param  pValue  The initial value of this link parameter.
   */
  public MmReferenceParam(String pName, String pValue) {
    this.name = pName;
    if (pValue == null) {
      this.value = "";
    } else {
      this.value = pValue;
    }
  }

  /**
   * Returns the name of this query parameter.
   *
   * @return  The name of this query parameter.
   *
   * @since   $maven.project.version$
   */
  @Override public String getMmName() {
    return this.name;
  }

  /**
   * Returns the value of this query parameter.
   *
   * @return  The value of this query parameter.
   *
   * @since   $maven.project.version$
   */
  @Override public String getMmValue() {
    return this.value;
  }

}
