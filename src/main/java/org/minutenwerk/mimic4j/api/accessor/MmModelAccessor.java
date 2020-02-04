package org.minutenwerk.mimic4j.api.accessor;

import java.util.List;

/**
 * An accessor gives access on a model, the model can be changed or may be null, the accessor remains the same.
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
   * Resets model and any other state information to null.
   */
  public void reset();
  /**
   * Returns a list of path or query parameter values of the URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return  A list of path or query parameter values of the URL. Usually this is a list of ids starting by id of root dto.
   */
  public List<String> getMmReferenceParams();

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
   * Returns accessor of parent model.
   *
   * @return  accessor of parent model.
   */
  public MmModelAccessor<?, PARENT_MODEL> getParentAccessor();

  /**
   * Returns parent model of the accessed model.
   *
   * @return  Parent model of the accessed model.
   */
  public PARENT_MODEL getParentModel();

  /**
   * Returns accessor on root model.
   *
   * @return  Accessor on root model.
   */
  public MmModelAccessor<?, ?> getRootAccessor();

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
  default public boolean isAttribute() {
    return false;
  }

  /**
   * Returns true, if the accessed model is a collection.
   *
   * @return  True, if the accessed model is a collection.
   */
  default public boolean isCollection() {
    return false;
  }

  /**
   * Returns true, if the accessed model is a component.
   *
   * @return  True, if the accessed model is a component.
   */
  default public boolean isComponent() {
    return false;
  }

  /**
   * Returns true, if value of the accessed model is not present.
   *
   * @return  True, if value of the accessed model is not present.
   */
  default public boolean isNotPresent() {
    return !isPresent();
  }

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
  default public boolean isRoot() {
    return false;
  }

  /**
   * Sets the specified model value, may be null.
   *
   * @param  value  The specified model value, may be null.
   */
  public void set(MODEL value);
}
