package org.minutenwerk.mimic4j.impl.view;

import java.util.List;

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

}
