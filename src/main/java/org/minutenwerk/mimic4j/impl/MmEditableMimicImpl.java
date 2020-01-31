package org.minutenwerk.mimic4j.impl;

import org.minutenwerk.mimic4j.api.mimic.MmEditableMimic;

/**
 * MmEditableMimic is the basic interface of all mimic types containing data, which can be changed from data model. Mimics of type MmEditableMimic can be
 * validated.
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
  public void passDataModelToViewModel();

  /**
   * TODOC.
   */
  public void passViewModelToDataModel();

  /**
   * TODOC.
   */
  public void validateDataModel();

}
