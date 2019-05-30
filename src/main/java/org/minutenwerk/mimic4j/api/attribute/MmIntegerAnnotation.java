package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmInteger.MmIntegerJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmInteger.MmIntegerJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationInteger;

/**
 * MmIntegerAnnotation annotates declarations of {@link MmInteger} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmIntegerAnnotation {

  public String id() default MmConfigurationInteger.UNDEFINED_ID;

  public boolean visible() default MmConfigurationInteger.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationInteger.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationInteger.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationInteger.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationInteger.DEFAULT_FORMAT_MAX_LENGTH;

  public MmIntegerJsfTag jsfTag() default MmIntegerJsfTag.TextField;

  public MmIntegerJsfDisabled jsfTagDisabled() default MmIntegerJsfDisabled.SameAsEnabled;

}
