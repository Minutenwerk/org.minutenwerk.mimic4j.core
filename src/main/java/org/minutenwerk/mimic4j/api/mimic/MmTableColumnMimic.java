package org.minutenwerk.mimic4j.api.mimic;

/**
 * MmTableColumnMimic is the basic interface of table column mimics.
 *
 * @author  Olaf Kossak
 */
public interface MmTableColumnMimic<MODEL> extends MmContainerMimic<MODEL> {

  /**
   * Returns the table column index of this column.
   *
   * @return  The table column index of this column.
   */
  public int getMmColumnIndex();

  /**
   * Returns table mimic of this column.
   *
   * @return  The table mimic of this column.
   */
  public <T extends MmTableMimic<?>> T getMmParentTable();

}
