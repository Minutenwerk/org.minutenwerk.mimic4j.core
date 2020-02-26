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

  /**
   * Creates a new MmConfigurationForm instance of default values.
   */
  public MmConfigurationForm() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationForm instance from annotation.
   *
   * @param  pFormAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationForm(MmFormAnnotation pFormAnnotation) {
    super(pFormAnnotation.id(), pFormAnnotation.visible(), pFormAnnotation.referenceEnabled(), pFormAnnotation.enabled(), pFormAnnotation.styleClasses());
  }

}
