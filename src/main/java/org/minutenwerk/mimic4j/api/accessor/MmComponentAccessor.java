package org.minutenwerk.mimic4j.api.accessor;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.minutenwerk.mimic4j.impl.accessor.MmBaseModelAccessor;

/**
 * Immutable base class for accessor on component models.
 *
 * @param   <PARENT_MODEL>     Type of parent model.
 * @param   <COMPONENT_MODEL>  Type of component model.
 *
 * @author  Olaf Kossak
 */
public class MmComponentAccessor<PARENT_MODEL, COMPONENT_MODEL> extends MmBaseModelAccessor<PARENT_MODEL, COMPONENT_MODEL> {

  /** Function which defines the component model getter method. */
  private final Function<PARENT_MODEL, COMPONENT_MODEL>   componentGetter;

  /** Function which defines the component model setter method. */
  private final BiConsumer<PARENT_MODEL, COMPONENT_MODEL> componentSetter;

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor    The model accessor of the parent model.
   * @param  pComponentGetter  The component model getter method.
   * @param  pComponentSetter  The component model setter method.
   */
  public MmComponentAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, //
    final Function<PARENT_MODEL, COMPONENT_MODEL> pComponentGetter, //
    final BiConsumer<PARENT_MODEL, COMPONENT_MODEL> pComponentSetter) {
    super(parentAccessor);
    componentGetter = pComponentGetter;
    componentSetter = pComponentSetter;
  }

  /**
   * Returns the model value. In case of the parent model supplier does not supply a parent, this method returns null.
   *
   * @return  the model value, may be null.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
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
  public PARENT_MODEL getParentModel() {
    return getParentAccessor().get();
  }

  /**
   * Returns true, if the accessed model is a component.
   *
   * @return  true, if the accessed model is a component.
   */
  @Override
  public final boolean isComponent() {
    return true;
  }

  /**
   * Returns true, if value of the accessed model is present.
   *
   * @return  true, if value of the accessed model is present.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  @Override
  public boolean isPresent() throws NullPointerException {
    return getComponentOptional().isPresent();
  }

  /**
   * Sets the specified model value, may be null.
   *
   * @param  value  The specified model value, may be null.
   */
  @Override
  public void set(final COMPONENT_MODEL value) {
    componentSetter.accept(getParentModel(), value);
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
    .orElseThrow(() -> new NullPointerException("component supplier does not supply a component"));
  }

  /**
   * Returns {@link Optional} of component.
   *
   * @return  {@link Optional} of component.
   */
  protected Optional<COMPONENT_MODEL> getComponentOptional() {
    PARENT_MODEL parentModel = getParentModel();
    if (parentModel != null) {
      return Optional.ofNullable(componentGetter.apply(parentModel));
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
