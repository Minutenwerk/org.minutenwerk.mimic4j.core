package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

import org.minutenwerk.mimic4j.api.attribute.MmListString;
import org.minutenwerk.mimic4j.api.attribute.MmListString.MmListStringJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmListString.MmListStringJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmListStringAnnotation;

/**
 * MmConfigurationListString contains static configuration information for mimics of type {@link MmListString}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationListString extends MmBaseAttributeConfiguration<List<String>> {

  /** Constant for default value of number of rows for select box. */
  public static final int                     DEFAULT_SIZE             = 4;

  /** Redundant to {@link MmListStringAnnotation.jsfTag()}. */
  public static final MmListStringJsfTag      DEFAULT_JSF_TAG          = MmListStringJsfTag.SelectManyListbox;

  /** Redundant to {@link MmListStringAnnotation.jsfTagDisabled()}. */
  public static final MmListStringJsfDisabled DEFAULT_JSF_TAG_DISABLED = MmListStringJsfDisabled.SameAsEnabled;

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
    size           = DEFAULT_SIZE;
    jsfTag         = DEFAULT_JSF_TAG;
    jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationListString instance from annotation.
   *
   * @param  pListStringAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationListString(MmListStringAnnotation pListStringAnnotation) {
    super(pListStringAnnotation.id(), pListStringAnnotation.visible(), pListStringAnnotation.readOnly(), pListStringAnnotation.enabled(),
      pListStringAnnotation.required());
    size           = pListStringAnnotation.size();
    jsfTag         = pListStringAnnotation.jsfTag();
    jsfTagDisabled = pListStringAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return jsfTag.name();
  }

  /**
   * Returns the configuration of number of rows for select box.
   *
   * @return  The configuration of number of rows for select box.
   */
  public int getSize() {
    return size;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmListStringJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

  /**
   * Sets the configuration of number of rows for select box.
   *
   * @param  pSize  The specified configuration of number of rows for select box.
   */
  public void setSize(int pSize) {
    size = pSize;
  }

}
