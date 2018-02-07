package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmLocalTime.MmTimeJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTime.MmTimeJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationLocalTime;

/**
 * MmLocalTimeAnnotation annotates declarations of {@link MmLocalTime} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLocalTimeAnnotation {

  public String id() default MmConfigurationLocalTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLocalTime.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLocalTime.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLocalTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationLocalTime.DEFAULT_IS_REQUIRED;

  public String formatPattern() default MmConfigurationLocalTime.DEFAULT_FORMAT_PATTERN;

  public int formatMaxLength() default MmConfigurationLocalTime.DEFAULT_FORMAT_MAX_LENGTH;

  public MmTimeJsfTag jsfTag() default MmTimeJsfTag.TextField;

  public MmTimeJsfDisabled jsfTagDisabled() default MmTimeJsfDisabled.SameAsEnabled;

}
