package org.minutenwerk.mimic4j.api.link;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.link.MmLink.MmLinkJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.link.MmConfigurationLink;

/**
 * MmLinkAnnotation annotates declarations of {@link MmLink} by static configuration values.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLinkAnnotation {

  public String id() default MmConfigurationLink.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLink.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLink.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLink.DEFAULT_IS_ENABLED;

  public MmLinkJsfTag jsfTag() default MmLinkJsfTag.Link;

  public String targetOutcome() default MmConfigurationLink.DEFAULT_TARGET_OUTCOME;

}
