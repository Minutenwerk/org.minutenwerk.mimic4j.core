package org.minutenwerk.mimic4j.api.accessor;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Immutable base class for accessor on collections of component models.
 *
 * @param   <PARENT_MODEL>      Type of parent model.
 * @param   <COLLECTION_MODEL>  Type of collection model.
 * @param   <VALUE_MODEL>       Type of value model in collection.
 *
 * @author  Olaf Kossak
 */
public class MmCollectionAccessor<PARENT_MODEL, COLLECTION_MODEL extends Collection<VALUE_MODEL>, VALUE_MODEL>
  extends MmComponentAccessor<PARENT_MODEL, COLLECTION_MODEL> {

  /** Function which defines the value model add or put method. */
  private final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueAdder;

  /** Optional function which defines the value model remove method. */
  private final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueRemover;

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   * @param  valueAdder       TODOC
   */
  public MmCollectionAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, COLLECTION_MODEL> componentGetter,
    final BiConsumer<PARENT_MODEL, COLLECTION_MODEL> componentSetter, final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueAdder) {
    this(parentAccessor, componentGetter, componentSetter, valueAdder, null);
  }

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   * @param  valueAdder       TODOC
   * @param  valueRemover     TODOC
   */
  public MmCollectionAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, COLLECTION_MODEL> componentGetter,
    final BiConsumer<PARENT_MODEL, COLLECTION_MODEL> componentSetter, final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueAdder,
    final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueRemover) {
    super(parentAccessor, componentGetter, componentSetter);
    this.valueAdder   = valueAdder;
    this.valueRemover = valueRemover;
  }

  /**
   * TODOC.
   *
   * @param  value  TODOC
   */
  public void add(final VALUE_MODEL value) {
    valueAdder.accept(getParentModel(), value);
  }

  /**
   * TODOC.
   *
   * @param   value  TODOC
   *
   * @return  TODOC
   */
  public boolean contains(final VALUE_MODEL value) {
    return get().contains(value);
  }

  /**
   * Returns true, if the accessed model is a collection.
   *
   * @return  true, if the accessed model is a collection.
   */
  @Override
  public final boolean isCollection() {
    return true;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public boolean isEmpty() {
    return get().isEmpty();
  }

  /**
   * TODOC.
   *
   * @param  value  TODOC
   */
  public void remove(final VALUE_MODEL value) {
    if (valueRemover != null) {
      valueRemover.accept(getParentModel(), value);
    } else {
      get().remove(value);
    }
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public int size() {
    return get().size();
  }

}
