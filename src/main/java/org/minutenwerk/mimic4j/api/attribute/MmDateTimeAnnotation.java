package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmDateTime.MmDateTimeJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmDateTime.MmDateTimeJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationDateTime;

/**
 * MmDateTimeAnnotation annotates declarations of {@link MmDateTime} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmDateTimeAnnotation {

  public String id() default MmConfigurationDateTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationDateTime.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationDateTime.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationDateTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationDateTime.DEFAULT_IS_REQUIRED;

  public String formatPattern() default MmConfigurationDateTime.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationDateTime.DEFAULT_FORMAT_MAX_LENGTH;

  public MmDateTimeJsfTag jsfTag() default MmDateTimeJsfTag.TextField;

  public MmDateTimeJsfDisabled jsfTagDisabled() default MmDateTimeJsfDisabled.SameAsEnabled;

}
