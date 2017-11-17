package org.minutenwerk.mimic4j.impl.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.container.MmTableRow;

/**
 * MmTableCallback defines a set of override-able methods common to all table mimics. Callback methods are part of the declaration API of
 * mimics. Callback methods have a default implementation, but can be overridden by a customized implementation on the declaration part.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmTableCallback<ROW_MODEL> extends MmContainerCallback<List<ROW_MODEL>> {

  /**
   * Returns a new table row mimic of type {@link MmTableRow}.
   *
   * @param   pRowIndex  The table row index to be stored in new table row mimic.
   *
   * @return  The new table row mimic.
   *
   * @since   $maven.project.version$
   */
  public MmTableRow<ROW_MODEL> callbackMmCreateTableRow(int pRowIndex);

  /**
   * Returns the list of table row mimics.
   *
   * @return  The list of table row mimics.
   *
   * @since   $maven.project.version$
   */
  public <T extends MmTableRow<ROW_MODEL>> List<T> getMmTableRows();

}
