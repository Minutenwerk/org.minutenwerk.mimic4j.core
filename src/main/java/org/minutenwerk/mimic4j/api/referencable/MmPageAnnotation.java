package org.minutenwerk.mimic4j.api.referencable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.referencable.MmConfigurationPage;

/**
 * MmPageAnnotation annotates declarations of {@link MmPage} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmPageAnnotation {

  public String id() default MmConfigurationPage.UNDEFINED_ID;

  public boolean visible() default MmConfigurationPage.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationPage.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationPage.DEFAULT_IS_ENABLED;

  public String referencePath() default MmConfigurationPage.DEFAULT_REFERENCE_PATH;

  public String referenceFile() default MmConfigurationPage.DEFAULT_REFERENCE_FILE;

}
