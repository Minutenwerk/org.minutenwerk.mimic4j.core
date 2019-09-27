package org.minutenwerk.mimic4j.api;

/**
 * MmExecutableMimic defines a mimic which controls execution of actions.
 *
 * @author  Olaf Kossak
 */
public interface MmExecutableMimic extends MmMimic {

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  public String getMmSubmitParam();

}
