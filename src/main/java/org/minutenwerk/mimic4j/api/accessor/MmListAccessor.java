package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Immutable base class for accessor on lists of component models.
 *
 * @param   <PARENT_MODEL>  Type of parent model.
 * @param   <VALUE_MODEL>   Type of value model in list.
 *
 * @author  Olaf Kossak
 */
public class MmListAccessor<PARENT_MODEL, VALUE_MODEL> extends MmCollectionAccessor<PARENT_MODEL, List<VALUE_MODEL>, VALUE_MODEL> {

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   * @param  valueAdder       TODOC
   */
  public MmListAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, List<VALUE_MODEL>> componentGetter,
    final BiConsumer<PARENT_MODEL, List<VALUE_MODEL>> componentSetter, final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueAdder) {
    super(parentAccessor, componentGetter, componentSetter, valueAdder);
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
  public MmListAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, List<VALUE_MODEL>> componentGetter,
    final BiConsumer<PARENT_MODEL, List<VALUE_MODEL>> componentSetter, final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueAdder,
    final BiConsumer<PARENT_MODEL, VALUE_MODEL> valueRemover) {
    super(parentAccessor, componentGetter, componentSetter, valueAdder, valueRemover);
  }

  /**
   * TODOC.
   *
   * @param   index  TODOC
   *
   * @return  TODOC
   */
  public VALUE_MODEL get(final int index) {
    return get().get(index);
  }
}
