package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.container.MmTableRowAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTableRow contains configuration information for mimics of type {@link MmTableRow}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationTableRow extends MmBaseConfiguration {

  /**
   * Creates a new MmConfigurationTableRow instance of default values.
   */
  public MmConfigurationTableRow() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationTableRow instance from annotation.
   *
   * @param  pTableRowAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTableRow(MmTableRowAnnotation pTableRowAnnotation) {
    super(pTableRowAnnotation.id(), pTableRowAnnotation.visible(), pTableRowAnnotation.readOnly(), pTableRowAnnotation.enabled(),
      pTableRowAnnotation.styleClasses());
  }
}
