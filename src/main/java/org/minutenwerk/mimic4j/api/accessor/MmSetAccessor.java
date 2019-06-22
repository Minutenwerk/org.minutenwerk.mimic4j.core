package org.minutenwerk.mimic4j.api.accessor;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Immutable base class for accessor on sets of component models.
 *
 * @param   <PARENT_MODEL>  Type of parent model.
 * @param   <SET_MODEL>     Type of set.
 * @param   <VALUE_MODEL>   Type of value model in set.
 *
 * @author  Olaf Kossak
 */
public class MmSetAccessor<PARENT_MODEL, SET_MODEL extends Set<VALUE_MODEL>, VALUE_MODEL>
  extends MmCollectionAccessor<PARENT_MODEL, SET_MODEL, VALUE_MODEL> {

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   * @param  valueAdder       TODOC
   */
  public MmSetAccessor(final MmComponentAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, SET_MODEL> componentGetter,
    final BiConsumer<PARENT_MODEL, SET_MODEL> componentSetter, final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueAdder) {
    super(parentAccessor, componentGetter, componentSetter, valueAdder);
  }
}
