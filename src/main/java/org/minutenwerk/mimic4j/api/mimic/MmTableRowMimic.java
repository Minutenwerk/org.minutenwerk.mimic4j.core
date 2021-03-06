package org.minutenwerk.mimic4j.api.mimic;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmListEntryAccessor;

/**
 * MmTableRowMimic is the basic interface of table row mimics containing table row cells.
 *
 * @author  Olaf Kossak
 */
public interface MmTableRowMimic<ROW_MODEL> extends MmContainerMimic<ROW_MODEL> {

  /**
   * Returns the accessor index of this row.
   *
   * @return  The accessor index of this row.
   */
  public int getMmAccessorIndex();

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public MmListEntryAccessor<ROW_MODEL> getMmModelAccessor();

  /**
   * Returns table mimic of this row.
   *
   * @return  The table mimic of this row.
   */
  public <T extends MmTableMimic<ROW_MODEL>> T getMmParentTable();

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  public int getMmRowIndex();

  /**
   * Returns list of table row children.
   *
   * @return  The list of table row children.
   */
  public List<MmMimic> getMmTableCells();

}
