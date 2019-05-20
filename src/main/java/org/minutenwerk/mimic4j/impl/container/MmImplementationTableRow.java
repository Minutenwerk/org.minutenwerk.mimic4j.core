package org.minutenwerk.mimic4j.impl.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmTableRowMimic;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.container.MmTableRowAnnotation;
import org.minutenwerk.mimic4j.impl.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

/**
 * MmImplementationTableRow is the specific class for the implementation part of table row mimics.
 *
 * @param   <ROW_MODEL>  The row model type.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationTableRow<ROW_MODEL>
  extends MmBaseContainerImplementation<MmTableRow<ROW_MODEL>, ROW_MODEL, MmConfigurationTableRow> implements MmTableRowMimic<ROW_MODEL> {

  /**
   * Creates a new MmImplementationTableRow instance.
   *
   * @param  pParent    The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pRowIndex  The table row index of this row.
   */
  public MmImplementationTableRow(MmDeclarationMimic pParent, int pRowIndex) {
    super(pParent);
    setName("row" + pRowIndex);
  }

  /**
   * Returns accessor of model.
   *
   * @return        The accessor of model.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  @SuppressWarnings("unchecked")
  public MmListEntryAccessor<? extends List<ROW_MODEL>, ROW_MODEL> getMmModelAccessor() {
    assureInitialization();

    return (MmListEntryAccessor<? extends List<ROW_MODEL>, ROW_MODEL>)modelAccessor;
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  @Override
  public int getMmRowIndex() {
    assureInitialization();

    return getMmModelAccessor().getIndex();
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return null;
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmTableRowAnnotation.class);

    MmTableRowAnnotation annotation = findAnnotation(declaration, MmTableRowAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationTableRow();
    } else {
      configuration = new MmConfigurationTableRow(annotation);
    }
  }

}
