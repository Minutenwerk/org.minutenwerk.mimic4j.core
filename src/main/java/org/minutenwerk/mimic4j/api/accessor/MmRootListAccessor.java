package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;
import java.util.Optional;

/**
 * Model accessor on root list. Because a root does not have a parent, the parent model is Void.
 *
 * @param   <VALUE_MODEL>  Type of root model in list.
 *
 * @author  Olaf Kossak
 */
public class MmRootListAccessor<VALUE_MODEL> extends MmListAccessor<Void, List<VALUE_MODEL>, VALUE_MODEL> {

  /** The root model is stored in the root accessor. Root Accessors are the only accessor which hold a reference on a model. */
  private List<VALUE_MODEL> rootModel;

  /**
   * Constructor of this mutable class.
   */
  public MmRootListAccessor() {
    super(null, null, null, null);
  }

  /**
   * TODOC.
   *
   * @param  value  TODOC
   */
  @Override
  public void add(final VALUE_MODEL value) {
    rootModel.add(value);
  }

  /**
   * Returns the model value. In case of the parent supplier does not supply a parent, this method returns null.
   *
   * @param   index  TODOC
   *
   * @return  the model value.
   */
  @Override
  public VALUE_MODEL get(final int index) {
    return rootModel.get(index);
  }

  /**
   * Returns parent model of the accessed model. Because a root does not have a parent, the parent model is Void.
   *
   * @return  parent model of the accessed model.
   */
  @Override
  public final Void getParent() {
    return null;
  }

  /**
   * Returns true, if value of the accessed model is present.
   *
   * @return  true, if value of the accessed model is present.
   */
  @Override
  public boolean isPresent() {
    return (rootModel != null);
  }

  /**
   * Returns true, if the accessed model is the root of an aggregate.
   *
   * @return  true, if the accessed model is the root of an aggregate.
   */
  @Override
  public final boolean isRoot() {
    return true;
  }

  /**
   * TODOC.
   *
   * @param  value  TODOC
   */
  @Override
  public void remove(final VALUE_MODEL value) {
    rootModel.remove(value);
  }

  /**
   * Sets the specified model value, may be null, and notifies model change listener.
   *
   * @param  value  The specified model value, may be null.
   */
  @Override
  public void set(final List<VALUE_MODEL> value) {
    rootModel = value;
  }

  /**
   * Sets the specified model value and returns the parent for chaining setters. Because a root does not have a parent, the parent model is
   * Void.
   *
   * @param   value  The specified model value, may be null.
   *
   * @return  The parent.
   */
  @Override
  public final Void with(final List<VALUE_MODEL> value) {
    set(value);
    return null;
  }

  /**
   * Returns {@link Optional} of component.
   *
   * @return  {@link Optional} of component.
   */
  @Override
  protected Optional<List<VALUE_MODEL>> getComponentOptional() {
    return Optional.of(rootModel);
  }

}
