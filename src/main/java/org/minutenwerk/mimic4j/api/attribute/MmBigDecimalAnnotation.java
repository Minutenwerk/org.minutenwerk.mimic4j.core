package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationBigDecimal;

/**
 * MmBigDecimalAnnotation annotates declarations of {@link MmBigDecimal} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmBigDecimalAnnotation {

  public String id() default MmConfigurationBigDecimal.UNDEFINED_ID;

  public boolean visible() default MmConfigurationBigDecimal.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationBigDecimal.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationBigDecimal.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationBigDecimal.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationBigDecimal.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationBigDecimal.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationBigDecimal.DEFAULT_FORMAT_MAX_LENGTH;

}
