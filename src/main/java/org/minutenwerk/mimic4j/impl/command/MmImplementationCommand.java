package org.minutenwerk.mimic4j.impl.command;

import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmExecutableMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfTag;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.referencable.MmReferenceImplementation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeCommand;

/**
 * MmImplementationCommand is the specific class for the implementation part of command mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationCommand extends MmBaseImplementation<MmBaseCommandDeclaration, MmConfigurationCommand>
  implements MmExecutableMimic {

  /**
   * Creates a new MmImplementationCommand instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationCommand(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Executes an action.
   *
   * @return  A control string, most times used as outcome string for JSF.
   */
  @Override
  public String doMmIt() {
    this.ensureInitialization();

    return this.declaration.callbackMmDoIt();
  }

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @return  The current JSF tag of this mimic.
   */
  @Override
  public String getJsfTag() {
    MmCommandJsfTag commandJsfTag = null;
    if ((this.isMmEnabled() && !this.isMmReadOnly()) || this.getConfiguration().getJsfTagDisabled().equals("SameAsEnabled")) {
      commandJsfTag = MmCommandJsfTag.valueOf(this.getConfiguration().getJsfTagEnabled());
    } else {
      commandJsfTag = MmCommandJsfTag.valueOf(this.getConfiguration().getJsfTagDisabled());
    }

    final MmCommandJsfTag callbackJsfTag = this.declaration.callbackMmGetJsfTag(commandJsfTag);
    assert callbackJsfTag != null : "callbackMmGetJsfTag must return a value";
    return callbackJsfTag.name();
  }

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return  A reference to some target.
   */
  @Override
  public MmReference getMmTargetReference() {
    this.ensureInitialization();

    MmReference   targetReference = null;
    final MmMimic targetMimic     = this.declaration.callbackMmGetTargetMimic(null);

    // if link references another mimic
    if (targetMimic != null) {
      targetReference = targetMimic.getMmReference();

      // if link references an URL
    } else {
      final String            configurationOutcome  = this.configuration.getTargetOutcome();
      final String            callbackOutcome       = this.declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> emptyList             = Collections.emptyList();
      final List<MmNameValue> targetReferenceParams = this.declaration.callbackMmGetTargetReferenceParams(emptyList, null);
      targetReference = new MmReferenceImplementation(callbackOutcome, targetReferenceParams);
    }
    return targetReference;
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled. This mimic is enabled, if its declaration method callbackMmIsEnabled returns <code>
   * true</code>.
   *
   * @return  <code>True</code>, if the mimic is enabled.
   */
  @Override
  public boolean isMmEnabled() {
    this.ensureInitialization();

    return this.declaration.callbackMmIsEnabled(this.configuration.isEnabled());
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeCommand(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmCommandAnnotation.class);

    MmCommandAnnotation annotation = this.findAnnotation(this.declaration, MmCommandAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationCommand();
    } else {
      this.configuration = new MmConfigurationCommand(annotation);
    }
  }

}
