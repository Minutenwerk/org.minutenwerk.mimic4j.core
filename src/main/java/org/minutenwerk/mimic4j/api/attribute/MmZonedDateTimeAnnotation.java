package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationZonedDateTime;

/**
 * MmZonedDateTimeAnnotation annotates declarations of {@link MmZonedDateTime} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmZonedDateTimeAnnotation {

  public String id() default MmConfigurationZonedDateTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationZonedDateTime.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationZonedDateTime.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationZonedDateTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationZonedDateTime.DEFAULT_IS_REQUIRED;

  public String styleClasses() default MmConfigurationZonedDateTime.DEFAULT_STYLE_CLASSES;

  public String formatPattern() default MmConfigurationZonedDateTime.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationZonedDateTime.DEFAULT_FORMAT_MAX_LENGTH;

}
