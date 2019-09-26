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
  public static final String DEFAULT_TARGET_REFERENCE_PATH = "";

  /** The path part of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display". */
  protected UriComponents    targetReferencePath;

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
    targetReferencePath = null;
  }

  /**
   * Creates a new MmBaseLinkConfiguration instance from annotation.
   *
   * @param  pId                   The HTML id of HTML tag.
   * @param  pVisible              True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly             True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled              True, if HTML tag of mimic is rendered enabled.
   * @param  pTargetReferencePath  The path part of the target URL.
   */
  public MmBaseLinkConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled, String pTargetReferencePath) {
    super(pId, pVisible, pReadOnly, pEnabled);
    setTargetReferencePath(pTargetReferencePath);
  }

  /**
   * Returns the path part of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @return  The path part of the target URL.
   */
  public UriComponents getTargetReferencePath() {
    return targetReferencePath;
  }

  /**
   * Sets the path part of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @param  pTargetReferencePath  The path part of the target URL.
   */
  public void setTargetReferencePath(String pTargetReferencePath) {
    if ((pTargetReferencePath != null) && !pTargetReferencePath.isEmpty()) {
      targetReferencePath = UriComponentsBuilder.fromPath(pTargetReferencePath).build();
    }
  }
}
