package org.minutenwerk.mimic4j.api.mimic;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmCollectionAccessor;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableRow;

/**
 * MmTableMimic is the basic interface of table mimics containing table rows and table columns.
 *
 * @author  Olaf Kossak
 */
public interface MmTableMimic<ROW_MODEL> extends MmContainerMimic<List<ROW_MODEL>> {

  /**
   * Clears all rows of this table.
   */
  public void doMmClearTableRows();

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL> getMmModelAccessor();

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return  The list of table column mimics.
   */
  public List<MmTableColumn<?>> getMmTableColumns();

  /**
   * Returns the list of table row mimics.
   *
   * @return  The list of table row mimics.
   */
  public <T extends MmTableRow<ROW_MODEL>> List<T> getMmTableRows();

  /**
   * Returns true, if table contains rows.
   *
   * @return  true, if table contains rows.
   */
  public boolean isContainingRows();

}
