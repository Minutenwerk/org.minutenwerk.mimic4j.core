package org.minutenwerk.mimic4j.impl.attribute;

/**
 * An option for an attribute value.
 *
 * @author  Olaf Kossak
 */
public class MmSelectOption<OPTION_VALUE_TYPE> {

  /** The id. */
  protected final String            id;

  /** The short description. */
  protected final String            shortDescription;

  /** The long description. */
  protected final String            longDescription;

  /** The value. */
  protected final OPTION_VALUE_TYPE value;

  /** True, if the select option is enabled to be selected. */
  protected boolean                 enabled;

  /**
   * Creates a new MmOptionImpl instance.
   *
   * @param  pId                The id.
   * @param  pShortDescription  The short description.
   * @param  pLongDescription   The long description.
   * @param  pValue             The value.
   */
  public MmSelectOption(String pId, String pShortDescription, String pLongDescription, OPTION_VALUE_TYPE pValue) {
    id               = pId;
    shortDescription = pShortDescription;
    longDescription  = pLongDescription;
    value            = pValue;
    enabled          = true;
  }

  /**
   * Returns true, if this and the specified other object are equal in technical sense.
   *
   * @param   pThat  The other object to be compared.
   *
   * @return  True, if both objects are the same.
   */
  @Override
  public boolean equals(Object pThat) {
    if (this == pThat) {
      return true;
    } else if ((pThat == null) || !(pThat instanceof MmSelectOption<?>)) {
      return false;
    } else if ((id != null) && id.equals(((MmSelectOption<?>)pThat).getDisplayId())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the display id of this select option.
   *
   * @return  The display id of this select option.
   */
  public String getDisplayId() {
    return id;
  }

  /**
   * Returns the long description of this select option.
   *
   * @return  The long description of this select option.
   */
  public String getLongDescription() {
    return longDescription;
  }

  /**
   * Returns the short description of this select option.
   *
   * @return  The short description of this select option.
   */
  public String getShortDescription() {
    return shortDescription;
  }

  /**
   * Returns the value of this select option, will be synchronized with mimic view value.
   *
   * @return  The value of this select option.
   */
  public OPTION_VALUE_TYPE getValue() {
    return value;
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hashtables such as those provided by <code>
   * java.util.Hashtable</code>.
   *
   * @return  The hash value of this Java object.
   */
  @Override
  public int hashCode() {
    return (id != null) ? id.hashCode() : super.hashCode();
  }

  /**
   * Returns true, if this select option is enabled to be selected.
   *
   * @return  True, if this select option can be selected.
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets state of this select option to be or to be not selectable.
   *
   * @param  pEnabled  The specified select state.
   */
  public void setEnabled(boolean pEnabled) {
    enabled = pEnabled;
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change at any time.
   *
   * @return  Some information about this object for development purposes like debugging and logging.
   */
  @Override
  public String toString() {
    StringBuilder snippet = new StringBuilder();
    snippet.append("id=");
    snippet.append(id);
    snippet.append(", short=");
    snippet.append(shortDescription);
    snippet.append(", long=");
    snippet.append(longDescription);
    snippet.append(", enabled=");
    snippet.append(enabled);
    snippet.append(", value=");
    snippet.append(value);
    return snippet.toString();
  }

}
