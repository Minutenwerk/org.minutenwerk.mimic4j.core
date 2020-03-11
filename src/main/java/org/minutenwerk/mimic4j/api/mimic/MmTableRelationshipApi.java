package org.minutenwerk.mimic4j.api.mimic;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * The MmTableRelationshipApi offers static methods to evaluate relationsships and properties in tables.
 *
 * @author  Olaf Kossak
 */
public final class MmTableRelationshipApi {

  /**
   * Constructor is not for use.
   */
  private MmTableRelationshipApi() {
  }

  /**
   * Returns corresponding table, if specified mimic is inside table cell.
   *
   * @param   pMimic  Specified mimic.
   *
   * @return  Corresponding table.
   */
  public static MmTableMimic<?> getMmParentTable(final MmMimic pMimic) {
    return MmRelationshipApi.getMmAncestorOfType(pMimic, MmTableMimic.class);
  }

  /**
   * Returns corresponding table row, if specified mimic is inside table cell.
   *
   * @param   pMimic  Specified mimic.
   *
   * @return  Corresponding table row.
   */
  public static MmTableRowMimic<?> getMmParentTableRow(final MmMimic pMimic) {
    return MmRelationshipApi.getMmAncestorOfType(pMimic, MmTableRowMimic.class);
  }
}
