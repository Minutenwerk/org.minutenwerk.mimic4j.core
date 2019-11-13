package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmLong.MmLongJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLong.MmLongJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationLong;

/**
 * MmLongAnnotation annotates declarations of {@link MmLong} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLongAnnotation {

  public String id() default MmConfigurationLong.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLong.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLong.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLong.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationLong.DEFAULT_IS_REQUIRED;

  public String styleClasses() default MmConfigurationLong.DEFAULT_STYLE_CLASSES;

  public int formatMaxLength() default MmConfigurationLong.DEFAULT_FORMAT_MAX_LENGTH;

  public MmLongJsfTag jsfTag() default MmLongJsfTag.TextField;

  public MmLongJsfDisabled jsfTagDisabled() default MmLongJsfDisabled.SameAsEnabled;

}
