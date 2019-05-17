package org.minutenwerk.mimic4j.impl.accessor;

/**
 * TODOC MmRootAccessor.
 */
public class MmRootAccessor<COMPONENT_MODEL> extends MmBaseModelAccessor<Void, COMPONENT_MODEL> {

  /** TODOC. */
  private COMPONENT_MODEL rootModel;

  /**
   * Creates a new MmRootAccessor instance.
   */
  public MmRootAccessor() {
    super(null);
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  @Override
  public COMPONENT_MODEL get() {
    return rootModel;
  }

  /**
   * TODOC.
   *
   * @return  TODOC
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
   * TODOC.
   *
   * @return  TODOC
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
   * TODOC.
   *
   * @param  value  TODOC
   */
  @Override
  public void set(final COMPONENT_MODEL value) {
    rootModel = value;
  }

  /**
   * TODOC.
   *
   * @param   value  TODOC
   *
   * @return  TODOC
   */
  @Override
  public Void with(final COMPONENT_MODEL value) {
    rootModel = value;
    return null;
  }
}
