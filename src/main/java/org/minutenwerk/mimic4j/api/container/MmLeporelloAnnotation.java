package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.container.MmLeporello.MmLeporelloJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationLeporello;

/**
 * MmLeporelloAnnotation annotates declarations of {@link MmLeporello} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLeporelloAnnotation {

  public String id() default MmConfigurationLeporello.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLeporello.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLeporello.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLeporello.DEFAULT_IS_ENABLED;

  public MmLeporelloJsfTag jsfTag() default MmLeporelloJsfTag.Leporello;

}
