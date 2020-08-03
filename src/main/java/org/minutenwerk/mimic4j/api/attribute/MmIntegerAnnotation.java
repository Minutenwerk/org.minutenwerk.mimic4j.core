package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationInteger;

/**
 * MmIntegerAnnotation annotates declarations of {@link MmInteger} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmIntegerAnnotation {

  public String id() default MmConfigurationInteger.UNDEFINED_ID;

  public boolean visible() default MmConfigurationInteger.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationInteger.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationInteger.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationInteger.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationInteger.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationInteger.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationInteger.DEFAULT_FORMAT_MAX_LENGTH;

}
