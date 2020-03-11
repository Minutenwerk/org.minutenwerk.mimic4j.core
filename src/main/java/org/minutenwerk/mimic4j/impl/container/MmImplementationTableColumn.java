package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableColumnMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableMimic;

/**
 * MmImplementationTableColumn is the specific class for the implementation part of table column mimics.
 *
 * @author  Olaf Kossak
 */
// TODO TableColumn needs accessor
public class MmImplementationTableColumn<MODEL>
  extends MmBaseContainerImplementation<MmTableColumn<MODEL>, MODEL, MmConfigurationTableColumn, MmTableColumnAnnotation>
  implements MmTableColumnMimic<MODEL> {

  /** Index of column in parent table, starting by 0. */
  private Integer columnIndex;

  /**
   * Creates a new MmImplementationTableColumn instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationTableColumn(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the table column index of this column.
   *
   * @return  The table column index of this column.
   */
  @Override
  public final int getMmColumnIndex() {
    ensureInitialization();

    if (columnIndex == null) {
      columnIndex = getMmParentTable().getMmColumnIndex(declaration);
    }
    return columnIndex;
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the footer of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the footer of this column.
   */
  public String getMmFooterClasses() {
    ensureInitialization();

    return configuration.getFooterClasses();
  }

  /**
   * Returns table mimic of this column.
   *
   * @return  The table mimic of this column.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T extends MmTableMimic<?>> T getMmParentTable() {
    ensureInitialization();

    return (T)implementationParent;
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   */
  public boolean isMmRowHeader() {
    ensureInitialization();

    return configuration.isRowHeader();
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationTableColumn onConstructConfiguration(MmTableColumnAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationTableColumn(pAnnotation);
    } else {
      return new MmConfigurationTableColumn();
    }
  }
}
