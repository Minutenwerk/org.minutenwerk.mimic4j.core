package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;

/**
 * MmConfigurationBoolean contains configuration information for mimics of type {@link MmBoolean}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationBoolean extends MmBaseAttributeConfiguration<Boolean> {

  /**
   * Creates a new MmConfigurationBoolean instance of default values.
   */
  public MmConfigurationBoolean() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
  }

  /**
   * Creates a new MmConfigurationBoolean instance from annotation.
   *
   * @param  pBooleanAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationBoolean(MmBooleanAnnotation pBooleanAnnotation) {
    super(pBooleanAnnotation.id(), pBooleanAnnotation.visible(), pBooleanAnnotation.referenceEnabled(), pBooleanAnnotation.enabled(),
      pBooleanAnnotation.required(), pBooleanAnnotation.transientDataModel(), pBooleanAnnotation.styleClasses());
  }

}
