package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationTableRow;

/**
 * MmTableRowAnnotation annotates declarations of {@link MmTableRow} by static configuration values.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmTableRowAnnotation {

  public String id() default MmConfigurationTableRow.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTableRow.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationTableRow.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationTableRow.DEFAULT_IS_ENABLED;

}
