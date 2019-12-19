package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationPage;

/**
 * MmPageAnnotation annotates declarations of {@link MmPage} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmPageAnnotation {

  public String id() default MmConfigurationPage.UNDEFINED_ID;

  public boolean visible() default MmConfigurationPage.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationPage.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationPage.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationPage.DEFAULT_STYLE_CLASSES;

}
