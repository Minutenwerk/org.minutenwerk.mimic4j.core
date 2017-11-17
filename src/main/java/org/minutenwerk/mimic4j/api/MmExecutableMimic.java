package org.minutenwerk.mimic4j.api;

/**
 * MmExecutableMimic defines a mimic which controls execution of actions.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmExecutableMimic extends MmMimic {

  /**
   * Executes an action.
   *
   * @return  A control string, most times used as outcome string for JSF.
   *
   * @since   $maven.project.version$
   */
  public String doMmIt();

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return  A reference to some target.
   *
   * @since   $maven.project.version$
   */
  public MmReference getMmTargetReference();

}
