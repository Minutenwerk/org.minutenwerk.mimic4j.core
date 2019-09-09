package org.minutenwerk.mimic4j.api.accessor;

import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;

/**
 * Model accessor on root of an aggregate. Because a root does not have a parent, the parent model is Void.
 *
 * @param   <COMPONENT_MODEL>  Type of root model.
 *
 * @author  Olaf Kossak
 */
public class MmRootAccessor<COMPONENT_MODEL> extends MmComponentAccessor<Void, COMPONENT_MODEL> {

  /** The root model is stored in the root accessor. This is the only accessor which stores a model. */
  private COMPONENT_MODEL                                         rootModel;

  /** TODOC. */
  private MmBaseContainerImplementation<?, COMPONENT_MODEL, ?, ?> mmContainer;

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
   * Sets the specified model value, may be null.
   *
   * @param   value  The specified model value, may be null.
   *
   * @throws  IllegalStateException  TODOC
   */
  @Override
  public void set(final COMPONENT_MODEL value) {
    rootModel = value;
    if (this.mmContainer == null) {
      throw new IllegalStateException("mmContainer must be set");
    }
    mmContainer.onModelChange();
  }

  /**
   * TODOC.
   *
   * @param   pMmContainer  TODOC
   *
   * @throws  IllegalStateException  TODOC
   */
  @SuppressWarnings("unchecked")
  public void setMmContainer(final MmBaseContainerImplementation<?, ?, ?, ?> pMmContainer) {
    if (this.mmContainer != null) {
      throw new IllegalStateException("mmContainer cannot be set twice");
    }
    this.mmContainer = (MmBaseContainerImplementation<?, COMPONENT_MODEL, ?, ?>)pMmContainer;
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
}
