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
 * @see     $HeadURL: $$maven.project.version$
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
   * For read/write tables: Set values from modelside of table row into specified model. For read only tables this method doesn't need to be
   * redefined.
   *
   * @param  pModel  The specified model.
   *
   * @since  $maven.project.version$
   */
  @Override public void callbackMmSetModelFromModelside(ROW_MODEL pModel) {
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   *
   * @since   $maven.project.version$
   */
  public final int getMmRowIndex() {
    return this.implementation.getMmRowIndex();
  }

}
