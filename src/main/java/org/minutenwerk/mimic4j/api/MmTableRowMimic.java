package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.api.accessor.MmListEntryAccessor;

/**
 * MmTableRowMimic is the basic interface of table row mimics containing table row cells.
 *
 * @author  Olaf Kossak
 */
public interface MmTableRowMimic<ROW_MODEL> extends MmContainerMimic<ROW_MODEL> {

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public MmListEntryAccessor<ROW_MODEL> getMmModelAccessor();

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  public int getMmRowIndex();

}
