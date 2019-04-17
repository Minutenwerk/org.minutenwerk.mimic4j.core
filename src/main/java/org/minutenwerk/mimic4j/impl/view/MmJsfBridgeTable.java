package org.minutenwerk.mimic4j.impl.view;

import java.util.List;

import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTable;

/**
 * MmJsfBridgeTable connects a table mimic and a JSF table view component.
 *
 * @param   <ROW_MODEL>  The row model type is a kind of POJO.
 *
 * @author  Olaf Kossak
 */
public class MmJsfBridgeTable<ROW_MODEL>
  extends MmJsfBridge<MmImplementationTable<ROW_MODEL>, List<MmTableRow<ROW_MODEL>>, List<ROW_MODEL>> {

  /**
   * Creates a new MmJsfBridgeTable instance.
   *
   * @param  pImplementation  The implementation part of connected mimic.
   */
  public MmJsfBridgeTable(MmImplementationTable<ROW_MODEL> pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the caption text of a table (tag caption in tag h:dataTable).
   *
   * @return  The caption text of a table.
   */
  @Override
  public String getCaption() {
    return this.implementation.getMmShortDescription();
  }

  /**
   * Returns the CSS style classes of columns, one style class for each column, comma separated.
   *
   * @return  The CSS style classes of columns.
   */
  @Override
  public String getColumnClasses() {
    String returnString = "";
    for (MmTableColumn column : this.implementation.getMmTableColumns()) {
      if (returnString.length() > 0) {
        returnString += ",";
      }

      returnString += column.getMmStyleClasses();
    }
    return returnString;
  }

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return  The list of table column mimics.
   */
  @Override
  public List<MmTableColumn> getColumnMms() {
    return this.implementation.getMmTableColumns();
  }

  /**
   * Returns the "value of a table mimic of type VIEWSIDE_VALUE", which is a list of table row mimics.
   *
   * @return  A list of table row mimics.
   */
  @Override
  public List<MmTableRow<ROW_MODEL>> getValue() {
    return this.implementation.getMmTableRows();
  }

}
