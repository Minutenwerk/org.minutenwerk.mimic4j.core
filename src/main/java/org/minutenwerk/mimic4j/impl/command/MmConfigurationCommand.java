package org.minutenwerk.mimic4j.impl.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.command.MmCommand;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkConfiguration;

/**
 * MmConfigurationCommand contains configuration information for mimics of type {@link MmCommand}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationCommand extends MmBaseLinkConfiguration {

  /** Logger of this class. */
  private static final Logger LOGGER               = LogManager.getLogger(MmConfigurationCommand.class);

  /** Constant for default value of command button submit parameter. */
  public static final String  DEFAULT_SUBMIT_PARAM = "andAction";

  /** The command button submit parameter. */
  protected String            submitParam;

  /**
   * Creates a new MmConfigurationCommand instance of default values.
   */
  public MmConfigurationCommand() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_ICON_BEFORE, DEFAULT_ICON_AFTER, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationCommand instance from annotation.
   *
   * @param  pCommandAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationCommand(MmCommandAnnotation pCommandAnnotation) {
    super(pCommandAnnotation.id(), pCommandAnnotation.visible(), pCommandAnnotation.referenceEnabled(), pCommandAnnotation.enabled(),
      pCommandAnnotation.iconBefore(), pCommandAnnotation.iconAfter(), pCommandAnnotation.targetReferencePath(), pCommandAnnotation.styleClasses());
    submitParam = DEFAULT_SUBMIT_PARAM;

    Class<? extends MmPage<?>> targetPage = pCommandAnnotation.targetPage();
    if (!targetPage.equals(MmPage.MmVoidTarget.class)) {
      try {
        String targetReferencePath = (String)targetPage.getMethod("getMmStaticSelfReferencePath").invoke(null);
        if (targetReferencePath == null) {
          LOGGER.error("class " + targetPage.getSimpleName() + " must define 'public static String getMmStaticSelfReferencePath()'");
        }
        setTargetReferencePath(targetReferencePath);
      } catch (Exception e) {
        LOGGER.error(e);
      }
    }
  }

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  public String getSubmitParam() {
    return submitParam;
  }

}
