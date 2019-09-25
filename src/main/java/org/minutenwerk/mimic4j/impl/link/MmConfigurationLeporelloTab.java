package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab.MmLeporelloTabJsfTag;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTabAnnotation;

/**
 * MmConfigurationLeporelloTab contains fixed configuration information for mimics of type {@link MmLeporelloTab}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLeporelloTab extends MmBaseLinkConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmLeporelloTabAnnotation.jsfTag()}. */
  public static final MmLeporelloTabJsfTag DEFAULT_JSF_TAG = MmLeporelloTabJsfTag.LeporelloTab;

  /** The JSF tag in enabled state. */
  protected MmLeporelloTabJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationLeporelloTab instance of default values.
   */
  public MmConfigurationLeporelloTab() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationLeporelloTab instance from annotation.
   *
   * @param  pLeporelloTabAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLeporelloTab(MmLeporelloTabAnnotation pLeporelloTabAnnotation) {
    super(pLeporelloTabAnnotation.id(), pLeporelloTabAnnotation.visible(), pLeporelloTabAnnotation.readOnly(),
      pLeporelloTabAnnotation.enabled(), pLeporelloTabAnnotation.targetReferencePath());
    jsfTag = pLeporelloTabAnnotation.jsfTag();
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
