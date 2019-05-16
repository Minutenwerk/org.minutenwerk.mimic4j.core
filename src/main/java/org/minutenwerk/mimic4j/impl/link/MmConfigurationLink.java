package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.api.link.MmLink;
import org.minutenwerk.mimic4j.api.link.MmLink.MmLinkJsfTag;
import org.minutenwerk.mimic4j.api.link.MmLinkAnnotation;

/**
 * MmConfigurationLink contains static configuration information for mimics of type {@link MmLink}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLink extends MmBaseLinkConfiguration {

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmLinkAnnotation.jsfTag()}. */
  public static final MmLinkJsfTag DEFAULT_JSF_TAG = MmLinkJsfTag.Link;

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmLinkAnnotation.jsfTag()}. */
  protected MmLinkJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationLink instance of default values.
   */
  public MmConfigurationLink() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationLink instance from annotation.
   *
   * @param  pLinkAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLink(MmLinkAnnotation pLinkAnnotation) {
    super(pLinkAnnotation.id(), pLinkAnnotation.visible(), pLinkAnnotation.readOnly(), pLinkAnnotation.enabled(),
      pLinkAnnotation.targetOutcome());
    jsfTag = pLinkAnnotation.jsfTag();
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
