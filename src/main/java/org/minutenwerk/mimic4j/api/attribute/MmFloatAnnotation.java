package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmFloat.MmFloatJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmFloat.MmFloatJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationFloat;

/**
 * MmFloatAnnotation annotates declarations of {@link MmFloat} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmFloatAnnotation {

  public String id() default MmConfigurationFloat.UNDEFINED_ID;

  public boolean visible() default MmConfigurationFloat.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationFloat.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationFloat.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationFloat.DEFAULT_IS_REQUIRED;

  public int formatMaxLength() default MmConfigurationFloat.DEFAULT_FORMAT_MAX_LENGTH;

  public MmFloatJsfTag jsfTag() default MmFloatJsfTag.TextField;

  public MmFloatJsfDisabled jsfTagDisabled() default MmFloatJsfDisabled.SameAsEnabled;

}
