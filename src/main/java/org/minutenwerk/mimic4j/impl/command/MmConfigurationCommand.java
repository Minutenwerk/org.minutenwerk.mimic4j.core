package org.minutenwerk.mimic4j.impl.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.command.MmCommand;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfDisabled;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfTag;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkConfiguration;

/**
 * MmConfigurationCommand contains configuration information for mimics of type {@link MmCommand}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationCommand extends MmBaseLinkConfiguration {

  /** Logger of this class. */
  private static final Logger              LOGGER                   = LogManager.getLogger(MmConfigurationCommand.class);

  /** Constant for default value of command button submit parameter. */
  public static final String               DEFAULT_SUBMIT_PARAM     = "andAction";

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmCommandAnnotation.jsfTag()}. */
  public static final MmCommandJsfTag      DEFAULT_JSF_TAG          = MmCommandJsfTag.CommandButton;

  /** Constant for default value of JSF tag in disabled state. Redundant to {@link MmCommandAnnotation.jsfTag()}. */
  public static final MmCommandJsfDisabled DEFAULT_JSF_TAG_DISABLED = MmCommandJsfDisabled.SameAsEnabled;

  /** The configuration of JSF tag in enabled state. */
  protected MmCommandJsfTag                jsfTag;

  /** The configuration of JSF tag in disabled state. */
  protected MmCommandJsfDisabled           jsfTagDisabled;

  /** The command button submit parameter. */
  protected String                         submitParam;

  /**
   * Creates a new MmConfigurationCommand instance of default values.
   */
  public MmConfigurationCommand() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    ;
    jsfTag         = DEFAULT_JSF_TAG;
    jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationCommand instance from annotation.
   *
   * @param  pCommandAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationCommand(MmCommandAnnotation pCommandAnnotation) {
    super(pCommandAnnotation.id(), pCommandAnnotation.visible(), pCommandAnnotation.readOnly(), pCommandAnnotation.enabled(),
      pCommandAnnotation.targetReferencePath());
    submitParam    = DEFAULT_SUBMIT_PARAM;
    jsfTag         = pCommandAnnotation.jsfTag();
    jsfTagDisabled = pCommandAnnotation.jsfTagDisabled();

    Class<? extends MmLeporello<?, ?>> targetLeporello = pCommandAnnotation.targetLeporello();
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
    return jsfTagDisabled.name();
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

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  public String getSubmitParam() {
    return submitParam;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmCommandJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

}
