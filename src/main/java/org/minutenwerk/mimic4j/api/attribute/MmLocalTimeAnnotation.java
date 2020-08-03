package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationLocalTime;

/**
 * MmLocalTimeAnnotation annotates declarations of {@link MmLocalTime} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmLocalTimeAnnotation {

  public String id() default MmConfigurationLocalTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLocalTime.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationLocalTime.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationLocalTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationLocalTime.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationLocalTime.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationLocalTime.DEFAULT_STYLE_CLASSES;

  public String formatPattern() default MmConfigurationLocalTime.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationLocalTime.DEFAULT_FORMAT_MAX_LENGTH;

}
