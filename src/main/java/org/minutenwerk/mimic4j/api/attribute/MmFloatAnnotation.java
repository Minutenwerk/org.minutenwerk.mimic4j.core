package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationFloat;

/**
 * MmFloatAnnotation annotates declarations of {@link MmFloat} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmFloatAnnotation {

  public String id() default MmConfigurationFloat.UNDEFINED_ID;

  public boolean visible() default MmConfigurationFloat.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationFloat.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationFloat.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationFloat.DEFAULT_IS_REQUIRED;

  public boolean transientDataModel() default MmConfigurationFloat.DEFAULT_IS_TRANSIENT_DATA_MODEL;

  public String styleClasses() default MmConfigurationFloat.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationFloat.DEFAULT_FORMAT_MAX_LENGTH;

}
