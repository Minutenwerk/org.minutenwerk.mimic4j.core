package org.minutenwerk.mimic4j.impl.composite;

import org.minutenwerk.mimic4j.api.composite.MmDiv;
import org.minutenwerk.mimic4j.api.composite.MmDiv.MmDivJsfTag;
import org.minutenwerk.mimic4j.api.composite.MmDivAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationDiv contains static configuration information for mimics of type {@link MmDiv}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationDiv extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmDivAnnotation.jsfTag()}. */
  public static final MmDivJsfTag DEFAULT_JSF_TAG = MmDivJsfTag.Div;

  /** The JSF tag in enabled state. */
  protected MmDivJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationDiv instance of default values.
   */
  public MmConfigurationDiv() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    this.jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationDiv instance from annotation.
   *
   * @param  pDivAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationDiv(MmDivAnnotation pDivAnnotation) {
    super(pDivAnnotation.id(), pDivAnnotation.visible(), pDivAnnotation.readOnly(), pDivAnnotation.enabled());
    this.jsfTag = pDivAnnotation.jsfTag();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmDivJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
