package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmAttributeMimic.MmBooleanLayout;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean.MmBooleanJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean.MmBooleanJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;

/**
 * MmConfigurationBoolean contains configuration information for mimics of type {@link MmBoolean}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBoolean extends MmBaseAttributeConfiguration<Boolean> {

  /** Constant for default value of layout direction of JSF tag. */
  public static final MmBooleanLayout      DEFAULT_LAYOUT           = MmBooleanLayout.PAGE_DIRECTION;

  /** Redundant to {@link MmBooleanAnnotation.jsfTag()}. */
  public static final MmBooleanJsfTag      DEFAULT_JSF_TAG          = MmBooleanJsfTag.SelectOneCheckbox;

  /** Redundant to {@link MmBooleanAnnotation.jsfTagDisabled()}. */
  public static final MmBooleanJsfDisabled DEFAULT_JSF_TAG_DISABLED = MmBooleanJsfDisabled.SameAsEnabled;

  /** Layout direction of JSF tag. */
  protected MmBooleanLayout                layout;

  /** The JSF tag in enabled state. */
  protected MmBooleanJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmBooleanJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationBoolean instance of default values.
   */
  public MmConfigurationBoolean() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    layout         = DEFAULT_LAYOUT;
    jsfTag         = DEFAULT_JSF_TAG;
    jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationBoolean instance from annotation.
   *
   * @param  pBooleanAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBoolean(MmBooleanAnnotation pBooleanAnnotation) {
    super(pBooleanAnnotation.id(), pBooleanAnnotation.visible(), pBooleanAnnotation.readOnly(), pBooleanAnnotation.enabled(),
      pBooleanAnnotation.required());
    layout         = pBooleanAnnotation.layout();
    jsfTag         = pBooleanAnnotation.jsfTag();
    jsfTagDisabled = pBooleanAnnotation.jsfTagDisabled();
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
   * Returns the configuration of layout direction of JSF tag.
   *
   * @return  The configuration of layout direction of JSF tag.
   */
  public MmBooleanLayout getLayout() {
    return layout;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmBooleanJsfTag pJsfTag) {
    jsfTag = pJsfTag;
  }

  /**
   * Sets the configuration of layout direction of JSF tag.
   *
   * @param  pLayout  The specified configuration of layout direction of JSF tag.
   */
  public void setLayout(MmBooleanLayout pLayout) {
    layout = pLayout;
  }

}
