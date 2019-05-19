package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;

/**
 * MmAttributeMimic is the basic interface of all mimic types containing editable data, which can be changed from viewside. Mimics of type
 * MmAttributeMimic can be set from modelside and viewside, and can be converted in both directions by means of format patterns.
 *
 * @author  Olaf Kossak
 */
public interface MmAttributeMimic<MODELSIDE_VALUE, VIEWSIDE_VALUE> extends MmEditableMimic {

  /**
   * MmBooleanLayout is an enumeration of layout directions for JSF tags of type checkbox.
   *
   * @author  Olaf Kossak
   */
  public enum MmBooleanLayout {

    /** Layout direction is vertical. */
    PAGE_DIRECTION("pageDirection"),

    /** Layout direction is horizontal. */
    LINE_DIRECTION("lineDirection");

    /** Text value of HTML attribute. */
    private final String value;

    /**
     * Creates a new MmBooleanLayout instance.
     *
     * @param  pValue  The text value of HTML attribute.
     */
    private MmBooleanLayout(String pValue) {
      value = pValue;
    }

    /**
     * Returns the text value of HTML attribute.
     *
     * @return  The text value of HTML attribute.
     */
    public String getValue() {
      return value;
    }
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of columns.
   */
  public int getMmCols();

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   */
  public int getMmFormatMaxLength();

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   */
  public String getMmFormatPattern();

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   */
  public MmBooleanLayout getMmLayout();

  /**
   * Returns accessor of attribute of model.
   *
   * @return  The accessor of attribute of model.
   */
  @Override
  public MmAttributeAccessor<?, MODELSIDE_VALUE> getMmModelAccessor();

  /**
   * Returns the type of modelside value of the mimic.
   *
   * @return  The type of modelside value of the mimic.
   */
  public Class<MODELSIDE_VALUE> getMmModelsideType();

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return  The modelside value of the mimic.
   */
  public MODELSIDE_VALUE getMmModelsideValue();

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of rows.
   */
  public int getMmRows();

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return  The attribute's row size of option list.
   */
  public int getMmSize();

  /**
   * Returns the attribute's type of viewside value (VIEWSIDE_VALUE).
   *
   * @return  The attribute's type of viewside value.
   */
  public Class<VIEWSIDE_VALUE> getMmViewsideType();

  /**
   * Returns the attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @return  The attribute's viewside value of type VIEWSIDE_VALUE.
   */
  public VIEWSIDE_VALUE getMmViewsideValue();

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  public boolean isMmEmpty();

  /**
   * Sets viewside value of mimic to specified value.
   *
   * @param  pViewsideValue  The specified value to be set.
   */
  public void setMmViewsideValue(VIEWSIDE_VALUE pViewsideValue);

}
