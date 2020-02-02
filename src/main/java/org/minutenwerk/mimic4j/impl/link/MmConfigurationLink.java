package org.minutenwerk.mimic4j.impl.link;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.api.link.MmLink;
import org.minutenwerk.mimic4j.api.link.MmLinkAnnotation;

/**
 * MmConfigurationLink contains configuration information for mimics of type {@link MmLink}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationLink extends MmBaseLinkConfiguration {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmConfigurationLink.class);

  /**
   * Creates a new MmConfigurationLink instance of default values.
   */
  public MmConfigurationLink() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_ICON_BEFORE, DEFAULT_ICON_AFTER, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationLink instance from annotation.
   *
   * @param   pLinkAnnotation  The annotation to create the configuration from.
   *
   * @throws  IllegalArgumentException  In case of link defines neither targetPage nor targetPageMany.
   */
  public MmConfigurationLink(MmLinkAnnotation pLinkAnnotation) {
    super(pLinkAnnotation.id(), pLinkAnnotation.visible(), pLinkAnnotation.readOnly(), pLinkAnnotation.enabled(), pLinkAnnotation.iconBefore(),
      pLinkAnnotation.iconAfter(), pLinkAnnotation.targetReferencePath(), pLinkAnnotation.styleClasses());

    final Class<? extends MmPage<?>> targetPage              = pLinkAnnotation.targetPage();
    final boolean                    targetPageIsDefined     = !targetPage.equals(MmPage.MmVoidTarget.class);
    final Class<? extends MmPage<?>> targetPageMany          = pLinkAnnotation.targetPageMany();
    final boolean                    targetPageManyIsDefined = !targetPageMany.equals(MmPage.MmVoidTarget.class);
    if (targetPageIsDefined && targetPageManyIsDefined) {
      throw new IllegalArgumentException("link can define either targetPage or targetPageMany");
    }

    if (targetPageIsDefined) {
      try {
        String targetSelfReferencePath = (String)targetPage.getMethod("getMmStaticSelfReferencePath").invoke(null);
        if (targetSelfReferencePath == null) {
          LOGGER.error("class " + targetPage.getSimpleName() + " must define 'public static String getMmStaticSelfReferencePath()'");
        }
        setTargetReferencePath(targetSelfReferencePath);
      } catch (Exception e) {
        LOGGER.error(e);
      }
    } else if (targetPageManyIsDefined) {
      try {
        String targetReferencePathMany = (String)targetPageMany.getMethod("getMmStaticReferencePathMany").invoke(null);
        if (targetReferencePathMany == null) {
          LOGGER.error("class " + targetPage.getSimpleName() + " must define 'public static String getMmStaticReferencePathMany()'");
        }
        setTargetReferencePath(targetReferencePathMany);
      } catch (Exception e) {
        LOGGER.error(e);
      }
    }
  }
}
