package org.minutenwerk.mimic4j.impl.container;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmTableMimic;
import org.minutenwerk.mimic4j.api.accessor.MmCollectionAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.MmEditableMimicImpl;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeTable;

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
  extends MmBaseContainerImplementation<MmTable<ROW_MODEL>, List<ROW_MODEL>, MmConfigurationTable, MmTableAnnotation>
  implements MmTableMimic<ROW_MODEL> {

  /** The list of table columns of this table. */
  protected final List<MmTableColumn<?>> tableColumns;

  /**
   * Creates a new MmImplementationTable instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationTable(MmDeclarationMimic pParent) {
    super(pParent);
    tableColumns = new ArrayList<>();
  }

  /**
   * Creates a new MmImplementationTable instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmImplementationTable(MmDeclarationMimic pParent, MmRootAccessor<List<ROW_MODEL>> pRootAccessor) {
    super(pParent, pRootAccessor);
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
    assureInitialization();

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

    // iterate over list of row models
    for (int i = 0; i < getMmModel().size(); i++) {

      // for each row model create a table row mimic and pass row model
      MmTableRow<ROW_MODEL> tableRowMm = declaration.callbackMmCreateTableRow(i);

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
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return        A new MmJsfBridge for this mimic.
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeTable<ROW_MODEL>(this);
  }

  /**
   * Clears all rows of this table.
   *
   * @jalopy.group  group-do
   */
  @Override
  public void doMmClearTableRows() {
    assureInitialization();

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
    assureInitialization();

    final List<MmTableColumn<?>> returnTableColumns = new ArrayList<>();
    getDirectDeclarationChildrenOfType(MmTableColumn.class).stream().forEach(returnTableColumns::add);
    return returnTableColumns;
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
    assureInitialization();

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
    assureInitialization();

    final List<?> rows = getMmTableRows();
    return (rows != null) && (rows.size() > 0);
  }

}
