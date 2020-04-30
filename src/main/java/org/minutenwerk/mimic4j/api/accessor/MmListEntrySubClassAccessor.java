package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Immutable base class for accessor on entries of list models.
 *
 * @param   <ENTRY_MODEL>  Type of entry model in list.
 * @param   <SUB_CLASS>    Type of entry model in entry accessor.
 *
 * @author  Olaf Kossak
 */
public class MmListEntrySubClassAccessor<ENTRY_MODEL, SUB_CLASS extends ENTRY_MODEL> extends MmComponentAccessor<List<ENTRY_MODEL>, SUB_CLASS> {

  /** Supplier function of index of entry in parent list. */
  protected final Supplier<Integer> indexSupplier;

  /**
   * Constructor of this immutable class.
   *
   * @param   pParentAccessor  Parent list accessor.
   * @param   pIndexSupplier   Index supplier of list entry.
   *
   * @throws  IllegalArgumentException  In case of arguments are null.
   */
  public MmListEntrySubClassAccessor(final MmListAccessor<?, ENTRY_MODEL> pParentAccessor, final Supplier<Integer> pIndexSupplier) {
    super(pParentAccessor, null, List<ENTRY_MODEL>::add);
    if (pParentAccessor == null) {
      throw new IllegalArgumentException("list entry accessor must have parent accessor of type list accessor");
    }
    if (pIndexSupplier == null) {
      throw new IllegalArgumentException("list entry accessor must have index supplier");
    }
    indexSupplier = pIndexSupplier;
  }

  /**
   * Returns index of entry in parent list.
   *
   * @return  The index of entry in parent list.
   */
  public Integer getIndex() {
    return indexSupplier.get();
  }

  /**
   * Returns supplier function of index of entry in parent list.
   *
   * @return  supplier function of index of entry in parent list.
   */
  public Supplier<Integer> getIndexSupplier() {
    return indexSupplier;
  }

  /**
   * Returns {@link Optional} of entry model.
   *
   * @return  {@link Optional} of entry model.
   */
  @Override
  @SuppressWarnings("unchecked")
  protected Optional<SUB_CLASS> getComponentOptional() {
    try {
      List<ENTRY_MODEL> parentList = getParentModel();
      if (parentList != null) {
        final Integer index = indexSupplier.get();
        if (index != null) {
          final ENTRY_MODEL entryModel = parentList.get(index);
          if (entryModel != null) {
            return Optional.of((SUB_CLASS)entryModel);
          }
        }
      }
    } catch (Exception e) {
      return Optional.empty();
    }
    return Optional.empty();
  }
}
