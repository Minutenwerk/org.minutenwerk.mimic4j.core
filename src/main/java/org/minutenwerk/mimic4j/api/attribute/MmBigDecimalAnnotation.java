package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal.MmBigDecimalJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBigDecimal.MmBigDecimalJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationBigDecimal;

/**
 * MmBigDecimalAnnotation annotates declarations of {@link MmBigDecimal} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmBigDecimalAnnotation {

  public String id() default MmConfigurationBigDecimal.UNDEFINED_ID;

  public boolean visible() default MmConfigurationBigDecimal.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationBigDecimal.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationBigDecimal.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationBigDecimal.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationBigDecimal.DEFAULT_FORMAT_MAX_LENGTH;

  public MmBigDecimalJsfTag jsfTag() default MmBigDecimalJsfTag.TextField;

  public MmBigDecimalJsfDisabled jsfTagDisabled() default MmBigDecimalJsfDisabled.SameAsEnabled;

}
