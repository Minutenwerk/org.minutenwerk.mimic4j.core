package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationDuration;

/**
 * MmDurationAnnotation annotates declarations of {@link MmDuration} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmDurationAnnotation {

  public String id() default MmConfigurationDuration.UNDEFINED_ID;

  public boolean visible() default MmConfigurationDuration.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationDuration.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationDuration.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationDuration.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationDuration.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationDuration.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationDuration.DEFAULT_FORMAT_MAX_LENGTH;

}
