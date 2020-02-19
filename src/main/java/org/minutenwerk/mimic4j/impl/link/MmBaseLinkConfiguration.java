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

  /** Constant for default value of icon before text. */
  public static final String DEFAULT_ICON_BEFORE           = "";

  /** Constant for default value of icon after text. */
  public static final String DEFAULT_ICON_AFTER            = "";

  /** Constant for default value of target outcome of this mimic. */
  public static final String DEFAULT_TARGET_REFERENCE_PATH = "";

  /** The icon before text. */
  protected String           iconBefore;

  /** The icon after text. */
  protected String           iconAfter;

  /** The path part of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display". */
  protected UriComponents    targetReferencePath;

  /**
   * Creates a new MmBaseLinkConfiguration instance from annotation.
   *
   * @param  pId            The HTML id of HTML tag.
   * @param  pVisible       True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly      True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled       True, if HTML tag of mimic is rendered enabled.
   * @param  pIconBefore    The icon before text.
   * @param  pIconAfter     The icon after text.
   * @param  pStyleClasses  The CSS style classes of this mimic.
   */
  public MmBaseLinkConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled, String pIconBefore, String pIconAfter,
    String pStyleClasses) {
    super(pId, pVisible, pReadOnly, pEnabled, pStyleClasses);
    iconBefore          = pIconBefore;
    iconAfter           = pIconAfter;
    targetReferencePath = null;
  }

  /**
   * Creates a new MmBaseLinkConfiguration instance from annotation.
   *
   * @param  pId                   The HTML id of HTML tag.
   * @param  pVisible              True, if HTML tag of mimic is rendered visible.
   * @param  pReadOnly             True, if HTML tag of mimic is rendered readonly.
   * @param  pEnabled              True, if HTML tag of mimic is rendered enabled.
   * @param  pIconBefore           The icon before text.
   * @param  pIconAfter            The icon after text.
   * @param  pTargetReferencePath  The path part of the target URL.
   * @param  pStyleClasses         The CSS style classes of this mimic.
   */
  public MmBaseLinkConfiguration(String pId, boolean pVisible, boolean pReadOnly, boolean pEnabled, String pIconBefore, String pIconAfter,
    String pTargetReferencePath, String pStyleClasses) {
    super(pId, pVisible, pReadOnly, pEnabled, pStyleClasses);
    iconBefore = pIconBefore;
    iconAfter  = pIconAfter;
    setTargetReferencePath(pTargetReferencePath);
  }

  /**
   * Returns icon after text.
   *
   * @return  The icon after text.
   */
  public String getIconAfter() {
    return iconAfter;
  }

  /**
   * Returns icon before text.
   *
   * @return  The icon before text.
   */
  public String getIconBefore() {
    return iconBefore;
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
      targetReferencePath = UriComponentsBuilder.fromUriString(pTargetReferencePath).build();
    }
  }

}
