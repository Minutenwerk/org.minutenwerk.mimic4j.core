package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.container.MmTableRowAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

/**
 * MmImplementationTableRow is the specific class for the implementation part of table row mimics.
 *
 * @param   <ROW_MODEL>  The row model type.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmImplementationTableRow<ROW_MODEL>
  extends MmBaseContainerImplementation<MmTableRow<ROW_MODEL>, ROW_MODEL, MmConfigurationTableRow> {

  /** The table row index of this row. */
  protected final int rowIndex;

  /**
   * Creates a new MmImplementationTableRow instance.
   *
   * @param  pParent    The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pRowIndex  The table row index of this row.
   */
  public MmImplementationTableRow(MmDeclarationMimic pParent, int pRowIndex) {
    super(pParent);
    this.rowIndex = pRowIndex;
    this.setName("row" + this.rowIndex);
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   *
   * @since   $maven.project.version$
   */
  public int getMmRowIndex() {
    return this.rowIndex;
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   *
   * @since   $maven.project.version$
   */
  @Override protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return null;
  }

  /**
   * Initialize this mimic after constructor phase.
   *
   * @since  $maven.project.version$
   */
  @Override protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmTableRowAnnotation.class);

    MmTableRowAnnotation annotation = this.findAnnotation(this.declaration, MmTableRowAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationTableRow();
    } else {
      this.configuration = new MmConfigurationTableRow(annotation);
    }
  }

}
