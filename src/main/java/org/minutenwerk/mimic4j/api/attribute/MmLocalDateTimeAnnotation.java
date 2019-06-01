package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTime.MmLocalDateTimeJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTime.MmLocalDateTimeJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationLocalDateTime;

/**
 * MmLocalDateTimeAnnotation annotates declarations of {@link MmLocalDateTime} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLocalDateTimeAnnotation {

  public String id() default MmConfigurationLocalDateTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLocalDateTime.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLocalDateTime.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLocalDateTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationLocalDateTime.DEFAULT_IS_REQUIRED;

  public String formatPattern() default MmConfigurationLocalDateTime.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationLocalDateTime.DEFAULT_FORMAT_MAX_LENGTH;

  public MmLocalDateTimeJsfTag jsfTag() default MmLocalDateTimeJsfTag.TextField;

  public MmLocalDateTimeJsfDisabled jsfTagDisabled() default MmLocalDateTimeJsfDisabled.SameAsEnabled;

}
