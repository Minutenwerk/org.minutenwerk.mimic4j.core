package org.minutenwerk.mimic4j.impl.accessor;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Immutable base class for accessor on attribute models of component models.
 *
 * @param   <PARENT_MODEL>     Type of parent model.
 * @param   <ATTRIBUTE_MODEL>  Type of attribute model.
 *
 * @author  Olaf Kossak
 */
public class MmAttributeAccessor<PARENT_MODEL, ATTRIBUTE_MODEL> extends MmBaseModelAccessor<PARENT_MODEL, ATTRIBUTE_MODEL> {

  /** Function which defines the attribute model getter method. */
  private final Function<PARENT_MODEL, ATTRIBUTE_MODEL>   attributeGetter;

  /** Function which defines the attribute model setter method. */
  private final BiConsumer<PARENT_MODEL, ATTRIBUTE_MODEL> attributeSetter;

  /**
   * Constructor of this immutable class.
   *
   * @param  parentAccessor    TODOC
   * @param  pAttributeGetter  TODOC
   * @param  pAttributeSetter  TODOC
   */
  public MmAttributeAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor,
    final Function<PARENT_MODEL, ATTRIBUTE_MODEL> pAttributeGetter, final BiConsumer<PARENT_MODEL, ATTRIBUTE_MODEL> pAttributeSetter) {
    super(parentAccessor);
    this.attributeGetter = pAttributeGetter;
    this.attributeSetter = pAttributeSetter;
  }

  /**
   * Returns the attribute value. In case of the parent component supplier does not supply a component, this method returns null.
   *
   * @return  the attribute value.
   */
  @Override
  public final ATTRIBUTE_MODEL get() {
    return getParentOptional().map(attributeGetter).orElse(null);
  }

  /**
   * Returns parent model of the accessed model.
   *
   * @return  parent model of the accessed model.
   *
   * @throws  NullPointerException  TODOC
   */
  @Override
  public PARENT_MODEL getParent() throws NullPointerException {
    return getParentOptional() //
    .orElseThrow(() ->
        new NullPointerException( //
          "parent supplier does not supply a component"));
  }

  /**
   * Returns true, if the accessed model is an attribute.
   *
   * @return  true, if the accessed model is an attribute.
   */
  @Override
  public boolean isAttribute() {
    return true;
  }

  /**
   * Returns true, if value of the accessed model is present.
   *
   * @return  true, if value of the accessed model is present.
   */
  @Override
  public boolean isPresent() {
    return getParentOptional().isPresent() && (get() != null);
  }

  /**
   * Sets the specified attribute value.
   *
   * @param   value  The specified attribute value.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  @Override
  public final void set(final ATTRIBUTE_MODEL value) throws NullPointerException {
    PARENT_MODEL component = getParent();
    attributeSetter.accept(component, value);
  }

  /**
   * Sets the specified attribute value and returns the component for chaining setters.
   *
   * @param   value  The specified attribute value.
   *
   * @return  The component.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  @Override
  public final PARENT_MODEL with(final ATTRIBUTE_MODEL value) throws NullPointerException {
    PARENT_MODEL component = getParent();
    attributeSetter.accept(component, value);
    return component;
  }

  /**
   * Returns {@link Optional} of parent component.
   *
   * @return  {@link Optional} of parent component.
   */
  protected Optional<PARENT_MODEL> getParentOptional() {
    return Optional.ofNullable(this.getParentAccessor().get());
  }
}
