package org.minutenwerk.mimic4j.impl.referencable;

import org.minutenwerk.mimic4j.api.referencable.MmPage;
import org.minutenwerk.mimic4j.api.referencable.MmPageAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;

/**
 * MmConfigurationPage contains static configuration information for mimics of type {@link MmPage}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationPage extends MmBaseConfiguration {

  /** Constant for default value of reference path of this mimic. */
  public static final String DEFAULT_REFERENCE_PATH = "";

  /** Constant for default value of reference file of this mimic. */
  public static final String DEFAULT_REFERENCE_FILE = "";

  /** Constant for default value of JSF tag in enabled state. Redundant to {@link MmPageAnnotation.jsfTag()}. */
  public static final String DEFAULT_JSF_TAG        = "Page";

  /** The path part of the URL including trailing slash but without base part. */
  protected String           referencePath;

  /** The file part of the URL without slashes. */
  protected String           referenceFile;

  /**
   * Creates a new MmConfigurationPage instance of default values.
   */
  public MmConfigurationPage() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED);
    referencePath = DEFAULT_REFERENCE_PATH;
    referenceFile = DEFAULT_REFERENCE_FILE;
  }

  /**
   * Creates a new MmConfigurationPage instance from annotation.
   *
   * @param  pPageAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationPage(MmPageAnnotation pPageAnnotation) {
    super(pPageAnnotation.id(), pPageAnnotation.visible(), pPageAnnotation.readOnly(), pPageAnnotation.enabled());
    referencePath = pPageAnnotation.referencePath();
    referenceFile = pPageAnnotation.referenceFile();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return DEFAULT_JSF_TAG;
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return DEFAULT_JSF_TAG;
  }

  /**
   * Returns the file part of the URL without slashes.
   *
   * @return  The file part of the URL without slashes.
   */
  public final String getReferenceFile() {
    return referenceFile;
  }

  /**
   * Returns the path part of the URL including trailing slash but without base part.
   *
   * @return  The path part of the URL including trailing slash but without base part.
   */
  public final String getReferencePath() {
    return referencePath;
  }

}
