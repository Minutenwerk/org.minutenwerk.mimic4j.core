package org.minutenwerk.mimic4j.impl.attribute;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.attribute.MmListString;
import org.minutenwerk.mimic4j.api.attribute.MmListString.MmListStringJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmListString.MmListStringJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmListStringAnnotation;

/**
 * MmConfigurationListString contains static configuration information for mimics of type {@link MmListString}.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmConfigurationListString extends MmBaseAttributeConfiguration<List<String>> {

  /** Constant for default value of default value. */
  public static final List<String>            DEFAULT_DEFAULT_VALUE    = new ArrayList<String>();

  /** Constant for default value of number of rows for select box. */
  public static final int                     DEFAULT_SIZE             = 4;

  /** Redundant to {@link MmListStringAnnotation.jsfTag()}. */
  public static final MmListStringJsfTag      DEFAULT_JSF_TAG          = MmListStringJsfTag.SelectManyListbox;

  /** Redundant to {@link MmListStringAnnotation.jsfTagDisabled()}. */
  public static final MmListStringJsfDisabled DEFAULT_JSF_TAG_DISABLED = MmListStringJsfDisabled.SameAsEnabled;

  /** The default value. */
  protected List<String>                      defaultValue;

  /** Number of rows for select box. */
  protected int                               size;

  /** The JSF tag in enabled state. */
  protected MmListStringJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmListStringJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationListString instance of default values.
   */
  public MmConfigurationListString() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.defaultValue   = DEFAULT_DEFAULT_VALUE;
    this.size           = DEFAULT_SIZE;
    this.jsfTag         = DEFAULT_JSF_TAG;
    this.jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationListString instance from annotation.
   *
   * @param  pListStringAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationListString(MmListStringAnnotation pListStringAnnotation) {
    super(pListStringAnnotation.id(), pListStringAnnotation.visible(), pListStringAnnotation.readOnly(), pListStringAnnotation.enabled(),
      pListStringAnnotation.required());

    // there is no default value for List<String> in annotation
    this.defaultValue   = DEFAULT_DEFAULT_VALUE;
    this.size           = pListStringAnnotation.size();
    this.jsfTag         = pListStringAnnotation.jsfTag();
    this.jsfTagDisabled = pListStringAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   *
   * @since   $maven.project.version$
   */
  @Override public List<String> getDefaultValue() {
    return this.defaultValue;
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagDisabled() {
    return this.jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   *
   * @since   $maven.project.version$
   */
  @Override public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Returns the configuration of number of rows for select box.
   *
   * @return  The configuration of number of rows for select box.
   *
   * @since   $maven.project.version$
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Sets the configuration of default value.
   *
   * @param  pDefaultValue  The specified configuration of default value.
   *
   * @since  $maven.project.version$
   */
  public void setDefaultValue(List<String> pDefaultValue) {
    this.defaultValue = pDefaultValue;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   *
   * @since  $maven.project.version$
   */
  public void setJsfTag(MmListStringJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

  /**
   * Sets the configuration of number of rows for select box.
   *
   * @param  pSize  The specified configuration of number of rows for select box.
   *
   * @since  $maven.project.version$
   */
  public void setSize(int pSize) {
    this.size = pSize;
  }

}
