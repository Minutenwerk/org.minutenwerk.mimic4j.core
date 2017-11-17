package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmAttributeMimic.MmBooleanLayout;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean.MmBooleanJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean.MmBooleanJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;

/**
 * MmConfigurationBoolean contains static configuration information for mimics of type {@link MmBoolean}.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmConfigurationBoolean extends MmBaseAttributeConfiguration<Boolean> {

  /** Constant for default value of default value. */
  public static final Boolean              DEFAULT_DEFAULT_VALUE              = Boolean.FALSE;

  /** Constant for default value of default value of type boolean. */
  public static final boolean              DEFAULT_DEFAULT_VALUE_AS_PRIMITIVE = false;

  /** Constant for default value of layout direction of JSF tag. */
  public static final MmBooleanLayout      DEFAULT_LAYOUT                     = MmBooleanLayout.PAGE_DIRECTION;

  /** Redundant to {@link MmBooleanAnnotation.jsfTag()}. */
  public static final MmBooleanJsfTag      DEFAULT_JSF_TAG                    = MmBooleanJsfTag.SelectOneCheckbox;

  /** Redundant to {@link MmBooleanAnnotation.jsfTagDisabled()}. */
  public static final MmBooleanJsfDisabled DEFAULT_JSF_TAG_DISABLED           = MmBooleanJsfDisabled.SameAsEnabled;

  /** The default value. */
  protected Boolean                        defaultValue;

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
    this.defaultValue   = DEFAULT_DEFAULT_VALUE;
    this.layout         = DEFAULT_LAYOUT;
    this.jsfTag         = DEFAULT_JSF_TAG;
    this.jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationBoolean instance from annotation.
   *
   * @param  pBooleanAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBoolean(MmBooleanAnnotation pBooleanAnnotation) {
    super(pBooleanAnnotation.id(), pBooleanAnnotation.visible(), pBooleanAnnotation.readOnly(), pBooleanAnnotation.enabled(),
      pBooleanAnnotation.required());
    this.defaultValue   = pBooleanAnnotation.defaultValue();
    this.layout         = pBooleanAnnotation.layout();
    this.jsfTag         = pBooleanAnnotation.jsfTag();
    this.jsfTagDisabled = pBooleanAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of default value.
   *
   * @return  The configuration of default value.
   *
   * @since   $maven.project.version$
   */
  @Override public Boolean getDefaultValue() {
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
   * Returns the configuration of layout direction of JSF tag.
   *
   * @return  The configuration of layout direction of JSF tag.
   *
   * @since   $maven.project.version$
   */
  public MmBooleanLayout getLayout() {
    return this.layout;
  }

  /**
   * Sets the configuration of default value.
   *
   * @param  pDefaultValue  The specified configuration of default value.
   *
   * @since  $maven.project.version$
   */
  public void setDefaultValue(boolean pDefaultValue) {
    this.defaultValue = pDefaultValue;
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   *
   * @since  $maven.project.version$
   */
  public void setJsfTag(MmBooleanJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

  /**
   * Sets the configuration of layout direction of JSF tag.
   *
   * @param  pLayout  The specified configuration of layout direction of JSF tag.
   *
   * @since  $maven.project.version$
   */
  public void setLayout(MmBooleanLayout pLayout) {
    this.layout = pLayout;
  }

}
