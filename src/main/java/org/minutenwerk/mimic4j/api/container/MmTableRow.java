package org.minutenwerk.mimic4j.api.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.mimic.MmTableMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableRowMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTableRow;

/**
 * MmTableRow is a container mimic to represent a row of a table, containing cells of attribute mimics.
 *
 * @param   <ROW_MODEL>  The row model type.
 *
 * @author  Olaf Kossak
 */
public abstract class MmTableRow<ROW_MODEL> extends MmBaseContainerDeclaration<MmImplementationTableRow<ROW_MODEL>, ROW_MODEL>
  implements MmTableRowMimic<ROW_MODEL> {

  /**
   * Creates a new MmTableRow instance.
   *
   * @param  pParent    The parent mimic of type {@link MmBaseDeclaration}.
   * @param  pRowIndex  The index of this row
   */
  public MmTableRow(MmDeclarationMimic pParent, Integer pRowIndex) {
    super(new MmImplementationTableRow<ROW_MODEL>(pParent, pRowIndex));
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
  public MmListEntryAccessor<ROW_MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      @SuppressWarnings("unchecked")
      MmListEntryAccessor<ROW_MODEL> modelAccessor = (MmListEntryAccessor<ROW_MODEL>)pParentAccessor;
      return modelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to listEntryAccessor. You must redefine callbackMmGetModelAccessor() for " + this);
    }
  }

  /**
   * Returns the accessor index of this row.
   *
   * @return  The accessor index of this row.
   */
  @Override
  public final int getMmAccessorIndex() {
    return implementation.getMmAccessorIndex();
  }

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public final MmListEntryAccessor<ROW_MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns table mimic of this row.
   *
   * @return  The table mimic of this row.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends MmTableMimic<ROW_MODEL>> T getMmParentTable() {
    return (T)MmRelationshipApi.getMmParent(this);
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  @Override
  public int getMmRowIndex() {
    return implementation.getMmRowIndex();
  }

  /**
   * Returns list of table row children.
   *
   * @return  The list of table row children.
   */
  @Override
  public final List<MmMimic> getMmTableCells() {
    return implementation.getMmTableCells();
  }

}
