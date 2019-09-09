package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;

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
   * @return  The model value, may be null.
   */
  public MODEL get();

  /**
   * Returns list of accessors, root accessor first.
   *
   * @return  List of accessors, root accessor first.
   */
  public List<MmModelAccessor<?, ?>> getModelAccessorPath();

  /**
   * Returns list of models, root model first.
   *
   * @return  List of models, root model first.
   */
  public <AN_INTERFACE_ALL_MODELS_IMPLEMENT> List<AN_INTERFACE_ALL_MODELS_IMPLEMENT> getModelPath();

  /**
   * Returns parent model of the accessed model.
   *
   * @return  Parent model of the accessed model.
   */
  public PARENT_MODEL getParent();

  /**
   * Returns accessor of parent model.
   *
   * @return  accessor of parent model.
   */
  public MmComponentAccessor<?, PARENT_MODEL> getParentAccessor();

  /**
   * Returns accessor on root model.
   *
   * @return  Accessor on root model.
   */
  public MmRootAccessor<?> getRootAccessor();

  /**
   * Returns root model.
   *
   * @return  Root model.
   */
  public <R> R getRootModel();

  /**
   * Returns true, if the accessed model is an attribute.
   *
   * @return  True, if the accessed model is an attribute.
   */
  public boolean isAttribute();

  /**
   * Returns true, if the accessed model is a collection.
   *
   * @return  True, if the accessed model is a collection.
   */
  public boolean isCollection();

  /**
   * Returns true, if the accessed model is a component.
   *
   * @return  True, if the accessed model is a component.
   */
  public boolean isComponent();

  /**
   * Returns true, if value of the accessed model is not present.
   *
   * @return  True, if value of the accessed model is not present.
   */
  public boolean isNotPresent();

  /**
   * Returns true, if value of the accessed model is present.
   *
   * @return  True, if value of the accessed model is present.
   */
  public boolean isPresent();

  /**
   * Returns true, if the accessed model is the root of an aggregate.
   *
   * @return  true, if the accessed model is the root of an aggregate.
   */
  public boolean isRoot();

  /**
   * Sets the specified model value, may be null.
   *
   * @param  value  The specified model value, may be null.
   */
  public void set(MODEL value);

  /**
   * Sets the specified model value and returns the parent for chaining setters.
   *
   * @param   value  The specified model value, may be null.
   *
   * @return  The parent.
   */
  public PARENT_MODEL with(MODEL value);
}
