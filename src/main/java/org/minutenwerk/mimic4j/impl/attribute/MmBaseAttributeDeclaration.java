package org.minutenwerk.mimic4j.impl.attribute;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseEditable is an abstract base class for all editable attribute mimics.
 *
 * @param               <IMPLEMENTATION>  Implementation part of this mimic.
 * @param               <ATTRIBUTE_TYPE>  Type of attribute of model.
 * @param               <VIEW_TYPE>       Type of view value of attribute, passed to HTML tag.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-lifecycle
 */
public abstract class MmBaseAttributeDeclaration<IMPLEMENTATION extends MmBaseAttributeImplementation<?, ?, ?, ATTRIBUTE_TYPE, VIEW_TYPE>,
  ATTRIBUTE_TYPE, VIEW_TYPE> extends MmBaseDeclaration<MmAttributeMimic<ATTRIBUTE_TYPE, VIEW_TYPE>, IMPLEMENTATION>
  implements MmAttributeMimic<ATTRIBUTE_TYPE, VIEW_TYPE>, MmAttributeCallback<ATTRIBUTE_TYPE, VIEW_TYPE> {

  /** Logger of this class. */
  private static final Logger LOGGER                           = LogManager.getLogger(MmBaseAttributeDeclaration.class);

  /** Constant for value to be displayed in case of the view value is null. */
  public static final String  ATTRIBUTE_STRING_VIEW_NULL_VALUE = "";

  /** Constant for default value of maximum input length. */
  public static final int     EDITABLE_DEFAULT_MAX_LENGTH      = 255;

  /**
   * Creates a new MmBaseEditable instance.
   *
   * @param  pImplementation  The implementation part of the mimic.
   */
  protected MmBaseAttributeDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the attribute's format pattern for displaying view value in view. It is used during conversion from data model to view model
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's format pattern for displaying view value.
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
   * Returns the attribute's accessor to corresponding model. The attribute accessor can be derived from specified parent component
   * accessor.
   *
   * @param         pParentAccessor  The specified parent component accessor.
   *
   * @return        The attribute's accessor.
   *
   * @throws        ClassCastException  IllegalStateException In case of model accessor is not defined.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_TYPE> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      @SuppressWarnings("unchecked")
      MmAttributeAccessor<?, ATTRIBUTE_TYPE> modelAccessor = (MmAttributeAccessor<?, ATTRIBUTE_TYPE>)pParentAccessor;
      return modelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to attributeAccessor. You must redefine callbackMmGetModelAccessor() for " + this);
    }
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
   *   <li>converting view value to data model type</li>
   *   <li>passing converted value into data model value</li>
   *   <li>validating data model value</li>
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
   * Sets view value of mimic to specified value.
   *
   * @param         pViewModelValue  The specified value to be set.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public final void setMmViewValue(VIEW_TYPE pViewModelValue) {
    implementation.setMmViewValue(pViewModelValue);
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
   * Returns the attribute's format pattern for displaying view value in view. It is used during conversion from data model to view model
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying view value.
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
   * Returns the data model value of the mimic. The data model value is exchanged between model and mimic.
   *
   * @return  The data model value of the mimic.
   */
  @Override
  public final ATTRIBUTE_TYPE getMmModel() {
    return implementation.getMmModel();
  }

  /**
   * Returns accessor of attribute of model.
   *
   * @return  The accessor of attribute of model.
   */
  @Override
  public MmAttributeAccessor<?, ATTRIBUTE_TYPE> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns the type of data model value of the mimic.
   *
   * @return  The type of data model value of the mimic.
   */
  @Override
  public final Class<ATTRIBUTE_TYPE> getMmModelType() {
    return implementation.getMmModelType();
  }

  /**
   * Returns accessor of model of parent container mimic, may be null.
   *
   * @return  The accessor of model of parent container mimic, may be null.
   */
  @Override
  public MmModelAccessor<?, ?> getMmParentAccessor() {
    return implementation.getMmParentAccessor();
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
   * Returns the attribute's type of view value (VIEW_TYPE).
   *
   * @return  The attribute's type of view value.
   */
  @Override
  public final Class<VIEW_TYPE> getMmViewType() {
    return implementation.getMmViewType();
  }

  /**
   * Returns the attribute's view value of type VIEW_TYPE.
   *
   * @return  The attribute's view value of type VIEW_TYPE.
   */
  @Override
  public final VIEW_TYPE getMmViewValue() {
    return implementation.getMmViewValue();
  }

  /**
   * Returns <code>true</code>, if the mimic has been changed from view model. If a mimic is changed, all ancestors of type MmEditableMimic
   * are marked as being changed as well.
   *
   * @return  <code>True</code>, if mimic has been changed from view model.
   */
  @Override
  public final boolean isMmChangedFromView() {
    return implementation.isMmChangedFromView();
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
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

    final Locale        locale                = getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.applyPattern(formatPattern);
    return returnNumberFormatter;
  }

}
