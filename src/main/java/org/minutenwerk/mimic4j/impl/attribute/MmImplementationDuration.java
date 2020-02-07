package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Duration;

import org.minutenwerk.mimic4j.api.attribute.MmDuration;
import org.minutenwerk.mimic4j.api.attribute.MmDurationAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationDuration is the implementation part of a mimic for {@link Duration}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationDuration extends MmBaseAttributeImplementation<MmDuration, MmConfigurationDuration, MmDurationAnnotation, Duration, String> {

  /**
   * Creates a new MmImplementationDuration instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationDuration(final MmDeclarationMimic pParent) {
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
  protected MmConfigurationDuration onConstructConfiguration(MmDurationAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationDuration(pAnnotation);
    } else {
      return new MmConfigurationDuration();
    }
  }

}
