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
public class MmImplementationBoolean extends MmBaseAttributeImplementation<MmBoolean, MmConfigurationBoolean, Boolean, Boolean> {

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
    this.ensureInitialization();

    return this.getConfiguration().getLayout();
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeAttribute<Boolean>(this);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmBooleanAnnotation.class);

    MmBooleanAnnotation annotation = this.findAnnotation(this.declaration, MmBooleanAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationBoolean();
    } else {
      this.configuration = new MmConfigurationBoolean(annotation);
    }
  }

}
