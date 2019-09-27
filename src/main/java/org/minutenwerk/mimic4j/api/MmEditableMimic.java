package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;

/**
 * MmEditableMimic is the basic interface of all mimic types containing data, which can be changed from data model. Mimics of type
 * MmEditableMimic can be validated.
 *
 * @author  Olaf Kossak
 */
public interface MmEditableMimic extends MmMimic {

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>converting view model value to data model type</li>
   *   <li>passing converted value into data model value</li>
   *   <li>validating data model value</li>
   * </ol>
   *
   * @throws  MmValidatorException  in case of semantic validation of container or one of its children failed.
   */
  public void doMmValidate() throws MmValidatorException;

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  public MmModelAccessor<?, ?> getMmModelAccessor();

  /**
   * Returns <code>true</code>, if the mimic has been changed from view model. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from view model.
   */
  public boolean isMmChangedFromView();

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return  <code>True</code> if a value from view has to be set.
   */
  public boolean isMmRequired();

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return  <code>True</code> if the mimic has been validated without any errors.
   */
  public boolean isMmValid();

}
