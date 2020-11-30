package org.minutenwerk.mimic4j.api.container;

import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableColumnMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableMimic;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTableColumn;

/**
 * MmTableColumn is a container mimic to represent a table column.
 *
 * @author  Olaf Kossak
 */
public class MmTableColumn<MODEL> extends MmBaseContainerDeclaration<MmImplementationTableColumn<MODEL>, MODEL> implements MmTableColumnMimic<MODEL> {

  /**
   * Creates a new MmTableColumn instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmTableColumn(MmDeclarationMimic pParent) {
    super(new MmImplementationTableColumn<>(pParent));
  }

  /**
   * Returns the table column index of this column.
   *
   * @return  The table column index of this column.
   */
  @Override
  public final int getMmColumnIndex() {
    return implementation.getMmColumnIndex();
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the footer of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the footer of this column.
   */
  public final String getMmFooterClasses() {
    return implementation.getMmFooterClasses();
  }

  /**
   * Returns table mimic of this column.
   *
   * @return  The table mimic of this column.
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends MmTableMimic<?>> T getMmParentTable() {
    return (T)getMmParentMimic();
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   */
  public final boolean isMmRowHeader() {
    return implementation.isMmRowHeader();
  }
}
