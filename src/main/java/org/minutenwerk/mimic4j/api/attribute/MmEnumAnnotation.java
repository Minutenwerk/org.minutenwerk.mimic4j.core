package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmEnum.MmEnumJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmEnum.MmEnumJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationEnum;

/**
 * MmEnumAnnotation annotates declarations of {@link MmEnum} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmEnumAnnotation {

  public String id() default MmConfigurationEnum.UNDEFINED_ID;

  public boolean visible() default MmConfigurationEnum.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationEnum.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationEnum.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationEnum.DEFAULT_IS_REQUIRED;

  public MmEnumJsfTag jsfTag() default MmEnumJsfTag.SelectOneMenu;

  public MmEnumJsfDisabled jsfTagDisabled() default MmEnumJsfDisabled.TextField;

}
