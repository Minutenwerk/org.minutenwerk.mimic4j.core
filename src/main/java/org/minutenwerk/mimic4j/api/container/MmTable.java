package org.minutenwerk.mimic4j.api.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTable;
import org.minutenwerk.mimic4j.impl.container.MmTableCallback;

/**
 * MmTable is a container mimic to represent a HTML table containing columns and rows.
 *
 * @author  Olaf Kossak
 */
public abstract class MmTable<ROW_MODEL> extends MmBaseContainerDeclaration<List<ROW_MODEL>, MmImplementationTable<ROW_MODEL>>
  implements MmTableCallback<ROW_MODEL> {

  /**
   * Creates a new MmTable instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmTable(MmDeclarationMimic pParent) {
    super(new MmImplementationTable<ROW_MODEL>(pParent));
  }

  /**
   * Semantic validation of model.
   *
   * @param   pModel  The model to be validated.
   *
   * @throws  MmValidatorException  In case of validation fails.
   */
  @Override
  public void callbackMmValidateModel(List<ROW_MODEL> pModel) throws MmValidatorException {
  }

  /**
   * Clears all rows of this table.
   */
  public final void doMmClearTableRows() {
    implementation.doMmClearTableRows();
  }

  /**
   * Returns the list of table row mimics.
   *
   * @return  The list of table row mimics.
   */
  @Override
  public final <T extends MmTableRow<ROW_MODEL>> List<T> getMmTableRows() {
    return implementation.getMmTableRows();
  }

}
