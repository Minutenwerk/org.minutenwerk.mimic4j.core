package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmDate.MmDateJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmDate.MmDateJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationDate;

/**
 * MmDateAnnotation annotates declarations of {@link MmDate} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmDateAnnotation {

  public String id() default MmConfigurationDate.UNDEFINED_ID;

  public boolean visible() default MmConfigurationDate.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationDate.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationDate.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationDate.DEFAULT_IS_REQUIRED;

  public String formatPattern() default MmConfigurationDate.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationDate.DEFAULT_FORMAT_MAX_LENGTH;

  public MmDateJsfTag jsfTag() default MmDateJsfTag.TextField;

  public MmDateJsfDisabled jsfTagDisabled() default MmDateJsfDisabled.SameAsEnabled;

}
