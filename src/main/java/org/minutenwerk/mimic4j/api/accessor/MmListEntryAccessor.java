package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Immutable base class for accessor on entries of list models.
 *
 * @param   <LIST_MODEL>   Type of parent list model.
 * @param   <ENTRY_MODEL>  Type of entry model in list.
 *
 * @author  Olaf Kossak
 */
public class MmListEntryAccessor<LIST_MODEL extends List<ENTRY_MODEL>, ENTRY_MODEL> extends MmComponentAccessor<LIST_MODEL, ENTRY_MODEL> {

  /** Supplier function of index of entry in parent list. */
  private final Supplier<Integer> indexSupplier;

  /**
   * Constructor of this immutable class.
   *
   * @param  pParentAccessor  TODOC
   * @param  pIndexSupplier   TODOC
   */
  public MmListEntryAccessor(final MmListAccessor<?, LIST_MODEL, ENTRY_MODEL> pParentAccessor, final Supplier<Integer> pIndexSupplier) {
    super(pParentAccessor, null, List<ENTRY_MODEL>::add);
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
   * Returns {@link Optional} of entry model.
   *
   * @return  {@link Optional} of entry model.
   */
  @Override
  protected Optional<ENTRY_MODEL> getComponentOptional() {
    LIST_MODEL parentList = getParentModel();
    if (parentList != null) {
      return Optional.ofNullable(parentList.get(indexSupplier.get()));
    } else {
      return Optional.empty();
    }
  }
}
