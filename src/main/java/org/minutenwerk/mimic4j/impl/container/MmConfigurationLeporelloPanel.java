package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel.MmLeporelloPanelJsfTag;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanelAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationLeporelloPanel contains static configuration information for mimics of type {@link MmLeporelloPanel}.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmConfigurationLeporelloPanel extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmLeporelloPanelAnnotation.jsfTag()}. */
  public static final MmLeporelloPanelJsfTag DEFAULT_JSF_TAG = MmLeporelloPanelJsfTag.LeporelloPanel;

  /** The JSF tag in enabled state. */
  protected MmLeporelloPanelJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationLeporelloPanel instance of default values.
   */
  public MmConfigurationLeporelloPanel() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    this.jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationLeporelloPanel instance from annotation.
   *
   * @param  pLeporelloPanelAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLeporelloPanel(MmLeporelloPanelAnnotation pLeporelloPanelAnnotation) {
    super(pLeporelloPanelAnnotation.id(), pLeporelloPanelAnnotation.visible(), pLeporelloPanelAnnotation.readOnly(),
      pLeporelloPanelAnnotation.enabled());
    this.jsfTag = pLeporelloPanelAnnotation.jsfTag();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

}
