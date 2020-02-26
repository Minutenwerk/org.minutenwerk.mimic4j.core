package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationListString;

/**
 * MmListStringAnnotation annotates declarations of {@link MmListString} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmListStringAnnotation {

  public String id() default MmConfigurationListString.UNDEFINED_ID;

  public boolean visible() default MmConfigurationListString.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationListString.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationListString.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationListString.DEFAULT_IS_REQUIRED;

  public String styleClasses() default MmConfigurationListString.DEFAULT_STYLE_CLASSES;

  public int size() default MmConfigurationListString.DEFAULT_SIZE;

}
