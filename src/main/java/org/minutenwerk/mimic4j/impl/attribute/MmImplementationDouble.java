package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmDouble;
import org.minutenwerk.mimic4j.api.attribute.MmDoubleAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationDouble is the implementation part of a mimic for {@link Double}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationDouble extends MmBaseAttributeImplementation<MmDouble, MmConfigurationDouble, MmDoubleAnnotation, Double, String> {

  /**
   * Creates a new MmImplementationDouble instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationDouble(final MmDeclarationMimic pParent) {
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
  protected MmConfigurationDouble onConstructConfiguration(MmDoubleAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationDouble(pAnnotation);
    } else {
      return new MmConfigurationDouble();
    }
  }

}
