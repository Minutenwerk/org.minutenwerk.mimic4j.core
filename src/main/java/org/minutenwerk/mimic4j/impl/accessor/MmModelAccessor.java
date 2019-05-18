package org.minutenwerk.mimic4j.impl.accessor;

/**
 * TODOC.
 *
 * @param   <PARENT_MODEL>  Type of parent model.
 * @param   <MODEL>         Type of model.
 *
 * @author  Olaf Kossak
 */
public interface MmModelAccessor<PARENT_MODEL, MODEL> {

  /**
   * Returns the model value. In case of the parent supplier does not supply a parent, this method returns null.
   *
   * @return  the model value.
   */
  public MODEL get();

  /**
   * Returns parent model of the accessed model.
   *
   * @return  parent model of the accessed model.
   */
  public PARENT_MODEL getParent();

  /**
   * Returns true, if the accessed model is an attribute.
   *
   * @return  true, if the accessed model is an attribute.
   */
  public boolean isAttribute();

  /**
   * Returns true, if the accessed model is a collection.
   *
   * @return  true, if the accessed model is a collection.
   */
  public boolean isCollection();

  /**
   * Returns true, if the accessed model is a component.
   *
   * @return  true, if the accessed model is a component.
   */
  public boolean isComponent();

  /**
   * Returns true, if value of the accessed model is not present.
   *
   * @return  true, if value of the accessed model is not present.
   */
  public boolean isNotPresent();

  /**
   * Returns true, if value of the accessed model is present.
   *
   * @return  true, if value of the accessed model is present.
   */
  public boolean isPresent();

  /**
   * Returns true, if the accessed model is the root of an aggregate.
   *
   * @return  true, if the accessed model is the root of an aggregate.
   */
  public boolean isRoot();

  /**
   * Sets the specified model value.
   *
   * @param  value  The specified model value.
   */
  public void set(MODEL value);

  /**
   * Sets the specified model value and returns the parent for chaining setters.
   *
   * @param   value  The specified model value.
   *
   * @return  The parent.
   */
  public PARENT_MODEL with(MODEL value);
}
