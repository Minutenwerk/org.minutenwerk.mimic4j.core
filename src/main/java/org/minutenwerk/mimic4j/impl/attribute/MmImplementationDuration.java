package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Duration;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmDuration;
import org.minutenwerk.mimic4j.api.attribute.MmDurationAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationDuration is the implementation part of a mimic for {@link Duration}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationDuration extends MmBaseAttributeImplementation<MmDuration, MmConfigurationDuration, Duration, String> {

  /**
   * Creates a new MmImplementationDuration instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationDuration(MmDeclarationMimic pParent) {
    super(pParent);
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
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmDurationAnnotation.class);

    MmDurationAnnotation annotation = this.findAnnotation(this.declaration, MmDurationAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationDuration();
    } else {
      this.configuration = new MmConfigurationDuration(annotation);
    }
  }

}
