package org.minutenwerk.mimic4j.impl.attribute;

import org.joda.time.LocalDateTime;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmDateTimeAnnotation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationDateTime is the implementation part of a mimic for {@link LocalDateTime}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationDateTime extends MmBaseAttributeImplementation<MmDateTime, MmConfigurationDateTime, LocalDateTime, String> {

  /**
   * Creates a new MmImplementationDateTime instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationDateTime(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
   */
  @Override public String getMmFormatPattern() {
    this.ensureInitialization();

    String formatPattern = this.configuration.getFormatPattern();
    if (formatPattern == null) {
      formatPattern = this.getMmI18nText(MmMessageType.FORMAT);
    }

    final String returnString = this.declaration.callbackMmGetFormatPattern(formatPattern);
    assert returnString != null : "callbackMmGetFormatPattern cannot return null";
    return returnString;
  }

  /**
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override public boolean isMmEmpty() {
    this.ensureInitialization();

    return ((this.viewsideValue == null) || this.viewsideValue.trim().isEmpty());
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeAttribute<String>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmDateTimeAnnotation.class);

    MmDateTimeAnnotation annotation = this.findAnnotation(this.declaration, MmDateTimeAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationDateTime();
    } else {
      this.configuration = new MmConfigurationDateTime(annotation);
    }
  }

}
