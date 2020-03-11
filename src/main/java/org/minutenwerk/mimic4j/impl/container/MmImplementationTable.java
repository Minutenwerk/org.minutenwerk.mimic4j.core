package org.minutenwerk.mimic4j.impl.container;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmCollectionAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableMimic;
import org.minutenwerk.mimic4j.impl.MmEditableMimicImpl;

/**
 * MmImplementationTable is the specific class for the implementation part of table mimics.
 *
 * @param               <ROW_MODEL>  The row model type.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-lifecycle, group-do, group-get
 */
public class MmImplementationTable<ROW_MODEL>
  extends MmBaseContainerImplementation<MmTable<ROW_MODEL>, List<ROW_MODEL>, MmConfigurationTable, MmTableAnnotation> implements MmTableMimic<ROW_MODEL> {

  /** The list of table columns of this table. */
  private final List<MmTableColumn<?>> tableColumns;

  /**
   * Creates a new MmImplementationTable instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationTable(MmDeclarationMimic pParent) {
    this(pParent, null);
  }

  /**
   * Creates a new MmImplementationTable instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root
   *                        accessor.
   */
  public MmImplementationTable(MmDeclarationMimic pParent, MmRootAccessor<List<ROW_MODEL>> pRootAccessor) {
    super(pParent, NULL_NAME, pRootAccessor);
    tableColumns = new ArrayList<>();
  }

  /**
   * Returns accessor of model.
   *
   * @return        The accessor of model.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  @SuppressWarnings("unchecked")
  public MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL> getMmModelAccessor() {
    ensureInitialization();

    return (MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL>)modelAccessor;
  }

  /**
   * TODOC.
   *
   * @throws        IllegalStateException  In case of callbackMmCreateTableRow doesn't return a MmTableRow.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passDataModelToViewModel() {
    // clear list of runtime children of table
    clearRuntimeChildrenList();

    final List<ROW_MODEL> rowModel = getMmModel();
    if (rowModel != null) {

      // iterate over list of row models
      for (int accessorIndex = 0; accessorIndex < rowModel.size(); accessorIndex++) {

        // for each row model create a table row mimic and pass row model
        MmTableRow<ROW_MODEL> tableRowMm = declaration.callbackMmCreateTableRow(accessorIndex);

        if (tableRowMm == null) {
          throw new IllegalStateException("callbackMmCreateTableRow() must return new MmTableRow");
        }

        // add table row mimic to list of runtime children
        addRuntimeChild(tableRowMm);
      }

      for (MmEditableMimicImpl child : getDirectImplementationChildrenOfType(MmEditableMimicImpl.class)) {
        child.passDataModelToViewModel();
      }
    }
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param         pAnnotation  The specified annotation, may be null.
   *
   * @return        Configuration of this mimic.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  protected MmConfigurationTable onConstructConfiguration(MmTableAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationTable(pAnnotation);
    } else {
      return new MmConfigurationTable();
    }
  }

  /**
   * Clears all rows of this table.
   *
   * @jalopy.group  group-do
   */
  @Override
  public void doMmClearTableRows() {
    ensureInitialization();

    clearRuntimeChildrenList();
  }

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return        The list of table column mimics.
   *
   * @jalopy.group  group-get
   */
  @Override
  public List<MmTableColumn<?>> getMmTableColumns() {
    ensureInitialization();

    if (tableColumns.isEmpty()) {
      getDirectDeclarationChildrenOfType(MmTableColumn.class).stream().forEach(tableColumns::add);
    }
    return tableColumns;
  }

  /**
   * Returns the list of table row mimics.
   *
   * @return        The list of table row mimics.
   *
   * @jalopy.group  group-get
   */
  @Override
  public <T extends MmTableRow<ROW_MODEL>> List<T> getMmTableRows() {
    ensureInitialization();

    List<T> returnList = new ArrayList<>();
    for (MmTableRow<?> child : getDirectDeclarationChildrenOfType(MmTableRow.class)) {
      @SuppressWarnings("unchecked")
      T tableRowMm = (T)child;
      returnList.add(tableRowMm);
    }
    return returnList;
  }

  /**
   * Returns true, if table contains rows.
   *
   * @return        true, if table contains rows.
   *
   * @jalopy.group  group-get
   */
  @Override
  public boolean isContainingRows() {
    ensureInitialization();

    final List<?> rows = getMmTableRows();
    return (rows != null) && (rows.size() > 0);
  }

  /**
   * Returns the table column index of specified column.
   *
   * @param   pTableColumn  TODOC
   *
   * @return  The table column index of specified column.
   *
   * @throws  IllegalArgumentException  In case of specified column is not a column of this table.
   */
  @Override
  public int getMmColumnIndex(final MmTableColumn<?> pTableColumn) {
    ensureInitialization();

    final int index = getMmTableColumns().indexOf(pTableColumn);
    if (index >= 0) {
      return index;
    } else {
      throw new IllegalArgumentException("Specified column " + pTableColumn + " is not a column of table " + this);
    }
  }

  /**
   * Returns the table column of specified column index.
   *
   * @param   pTableColumnIndex  Specified column index.
   *
   * @return  The table column of specified column index.
   *
   * @throws  IllegalArgumentException  In case of specified column does not exist.
   */
  @Override
  public final MmTableColumn<?> getMmColumnOfIndex(int pTableColumnIndex) {
    ensureInitialization();

    try {
      return getMmTableColumns().get(pTableColumnIndex);
    } catch (Exception e) {
      throw new IllegalArgumentException("Specified column of index " + pTableColumnIndex + " does not exist for table " + this);
    }
  }

}
