package org.minutenwerk.mimic4j.api.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmCollectionAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableMimic;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTable;
import org.minutenwerk.mimic4j.impl.container.MmTableCallback;

/**
 * MmTable is a container mimic to represent a HTML table containing columns and rows.
 *
 * @author  Olaf Kossak
 */
public abstract class MmTable<ROW_MODEL> extends MmBaseContainerDeclaration<MmImplementationTable<ROW_MODEL>, List<ROW_MODEL>>
  implements MmTableCallback<ROW_MODEL>, MmTableMimic<ROW_MODEL> {

  /**
   * Creates a new MmTable instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmTable(MmDeclarationMimic pParent) {
    super(new MmImplementationTable<ROW_MODEL>(pParent));
  }

  /**
   * Creates a new MmTable instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model has a root
   *                        accessor.
   */
  public MmTable(MmDeclarationMimic pParent, final MmRootAccessor<List<ROW_MODEL>> pRootAccessor) {
    super(new MmImplementationTable<>(pParent, pRootAccessor));
  }

  /**
   * Returns the container's accessor to corresponding model, derived from specified parent accessor.
   *
   * @param   pParentAccessor  The specified parent component accessor.
   *
   * @return  The container's accessor.
   *
   * @throws  ClassCastException  IllegalStateException In case of model accessor is not defined.
   */
  @Override
  public MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      @SuppressWarnings("unchecked")
      MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL> modelAccessor = (MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL>)pParentAccessor;
      return modelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to collectionAccessor. You must redefine callbackMmGetModelAccessor() for " + this);
    }
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
   * Returns the table column index of specified column.
   *
   * @param   pTableColumn  TODOC
   *
   * @return  The table column index of specified column.
   */
  @Override
  public final int getMmColumnIndex(MmTableColumn<?> pTableColumn) {
    return implementation.getMmColumnIndex(pTableColumn);
  }

  /**
   * Returns the table column of specified column index.
   *
   * @param   pTableColumnIndex  Specified column index.
   *
   * @return  The table column of specified column index.
   */
  @Override
  public final MmTableColumn<?> getMmColumnOfIndex(int pTableColumnIndex) {
    return implementation.getMmColumnOfIndex(pTableColumnIndex);
  }

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public final MmCollectionAccessor<?, List<ROW_MODEL>, ROW_MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns the list of table column mimics of this table mimic.
   *
   * @return  The list of table column mimics.
   */
  @Override
  public List<MmTableColumn<?>> getMmTableColumns() {
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

  /**
   * Returns true, if table contains rows.
   *
   * @return  true, if table contains rows.
   */
  @Override
  public boolean isContainingRows() {
    return implementation.isContainingRows();
  }

}
