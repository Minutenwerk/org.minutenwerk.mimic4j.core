package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;

/**
 * MmAttributeMimic is the basic interface of all mimic types containing editable data, which can be changed from view model. Mimics of type
 * MmAttributeMimic can be set from data model and view model, and can be converted in both directions by means of format patterns.
 *
 * @author  Olaf Kossak
 */
public interface MmAttributeMimic<DATA_MODEL, VIEW_MODEL> extends MmEditableMimic {

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
   * Returns the type of data model value of the mimic.
   *
   * @return  The type of data model value of the mimic.
   */
  public Class<DATA_MODEL> getMmDataModelType();

  /**
   * Returns the data model value of the mimic. The data model value is exchanged between model and mimic.
   *
   * @return  The data model value of the mimic.
   */
  public DATA_MODEL getMmDataModelValue();

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   */
  public int getMmFormatMaxLength();

  /**
   * Returns the attribute's format pattern for displaying view model value in view. It is used during conversion from data model to view
   * model value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying view model value.
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
  public MmAttributeAccessor<?, DATA_MODEL> getMmModelAccessor();

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
   * Returns the attribute's type of view model value (VIEW_MODEL).
   *
   * @return  The attribute's type of view model value.
   */
  public Class<VIEW_MODEL> getMmViewModelType();

  /**
   * Returns the attribute's view model value of type VIEW_MODEL.
   *
   * @return  The attribute's view model value of type VIEW_MODEL.
   */
  public VIEW_MODEL getMmViewModelValue();

  /**
   * Returns <code>true</code> if the view model value of this mimic is empty.
   *
   * @return  <code>True</code> if the view model value of this mimic is empty.
   */
  public boolean isMmEmpty();

  /**
   * Sets view model value of mimic to specified value.
   *
   * @param  pViewModelValue  The specified value to be set.
   */
  public void setMmViewModelValue(VIEW_MODEL pViewModelValue);

}
