package org.minutenwerk.mimic4j.impl.command;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
public class MmImplementationCommand extends MmBaseImplementation<MmBaseCommandDeclaration, MmConfigurationCommand, MmCommandAnnotation>
  implements MmExecutableMimic {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationCommand.class);

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
    assureInitialization();

    return declaration.callbackMmDoIt();
  }

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @return  The current JSF tag of this mimic.
   *
   * @throws  IllegalStateException  In case of callbackMmGetJsfTag doesn't return a value.
   */
  @Override
  public String getJsfTag() {
    MmCommandJsfTag commandJsfTag = null;
    if ((isMmEnabled() && !isMmReadOnly()) || getConfiguration().getJsfTagDisabled().equals("SameAsEnabled")) {
      commandJsfTag = MmCommandJsfTag.valueOf(getConfiguration().getJsfTagEnabled());
    } else {
      commandJsfTag = MmCommandJsfTag.valueOf(getConfiguration().getJsfTagDisabled());
    }

    final MmCommandJsfTag callbackJsfTag = declaration.callbackMmGetJsfTag(commandJsfTag);
    if (LOGGER.isDebugEnabled()) {
      if (callbackJsfTag == null) {
        throw new IllegalStateException("callbackMmGetJsfTag must return a value");
      }
    }
    return callbackJsfTag.name();
  }

  /**
   * Returns a reference to some target, either an URL or an outcome.
   *
   * @return  A reference to some target.
   */
  @Override
  public MmReference getMmTargetReference() {
    assureInitialization();

    MmReference   targetReference = null;
    final MmMimic targetMimic     = declaration.callbackMmGetTargetMimic(null);

    // if link references another mimic
    if (targetMimic != null) {
      targetReference = targetMimic.getMmReference();

      // if link references an URL
    } else {
      final String            configurationOutcome  = configuration.getTargetOutcome();
      final String            callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> emptyList             = Collections.emptyList();
      final List<MmNameValue> targetReferenceParams = declaration.callbackMmGetTargetReferenceParams(emptyList, null);
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
    assureInitialization();

    return declaration.callbackMmIsEnabled(configuration.isEnabled());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationCommand onConstructConfiguration(MmCommandAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationCommand(pAnnotation);
    } else {
      return new MmConfigurationCommand();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeCommand(this);
  }

}
