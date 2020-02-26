package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;

/**
 * MmConfigurationEnum contains configuration information for mimics of type {@link MmEnum}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationEnum<ENUM_TYPE extends Enum<ENUM_TYPE>> extends MmBaseAttributeConfiguration<ENUM_TYPE> {

  /**
   * Creates a new MmConfigurationEnum instance of default values.
   */
  public MmConfigurationEnum() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationEnum instance from annotation.
   *
   * @param  pEnumAnnotation  The annotation to create the configuration from.
   * @param  pEnumType        The enumeration class.
   */
  public MmConfigurationEnum(MmEnumAnnotation pEnumAnnotation, Class<ENUM_TYPE> pEnumType) {
    super(pEnumAnnotation.id(), pEnumAnnotation.visible(), pEnumAnnotation.referenceEnabled(), pEnumAnnotation.enabled(), pEnumAnnotation.required(),
      pEnumAnnotation.styleClasses());
  }

}
