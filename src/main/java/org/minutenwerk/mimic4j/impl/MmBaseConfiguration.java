package org.minutenwerk.mimic4j.impl;

/**
 * MmBaseConfiguration is the abstract base class of all mimic configurations.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseConfiguration {

  /** Constant for undefined state. */
  public static final String  UNDEFINED_ID          = "UNDEFINED-ID";

  /** Constant for default value of visible. */
  public static final boolean DEFAULT_IS_VISIBLE    = true;

  /** Constant for default value of readonly. */
  public static final boolean DEFAULT_IS_READONLY   = false;

  /** Constant for default value of enabled. */
  public static final boolean DEFAULT_IS_ENABLED    = true;

  /** Constant for default value CSS style classes. */
  public static final String  DEFAULT_STYLE_CLASSES = "";

  /** The HTML id of HTML tag. */
  protected String            id;

  /** True, if HTML tag of mimic is rendered visible. */
  protected boolean           visible;

  /** True, if HTML tag of mimic is rendered readonly. */
  protected boolean           readOnly;

  /** True, if HTML tag of mimic is rendered enabled. */
  protected boolean           enabled;

  /** CSS style classes. */
  protected String            styleClasses;

  /**
   * Creates a new MmBaseConfiguration instance.
   *
   * @param  pId            The HTML id of HTML tag.
   * @param  pVisible       True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly      True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled       True, if HTML tag of mimic is rendered enabled.
   * @param  pStyleClasses  The CSS style classes, separated by blank.
   */
  public MmBaseConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled, String pStyleClasses) {
    id           = pId;
    visible      = pVisible;
    readOnly     = pReadOnly;
    enabled      = pEnabled;
    styleClasses = pStyleClasses;
  }

  /**
   * Returns the HTML id of HTML tag.
   *
   * @return  The HTML id of HTML tag.
   */
  public String getId() {
    return id;
  }

  /**
   * Returns the JSF tag type for this mimic in disabled state.
   *
   * @return  The JSF tag type for this mimic in disabled state.
   */
  public abstract String getJsfTagDisabled();

  /**
   * Returns the JSF tag type for this mimic in enabled state.
   *
   * @return  The JSF tag type for this mimic in enabled state.
   */
  public abstract String getJsfTagEnabled();

  /**
   * Returns CSS style classes.
   *
   * @return  CSS style classes.
   */
  public String getStyleClasses() {
    return styleClasses;
  }

  /**
   * Returns true, if HTML tag of mimic is rendered enabled.
   *
   * @return  True, if HTML tag of mimic is rendered enabled.
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Returns true, if HTML tag of mimic is rendered readonly.
   *
   * @return  True, if HTML tag of mimic is rendered readonly.
   */
  public boolean isReadOnly() {
    return readOnly;
  }

  /**
   * Returns true, if HTML tag of mimic is rendered visible.
   *
   * @return  True, if HTML tag of mimic is rendered visible.
   */
  public boolean isVisible() {
    return visible;
  }

  /**
   * Set configuration for mimic, whether HTML tag of mimic is rendered enabled.
   *
   * @param  pEnabled  True, if HTML tag of mimic is rendered enabled.
   */
  public void setEnabled(boolean pEnabled) {
    enabled = pEnabled;
  }

  /**
   * Sets HTML id of mimic, cannot be set twice.
   *
   * @param   pId  The HTML id to be set.
   *
   * @throws  IllegalArgumentException  In case of the view id has been set already.
   */
  public void setId(String pId) {
    if (!id.equals(UNDEFINED_ID)) {
      throw new IllegalArgumentException("Id " + id + " cannot be set twice: " + pId);
    }
    id = pId;
  }

  /**
   * Set configuration for mimic, whether HTML tag of mimic is rendered readonly.
   *
   * @param  pReadOnly  True, if HTML tag of mimic is rendered readonly.
   */
  public void setReadOnly(boolean pReadOnly) {
    readOnly = pReadOnly;
  }

  /**
   * Sets specified CSS style classes, separated by blank.
   *
   * @param  pStyleClasses  The specified CSS style classes, separated by blank.
   */
  public void setStyleClasses(String pStyleClasses) {
    styleClasses = pStyleClasses;
  }

  /**
   * Set configuration for mimic, whether HTML tag of mimic is rendered visible.
   *
   * @param  pVisible  True, if HTML tag of mimic is rendered visible.
   */
  public void setVisible(boolean pVisible) {
    visible = pVisible;
  }

}
