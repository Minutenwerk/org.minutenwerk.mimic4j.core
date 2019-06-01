package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.MmAttributeMimic.MmBooleanLayout;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean.MmBooleanJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean.MmBooleanJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationBoolean;

/**
 * MmBooleanAnnotation annotates declarations of {@link MmBoolean} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmBooleanAnnotation {

  public String id() default MmConfigurationBoolean.UNDEFINED_ID;

  public boolean visible() default MmConfigurationBoolean.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationBoolean.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationBoolean.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationBoolean.DEFAULT_IS_REQUIRED;

  public MmBooleanLayout layout() default MmBooleanLayout.PAGE_DIRECTION;

  public MmBooleanJsfTag jsfTag() default MmBooleanJsfTag.SelectOneCheckbox;

  public MmBooleanJsfDisabled jsfTagDisabled() default MmBooleanJsfDisabled.SameAsEnabled;

}
