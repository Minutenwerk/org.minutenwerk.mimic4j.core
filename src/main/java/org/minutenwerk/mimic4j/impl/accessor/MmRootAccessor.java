package org.minutenwerk.mimic4j.impl.accessor;

/**
 * Model accessor on root of an aggregate. Because a root does not have a parent, the parent model is Void.
 *
 * @param   <COMPONENT_MODEL>  Type of root model.
 *
 * @author  Olaf Kossak
 */
public class MmRootAccessor<COMPONENT_MODEL> extends MmBaseModelAccessor<Void, COMPONENT_MODEL> {

  /** The root model is stored in the root accessor. This is the only accessor which stores a model. */
  private COMPONENT_MODEL rootModel;

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
  public COMPONENT_MODEL get() {
    return rootModel;
  }

  /**
   * Returns parent model of the accessed model. Because a root does not have a parent, the parent model is Void.
   *
   * @return  parent model of the accessed model.
   */
  @Override
  public Void getParent() {
    return null;
  }

  /**
   * Returns true, if the accessed model is a component.
   *
   * @return  true, if the accessed model is a component.
   */
  @Override
  public boolean isComponent() {
    return true;
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
  public boolean isRoot() {
    return true;
  }

  /**
   * Sets the specified model value.
   *
   * @param  value  The specified model value.
   */
  @Override
  public void set(final COMPONENT_MODEL value) {
    rootModel = value;
  }

  /**
   * Sets the specified model value and returns the parent for chaining setters. Because a root does not have a parent, the parent model is
   * Void.
   *
   * @param   value  The specified model value.
   *
   * @return  The parent.
   */
  @Override
  public Void with(final COMPONENT_MODEL value) {
    rootModel = value;
    return null;
  }
}
