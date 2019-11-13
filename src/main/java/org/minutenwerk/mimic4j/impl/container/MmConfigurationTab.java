package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTab contains configuration information for mimics of type {@link MmTab}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationTab extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmTabAnnotation.jsfTag()}. */
  public static final String DEFAULT_JSF_TAG = "Tab";

  /**
   * Creates a new MmConfigurationTab instance of default values.
   */
  public MmConfigurationTab() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationTab instance from annotation.
   *
   * @param  pTabAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTab(MmTabAnnotation pTabAnnotation) {
    super(pTabAnnotation.id(), pTabAnnotation.visible(), pTabAnnotation.readOnly(), pTabAnnotation.enabled(),
      pTabAnnotation.styleClasses());
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return DEFAULT_JSF_TAG;
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return DEFAULT_JSF_TAG;
  }

}
