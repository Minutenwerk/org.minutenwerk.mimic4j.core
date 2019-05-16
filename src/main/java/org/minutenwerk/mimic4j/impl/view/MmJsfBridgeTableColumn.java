package org.minutenwerk.mimic4j.impl.view;

import org.minutenwerk.mimic4j.impl.composite.MmImplementationTableColumn;

/**
 * MmJsfBridgeTableColumn connects a table column mimic and a JSF table column view component.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeTableColumn extends MmJsfBridge<MmImplementationTableColumn, String, String> {

  /**
   * Creates a new MmJsfBridgeTableColumn instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeTableColumn(MmImplementationTableColumn pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the CSS style class of this column's footer.
   *
   * @return  The CSS style class of this column's footer.
   */
  @Override
  public String getFooterClass() {
    return implementation.getMmFooterClasses();
  }

  /**
   * Returns the CSS style class of this column's header.
   *
   * @return  The CSS style class of this column's header.
   */
  @Override
  public String getHeaderClass() {
    return implementation.getMmHeaderClasses();
  }

  /**
   * Returns the text of this column's header title (displayed by mouse over).
   *
   * @return  The text of this column's header title.
   */
  @Override
  public String getHeaderTitle() {
    return implementation.getMmLongDescription();
  }

  /**
   * Returns the text of this column's header.
   *
   * @return  The text of this column's header.
   */
  @Override
  public String getHeaderValue() {
    return implementation.getMmShortDescription();
  }

  /**
   * Returns true, if this column is a row header column of the table.
   *
   * @return  True, if this column is a row header column of the table.
   */
  @Override
  public boolean isRowHeader() {
    return implementation.isMmRowHeader();
  }

}
