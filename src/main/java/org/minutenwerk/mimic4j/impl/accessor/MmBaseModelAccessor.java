package org.minutenwerk.mimic4j.impl.accessor;

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
   * @param  pParentAccessor  TODOC
   */
  public MmBaseModelAccessor(final MmModelAccessor<?, PARENT_MODEL> pParentAccessor) {
    this.parentAccessor = pParentAccessor;
  }

  /**
   * Returns true, if the accessed model is an attribute.
   *
   * @return  true, if the accessed model is an attribute.
   */
  @Override
  public boolean isAttribute() {
    return false;
  }

  /**
   * Returns true, if the accessed model is a collection.
   *
   * @return  true, if the accessed model is a collection.
   */
  @Override
  public boolean isCollection() {
    return false;
  }

  /**
   * Returns true, if the accessed model is a component.
   *
   * @return  true, if the accessed model is a component.
   */
  @Override
  public boolean isComponent() {
    return false;
  }

  /**
   * Returns true, if value of the accessed model is not present.
   *
   * @return  true, if value of the accessed model is not present.
   */
  @Override
  public final boolean isNotPresent() {
    return !this.isPresent();
  }

  /**
   * Returns true, if the accessed model is the root of an aggregate.
   *
   * @return  true, if the accessed model is the root of an aggregate.
   */
  @Override
  public boolean isRoot() {
    return false;
  }

  /**
   * Returns accessor of parent model.
   *
   * @return  accessor of parent model.
   */
  protected final MmModelAccessor<?, PARENT_MODEL> getParentAccessor() {
    return this.parentAccessor;
  }
}
