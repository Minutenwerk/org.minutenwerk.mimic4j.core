package org.minutenwerk.mimic4j.api.accessor;

import org.minutenwerk.mimic4j.impl.accessor.MmBaseModelAccessor;

/**
 * Model accessor on root of an aggregate. Because a root does not have a parent, the parent model is Void.
 *
 * @param   <MODEL>  Type of root model.
 *
 * @author  Olaf Kossak
 */
public class MmRootAccessor<MODEL> extends MmBaseModelAccessor<Void, MODEL> {

  /** The root model is stored in the root accessor. Root Accessors are the only accessor which hold a reference on a model. */
  private MODEL rootModel;

  /**
   * Constructor of this mutable class.
   */
  public MmRootAccessor() {
    super(null);
  }

  /**
   * Returns the model value. In case of the parent supplier does not supply a parent, this method returns null.
   *
   * @return  the model value.
   */
  @Override
  public MODEL get() {
    return rootModel;
  }

  /**
   * Returns parent model of the accessed model. Because a root does not have a parent, the parent model is Void.
   *
   * @return  parent model of the accessed model.
   */
  @Override
  public final Void getParentModel() {
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
   * Resets model and any other state information to null.
   */
  @Override
  public void reset() {
    super.reset();
    rootModel = null;
  }

  /**
   * Sets the specified model value, may be null, and notifies model change listener.
   *
   * @param  value  The specified model value, may be null.
   */
  @Override
  public void set(final MODEL value) {
    rootModel = value;
  }

}
