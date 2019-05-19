package org.minutenwerk.mimic4j.api;

/**
 * MmTableRowMimic is the basic interface of table row mimics containing table row cells.
 *
 * @author  Olaf Kossak
 */
public interface MmTableRowMimic<ROW_MODEL> extends MmContainerMimic<ROW_MODEL> {

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  public int getMmRowIndex();

}
