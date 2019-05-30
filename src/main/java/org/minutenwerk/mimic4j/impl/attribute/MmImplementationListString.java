package org.minutenwerk.mimic4j.impl.attribute;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmListString;
import org.minutenwerk.mimic4j.api.attribute.MmListStringAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationListString is the implementation part of a mimic for a list of {@link String}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationListString
  extends MmBaseAttributeImplementation<MmListString, MmConfigurationListString, MmListStringAnnotation, List<String>, List<String>> {

  /**
   * Creates a new MmImplementationListString instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationListString(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewsideValue == null) || viewsideValue.isEmpty());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationListString onConstructConfiguration(MmListStringAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationListString(pAnnotation);
    } else {
      return new MmConfigurationListString();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeAttribute<List<String>>(this);
  }

}
