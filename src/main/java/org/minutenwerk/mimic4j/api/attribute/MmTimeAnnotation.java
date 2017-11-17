package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmTime.MmTimeJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmTime.MmTimeJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationTime;

/**
 * MmTimeAnnotation annotates declarations of {@link MmTime} by static configuration values.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmTimeAnnotation {

  public String id() default MmConfigurationTime.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTime.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationTime.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationTime.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationTime.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationTime.DEFAULT_FORMAT_MAX_LENGTH;

  public String formatPattern() default MmConfigurationTime.DEFAULT_FORMAT_PATTERN;

  public MmTimeJsfTag jsfTag() default MmTimeJsfTag.TextField;

  public MmTimeJsfDisabled jsfTagDisabled() default MmTimeJsfDisabled.SameAsEnabled;

}
