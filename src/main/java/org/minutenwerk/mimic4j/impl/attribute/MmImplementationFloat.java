package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmFloat;
import org.minutenwerk.mimic4j.api.attribute.MmFloatAnnotation;

/**
 * MmImplementationFloat is the implementation part of a mimic for {@link Float}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationFloat extends MmBaseAttributeImplementation<MmFloat, MmConfigurationFloat, MmFloatAnnotation, Float, String> {

  /**
   * Creates a new MmImplementationFloat instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationFloat(final MmDeclarationMimic pParent) {
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
  protected MmConfigurationFloat onConstructConfiguration(MmFloatAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationFloat(pAnnotation);
    } else {
      return new MmConfigurationFloat();
    }
  }

}
