package org.minutenwerk.mimic4j.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.container.MmPage;
import org.minutenwerk.mimic4j.api.container.MmPage.MmVoidTarget;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.command.MmConfigurationCommand;

/**
 * MmCommandAnnotation annotates declarations of {@link MmCommand} by hardcoded configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface MmCommandAnnotation {

  public String id() default MmConfigurationCommand.UNDEFINED_ID;

  public String longDescription() default "";

  public boolean visible() default MmConfigurationCommand.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationCommand.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationCommand.DEFAULT_IS_ENABLED;

  public String iconBefore() default MmConfigurationCommand.DEFAULT_ICON_BEFORE;

  public String iconAfter() default MmConfigurationCommand.DEFAULT_ICON_AFTER;

  public String styleClasses() default MmConfigurationCommand.DEFAULT_STYLE_CLASSES;

  public String targetReferencePath() default MmConfigurationCommand.DEFAULT_TARGET_REFERENCE_PATH;

  public Class<? extends MmPage<?>> targetPage() default MmVoidTarget.class;

  public String submitParam() default MmConfigurationCommand.DEFAULT_SUBMIT_PARAM;
}
