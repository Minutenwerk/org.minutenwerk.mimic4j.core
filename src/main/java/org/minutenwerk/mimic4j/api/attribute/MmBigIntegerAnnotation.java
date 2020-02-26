package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationBigInteger;

/**
 * MmBigIntegerAnnotation annotates declarations of {@link MmBigInteger} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmBigIntegerAnnotation {

  public String id() default MmConfigurationBigInteger.UNDEFINED_ID;

  public boolean visible() default MmConfigurationBigInteger.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationBigInteger.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationBigInteger.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationBigInteger.DEFAULT_IS_REQUIRED;

  public String styleClasses() default MmConfigurationBigInteger.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationBigInteger.DEFAULT_FORMAT_MAX_LENGTH;

}
