package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationString is the implementation part of a mimic for {@link String}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationString
  extends MmBaseAttributeImplementation<MmString, MmConfigurationString, MmStringAnnotation, String, String> {

  /**
   * Creates a new MmImplementationString instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationString(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's number of columns in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of columns.
   */
  @Override
  public int getMmCols() {
    assureInitialization();

    return configuration.getCols();
  }

  /**
   * Returns the attribute's number of rows in case it is displayed as multi line text field.
   *
   * @return  The attribute's number of rows.
   */
  @Override
  public int getMmRows() {
    assureInitialization();

    return configuration.getRows();
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewsideValue == null) || viewsideValue.trim().isEmpty());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationString onConstructConfiguration(MmStringAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationString(pAnnotation);
    } else {
      return new MmConfigurationString();
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
