package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationTab;

/**
 * MmTabAnnotation annotates declarations of {@link MmTab} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmTabAnnotation {

  public String id() default MmConfigurationTab.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTab.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationTab.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationTab.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationTab.DEFAULT_STYLE_CLASSES;

}
