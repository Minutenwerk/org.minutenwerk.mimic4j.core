package org.minutenwerk.mimic4j.api.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmTableRowMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTableRow;

/**
 * MmTableRow is a container mimic to represent a row of a table, containing cells of attribute mimics.
 *
 * @param   <ROW_MODEL>  The row model type.
 *
 * @author  Olaf Kossak
 */
public abstract class MmTableRow<ROW_MODEL> extends MmBaseContainerDeclaration<ROW_MODEL, MmImplementationTableRow<ROW_MODEL>>
  implements MmTableRowMimic<ROW_MODEL> {

  /** The table row index of this row. */
  protected final int rowIndex;

  /**
   * Creates a new MmTableRow instance.
   *
   * @param  pParent    The parent mimic of type {@link MmBaseDeclaration}.
   * @param  pRowIndex  The index of this row
   */
  public MmTableRow(MmDeclarationMimic pParent, int pRowIndex) {
    super(new MmImplementationTableRow<ROW_MODEL>(pParent, pRowIndex));
    rowIndex = pRowIndex;
  }

  /**
   * Returns the container's accessor to corresponding model. The container accessor can be derived from specified root component accessor.
   *
   * @param   pRootAccessor  The specified root component accessor.
   *
   * @return  The container's accessor.
   *
   * @throws  IllegalStateException  In case of model accessor is not defined.
   */
  @Override
  public MmListEntryAccessor<? extends List<ROW_MODEL>, ROW_MODEL> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
    throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + getMmFullName());
  }

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  @Override
  public MmListEntryAccessor<? extends List<ROW_MODEL>, ROW_MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  @Override
  public final int getMmRowIndex() {
    return implementation.getMmRowIndex();
  }

}
