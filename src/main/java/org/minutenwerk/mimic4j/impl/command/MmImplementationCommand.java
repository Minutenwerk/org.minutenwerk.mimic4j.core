package org.minutenwerk.mimic4j.impl.command;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmExecutableMimic;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeCommand;

/**
 * MmImplementationCommand is the specific class for the implementation part of command mimics.
 *
 * @author  Olaf Kossak
 */
// TODO MmCommand needs a modelAccessor and an actionAccessor
public class MmImplementationCommand extends MmBaseImplementation<MmBaseCommandDeclaration, MmConfigurationCommand, MmCommandAnnotation>
  implements MmExecutableMimic {

  /**
   * Creates a new MmImplementationCommand instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationCommand(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @return  The current JSF tag of this mimic.
   */
  @Override
  public String getJsfTag() {
    return configuration.getJsfTagEnabled();
  }

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  @Override
  public String getMmSubmitParam() {
    assureInitialization();

    return declaration.callbackMmGetSubmitParam(configuration.getSubmitParam());
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
