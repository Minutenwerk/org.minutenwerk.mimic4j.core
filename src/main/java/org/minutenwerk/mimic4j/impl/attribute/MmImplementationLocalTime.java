package org.minutenwerk.mimic4j.impl.attribute;

import java.time.LocalTime;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTimeAnnotation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationLocalTime is the implementation part of a mimic for {@link LocalTime}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLocalTime extends MmBaseAttributeImplementation<MmLocalTime, MmConfigurationLocalTime, LocalTime, String> {

  /**
   * Creates a new MmImplementationDate instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLocalTime(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   */
  @Override
  public String getMmFormatPattern() {
    assureInitialization();

    String formatPattern = configuration.getFormatPattern();
    if (formatPattern == null) {
      formatPattern = getMmI18nText(MmMessageType.FORMAT);
    }

    final String returnString = declaration.callbackMmGetFormatPattern(formatPattern);
    assert returnString != null : "callbackMmGetFormatPattern cannot return null";
    return returnString;
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
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeAttribute<String>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    checkForIllegalAnnotationsOtherThan(declaration, MmLocalTimeAnnotation.class);

    MmLocalTimeAnnotation annotation = findAnnotation(declaration, MmLocalTimeAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      configuration = new MmConfigurationLocalTime();
    } else {
      configuration = new MmConfigurationLocalTime(annotation);
    }
  }

}
