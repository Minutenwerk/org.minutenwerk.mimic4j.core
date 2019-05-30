package org.minutenwerk.mimic4j.impl.container;

import java.lang.reflect.Field;

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
  extends MmBaseContainerImplementation<MmTableRow<ROW_MODEL>, ROW_MODEL, MmConfigurationTableRow, MmTableRowAnnotation>
  implements MmTableRowMimic<ROW_MODEL> {

  /**
   * Creates a new MmImplementationTableRow instance.
   *
   * @param  pParent    The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pRowIndex  The table row index of this row.
   */
  public MmImplementationTableRow(final MmDeclarationMimic pParent, final int pRowIndex) {
    super(pParent, pRowIndex);
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
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationTableRow onConstructConfiguration(MmTableRowAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationTableRow(pAnnotation);
    } else {
      return new MmConfigurationTableRow();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return null;
  }

  /**
   * Evaluates and returns the name of this mimic. The name is derived from the specified field, if the mimic is declared as a field of
   * another mimic. If there is a runtime index, the name is derived from the index value. Otherwise the name is an empty string.
   *
   * @param         pField         The specified field, or null.
   * @param         pRuntimeIndex  The specified runtime index, or null.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-construction
   */
  @Override
  protected String onConstructName(final Field pField, final Integer pRuntimeIndex) {
    return "row" + pRuntimeIndex;
  }

}
