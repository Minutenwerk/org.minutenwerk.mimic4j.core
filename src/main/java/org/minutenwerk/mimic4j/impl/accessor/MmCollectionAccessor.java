package org.minutenwerk.mimic4j.impl.accessor;

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
  extends MmAttributeAccessor<PARENT_MODEL, COLLECTION_MODEL> {

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   */
  public MmCollectionAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor,
    final Function<PARENT_MODEL, COLLECTION_MODEL> componentGetter, final BiConsumer<PARENT_MODEL, COLLECTION_MODEL> componentSetter) {
    super(parentAccessor, componentGetter, componentSetter);
  }

  /**
   * TODOC.
   *
   * @param  value  TODOC
   */
  public void add(final VALUE_MODEL value) {
    this.get().add(value);
  }

  /**
   * TODOC.
   *
   * @param   value  TODOC
   *
   * @return  TODOC
   */
  public boolean contains(final VALUE_MODEL value) {
    return this.get().contains(value);
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
    return this.get().isEmpty();
  }

  /**
   * TODOC.
   *
   * @param  value  TODOC
   */
  public void remove(final VALUE_MODEL value) {
    this.get().remove(value);
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public int size() {
    return this.get().size();
  }

}
