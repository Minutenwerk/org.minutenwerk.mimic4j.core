package org.minutenwerk.mimic4j.impl.view;

import java.util.List;

import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessage;
import org.minutenwerk.mimic4j.impl.message.MmMessageSeverity;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmJsfBridgeAttribute connects an attribute mimic and a JSF attribute view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeAttribute<VIEW_MODEL>
  extends MmJsfBridge<MmBaseAttributeImplementation<?, ?, ?, ?, VIEW_MODEL>, VIEW_MODEL, String> {

  /**
   * Creates a new MmJsfBridgeAttribute instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeAttribute(MmBaseAttributeImplementation<?, ?, ?, ?, VIEW_MODEL> pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the number of columns of a multiline text field (attribute {@code}cols{@code}).
   *
   * @return  The number of columns of a multiline text field.
   */
  @Override
  public int getCols() {
    return implementation.getMmCols();
  }

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   */
  @Override
  public String getLayout() {
    return implementation.getMmLayout().getValue();
  }

  /**
   * Returns the attribute's maximum number of characters for input in view.
   *
   * @return  The attribute's maximum number of characters for input.
   */
  @Override
  public int getMaxlength() {
    return implementation.getMmFormatMaxLength();
  }

  /**
   * Returns a list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   *
   * @return  A list of {@link MmMessage}, containing error, warning, info and success messages of this mimic.
   */
  @Override
  public List<MmMessage> getMsgList() {
    return implementation.getMmMessages();
  }

  /**
   * Returns one string containing all error, warning, info and success messages of this mimic.
   *
   * @return  One string containing all error, warning, info and success messages of this mimic.
   */
  @Override
  public String getMsgListText() {
    String returnText = "";
    for (MmMessage message : implementation.getMmMessages()) {
      returnText = returnText + message.getText() + " ";
    }
    return returnText;
  }

  /**
   * Returns the highest severity of error message of this mimic, returns empty string in case of no messages.
   *
   * @return  The highest severity of error message of this mimic.
   */
  @Override
  public String getMsgMaxSeverity() {
    MmMessageSeverity severity = implementation.getMmMaximumSeverity();
    if (severity != null) {
      return severity.name().toLowerCase();
    } else {
      return "";
    }
  }

  /**
   * Returns the title of error message of highest severity of this mimic.
   *
   * @return  The title of error message of highest severity of this mimic.
   */
  @Override
  public String getMsgMaxTitle() {
    String returnTitle = "";
    String maxSeverity = getMsgMaxSeverity();
    if (!maxSeverity.isEmpty()) {
      returnTitle = implementation.getMmRoot().getMmI18nText(maxSeverity, MmMessageType.SHORT);
    }
    return returnTitle;
  }

  /**
   * Returns the number of rows of a multiline text field (attribute {@code}cols{@code}).
   *
   * @return  The number of rows of a multiline text field.
   */
  @Override
  public int getRows() {
    return implementation.getMmRows();
  }

  /**
   * Returns the attribute's row size of option list in view.
   *
   * @return  The attribute's row size of option list.
   */
  @Override
  public int getSize() {
    return implementation.getMmSize();
  }

  /**
   * Returns the value of type VIEW_MODEL from mimic, to be displayed in HTML tag.
   *
   * @return  The value of type VIEW_MODEL from mimic, to be displayed in HTML tag.
   */
  @Override
  public VIEW_MODEL getValue() {
    return implementation.getMmViewModelValue();
  }

  /**
   * Sets the value of type VIEW_MODEL into mimic, usually called by input HTML tags.
   *
   * @param  pValue  The value of type VIEW_MODEL to be set.
   */
  @Override
  public void setValue(VIEW_MODEL pValue) {
    implementation.setMmViewModelValue(pValue);
  }

}
