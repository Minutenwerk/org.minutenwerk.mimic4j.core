package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationBoolean;

/**
 * MmBooleanAnnotation annotates declarations of {@link MmBoolean} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmBooleanAnnotation {

  public String id() default MmConfigurationBoolean.UNDEFINED_ID;

  public boolean visible() default MmConfigurationBoolean.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationBoolean.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationBoolean.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationBoolean.DEFAULT_IS_REQUIRED;

  public String styleClasses() default MmConfigurationBoolean.DEFAULT_STYLE_CLASSES;

}
