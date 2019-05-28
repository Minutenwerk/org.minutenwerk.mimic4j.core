package org.minutenwerk.mimic4j.impl.container;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmEditableMimicImpl;
import org.minutenwerk.mimic4j.api.MmTableMimic;
import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.accessor.MmCollectionAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeTable;

/**
 * MmImplementationTable is the specific class for the implementation part of table mimics.
 *
 * @param   <ROW_MODEL>  The row model type.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationTable<ROW_MODEL>
  extends MmBaseContainerImplementation<MmTable<ROW_MODEL>, List<ROW_MODEL>, MmConfigurationTable, MmTableAnnotation>
  implements MmTableMimic<ROW_MODEL> {

  /** The logger of this class. */
  private static final Logger         LOGGER       = LogManager.getLogger(MmImplementationTable.class);

  /** The list of table columns of this table. */
  protected final List<MmTableColumn> tableColumns;

  /**
   * Creates a new MmImplementationTable instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationTable(MmDeclarationMimic pParent) {
    super(pParent);
    tableColumns = new ArrayList<>();
  }

  /**
   * Creates a new MmImplementationTable instance.
   *
   * @param  pParent        The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmImplementationTable(MmDeclarationMimic pParent, MmRootAccessor<List<ROW_MODEL>> pRootAccessor) {
    super(pParent, pRootAccessor);
    tableColumns = new ArrayList<>();
  }

  /**
   * Clears all rows of this table.
   */
  @Override
  public void doMmClearTableRows() {
    assureInitialization();

    clearRuntimeChildrenList();
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
   * Returns the list of table column mimics of this table mimic.
   *
   * @return        The list of table column mimics.
   *
   * @jalopy.group  group-get
   */
  @Override
  public List<MmTableColumn> getMmTableColumns() {
    assureInitialization();

    return getImplementationChildrenOfType(MmTableColumn.class);
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
    for (MmTableRow<?> child : getImplementationChildrenOfType(MmTableRow.class)) {
      @SuppressWarnings("unchecked")
      T tableRowMm = (T)child;
      returnList.add(tableRowMm);
    }
    return returnList;
  }

  /**
   * TODOC.
   *
   * @throws        IllegalStateException  TODOC
   *
   * @jalopy.group  group-lifecycle
   */
  @Override
  public void passModelsideToViewside() {
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
      // TODO warum kein Name?
      addChild(tableRowMm, null, typeOfFirstGenericParameter);
    }

    for (MmImplementationTableRow<?> child : getImplementationChildrenOfType(MmImplementationTableRow.class)) {
      child.initialize();
    }

    for (MmEditableMimicImpl child : getImplementationChildrenOfType(MmEditableMimicImpl.class)) {
      child.passModelsideToViewside();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeTable<ROW_MODEL>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    if (LOGGER.isDebugEnabled()) {
      checkForIllegalAnnotationsOtherThan(declaration, MmTableAnnotation.class);
    }

    MmTableAnnotation annotation = findAnnotation(declaration, MmTableAnnotation.class);
    if (annotation != null) {
      configuration = new MmConfigurationTable(annotation);
    } else {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationTable();
    }
  }
}
