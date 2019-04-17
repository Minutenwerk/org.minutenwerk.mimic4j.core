package org.minutenwerk.mimic4j.impl.composite;

import org.minutenwerk.mimic4j.api.composite.MmDiv.MmRootJsfTag;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.composite.MmRootAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationRoot contains static configuration information for mimics of type {@link MmRoot}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationRoot extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmRootAnnotation.jsfTag()}. */
  public static final MmRootJsfTag DEFAULT_JSF_TAG = MmRootJsfTag.Root;

  /** The JSF tag in enabled state. */
  protected MmRootJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationRoot instance of default values.
   */
  public MmConfigurationRoot() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    this.jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationRoot instance from annotation.
   *
   * @param  pRootAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationRoot(MmRootAnnotation pRootAnnotation) {
    super(pRootAnnotation.id(), pRootAnnotation.visible(), pRootAnnotation.readOnly(), pRootAnnotation.enabled());
    this.jsfTag = pRootAnnotation.jsfTag();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

}
