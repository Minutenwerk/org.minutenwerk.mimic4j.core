package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationInstant;

/**
 * MmInstantAnnotation annotates declarations of {@link MmInstant} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmInstantAnnotation {

  public String id() default MmConfigurationInstant.UNDEFINED_ID;

  public boolean visible() default MmConfigurationInstant.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationInstant.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationInstant.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationInstant.DEFAULT_IS_REQUIRED;

  public String styleClasses() default MmConfigurationInstant.DEFAULT_STYLE_CLASSES;

  public String formatPattern() default MmConfigurationInstant.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationInstant.DEFAULT_FORMAT_MAX_LENGTH;

}
