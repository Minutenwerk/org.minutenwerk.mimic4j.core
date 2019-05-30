package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationBoolean is the implementation part of a mimic for {@link Boolean}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationBoolean
  extends MmBaseAttributeImplementation<MmBoolean, MmConfigurationBoolean, MmBooleanAnnotation, Boolean, Boolean> {

  /**
   * Creates a new MmImplementationBoolean instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationBoolean(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's layout direction in case the attribute is of subtype MmBoolean.
   *
   * @return  The attribute's layout direction.
   */
  @Override
  public MmBooleanLayout getMmLayout() {
    assureInitialization();

    return getConfiguration().getLayout();
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationBoolean onConstructConfiguration(MmBooleanAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationBoolean(pAnnotation);
    } else {
      return new MmConfigurationBoolean();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeAttribute<Boolean>(this);
  }

}
