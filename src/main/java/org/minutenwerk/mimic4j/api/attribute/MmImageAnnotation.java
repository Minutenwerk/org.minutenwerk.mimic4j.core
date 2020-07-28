package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationImage;

/**
 * MmImageAnnotation annotates declarations of {@link MmImage} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmImageAnnotation {

  public String id() default MmConfigurationImage.UNDEFINED_ID;

  public boolean visible() default MmConfigurationImage.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationImage.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationImage.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationImage.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationImage.DEFAULT_FORMAT_MAX_LENGTH;

  public String styleClasses() default MmConfigurationImage.DEFAULT_STYLE_CLASSES;

  public String fixedSrc() default MmConfigurationImage.DEFAULT_FIXED_SRC;
}
