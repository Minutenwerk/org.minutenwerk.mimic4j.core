package org.minutenwerk.mimic4j.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporello.MmVoidTarget;
import org.minutenwerk.mimic4j.impl.MmMetaAnnotation;
import org.minutenwerk.mimic4j.impl.command.MmConfigurationCommand;
import org.minutenwerk.mimic4j.impl.link.MmConfigurationLink;

/**
 * MmCommandAnnotation annotates declarations of {@link MmCommand} by hardcoded configuration values.
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

  public String targetReferencePath() default MmConfigurationLink.DEFAULT_TARGET_REFERENCE_PATH;

  public Class<? extends MmLeporello<?, ?>> targetLeporello() default MmVoidTarget.class;

  public String submitParam() default MmConfigurationCommand.DEFAULT_SUBMIT_PARAM;
}
