package org.minutenwerk.mimic4j.impl;

import org.minutenwerk.mimic4j.api.MmEditableMimic;

/**
 * MmEditableMimic is the basic interface of all mimic types containing data, which can be changed from modelside. Mimics of type
 * MmEditableMimic can be validated.
 *
 * @author  Olaf Kossak
 */
public interface MmEditableMimicImpl extends MmEditableMimic {

  /**
   * TODOC.
   */
  public void clearMmMessageList();

  /**
   * TODOC.
   */
  public void passModelsideToViewside();

  /**
   * TODOC.
   */
  public void passViewsideToModelside();

  /**
   * TODOC.
   */
  public void validateModelside();

}
