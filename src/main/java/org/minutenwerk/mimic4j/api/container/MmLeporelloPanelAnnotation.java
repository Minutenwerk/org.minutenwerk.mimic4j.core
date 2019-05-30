package org.minutenwerk.mimic4j.api.container;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel.MmLeporelloPanelJsfTag;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.container.MmConfigurationLeporelloPanel;

/**
 * MmLeporelloPanelAnnotation annotates declarations of {@link MmLeporelloPanel} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmLeporelloPanelAnnotation {

  public String id() default MmConfigurationLeporelloPanel.UNDEFINED_ID;

  public boolean visible() default MmConfigurationLeporelloPanel.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationLeporelloPanel.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationLeporelloPanel.DEFAULT_IS_ENABLED;

  public MmLeporelloPanelJsfTag jsfTag() default MmLeporelloPanelJsfTag.LeporelloPanel;

}
