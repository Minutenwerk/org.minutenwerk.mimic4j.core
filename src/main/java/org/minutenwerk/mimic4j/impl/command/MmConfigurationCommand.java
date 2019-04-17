package org.minutenwerk.mimic4j.impl.command;

import org.minutenwerk.mimic4j.api.command.MmCommand;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfDisabled;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfTag;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationCommand contains static configuration information for mimics of type {@link MmCommand}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationCommand extends MmBaseConfiguration {

  /** Constant for default value of target outcome of this mimic. */
  public static final String               DEFAULT_TARGET_OUTCOME   = "";

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmCommandAnnotation.jsfTag()}. */
  public static final MmCommandJsfTag      DEFAULT_JSF_TAG          = MmCommandJsfTag.CommandButton;

  /** Constant for default value of JSF tag in disabled state. Redundant to {@link MmCommandAnnotation.jsfTag()}. */
  public static final MmCommandJsfDisabled DEFAULT_JSF_TAG_DISABLED = MmCommandJsfDisabled.SameAsEnabled;

  /** A string referencing a target, either an URL or an outcome, to be translated by FacesNavigator. */
  protected String                         targetOutcome;

  /** The configuration of JSF tag in enabled state. */
  protected MmCommandJsfTag                jsfTag;

  /** The configuration of JSF tag in disabled state. */
  protected MmCommandJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationCommand instance of default values.
   */
  public MmConfigurationCommand() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    this.targetOutcome  = DEFAULT_TARGET_OUTCOME;
    this.jsfTag         = DEFAULT_JSF_TAG;
    this.jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationCommand instance from annotation.
   *
   * @param  pCommandAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationCommand(MmCommandAnnotation pCommandAnnotation) {
    super(pCommandAnnotation.id(), pCommandAnnotation.visible(), pCommandAnnotation.readOnly(), pCommandAnnotation.enabled());
    this.targetOutcome  = pCommandAnnotation.targetOutcome();
    this.jsfTag         = pCommandAnnotation.jsfTag();
    this.jsfTagDisabled = pCommandAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return this.jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns a string referencing a target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return  A string referencing a target, either an URL or an outcome
   */
  public String getTargetOutcome() {
    return this.targetOutcome;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmCommandJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

  /**
   * Sets a string referencing a target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @param  pTargetOutcome  A string referencing a target.
   */
  public void setTargetOutcome(String pTargetOutcome) {
    this.targetOutcome = pTargetOutcome;
  }

}
