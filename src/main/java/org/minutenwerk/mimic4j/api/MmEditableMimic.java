package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.api.exception.MmValidatorException;

/**
 * MmEditableMimic is the basic interface of all mimic types containing data, which can be changed from modelside. Mimics of type
 * MmEditableMimic can set to default value, can be validated and can be reset.
 *
 * @author  Olaf Kossak
 */
public interface MmEditableMimic extends MmMimic {

  /**
   * Resets the attribute to its reset value, by:
   *
   * <ol>
   *   <li>passing reset value into modelside value</li>
   *   <li>converting modelside value to viewside type</li>
   *   <li>passing converted value into viewside value</li>
   * </ol>
   */
  public void doMmReset();

  /**
   * Sets the attribute to its default value, by:
   *
   * <ol>
   *   <li>passing default value into modelside value</li>
   *   <li>converting modelside value to viewside type</li>
   *   <li>passing converted value into viewside value</li>
   * </ol>
   */
  public void doMmSetDefaults();

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>passing viewside value into modelside value</li>
   *   <li>converting viewside value to modelside type</li>
   *   <li>passing converted value into modelside value</li>
   *   <li>validating modelside value</li>
   * </ol>
   *
   * @throws  MmValidatorException  in case of semantic validation of container or one of its children failed.
   */
  public void doMmValidate() throws MmValidatorException;

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from viewside.
   */
  public boolean isMmChangedFromViewside();

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return  <code>True</code> if a value from view has to be set.
   */
  public boolean isMmRequired();

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.doMmReset} is executable.
   *
   * @return  <code>true</code> if the action {@link MmEditableMimic.doMmReset} is executable.
   */
  public boolean isMmResetEnabled();

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return  <code>True</code> if the mimic has been validated without any errors.
   */
  public boolean isMmValid();

}
