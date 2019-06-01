package org.minutenwerk.mimic4j.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.command.MmConfigurationCommand;

/**
 * MmCommandAnnotation annotates declarations of {@link MmCommand} by static configuration values.
 *
 * @author  Olaf Kossak
 */
@MmMetaAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MmCommandAnnotation {

  public String id() default MmConfigurationCommand.UNDEFINED_ID;

  public String longDescription() default "";

  public boolean visible() default MmConfigurationCommand.DEFAULT_IS_VISIBLE;

  public boolean readOnly() default MmConfigurationCommand.DEFAULT_IS_READONLY;

  public boolean enabled() default MmConfigurationCommand.DEFAULT_IS_ENABLED;

  public MmCommand.MmCommandJsfTag jsfTag() default MmCommand.MmCommandJsfTag.CommandButton;

  public MmCommand.MmCommandJsfDisabled jsfTagDisabled() default MmCommand.MmCommandJsfDisabled.SameAsEnabled;

  public String targetOutcome() default MmConfigurationCommand.DEFAULT_TARGET_OUTCOME;

}
