package org.minutenwerk.mimic4j.api.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
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
public abstract class MmTableRow<ROW_MODEL> extends MmBaseContainerDeclaration<ROW_MODEL, MmImplementationTableRow<ROW_MODEL>> {

  /**
   * Creates a new MmTableRow instance.
   *
   * @param  pParent    The parent mimic of type {@link MmBaseDeclaration}.
   * @param  pRowIndex  The index of this row
   */
  public MmTableRow(MmDeclarationMimic pParent, int pRowIndex) {
    super(new MmImplementationTableRow<ROW_MODEL>(pParent, pRowIndex));
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  public final int getMmRowIndex() {
    return implementation.getMmRowIndex();
  }

}
