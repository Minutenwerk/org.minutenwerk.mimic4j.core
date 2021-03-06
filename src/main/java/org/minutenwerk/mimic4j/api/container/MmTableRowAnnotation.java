package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationTableRow;

/**
 * MmTableRowAnnotation annotates declarations of {@link MmTableRow} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmTableRowAnnotation {

  public String id() default MmConfigurationTableRow.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTableRow.DEFAULT_IS_VISIBLE;

  public boolean referenceEnabled() default MmConfigurationTableRow.DEFAULT_IS_REFERENCE_ENABLED;

  public boolean enabled() default MmConfigurationTableRow.DEFAULT_IS_ENABLED;

  public String styleClasses() default MmConfigurationTableRow.DEFAULT_STYLE_CLASSES;

}
