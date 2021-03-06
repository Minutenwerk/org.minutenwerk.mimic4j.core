package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationTable;

/**
 * MmTableAnnotation annotates declarations of {@link MmTable} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmTableAnnotation {

  public String id() default MmConfigurationTable.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTable.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationTable.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationTable.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationTable.DEFAULT_STYLE_CLASSES;

}
