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

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmTableAnnotation.jsfTag()}. */
  public static final String DEFAULT_JSF_TAG = "Table";

  /**
   * Creates a new MmConfigurationTable instance of default values.
   */
  public MmConfigurationTable() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
  }

  /**
   * Creates a new MmConfigurationTable instance from annotation.
   *
   * @param  pTableAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationTable(MmTableAnnotation pTableAnnotation) {
    super(pTableAnnotation.id(), pTableAnnotation.visible(), pTableAnnotation.readOnly(), pTableAnnotation.enabled());
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
