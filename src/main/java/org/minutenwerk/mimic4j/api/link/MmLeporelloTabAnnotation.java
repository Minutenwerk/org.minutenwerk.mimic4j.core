package org.minutenwerk.mimic4j.api.link;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.link.MmConfigurationLeporelloTab;

/**
 * MmLeporelloTabAnnotation annotates declarations of {@link MmLeporelloTab} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmLeporelloTabAnnotation {

  public String id() default MmConfigurationLeporelloTab.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLeporelloTab.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLeporelloTab.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLeporelloTab.DEFAULT_IS_ENABLED;

  public MmLeporelloTab.MmLeporelloTabJsfTag jsfTag() default MmLeporelloTab.MmLeporelloTabJsfTag.LeporelloTab;

  public String targetOutcome() default MmConfigurationLeporelloTab.DEFAULT_TARGET_OUTCOME;

}
