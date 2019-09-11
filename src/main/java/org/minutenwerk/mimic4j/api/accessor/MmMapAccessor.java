package org.minutenwerk.mimic4j.api.accessor;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Immutable base class for accessor on maps of component models.
 *
 * @param   <PARENT_MODEL>  Type of parent model.
 * @param   <MAP_MODEL>     Type of map model.
 * @param   <KEY>           Type of key in map.
 * @param   <VALUE_MODEL>   Type of value model in map.
 *
 * @author  Olaf Kossak
 */
public class MmMapAccessor<PARENT_MODEL, MAP_MODEL extends Map<KEY, VALUE_MODEL>, KEY, VALUE_MODEL>
  extends MmAttributeAccessor<PARENT_MODEL, MAP_MODEL> {

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   */
  public MmMapAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, MAP_MODEL> componentGetter,
    final BiConsumer<PARENT_MODEL, MAP_MODEL> componentSetter) {
    super(parentAccessor, componentGetter, componentSetter);
  }

  /**
   * TODOC.
   *
   * @param   key  TODOC
   *
   * @return  TODOC
   */
  public boolean containsKey(final KEY key) {
    return get().containsKey(key);
  }

  /**
   * TODOC.
   *
   * @param   value  TODOC
   *
   * @return  TODOC
   */
  public boolean containsValue(final VALUE_MODEL value) {
    return get().containsValue(value);
  }

  /**
   * TODOC.
   *
   * @param   key  TODOC
   *
   * @return  TODOC
   */
  public VALUE_MODEL get(final KEY key) {
    return get().get(key);
  }

  /**
   * Returns true, if the accessed model is a collection.
   *
   * @return  true, if the accessed model is a collection.
   */
  @Override
  public boolean isCollection() {
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
   * @param  key    TODOC
   * @param  value  TODOC
   */
  public void put(final KEY key, final VALUE_MODEL value) {
    get().put(key, value);
  }

  /**
   * TODOC.
   *
   * @param  key  TODOC
   */
  public void remove(final VALUE_MODEL key) {
    get().remove(key);
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
