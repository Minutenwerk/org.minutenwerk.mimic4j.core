package org.minutenwerk.mimic4j.impl.attribute;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseEditable is an abstract base class for all editable attribute mimics.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-lifecycle
 */
public abstract class MmBaseAttributeDeclaration<IMPLEMENTATION extends MmBaseAttributeImplementation<?, ?, MODELSIDE_VALUE, VIEWSIDE_VALUE>,
  MODELSIDE_VALUE, VIEWSIDE_VALUE> extends MmBaseDeclaration<MmAttributeMimic<MODELSIDE_VALUE, VIEWSIDE_VALUE>, IMPLEMENTATION>
  implements MmAttributeMimic<MODELSIDE_VALUE, VIEWSIDE_VALUE>, MmAttributeCallback<MODELSIDE_VALUE, VIEWSIDE_VALUE> {

  /** Constant for value to be displayed in case of the viewside value is null. */
  public static final String ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE = "";

  /** Constant for default value of maximum input length. */
  public static final int    EDITABLE_DEFAULT_MAX_LENGTH          = 255;

  /**
   * Creates a new MmBaseEditable instance.
   *
   * @param  pImplementation  The implementation part of the mimic.
   */
  public MmBaseAttributeDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's format pattern for displaying viewside value.
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmGetFormatPattern(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the configuration of maximum length of formatted input string.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The configuration of maximum length of formatted input string.
   *
   * @jalopy.group  group-callback
   */
  @Override public int callbackMmGetMaxLength(int pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        <code>True</code> if a value from view has to be set.
   *
   * @jalopy.group  group-callback
   */
  @Override public boolean callbackMmIsRequired(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Sets modelside value of mimic to specified value. Sets reset value as well to specified value.
   *
   * @param         pModelsideValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override public final void setMmModelsideValue(MODELSIDE_VALUE pModelsideValue) {
    this.implementation.setMmModelsideValue(pModelsideValue);
  }

  /**
   * Sets viewside value of mimic to specified value.
   *
   * @param         pViewsideValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override public final void setMmViewsideValue(VIEWSIDE_VALUE pViewsideValue) {
    this.implementation.setMmViewsideValue(pViewsideValue);
  }

  /**
   * Resets the attribute to its reset value, by:
   *
   * <ol>
   *   <li>passing reset value into modelside value</li>
   *   <li>converting modelside value to viewside type</li>
   *   <li>passing converted value into viewside value</li>
   * </ol>
   */
  @Override public final void doMmReset() {
    this.implementation.clearMmMessageList();
    this.implementation.doMmReset();
  }

  /**
   * Sets the attribute to its default value, by:
   *
   * <ol>
   *   <li>passing default value into modelside value</li>
   *   <li>converting modelside value to viewside type</li>
   *   <li>passing converted value into viewside value</li>
   * </ol>
   */
  @Override public final void doMmSetDefaults() {
    this.implementation.clearMmMessageList();
    this.implementation.doMmSetDefaults();
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>passing viewside value into modelside value</li>
   *   <li>converting viewside value to modelside type</li>
   *   <li>passing converted value into modelside value</li>
   *   <li>validating modelside value</li>
   * </ol>
   */
  @Override public final void doMmValidate() {
    this.implementation.clearMmMessageList();
    this.implementation.doMmValidate();
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of columns.
   */
  @Override public final int getMmCols() {
    return this.implementation.getMmCols();
  }

  /**
   * Returns the attribute's default value of type MODELSIDE_VALUE.
   *
   * @return  The attribute's default value of type MODELSIDE_VALUE.
   */
  @Override public final MODELSIDE_VALUE getMmDefaultValue() {
    return this.implementation.getMmDefaultValue();
  }

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   */
  @Override public final int getMmFormatMaxLength() {
    return this.implementation.getMmFormatMaxLength();
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   */
  @Override public final String getMmFormatPattern() {
    return this.implementation.getMmFormatPattern();
  }

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   */
  @Override public final MmBooleanLayout getMmLayout() {
    return this.implementation.getMmLayout();
  }

  /**
   * Returns the type of modelside value of the mimic.
   *
   * @return  The type of modelside value of the mimic.
   */
  @Override public final Class<MODELSIDE_VALUE> getMmModelsideType() {
    return this.implementation.getMmModelsideType();
  }

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return  The modelside value of the mimic.
   */
  @Override public final MODELSIDE_VALUE getMmModelsideValue() {
    return this.implementation.getMmModelsideValue();
  }

  /**
   * Returns the attribute's reset value of type MODELSIDE_VALUE.
   *
   * @return  The attribute's reset value of type MODELSIDE_VALUE.
   */
  @Override public final MODELSIDE_VALUE getMmResetValue() {
    return this.implementation.getMmResetValue();
  }

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of rows.
   */
  @Override public final int getMmRows() {
    return this.implementation.getMmRows();
  }

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return  The attribute's row size of option list.
   */
  @Override public final int getMmSize() {
    return this.implementation.getMmSize();
  }

  /**
   * Returns the attribute's type of viewside value (VIEWSIDE_VALUE).
   *
   * @return  The attribute's type of viewside value.
   */
  @Override public final Class<VIEWSIDE_VALUE> getMmViewsideType() {
    return this.implementation.getMmViewsideType();
  }

  /**
   * Returns the attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @return  The attribute's viewside value of type VIEWSIDE_VALUE.
   */
  @Override public final VIEWSIDE_VALUE getMmViewsideValue() {
    return this.implementation.getMmViewsideValue();
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from viewside.
   */
  @Override public final boolean isMmChangedFromViewside() {
    return this.implementation.isMmChangedFromViewside();
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override public final boolean isMmEmpty() {
    return this.implementation.isMmEmpty();
  }

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return  <code>True</code> if a value from view has to be set.
   */
  @Override public final boolean isMmRequired() {
    return this.implementation.isMmRequired();
  }

  /**
   * Returns <code>true</code> if the mimic is in such a state, that the action {@link MmEditableMimic.doMmReset} is executable.
   *
   * @return  <code>true</code> if the action {@link MmEditableMimic.doMmReset} is executable.
   */
  @Override public final boolean isMmResetEnabled() {
    return this.implementation.isMmResetEnabled();
  }

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return  <code>True</code> if the mimic has been validated without any errors.
   */
  @Override public final boolean isMmValid() {
    return this.implementation.isMmValid();
  }

  /**
   * Returns the initialized number formatter of this mimic.
   *
   * @return  The initialized number formatter of this mimic.
   */
  protected NumberFormat getMmNumberFormatter() {
    final String formatPattern = this.getMmFormatPattern();
    assert formatPattern != null : "callbackMmGetFormatPattern() must return valid format pattern";

    final MmRoot        root                  = MmRelationshipApi.getMmRoot(this);
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.applyPattern(formatPattern);
    return returnNumberFormatter;
  }

}
