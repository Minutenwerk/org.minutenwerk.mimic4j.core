package org.minutenwerk.mimic4j.impl.command;

import java.net.URI;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmExecutableMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfTag;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeCommand;

import org.springframework.web.util.UriComponents;

/**
 * MmImplementationCommand is the specific class for the implementation part of command mimics.
 *
 * @author  Olaf Kossak
 */
// TODO MmCommand needs a modelAccessor and an actionAccessor
public class MmImplementationCommand extends MmBaseImplementation<MmBaseCommandDeclaration, MmConfigurationCommand, MmCommandAnnotation>
  implements MmExecutableMimic {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationCommand.class);

  /**
   * Creates a new MmImplementationCommand instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
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
   * Returns a target URI.
   *
   * @return  A reference to some target.
   */
  @Override
  public URI getMmTargetReference() {
    assureInitialization();

    URI           targetReference = null;
    final MmMimic targetMimic     = declaration.callbackMmGetTargetMimic(null);

    // if link references another mimic
    if (targetMimic != null) {
      targetReference = targetMimic.getMmSelfReference();

      // if link references an URL
    } else {
      final UriComponents configurationOutcome  = configuration.getTargetOutcome();
      final UriComponents callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<String>  emptyList             = Collections.emptyList();
      final List<String>  targetReferenceParams = declaration.callbackMmGetTargetReferenceValues(emptyList, null);
      targetReference = callbackOutcome.expand(targetReferenceParams).toUri();
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
   * Returns <code>true</code>, if the mimic has a model, which delivers data for this model, and a model instance is currently present.
   *
   * @return        <code>True</code>, if a model instance is currently present.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public final boolean isMmModelPresent() {
    return false;
  }

  /**
   * Returns data model for self reference. The data model delivers parameters of the target URL, like "123", "4711" in
   * "city/123/person/4711/display".
   *
   * @return        The data model for self reference.
   *
   * @jalopy.group  group-override
   */
  @Override
  // TODO getMmReferencableModel
  protected MmReferencableModel getMmReferencableModel() {
    return null;
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
