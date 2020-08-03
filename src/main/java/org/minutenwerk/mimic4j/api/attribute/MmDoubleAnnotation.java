package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationDouble;

/**
 * MmDoubleAnnotation annotates declarations of {@link MmDouble} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmDoubleAnnotation {

  public String id() default MmConfigurationDouble.UNDEFINED_ID;

  public boolean visible() default MmConfigurationDouble.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationDouble.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationDouble.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationDouble.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationDouble.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationDouble.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationDouble.DEFAULT_FORMAT_MAX_LENGTH;

}
