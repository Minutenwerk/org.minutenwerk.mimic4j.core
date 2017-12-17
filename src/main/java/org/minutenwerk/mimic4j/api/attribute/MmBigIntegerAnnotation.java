package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmBigInteger.MmBigIntegerJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBigInteger.MmBigIntegerJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationBigInteger;

/**
 * MmBigIntegerAnnotation annotates declarations of {@link MmBigInteger} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmBigIntegerAnnotation {

  public String id() default MmConfigurationBigInteger.UNDEFINED_ID;

  public boolean visible() default MmConfigurationBigInteger.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationBigInteger.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationBigInteger.DEFAULT_IS_ENABLED;

  public long defaultValue() default MmConfigurationBigInteger.DEFAULT_DEFAULT_VALUE_AS_LONG;

  public boolean required() default MmConfigurationBigInteger.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationBigInteger.DEFAULT_FORMAT_MAX_LENGTH;

  public MmBigIntegerJsfTag jsfTag() default MmBigIntegerJsfTag.TextField;

  public MmBigIntegerJsfDisabled jsfTagDisabled() default MmBigIntegerJsfDisabled.SameAsEnabled;

}
