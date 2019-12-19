package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.api.container.MmPageAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationPage contains configuration information for mimics of type {@link MmPage}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationPage extends MmBaseConfiguration {

  /**
   * Creates a new MmConfigurationPage instance of default values.
   */
  public MmConfigurationPage() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationPage instance from annotation.
   *
   * @param  pPageAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationPage(MmPageAnnotation pPageAnnotation) {
    super(pPageAnnotation.id(), pPageAnnotation.visible(), pPageAnnotation.readOnly(), pPageAnnotation.enabled(), pPageAnnotation.styleClasses());
  }

}
