package org.minutenwerk.mimic4j.api.accessor;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.minutenwerk.mimic4j.impl.accessor.MmBaseModelAccessor;

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
   * @param  parentAccessor    The model accessor of the parent model.
   * @param  pAttributeGetter  The attribute model getter method.
   * @param  pAttributeSetter  The attribute model setter method.
   */
  public MmAttributeAccessor(final MmModelAccessor<?, PARENT_MODEL> parentAccessor, final Function<PARENT_MODEL, ATTRIBUTE_MODEL> pAttributeGetter,
    final BiConsumer<PARENT_MODEL, ATTRIBUTE_MODEL> pAttributeSetter) {
    super(parentAccessor);
    attributeGetter = pAttributeGetter;
    attributeSetter = pAttributeSetter;
  }

  /**
   * Returns the attribute value. In case of the parent component supplier does not supply a component, this method returns null.
   *
   * @return  the attribute value.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  @Override
  public ATTRIBUTE_MODEL get() throws NullPointerException {
    try {
      return getParentOptional().map(attributeGetter).orElse(null);
      // TODO catch (ClassCastException e)
    } catch (ClassCastException e) {
      return null;
    }
  }

  /**
   * Returns parent model of the accessed model.
   *
   * @return  parent model of the accessed model.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  @Override
  public PARENT_MODEL getParentModel() throws NullPointerException {
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
  public final boolean isAttribute() {
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
  public void set(final ATTRIBUTE_MODEL value) throws NullPointerException {
    PARENT_MODEL component = getParentModel();
    attributeSetter.accept(component, value);
  }

  /**
   * Returns {@link Optional} of parent component.
   *
   * @return  {@link Optional} of parent component.
   *
   * @throws  NullPointerException  In case of the parent component supplier does not supply a component.
   */
  protected Optional<PARENT_MODEL> getParentOptional() throws NullPointerException {
    return Optional.ofNullable(getParentAccessor().get());
  }
}
