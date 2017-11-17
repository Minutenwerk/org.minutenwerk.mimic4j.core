package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationTable;

/**
 * MmTableAnnotation annotates declarations of {@link MmTable} by static configuration values.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmTableAnnotation {

  public String id() default MmConfigurationTable.UNDEFINED_ID;

  public boolean visible() default MmConfigurationTable.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationTable.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationTable.DEFAULT_IS_ENABLED;

}
