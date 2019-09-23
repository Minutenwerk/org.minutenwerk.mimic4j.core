package org.minutenwerk.mimic4j.impl.command;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmExecutableMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.command.MmCommand.MmCommandJsfTag;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

import org.springframework.web.util.UriComponents;

/**
 * MmBaseCommandDeclaration is an abstract base class for command mimics.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseCommandDeclaration extends MmBaseDeclaration<MmExecutableMimic, MmImplementationCommand>
  implements MmExecutableMimic, MmCommandCallback {

  /**
   * Creates a new MmBaseCommandDeclaration instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  protected MmBaseCommandDeclaration(MmDeclarationMimic pParent) {
    super(new MmImplementationCommand(pParent));
  }

  /**
   * Executes an action.
   *
   * @return  A control string, most times used as outcome string for JSF.
   *
   * @throws  IllegalStateException  In case of mimic is not an instance of MmCommand.
   */
  @Override
  public String callbackMmDoIt() {
    throw new IllegalStateException("method callbackMmDoIt is not implemented on " + getClass().getSimpleName());
  }

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  The current JSF tag of this mimic.
   */
  @Override
  public MmCommandJsfTag callbackMmGetJsfTag(MmCommandJsfTag pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A mimic, which is the target reference of this link mimic.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmMimic callbackMmGetTargetMimic(MmMimic pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a string referencing a target, either an URL or an outcome.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A string referencing a target, either an URL or an outcome
   *
   * @jalopy.group  group-callback
   */
  @Override
  public UriComponents callbackMmGetTargetOutcome(UriComponents pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of path or query parameter values of the URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The model data, which may control the query string.
   *
   * @return  A list of path or query parameter values of the URL. Usually this is a list of ids starting by id of root dto.
   */
  @Override
  public List<String> callbackMmGetTargetReferenceValues(List<String> pPassThroughValue, MmReferencableModel pModel) {
    return pPassThroughValue;
  }

  /**
   * Executes an action.
   *
   * @return  A control string, most times used as outcome string for JSF.
   */
  @Override
  public final String doMmIt() {
    return implementation.doMmIt();
  }

  /**
   * Returns a target URI.
   *
   * @return        A reference to some target.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final URI getMmTargetReference() {
    return implementation.getMmTargetReference();
  }

}
