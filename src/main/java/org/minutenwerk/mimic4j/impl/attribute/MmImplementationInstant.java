package org.minutenwerk.mimic4j.impl.attribute;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.attribute.MmInstant;
import org.minutenwerk.mimic4j.api.attribute.MmInstantAnnotation;
import org.minutenwerk.mimic4j.api.message.MmMessageType;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;

/**
 * MmImplementationInstant is the implementation part of a mimic for {@link Instant}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationInstant extends MmBaseAttributeImplementation<MmInstant, MmConfigurationInstant, MmInstantAnnotation, Instant, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationInstant.class);

  /**
   * Creates a new MmImplementationDate instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationInstant(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's format pattern for displaying view value in view. It is used during conversion from data model to view model value and vice
   * versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying view value.
   *
   * @throws  IllegalStateException  In case of callbackMmGetFormatPattern returns an invalid format pattern.
   */
  @Override
  public String getMmFormatPattern() {
    ensureInitialization();

    String formatPattern = configuration.getFormatPattern();
    if (formatPattern == null) {
      formatPattern = getMmThisI18nText(MmMessageType.FORMAT);
    }

    final String returnString = declaration.callbackMmGetFormatPattern(formatPattern);
    if (LOGGER.isDebugEnabled()) {
      if (returnString == null) {
        throw new IllegalStateException("callbackMmGetFormatPattern() must return valid format pattern");
      }
    }
    return returnString;
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
  protected MmConfigurationInstant onConstructConfiguration(MmInstantAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationInstant(pAnnotation);
    } else {
      return new MmConfigurationInstant();
    }
  }

}
