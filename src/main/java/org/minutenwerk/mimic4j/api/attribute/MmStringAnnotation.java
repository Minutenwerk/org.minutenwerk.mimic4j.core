package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmString.MmStringJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmString.MmStringJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationString;

/**
 * MmStringAnnotation annotates declarations of {@link MmString} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmStringAnnotation {

  public String id() default MmConfigurationString.UNDEFINED_ID;

  public boolean visible() default MmConfigurationString.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationString.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationString.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationString.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationString.DEFAULT_FORMAT_MAX_LENGTH;

  public int cols() default MmConfigurationString.DEFAULT_COLS;

  public int rows() default MmConfigurationString.DEFAULT_ROWS;

  public MmStringJsfTag jsfTag() default MmStringJsfTag.TextField;

  public MmStringJsfDisabled jsfTagDisabled() default MmStringJsfDisabled.SameAsEnabled;
}
