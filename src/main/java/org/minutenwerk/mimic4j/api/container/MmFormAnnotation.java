package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationForm;

/**
 * MmFormAnnotation annotates declarations of {@link MmForm} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmFormAnnotation {

  public String id() default MmConfigurationForm.UNDEFINED_ID;

  public boolean visible() default MmConfigurationForm.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationForm.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationForm.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationForm.DEFAULT_STYLE_CLASSES;

}
