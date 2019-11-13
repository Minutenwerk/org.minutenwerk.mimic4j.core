package org.minutenwerk.mimic4j.api.link;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporello.MmVoidTarget;
import org.minutenwerk.mimic4j.api.link.MmLink.MmLinkJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.link.MmConfigurationLink;

/**
 * MmLinkAnnotation annotates declarations of {@link MmLink} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLinkAnnotation {

  public String id() default MmConfigurationLink.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLink.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLink.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLink.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationLink.DEFAULT_STYLE_CLASSES;

  public MmLinkJsfTag jsfTag() default MmLinkJsfTag.Link;

  public String targetReferencePath() default MmConfigurationLink.DEFAULT_TARGET_REFERENCE_PATH;

  public Class<? extends MmLeporello<?, ?>> targetLeporello() default MmVoidTarget.class;

}
