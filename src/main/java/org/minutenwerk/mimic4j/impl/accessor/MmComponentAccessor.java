package org.minutenwerk.mimic4j.impl.accessor;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Immutable base class for accessor on component models.
 *
 * @param   <PARENT_MODEL>     Type of parent model.
 * @param   <COMPONENT_MODEL>  Type of component model.
 *
 * @author  Olaf Kossak
 */
public class MmComponentAccessor<PARENT_MODEL, COMPONENT_MODEL> extends MmBaseModelAccessor<PARENT_MODEL, COMPONENT_MODEL> {

  /** Reference to root model. One of rootModel and parentSupplier must be set. */
  private final PARENT_MODEL                              rootModel;

  /** Function which defines the component model getter method. */
  private final Function<PARENT_MODEL, COMPONENT_MODEL>   componentGetter;

  /** Function which defines the component model setter method. */
  private final BiConsumer<PARENT_MODEL, COMPONENT_MODEL> componentSetter;

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor   TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   */
  public MmComponentAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor,
    final Function<PARENT_MODEL, COMPONENT_MODEL> componentGetter, final BiConsumer<PARENT_MODEL, COMPONENT_MODEL> componentSetter) {
    super(parentAccessor);
    this.rootModel       = null;
    this.componentGetter = componentGetter;
    this.componentSetter = componentSetter;
  }

  /**
   * Constructor of this immutable class.
   *
   * @param  rootModel        TODOC
   * @param  componentGetter  TODOC
   * @param  componentSetter  TODOC
   */
  public MmComponentAccessor(final PARENT_MODEL rootModel, final Function<PARENT_MODEL, COMPONENT_MODEL> componentGetter,
    final BiConsumer<PARENT_MODEL, COMPONENT_MODEL> componentSetter) {
    super(null);
    this.rootModel       = rootModel;
    this.componentGetter = componentGetter;
    this.componentSetter = componentSetter;
  }

  /**
   * Returns the model value. In case of the parent model supplier does not supply a parent, this method returns null.
   *
   * @return  the model value.
   *
   * @throws  NullPointerException  TODOC
   */
  @Override
  public COMPONENT_MODEL get() throws NullPointerException {
    return getComponentOptional().orElse(null);
  }

  /**
   * Returns parent of the accessed model.
   *
   * @return  parent of the accessed model.
   */
  @Override
  public PARENT_MODEL getParent() {
    return this.isRoot() ? this.rootModel : this.getParentAccessor().get();
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
  public final boolean isPresent() {
    return getComponentOptional().isPresent();
  }

  /**
   * Returns true, if the accessed model is the root of an aggregate.
   *
   * @return  true, if the accessed model is the root of an aggregate.
   */
  @Override
  public boolean isRoot() {
    return (this.rootModel != null);
  }

  /**
   * Sets the specified model value.
   *
   * @param  value  The specified model value.
   */
  @Override
  public void set(final COMPONENT_MODEL value) {
    componentSetter.accept(getParent(), value);
  }

  /**
   * Sets the specified model value and returns the parent for chaining setters.
   *
   * @param   value  The specified model value.
   *
   * @return  The parent.
   */
  @Override
  public PARENT_MODEL with(final COMPONENT_MODEL value) {
    PARENT_MODEL withParent = this.getParent();
    componentSetter.accept(withParent, value);
    return withParent;
  }

  /**
   * Returns component.
   *
   * @return  component.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  protected COMPONENT_MODEL getComponent() throws NullPointerException {
    return getComponentOptional() //
    .orElseThrow(() ->
        new NullPointerException( //
          "component supplier does not supply a component"));
  }

  /**
   * Returns {@link Optional} of component.
   *
   * @return  {@link Optional} of component.
   */
  protected Optional<COMPONENT_MODEL> getComponentOptional() {
    PARENT_MODEL thisParent = this.getParent();
    if (thisParent != null) {
      return Optional.ofNullable(this.componentGetter.apply(thisParent));
    } else {
      return Optional.empty();
    }
  }

  /**
   * Returns function which supplies the component.
   *
   * @return  function which supplies the component.
   */
  protected Supplier<COMPONENT_MODEL> getComponentSupplier() {
    return this::get;
  }
}
