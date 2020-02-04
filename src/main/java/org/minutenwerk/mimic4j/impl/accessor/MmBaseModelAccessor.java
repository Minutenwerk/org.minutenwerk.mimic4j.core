package org.minutenwerk.mimic4j.impl.accessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.mimic.MmReferencableModel;

/**
 * Abstract base class for model accessors.
 *
 * @param   <PARENT_MODEL>  Type of parent model.
 * @param   <MODEL>         Type of model.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseModelAccessor<PARENT_MODEL, MODEL> implements MmModelAccessor<PARENT_MODEL, MODEL> {

  /** Accessor of parent model. */
  private final MmModelAccessor<?, PARENT_MODEL> parentAccessor;

  /**
   * Constructor of this immutable class.
   *
   * @param  pParentAccessor  The model accessor of the parent model.
   */
  public MmBaseModelAccessor(final MmModelAccessor<?, PARENT_MODEL> pParentAccessor) {
    parentAccessor = pParentAccessor;
  }

  /**
   * Resets model and any other state information to null.
   */
  @Override
  public void reset() {
  }

  /**
   * Returns a list of path or query parameter values of the URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @return  A list of path or query parameter values of the URL. Usually this is a list of ids starting by id of root dto.
   */
  @Override
  public List<String> getMmReferenceParams() {
    MODEL model = get();
    if ((model != null) && (model instanceof MmReferencableModel)) {
      return ((MmReferencableModel)model).getMmReferenceParams();
    }
    return Collections.emptyList();
  }

  /**
   * Returns list of accessors, root accessor first.
   *
   * @return  List of accessors, root accessor first.
   */
  @Override
  public List<MmModelAccessor<?, ?>> getModelAccessorPath() {
    List<MmModelAccessor<?, ?>> list = (getParentAccessor() != null) ? getParentAccessor().getModelAccessorPath() : new ArrayList<>();
    list.add(this);
    return list;
  }

  /**
   * Returns list of models, root model first.
   *
   * @return  List of models, root model first.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <M> List<M> getModelPath() {
    List<M> list  = (getParentAccessor() != null) ? getParentAccessor().getModelPath() : new ArrayList<>();
    M       model = (M)get();
    if (model != null) {
      list.add(model);
    }
    return list;
  }

  /**
   * Returns accessor of parent model.
   *
   * @return  accessor of parent model.
   */
  @Override
  public final MmModelAccessor<?, PARENT_MODEL> getParentAccessor() {
    return parentAccessor;
  }

  /**
   * Returns accessor on root model.
   *
   * @return  Accessor on root model.
   */
  @Override
  public MmModelAccessor<?, ?> getRootAccessor() {
    return (getParentAccessor() != null) ? getParentAccessor().getRootAccessor() : (MmModelAccessor<?, ?>)this;
  }

  /**
   * Returns root model.
   *
   * @return  Root model.
   */
  @Override
  @SuppressWarnings("unchecked")
  public <R> R getRootModel() {
    return (R)getRootAccessor().get();
  }
}
