package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.container.MmTableRowAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationTableRow contains static configuration information for mimics of type {@link MmTableRow}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationTableRow extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmTableRowAnnotation.jsfTag()}. */
  public static final String DEFAULT_JSF_TAG = "TableRow";

  /**
   * Creates a new MmConfigurationTableRow instance of default values.
   */
  public MmConfigurationTableRow() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
  }

  /**
   * Creates a new MmConfigurationTableRow instance from annotation.
   *
   * @param  pTableRowAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTableRow(MmTableRowAnnotation pTableRowAnnotation) {
    super(pTableRowAnnotation.id(), pTableRowAnnotation.visible(), pTableRowAnnotation.readOnly(), pTableRowAnnotation.enabled());
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override public String getJsfTagDisabled() {
    return DEFAULT_JSF_TAG;
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override public String getJsfTagEnabled() {
    return DEFAULT_JSF_TAG;
  }
}
