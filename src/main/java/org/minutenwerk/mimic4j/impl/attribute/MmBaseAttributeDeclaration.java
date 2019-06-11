package org.minutenwerk.mimic4j.impl.attribute;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseEditable is an abstract base class for all editable attribute mimics.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-lifecycle
 */
public abstract class MmBaseAttributeDeclaration<IMPLEMENTATION extends MmBaseAttributeImplementation<?,
    ?, ?, ATTRIBUTE_MODEL, VIEWSIDE_VALUE>, ATTRIBUTE_MODEL, VIEWSIDE_VALUE>
  extends MmBaseDeclaration<MmAttributeMimic<ATTRIBUTE_MODEL, VIEWSIDE_VALUE>, IMPLEMENTATION>
  implements MmAttributeMimic<ATTRIBUTE_MODEL, VIEWSIDE_VALUE>, MmAttributeCallback<ATTRIBUTE_MODEL, VIEWSIDE_VALUE> {

  /** Logger of this class. */
  private static final Logger LOGGER                               = LogManager.getLogger(MmBaseAttributeDeclaration.class);

  /** Constant for value to be displayed in case of the viewside value is null. */
  public static final String  ATTRIBUTE_STRING_VIEWSIDE_NULL_VALUE = "";

  /** Constant for default value of maximum input length. */
  public static final int     EDITABLE_DEFAULT_MAX_LENGTH          = 255;

  /**
   * Creates a new MmBaseEditable instance.
   *
   * @param  pImplementation  The implementation part of the mimic.
   */
  protected MmBaseAttributeDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the attribute's accessor to corresponding model field. The attribute accessor can be derived from specified root component
   * accessor.
   *
   * @param         pRootAccessor  The specified root component accessor.
   *
   * @return        The attribute's accessor.
   *
   * @throws        IllegalStateException  In case of model accessor is not defined.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_MODEL> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
    throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + getMmFullName());
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
  @Override
  public String callbackMmGetFormatPattern(String pPassThroughValue) {
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
  @Override
  public int callbackMmGetMaxLength(int pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of options of type {@link MmSelectOption}, which can be transformed to an option list of a select box.
   *
   * @return        A list of options.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public <OPTION_VALUE_TYPE> List<MmSelectOption<OPTION_VALUE_TYPE>> callbackMmGetSelectOptions() {
    return null;
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
  @Override
  public boolean callbackMmIsRequired(boolean pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Validates attribute, by:
   *
   * <ol>
   *   <li>converting viewside value to modelside type</li>
   *   <li>passing converted value into modelside value</li>
   *   <li>validating modelside value</li>
   * </ol>
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public final void doMmValidate() {
    implementation.clearMmMessageList();
    implementation.doMmValidate();
  }

  /**
   * Sets viewside value of mimic to specified value.
   *
   * @param         pViewsideValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public final void setMmViewsideValue(VIEWSIDE_VALUE pViewsideValue) {
    implementation.setMmViewsideValue(pViewsideValue);
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of columns.
   */
  @Override
  public final int getMmCols() {
    return implementation.getMmCols();
  }

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   */
  @Override
  public final int getMmFormatMaxLength() {
    return implementation.getMmFormatMaxLength();
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   */
  @Override
  public final String getMmFormatPattern() {
    return implementation.getMmFormatPattern();
  }

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   */
  @Override
  public final MmBooleanLayout getMmLayout() {
    return implementation.getMmLayout();
  }

  /**
   * Returns accessor of attribute of model.
   *
   * @return  The accessor of attribute of model.
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns the type of modelside value of the mimic.
   *
   * @return  The type of modelside value of the mimic.
   */
  @Override
  public final Class<ATTRIBUTE_MODEL> getMmModelsideType() {
    return implementation.getMmModelsideType();
  }

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return  The modelside value of the mimic.
   */
  @Override
  public final ATTRIBUTE_MODEL getMmModelsideValue() {
    return implementation.getMmModelsideValue();
  }

  /**
   * Returns accessor of root component of model.
   *
   * @return  The accessor of root component of model.
   */
  @Override
  public MmRootAccessor<?> getMmRootAccessor() {
    return implementation.getMmRootAccessor();
  }

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of rows.
   */
  @Override
  public final int getMmRows() {
    return implementation.getMmRows();
  }

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return  The attribute's row size of option list.
   */
  @Override
  public final int getMmSize() {
    return implementation.getMmSize();
  }

  /**
   * Returns the attribute's type of viewside value (VIEWSIDE_VALUE).
   *
   * @return  The attribute's type of viewside value.
   */
  @Override
  public final Class<VIEWSIDE_VALUE> getMmViewsideType() {
    return implementation.getMmViewsideType();
  }

  /**
   * Returns the attribute's viewside value of type VIEWSIDE_VALUE.
   *
   * @return  The attribute's viewside value of type VIEWSIDE_VALUE.
   */
  @Override
  public final VIEWSIDE_VALUE getMmViewsideValue() {
    return implementation.getMmViewsideValue();
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from viewside. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from viewside.
   */
  @Override
  public final boolean isMmChangedFromViewside() {
    return implementation.isMmChangedFromViewside();
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public final boolean isMmEmpty() {
    return implementation.isMmEmpty();
  }

  /**
   * Returns <code>true</code> if a value from view has to be set for this mimic or one of its children.
   *
   * @return  <code>True</code> if a value from view has to be set.
   */
  @Override
  public final boolean isMmRequired() {
    return implementation.isMmRequired();
  }

  /**
   * Returns <code>true</code> if the mimic has been validated without any errors.
   *
   * @return  <code>True</code> if the mimic has been validated without any errors.
   */
  @Override
  public final boolean isMmValid() {
    return implementation.isMmValid();
  }

  /**
   * Returns the initialized number formatter of this mimic.
   *
   * @return  The initialized number formatter of this mimic.
   *
   * @throws  IllegalStateException  In case of getMmFormatPattern returns an invalid format pattern.
   */
  protected NumberFormat getMmNumberFormatter() {
    final String formatPattern = getMmFormatPattern();
    if (LOGGER.isDebugEnabled()) {
      if (formatPattern == null) {
        throw new IllegalStateException("getMmFormatPattern() must return valid format pattern");
      }
    }

    final MmRoot        root                  = MmRelationshipApi.getMmRoot(this);
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.applyPattern(formatPattern);
    return returnNumberFormatter;
  }

}
