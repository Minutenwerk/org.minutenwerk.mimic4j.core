package org.minutenwerk.mimic4j.impl.accessor;

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
   * @param  parentAccessor  TODOC
   * @param  indexSupplier   TODOC
   */
  public MmListEntryAccessor(final MmListAccessor<?, LIST_MODEL, ENTRY_MODEL> parentAccessor, final Supplier<Integer> indexSupplier) {
    super(parentAccessor, null, List<ENTRY_MODEL>::add);
    this.indexSupplier = indexSupplier;
  }

  /**
   * Returns {@link Optional} of entry model.
   *
   * @return  {@link Optional} of entry model.
   */
  @Override
  protected Optional<ENTRY_MODEL> getComponentOptional() {
    LIST_MODEL parentList = this.getParent();
    if (parentList != null) {
      return Optional.ofNullable(parentList.get(this.indexSupplier.get()));
    } else {
      return Optional.empty();
    }
  }

}
