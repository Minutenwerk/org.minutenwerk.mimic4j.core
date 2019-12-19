package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;

/**
 * MmAttributeMimic is the basic interface of all mimic types containing editable data, which can be changed from view model. Mimics of type MmAttributeMimic
 * can be set from data model and view model, and can be converted in both directions by means of format patterns.
 *
 * @param   <ATTRIBUTE_TYPE>  Type of attribute of model.
 * @param   <VIEW_TYPE>       Type of view value of attribute, passed to HTML tag, is either String or Boolean.
 *
 * @author  Olaf Kossak
 */
public interface MmAttributeMimic<ATTRIBUTE_TYPE, VIEW_TYPE> extends MmEditableMimic {

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
   * Returns the attribute's format pattern for displaying view value in view. It is used during conversion from data model to view model value and vice
   * versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying view value.
   */
  public String getMmFormatPattern();

  /**
   * Returns the data model value of the mimic. The data model value is exchanged between model and mimic.
   *
   * @return  The data model value of the mimic.
   */
  public ATTRIBUTE_TYPE getMmModel();

  /**
   * Returns accessor of attribute of model.
   *
   * @return  The accessor of attribute of model.
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_TYPE> getMmModelAccessor();

  /**
   * Returns the type of data model value of the mimic.
   *
   * @return  The type of data model value of the mimic.
   */
  public Class<ATTRIBUTE_TYPE> getMmModelType();

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
   * Returns the attribute's type of view value.
   *
   * @return  The attribute's type of view value.
   */
  public Class<VIEW_TYPE> getMmViewType();

  /**
   * Returns the attribute's view value of type VIEW_TYPE.
   *
   * @return  The attribute's view value of type VIEW_TYPE.
   */
  public VIEW_TYPE getMmViewValue();

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  public boolean isMmEmpty();

  /**
   * Sets view value of mimic to specified value.
   *
   * @param  pViewModelValue  The specified value to be set.
   */
  public void setMmViewValue(VIEW_TYPE pViewModelValue);

}
