package org.minutenwerk.mimic4j.impl.container;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Optional;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.container.MmTableRowAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmTableRowMimic;

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
   * @param  pParent    The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRowIndex  The table row index of this row.
   */
  public MmImplementationTableRow(final MmDeclarationMimic pParent, final Integer pRowIndex) {
    super(pParent, pRowIndex);
  }

  /**
   * Returns the accessor index of this row.
   *
   * @return  The accessor index of this row.
   */
  @Override
  public int getMmAccessorIndex() {
    assureInitialization();

    return getMmModelAccessor().getIndex();
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
  public MmListEntryAccessor<ROW_MODEL> getMmModelAccessor() {
    assureInitialization();

    return (MmListEntryAccessor<ROW_MODEL>)modelAccessor;
  }

  /**
   * Returns the table row index of this row.
   *
   * @return  The table row index of this row.
   */
  @Override
  public int getMmRowIndex() {
    assureInitialization();

    return Optional.of(getRuntimeIndex()).orElseThrow(() -> new UnsupportedOperationException("no runtime index provided for: " + this));
  }

  /**
   * Returns list of table row children.
   *
   * @return  The list of table row children.
   */
  @Override
  public List<MmMimic> getMmTableCells() {
    return getMmChildren();
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
   * Evaluates and returns the name of this mimic. The name is derived from the specified field, if the mimic is declared as a field of another mimic. If
   * there is a runtime index, the name is derived from the index value. Otherwise the name is an empty string.
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
