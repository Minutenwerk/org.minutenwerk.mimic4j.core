package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmForm;
import org.minutenwerk.mimic4j.api.container.MmFormAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationForm contains configuration information for mimics of type {@link MmForm}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationForm extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmFormAnnotation.jsfTag()}. */
  public static final String DEFAULT_JSF_TAG = "Form";

  /**
   * Creates a new MmConfigurationForm instance of default values.
   */
  public MmConfigurationForm() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationForm instance from annotation.
   *
   * @param  pFormAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationForm(MmFormAnnotation pFormAnnotation) {
    super(pFormAnnotation.id(), pFormAnnotation.visible(), pFormAnnotation.readOnly(), pFormAnnotation.enabled(),
      pFormAnnotation.styleClasses());
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
