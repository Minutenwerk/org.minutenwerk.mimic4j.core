package org.minutenwerk.mimic4j.impl.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab.MmLeporelloTabJsfTag;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTabAnnotation;

/**
 * MmConfigurationLeporelloTab contains configuration information for mimics of type {@link MmLeporelloTab}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLeporelloTab extends MmBaseLinkConfiguration {

  /** Logger of this class. */
  private static final Logger              LOGGER          = LogManager.getLogger(MmConfigurationLeporelloTab.class);

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmLeporelloTabAnnotation.jsfTag()}. */
  public static final MmLeporelloTabJsfTag DEFAULT_JSF_TAG = MmLeporelloTabJsfTag.LeporelloTab;

  /** The JSF tag in enabled state. */
  protected MmLeporelloTabJsfTag           jsfTag;

  /**
   * Creates a new MmConfigurationLeporelloTab instance of default values.
   */
  public MmConfigurationLeporelloTab() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_STYLE_CLASSES);
    jsfTag = DEFAULT_JSF_TAG;
  }

  /**
   * Creates a new MmConfigurationLeporelloTab instance from annotation.
   *
   * @param  pLeporelloTabAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationLeporelloTab(MmLeporelloTabAnnotation pLeporelloTabAnnotation) {
    super(pLeporelloTabAnnotation.id(), pLeporelloTabAnnotation.visible(), pLeporelloTabAnnotation.readOnly(),
      pLeporelloTabAnnotation.enabled(), pLeporelloTabAnnotation.targetReferencePath(), pLeporelloTabAnnotation.styleClasses());
    jsfTag = pLeporelloTabAnnotation.jsfTag();

    Class<? extends MmLeporello<?, ?>> targetLeporello = pLeporelloTabAnnotation.targetLeporello();
    if (!targetLeporello.equals(MmLeporello.MmVoidTarget.class)) {
      try {
        String targetReferencePath = (String)targetLeporello.getMethod("getMmStaticReferencePath").invoke(null);
        if (targetReferencePath == null) {
          LOGGER.error("class " + targetLeporello.getSimpleName() + " must define 'public static String getMmStaticReferencePath()'");
        }
        setTargetReferencePath(targetReferencePath);
      } catch (Exception e) {
        LOGGER.error(e);
      }
    }
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
