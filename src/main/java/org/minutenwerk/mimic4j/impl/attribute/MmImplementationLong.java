package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmLong;
import org.minutenwerk.mimic4j.api.attribute.MmLongAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationLong is the implementation part of a mimic for {@link Long}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLong extends MmBaseAttributeImplementation<MmLong, MmConfigurationLong, MmLongAnnotation, Long, String> {

  /**
   * Creates a new MmImplementationLong instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLong(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewModelValue == null) || viewModelValue.trim().isEmpty());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationLong onConstructConfiguration(MmLongAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLong(pAnnotation);
    } else {
      return new MmConfigurationLong();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeAttribute<String>(this);
  }

}
