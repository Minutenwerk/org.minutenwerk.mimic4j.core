package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmDouble.MmDoubleJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmDouble.MmDoubleJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationDouble;

/**
 * MmDoubleAnnotation annotates declarations of {@link MmDouble} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmDoubleAnnotation {

  public String id() default MmConfigurationDouble.UNDEFINED_ID;

  public boolean visible() default MmConfigurationDouble.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationDouble.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationDouble.DEFAULT_IS_ENABLED;

  public double defaultValue() default MmConfigurationDouble.DEFAULT_DEFAULT_VALUE;

  public boolean required() default MmConfigurationDouble.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationDouble.DEFAULT_FORMAT_MAX_LENGTH;

  public MmDoubleJsfTag jsfTag() default MmDoubleJsfTag.TextField;

  public MmDoubleJsfDisabled jsfTagDisabled() default MmDoubleJsfDisabled.SameAsEnabled;

}
