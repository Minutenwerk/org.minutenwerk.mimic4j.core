package org.minutenwerk.mimic4j.api.composite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.composite.MmDiv.MmRootJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.composite.MmConfigurationRoot;

/**
 * MmRootAnnotation annotates declarations of {@link MmRoot} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmRootAnnotation {

  public String id() default MmConfigurationRoot.UNDEFINED_ID;

  public boolean visible() default MmConfigurationRoot.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationRoot.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationRoot.DEFAULT_IS_ENABLED;

  public MmRootJsfTag jsfTag() default MmRootJsfTag.Root;

}
