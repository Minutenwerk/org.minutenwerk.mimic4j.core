package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTable contains configuration information for mimics of type {@link MmTable}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationTable extends MmBaseConfiguration {

  /**
   * Creates a new MmConfigurationTable instance of default values.
   */
  public MmConfigurationTable() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationTable instance from annotation.
   *
   * @param  pTableAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTable(MmTableAnnotation pTableAnnotation) {
    super(pTableAnnotation.id(), pTableAnnotation.visible(), pTableAnnotation.referenceEnabled(), pTableAnnotation.enabled(),
      pTableAnnotation.styleClasses());
  }

}
