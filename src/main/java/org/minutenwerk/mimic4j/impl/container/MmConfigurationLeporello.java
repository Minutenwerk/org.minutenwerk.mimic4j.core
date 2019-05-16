package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporello.MmLeporelloJsfTag;
import org.minutenwerk.mimic4j.api.container.MmLeporelloAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationLeporello contains static configuration information for mimics of type {@link MmLeporello}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLeporello extends MmBaseConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmLeporelloAnnotation.jsfTag()}. */
  public static final MmLeporelloJsfTag DEFAULT_JSF_TAG = MmLeporelloJsfTag.Leporello;

  /** The JSF tag in enabled state. */
  protected MmLeporelloJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationLeporello instance of default values.
   */
  public MmConfigurationLeporello() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationLeporello instance from annotation.
   *
   * @param  pLeporelloAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLeporello(MmLeporelloAnnotation pLeporelloAnnotation) {
    super(pLeporelloAnnotation.id(), pLeporelloAnnotation.visible(), pLeporelloAnnotation.readOnly(), pLeporelloAnnotation.enabled());
    jsfTag = pLeporelloAnnotation.jsfTag();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return jsfTag.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return jsfTag.name();
  }

}
