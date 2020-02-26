package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmBaseAttributeConfiguration is the abstract base class for configuration of all attribute mimic classes.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseAttributeConfiguration<ATTRIBUTE_TYPE> extends MmBaseConfiguration {

  /** Constant for default value of required. */
  public static final boolean DEFAULT_IS_REQUIRED = false;

  /** True, if input is required. */
  protected boolean           required;

  /**
   * Creates a new MmBaseAttributeConfiguration instance from annotation.
   *
   * @param  pId                The HTML id of HTML tag.
   * @param  pVisible           True, if HTML tag of mimic is rendered visible.
   * @param  pReferenceEnabled  True, if HTML tag of mimic is rendered reference enabled.
   * @param  pEnabled           True, if HTML tag of mimic is rendered enabled.
   * @param  pRequired          True, if input is required.
   * @param  pStyleClasses      TODOC
   */
  public MmBaseAttributeConfiguration(String pId, boolean pVisible, boolean pReferenceEnabled, boolean pEnabled, boolean pRequired, String pStyleClasses) {
    super(pId, pVisible, pReferenceEnabled, pEnabled, pStyleClasses);
    required = pRequired;
  }

  /**
   * Returns the configuration of input is required.
   *
   * @return  The configuration of input is required.
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * Set configuration for mimic, whether input is required.
   *
   * @param  pRequired  True, if input is required.
   */
  public void setRequired(boolean pRequired) {
    required = pRequired;
  }

}
