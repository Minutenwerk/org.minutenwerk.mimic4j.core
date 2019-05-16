package org.minutenwerk.mimic4j.impl.container;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
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
  extends MmBaseContainerImplementation<MmTable<ROW_MODEL>, List<ROW_MODEL>, MmConfigurationTable> {

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
   * Clears all table rows of this table.
   */
  public void doMmClearTableRows() {
    assureInitialization();

    clearRuntimeChildrenList();
  }

  /**
   * Sets the values from model to modelside of mimic.
   *
   * @param         pRowModelList  The model to set.
   *
   * @jalopy.group  group-do
   */
  public void doMmSetModelsideFromModel(List<ROW_MODEL> pRowModelList) {
    assureInitialization();

    clearMessageListRecursively(this);

    // store modelside reference to model
    // model = pRowModelList;

    // clear list of runtime children of table
    clearRuntimeChildrenList();

    // iterate over list of row models
    int i = 0;
    for (ROW_MODEL rowModel : pRowModelList) {

      // for each row model create a table row mimic and pass row model
      i++;

      MmTableRow<ROW_MODEL> tableRowMm = declaration.callbackMmCreateTableRow(i);
      // tableRowMm.doMmSetModelsideFromModel(rowModel);

      // add table row mimic to list of runtime children
      addChild(tableRowMm, null, typeOfFirstGenericParameter);
    }

    // invoke callbackMm to pass values from model to modelside value is NOT necessary here
    // declaration.callbackMmSetModelsideFromModel(model);

    doPassModelsideToViewsideRecursively(this);
  }

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return  The list of table column mimics.
   */
  public List<MmTableColumn> getMmTableColumns() {
    assureInitialization();

    if (tableColumns.isEmpty()) {
      for (MmMimic child : getMmChildren()) {
        if (child instanceof MmTableColumn) {
          tableColumns.add((MmTableColumn)child);
        }
      }
    }
    return tableColumns;
  }

  /**
   * Returns the list of table row mimics.
   *
   * @return  The list of table row mimics.
   */
  public <T extends MmTableRow<ROW_MODEL>> List<T> getMmTableRows() {
    assureInitialization();

    List<T> returnList = new ArrayList<>();
    for (MmMimic child : getMmChildren()) {
      if (child instanceof MmTableRow<?>) {
        @SuppressWarnings("unchecked")
        T tableRowMm = (T)child;
        returnList.add(tableRowMm);
      }
    }
    return returnList;
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
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmTableAnnotation.class);

    MmTableAnnotation annotation = findAnnotation(declaration, MmTableAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationTable();
    } else {
      configuration = new MmConfigurationTable(annotation);
    }
  }

}
