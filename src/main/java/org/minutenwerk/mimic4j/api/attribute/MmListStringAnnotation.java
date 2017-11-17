package org.minutenwerk.mimic4j.api.attribute;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.attribute.MmListString.MmListStringJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmListString.MmListStringJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationListString;

/**
 * MmListStringAnnotation annotates declarations of {@link MmListString} by static configuration values.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmListStringAnnotation {

  public String id() default MmConfigurationListString.UNDEFINED_ID;

  public boolean visible() default MmConfigurationListString.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationListString.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationListString.DEFAULT_IS_ENABLED;

  public boolean required() default MmConfigurationListString.DEFAULT_IS_REQUIRED;

  public int size() default MmConfigurationListString.DEFAULT_SIZE;

  public MmListStringJsfTag jsfTag() default MmListStringJsfTag.SelectManyListbox;

  public MmListStringJsfDisabled jsfTagDisabled() default MmListStringJsfDisabled.SameAsEnabled;

}
