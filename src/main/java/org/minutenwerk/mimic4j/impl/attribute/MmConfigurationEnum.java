package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnum.MmEnumJsfDisabled;
import org.minutenwerk.mimic4j.api.attribute.MmEnum.MmEnumJsfTag;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;

/**
 * MmConfigurationEnum contains static configuration information for mimics of type {@link MmEnum}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationEnum<ENUM_TYPE extends Enum<ENUM_TYPE>> extends MmBaseAttributeConfiguration<ENUM_TYPE> {

  /** Redundant to {@link MmEnumAnnotation.jsfTag()}. */
  public static final MmEnumJsfTag      DEFAULT_JSF_TAG                   = MmEnumJsfTag.SelectOneMenu;

  /** Redundant to {@link MmEnumAnnotation.jsfTagDisabled()}. */
  public static final MmEnumJsfDisabled DEFAULT_JSF_TAG_DISABLED          = MmEnumJsfDisabled.TextField;

  /** Index of generic type of enumeration type. */
  public static final int               GENERIC_PARAMETER_INDEX_ENUM_TYPE = 1;

  /** The JSF tag in enabled state. */
  protected MmEnumJsfTag                jsfTag;

  /** The JSF tag in disabled state. */
  protected MmEnumJsfDisabled           jsfTagDisabled;

  /**
   * Creates a new MmConfigurationEnum instance of default values.
   */
  public MmConfigurationEnum() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_READONLY, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED);
    this.jsfTag         = DEFAULT_JSF_TAG;
    this.jsfTagDisabled = DEFAULT_JSF_TAG_DISABLED;
  }

  /**
   * Creates a new MmConfigurationEnum instance from annotation.
   *
   * @param  pEnumAnnotation  The annotation to create the configuration from.
   * @param  pEnumType        The enumeration class.
   */
  public MmConfigurationEnum(MmEnumAnnotation pEnumAnnotation, Class<ENUM_TYPE> pEnumType) {
    super(pEnumAnnotation.id(), pEnumAnnotation.visible(), pEnumAnnotation.readOnly(), pEnumAnnotation.enabled(),
      pEnumAnnotation.required());

    this.jsfTag         = pEnumAnnotation.jsfTag();
    this.jsfTagDisabled = pEnumAnnotation.jsfTagDisabled();
  }

  /**
   * Returns the configuration of JSF tag in disabled state.
   *
   * @return  The configuration of JSF tag in disabled state.
   */
  @Override
  public String getJsfTagDisabled() {
    return this.jsfTagDisabled.name();
  }

  /**
   * Returns the configuration of JSF tag in enabled state.
   *
   * @return  The configuration of JSF tag in enabled state.
   */
  @Override
  public String getJsfTagEnabled() {
    return this.jsfTag.name();
  }

  /**
   * Sets the configuration of JSF tag in enabled state.
   *
   * @param  pJsfTag  The specified configuration of JSF tag in enabled state.
   */
  public void setJsfTag(MmEnumJsfTag pJsfTag) {
    this.jsfTag = pJsfTag;
  }

}
