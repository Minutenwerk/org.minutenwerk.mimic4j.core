package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;

/**
 * MmImplementationBoolean is the implementation part of a mimic for {@link Boolean}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationBoolean extends MmBaseAttributeImplementation<MmBoolean, MmConfigurationBoolean, MmBooleanAnnotation, Boolean, Boolean> {

  /**
   * Creates a new MmImplementationBoolean instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationBoolean(final MmDeclarationMimic pParent) {
    super(pParent);
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

}
