package org.minutenwerk.mimic4j.api.composite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.composite.MmDiv.MmDivJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.composite.MmConfigurationDiv;

/**
 * MmDivAnnotation annotates declarations of {@link MmDiv} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmDivAnnotation {

  public String id() default MmConfigurationDiv.UNDEFINED_ID;

  public boolean visible() default MmConfigurationDiv.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationDiv.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationDiv.DEFAULT_IS_ENABLED;

  public MmDivJsfTag jsfTag() default MmDivJsfTag.Div;

}
