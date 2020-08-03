package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

import org.minutenwerk.mimic4j.api.attribute.MmListString;
import org.minutenwerk.mimic4j.api.attribute.MmListStringAnnotation;

/**
 * MmConfigurationListString contains configuration information for mimics of type {@link MmListString}.
 *
 * @author  Olaf Kossak
 */
public class MmConfigurationListString extends MmBaseAttributeConfiguration<List<String>> {

  /** Constant for default value of number of rows for select box. */
  public static final int DEFAULT_SIZE = 4;

  /** Number of rows for select box. */
  protected int           size;

  /**
   * Creates a new MmConfigurationListString instance of default values.
   */
  public MmConfigurationListString() {
    super(UNDEFINED_ID, DEFAULT_IS_VISIBLE, DEFAULT_IS_REFERENCE_ENABLED, DEFAULT_IS_ENABLED, DEFAULT_IS_REQUIRED, DEFAULT_IS_TRANSIENT_DATA_MODEL,
      DEFAULT_STYLE_CLASSES);
    size = DEFAULT_SIZE;
  }

  /**
   * Creates a new MmConfigurationListString instance from annotation.
   *
   * @param  pListStringAnnotation  The annotation to create the configuration from.
   */
  public MmConfigurationListString(MmListStringAnnotation pListStringAnnotation) {
    super(pListStringAnnotation.id(), pListStringAnnotation.visible(), pListStringAnnotation.referenceEnabled(), pListStringAnnotation.enabled(),
      pListStringAnnotation.required(), pListStringAnnotation.transientDataModel(), pListStringAnnotation.styleClasses());
    size = pListStringAnnotation.size();
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
   * Sets the configuration of number of rows for select box.
   *
   * @param  pSize  The specified configuration of number of rows for select box.
   */
  public void setSize(int pSize) {
    size = pSize;
  }

}
