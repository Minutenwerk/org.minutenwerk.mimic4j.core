package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableColumnAnnotation;

/**
 * MmImplementationTableColumn is the specific class for the implementation part of table column mimics.
 *
 * @author  Olaf Kossak
 */
// TableColumn hat noch keinen Accessor
public class MmImplementationTableColumn<MODEL>
  extends MmBaseContainerImplementation<MmTableColumn<MODEL>, MODEL, MmConfigurationTableColumn, MmTableColumnAnnotation> {

  /**
   * Creates a new MmImplementationTableColumn instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationTableColumn(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns a String of space delimited <code>CSS</code> style classes for the footer of this column.
   *
   * @return  A String of space delimited <code>CSS</code> style classes for the footer of this column.
   */
  public String getMmFooterClasses() {
    assureInitialization();

    return configuration.getFooterClasses();
  }

  /**
   * Returns true, if this row is a table row header.
   *
   * @return  True, if this row is a table row header.
   */
  public boolean isMmRowHeader() {
    assureInitialization();

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
