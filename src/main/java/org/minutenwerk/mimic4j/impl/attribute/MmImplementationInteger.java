package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmInteger;
import org.minutenwerk.mimic4j.api.attribute.MmIntegerAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationInteger is the implementation part of a mimic for {@link Integer}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationInteger extends MmBaseAttributeImplementation<MmInteger, MmConfigurationInteger, MmIntegerAnnotation, Integer, String> {

  /**
   * Creates a new MmImplementationInteger instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationInteger(final MmDeclarationMimic pParent) {
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
  protected MmConfigurationInteger onConstructConfiguration(MmIntegerAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationInteger(pAnnotation);
    } else {
      return new MmConfigurationInteger();
    }
  }

}
