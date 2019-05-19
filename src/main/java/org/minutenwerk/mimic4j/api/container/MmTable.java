package org.minutenwerk.mimic4j.api.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmTableMimic;
import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTable;
import org.minutenwerk.mimic4j.impl.container.MmTableCallback;

/**
 * MmTable is a container mimic to represent a HTML table containing columns and rows.
 *
 * @author  Olaf Kossak
 */
public abstract class MmTable<ROW_MODEL> extends MmBaseContainerDeclaration<List<ROW_MODEL>, MmImplementationTable<ROW_MODEL>>
  implements MmTableCallback<ROW_MODEL>, MmTableMimic<ROW_MODEL> {

  /**
   * Creates a new MmTable instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmTable(MmDeclarationMimic pParent) {
    super(new MmImplementationTable<ROW_MODEL>(pParent));
  }

  /**
   * Creates a new MmTable instance.
   *
   * @param  pParent        The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmTable(MmDeclarationMimic pParent, final MmRootAccessor<List<ROW_MODEL>> pRootAccessor) {
    super(new MmImplementationTable<>(pParent, pRootAccessor));
  }

  /**
   * Semantic validation of model.
   *
   * @param   pModel  The model to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateModel(List<ROW_MODEL> pModel) throws MmValidatorException {
  }

  /**
   * Clears all rows of this table.
   */
  @Override
  public void doMmClearTableRows() {
    implementation.doMmClearTableRows();
  }

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return  The list of table column mimics.
   */
  @Override
  public List<MmTableColumn> getMmTableColumns() {
    return implementation.getMmTableColumns();
  }

  /**
   * Returns the list of table row mimics.
   *
   * @return  The list of table row mimics.
   */
  @Override
  public final <T extends MmTableRow<ROW_MODEL>> List<T> getMmTableRows() {
    return implementation.getMmTableRows();
  }

}
