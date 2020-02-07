package org.minutenwerk.mimic4j.impl.attribute;

import org.minutenwerk.mimic4j.api.attribute.MmLong;
import org.minutenwerk.mimic4j.api.attribute.MmLongAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationLong is the implementation part of a mimic for {@link Long}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLong extends MmBaseAttributeImplementation<MmLong, MmConfigurationLong, MmLongAnnotation, Long, String> {

  /**
   * Creates a new MmImplementationLong instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationLong(final MmDeclarationMimic pParent) {
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
  protected MmConfigurationLong onConstructConfiguration(MmLongAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLong(pAnnotation);
    } else {
      return new MmConfigurationLong();
    }
  }

}
