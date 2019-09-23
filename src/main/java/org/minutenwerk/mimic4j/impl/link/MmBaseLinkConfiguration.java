package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * MmBaseAttributeConfiguration is the abstract base class for configuration of all link mimic classes.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseLinkConfiguration extends MmBaseConfiguration {

  /** Constant for default value of target outcome of this mimic. */
  public static final String DEFAULT_TARGET_OUTCOME = "";

  /** A string referencing a target, either an URL or an outcome. */
  protected UriComponents    targetOutcome;

  /**
   * Creates a new MmBaseLinkConfiguration instance from annotation.
   *
   * @param  pId        The HTML id of HTML tag.
   * @param  pVisible   True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly  True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled   True, if HTML tag of mimic is rendered enabled.
   */
  public MmBaseLinkConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled) {
    super(pId, pVisible, pReadOnly, pEnabled);
    targetOutcome = UriComponentsBuilder.fromPath(DEFAULT_TARGET_OUTCOME).build();
    ;
  }

  /**
   * Creates a new MmBaseLinkConfiguration instance from annotation.
   *
   * @param  pId             The HTML id of HTML tag.
   * @param  pVisible        True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly       True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled        True, if HTML tag of mimic is rendered enabled.
   * @param  pTargetOutcome  A string referencing some target, either an URL or an outcome.
   */
  public MmBaseLinkConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled, UriComponents pTargetOutcome) {
    super(pId, pVisible, pReadOnly, pEnabled);
    targetOutcome = pTargetOutcome;
  }

  /**
   * Returns a string referencing a target, either an URL or an outcome.
   *
   * @return  A string referencing a target, either an URL or an outcome
   */
  public UriComponents getTargetOutcome() {
    return targetOutcome;
  }

  /**
   * Sets a string referencing a target, either an URL or an outcome.
   *
   * @param  pTargetOutcome  A string referencing a target.
   */
  public void setTargetOutcome(UriComponents pTargetOutcome) {
    targetOutcome = pTargetOutcome;
  }
}
