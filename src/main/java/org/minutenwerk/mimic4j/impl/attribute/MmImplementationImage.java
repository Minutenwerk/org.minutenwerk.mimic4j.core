package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmImage;
import org.minutenwerk.mimic4j.api.attribute.MmImageAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationImage is the implementation part of a mimic for {@link String}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationImage extends MmBaseAttributeImplementation<MmImage, MmConfigurationImage, MmImageAnnotation, String, String> {

  /**
   * Creates a new MmImplementationImage instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationImage(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns <code>true</code> if the view value of this mimic is empty.
   *
   * @return  <code>True</code> if the view value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    ensureInitialization();

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
  protected MmConfigurationImage onConstructConfiguration(MmImageAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationImage(pAnnotation);
    } else {
      return new MmConfigurationImage();
    }
  }

}
