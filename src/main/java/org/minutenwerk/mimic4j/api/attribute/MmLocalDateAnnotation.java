package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmLocalDate.MmDateJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate.MmDateJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationLocalDate;

/**
 * MmLocalDateAnnotation annotates declarations of {@link MmLocalDate} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmLocalDateAnnotation {

  public String id() default MmConfigurationLocalDate.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLocalDate.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLocalDate.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLocalDate.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationLocalDate.DEFAULT_IS_REQUIRED;

  public String formatPattern() default MmConfigurationLocalDate.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationLocalDate.DEFAULT_FORMAT_MAX_LENGTH;

  public MmDateJsfTag jsfTag() default MmDateJsfTag.TextField;

  public MmDateJsfDisabled jsfTagDisabled() default MmDateJsfDisabled.SameAsEnabled;

}
