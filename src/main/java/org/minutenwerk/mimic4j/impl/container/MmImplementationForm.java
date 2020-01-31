package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.container.MmForm;
import org.minutenwerk.mimic4j.api.container.MmFormAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationForm is the specific class for the implementation part of form mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationForm<MODEL> extends MmBaseContainerImplementation<MmForm<MODEL>, MODEL, MmConfigurationForm, MmFormAnnotation> {

  /**
   * Creates a new MmImplementationForm instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationForm(MmDeclarationMimic pParent) {
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
  protected MmConfigurationForm onConstructConfiguration(MmFormAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationForm(pAnnotation);
    } else {
      return new MmConfigurationForm();
    }
  }

}
