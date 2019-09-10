package org.minutenwerk.mimic4j.api.accessor;

/**
 * Model accessor on root of an aggregate. Because a root does not have a parent, the parent model is Void.
 *
 * @param   <COMPONENT_MODEL>  Type of root model.
 *
 * @author  Olaf Kossak
 */
public class MmRootAccessor<COMPONENT_MODEL> extends MmComponentAccessor<Void, COMPONENT_MODEL> {

  /** The root model is stored in the root accessor. Root Accessors are the only accessor which hold a reference on a model. */
  private COMPONENT_MODEL       rootModel;

  /** Listener to model changes. */
  private MmModelChangeListener modelChangeListener;

  /**
   * Constructor of this mutable class.
   */
  public MmRootAccessor() {
    super(null, null, null);
  }

  /**
   * Returns the model value. In case of the parent supplier does not supply a parent, this method returns null.
   *
   * @return  the model value.
   */
  @Override
  public COMPONENT_MODEL get() {
    return rootModel;
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
   * Sets the specified model value, may be null, and notifies model change listener.
   *
   * @param  value  The specified model value, may be null.
   */
  @Override
  public void set(final COMPONENT_MODEL value) {
    rootModel = value;
    notifyListener();
    modelChangeListener.onModelChange();
  }

  /**
   * Sets specified listener to model changes, cannot be set twice.
   *
   * @param   pModelChangeListener  The specified listener to model changes.
   *
   * @throws  IllegalStateException  In case of model change listener is set already.
   */
  public void setMmModelChangeListener(final MmModelChangeListener pModelChangeListener) {
    if (modelChangeListener != null) {
      throw new IllegalStateException("modelChangeListener cannot be set twice");
    }
    modelChangeListener = pModelChangeListener;
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
  public final Void with(final COMPONENT_MODEL value) {
    set(value);
    return null;
  }

  /**
   * TODOC.
   */
  private void notifyListener() {
// if (modelChangeListener == null) {
// throw new IllegalStateException("modelChangeListener must be set");
// }
    if (modelChangeListener != null) {
      modelChangeListener.onModelChange();
    }
  }

}
