package org.minutenwerk.mimic4j.api;

/**
 * MmAttributeMimic is the basic interface of all mimic types containing editable data, which can be changed from viewside. Mimics of type
 * MmAttributeMimic can be set from modelside and viewside, and can be converted in both directions by means of format patterns.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmAttributeMimic<MODELSIDE_VALUE, VIEWSIDE_VALUE> extends MmEditableMimic {

  /**
   * MmBooleanLayout is an enumeration of layout directions for JSF tags of type checkbox.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/MmAttributeMimic.java\$
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
      this.value = pValue;
    }

    /**
     * Returns the text value of HTML attribute.
     *
     * @return  The text value of HTML attribute.
     *
     * @since   $maven.project.version$
     */
    public String getValue() {
      return this.value;
    }
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of columns.
   *
   * @since   $maven.project.version$
   */
  public int getMmCols();

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @return  The attribute's default value of type MODELSIDE_VALUE.
   *
   * @since   $maven.project.version$
   */
  public MODELSIDE_VALUE getMmDefaultValue();

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   *
   * @since   $maven.project.version$
   */
  public int getMmFormatMaxLength();

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   *
   * @since   $maven.project.version$
   */
  public String getMmFormatPattern();

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   *
   * @since   $maven.project.version$
   */
  public MmBooleanLayout getMmLayout();

  /**
   * Returns the type of modelside value of the mimic.
   *
   * @return  The type of modelside value of the mimic.
   *
   * @since   $maven.project.version$
   */
  public Class<MODELSIDE_VALUE> getMmModelsideType();

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return  The modelside value of the mimic.
   *
   * @since   $maven.project.version$
   */
  public MODELSIDE_VALUE getMmModelsideValue();

  /**
   * Returns the attribute's reset value of type MODELSIDE_VALUE.
   *
   * @return  The attribute's reset value of type MODELSIDE_VALUE.
   *
   * @since   $maven.project.version$
   */
  public MODELSIDE_VALUE getMmResetValue();

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of rows.
   *
   * @since   $maven.project.version$
   */
  public int getMmRows();

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return  The attribute's row size of option list.
   *
   * @since   $maven.project.version$
   */
  public int getMmSize();

  /**
   * Returns the attribute's type of viewside value (VIEWSIDE_VALUE).
   *
   * @return  The attribute's type of viewside value.
   *
   * @since   $maven.project.version$
   */
  public Class<VIEWSIDE_VALUE> getMmViewsideType();

  /**
   * Returns the attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @return  The attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @since   $maven.project.version$
   */
  public VIEWSIDE_VALUE getMmViewsideValue();

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   *
   * @since   $maven.project.version$
   */
  public boolean isMmEmpty();

  /**
   * Sets modelside value of mimic to specified value. Sets reset value as well to specified value.
   *
   * @param  pModelsideValue  The specified value to be set.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(MODELSIDE_VALUE pModelsideValue);

  /**
   * Sets viewside value of mimic to specified value.
   *
   * @param  pViewsideValue  The specified value to be set.
   *
   * @since  $maven.project.version$
   */
  public void setMmViewsideValue(VIEWSIDE_VALUE pViewsideValue);

}
