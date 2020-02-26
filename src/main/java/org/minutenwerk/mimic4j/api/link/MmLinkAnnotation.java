package org.minutenwerk.mimic4j.api.link;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.api.container.MmPage.MmVoidTarget;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.link.MmConfigurationLink;

/**
 * MmLinkAnnotation annotates declarations of {@link MmLink} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmLinkAnnotation {

  public String id() default MmConfigurationLink.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLink.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationLink.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationLink.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationLink.DEFAULT_STYLE_CLASSES;

  public String iconBefore() default MmConfigurationLink.DEFAULT_ICON_BEFORE;

  public String iconAfter() default MmConfigurationLink.DEFAULT_ICON_AFTER;

  public String targetReferencePath() default MmConfigurationLink.DEFAULT_TARGET_REFERENCE_PATH;

  public Class<? extends MmPage<?>> targetPage() default MmVoidTarget.class;

  public Class<? extends MmPage<?>> targetPageMany() default MmVoidTarget.class;

}
