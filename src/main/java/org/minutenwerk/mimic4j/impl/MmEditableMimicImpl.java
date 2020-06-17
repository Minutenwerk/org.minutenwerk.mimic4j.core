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
   * Clears message lists of specified mimic and all descendants of type attribute or container.
   */
  public void clearMmMessageList();

  /**
   * Converts and formats data from data model to view model type and transfers converted data into view model.
   */
  public void passDataModelToViewModel();

  /**
   * Validates and converts data from view model to data model type and transfers converted data into data model.
   */
  public void passViewModelToDataModel();

  /**
   * Semantic validation of data model value.
   */
  public void validateDataModel();

}
