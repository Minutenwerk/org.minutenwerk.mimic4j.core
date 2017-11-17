package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmBaseAttributeConfiguration is the abstract base class for configuration of all attribute mimic classes.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public abstract class MmBaseAttributeConfiguration<MODELSIDE_VALUE> extends MmBaseConfiguration {

  /** Constant for default value of required. */
  public static final boolean DEFAULT_IS_REQUIRED = false;

  /** True, if input is required. */
  protected boolean           required;

  /**
   * Creates a new MmBaseAttributeConfiguration instance from annotation.
   *
   * @param  pId        The JSF view id of HTML tag.
   * @param  pVisible   True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly  True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled   True, if HTML tag of mimic is rendered enabled.
   * @param  pRequired  True, if input is required.
   */
  public MmBaseAttributeConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled, boolean pRequired) {
    super(pId, pVisible, pReadOnly, pEnabled);
    this.required = pRequired;
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   *
   * @since   $maven.project.version$
   */
  public abstract MODELSIDE_VALUE getDefaultValue();

  /**
   * Returns the configuration of input is required.
   *
   * @return  The configuration of input is required.
   *
   * @since   $maven.project.version$
   */
  public boolean isRequired() {
    return this.required;
  }

}
