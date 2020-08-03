package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationLocalDateTime;

/**
 * MmLocalDateTimeAnnotation annotates declarations of {@link MmLocalDateTime} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmLocalDateTimeAnnotation {

  public String id() default MmConfigurationLocalDateTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLocalDateTime.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationLocalDateTime.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationLocalDateTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationLocalDateTime.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationLocalDateTime.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationLocalDateTime.DEFAULT_STYLE_CLASSES;

  public String formatPattern() default MmConfigurationLocalDateTime.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationLocalDateTime.DEFAULT_FORMAT_MAX_LENGTH;

}
