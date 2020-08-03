package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmBaseAttributeConfiguration is the abstract base class for configuration of all attribute mimic classes.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseAttributeConfiguration<ATTRIBUTE_TYPE> extends MmBaseConfiguration {

  /** Constant for default value of required. */
  public static final boolean DEFAULT_IS_REQUIRED             = false;

  /** Constant for default value of transient data model. */
  public static final boolean DEFAULT_IS_TRANSIENT_DATA_MODEL = false;

  /** True, if input is required. */
  protected boolean           required;

  /** True, if view value is stored only in transient data model. */
  protected boolean           transientDataModel;

  /**
   * Creates a new MmBaseAttributeConfiguration instance from annotation.
   *
   * @param  pId                  The HTML id of HTML tag.
   * @param  pVisible             True, if HTML tag of mimic is rendered visible.
   * @param  pReferenceEnabled    True, if HTML tag of mimic is rendered reference enabled.
   * @param  pEnabled             True, if HTML tag of mimic is rendered enabled.
   * @param  pRequired            True, if input is required.
   * @param  pTransientDataModel  True, if view value is stored only in transient data model.
   * @param  pStyleClasses        The CSS style classes, separated by blank.
   */
  public MmBaseAttributeConfiguration(String pId, boolean pVisible, boolean pReferenceEnabled, boolean pEnabled, boolean pRequired,
    boolean pTransientDataModel, String pStyleClasses) {
    super(pId, pVisible, pReferenceEnabled, pEnabled, pStyleClasses);
    required           = pRequired;
    transientDataModel = pTransientDataModel;
  }

  /**
   * Returns the configuration of input is required.
   *
   * @return  True, if input is required.
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * Returns the configuration of view value is stored only in transient data model.
   *
   * @return  True, if view value is stored only in transient data model.
   */
  public boolean isTransientDataModel() {
    return transientDataModel;
  }

  /**
   * Set configuration for mimic, whether input is required.
   *
   * @param  pRequired  True, if input is required.
   */
  public void setRequired(boolean pRequired) {
    required = pRequired;
  }

  /**
   * Set configuration for mimic, whether view value is stored only in transient data model.
   *
   * @param  pTransientDataModel  True, if view value is stored only in transient data model.
   */
  public void setTransientDataModel(boolean pTransientDataModel) {
    transientDataModel = pTransientDataModel;
  }

}
