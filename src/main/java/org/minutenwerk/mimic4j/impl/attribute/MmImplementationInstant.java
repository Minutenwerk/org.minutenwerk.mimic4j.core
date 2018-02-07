package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Instant;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmInstant;
import org.minutenwerk.mimic4j.api.attribute.MmInstantAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

/**
 * MmImplementationInstant is the implementation part of a mimic for {@link Instant}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationInstant extends MmBaseAttributeImplementation<MmInstant, MmConfigurationInstant, Instant, String> {

  /**
   * Creates a new MmImplementationInstant instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationInstant(MmDeclarationMimic pParent) {
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
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmInstantAnnotation.class);

    MmInstantAnnotation annotation = this.findAnnotation(this.declaration, MmInstantAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationInstant();
    } else {
      this.configuration = new MmConfigurationInstant(annotation);
    }
  }

}
