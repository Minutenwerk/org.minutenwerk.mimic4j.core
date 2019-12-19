package org.minutenwerk.mimic4j.impl.attribute;

import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTimeAnnotation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmImplementationDateTime is the implementation part of a mimic for {@link ZonedDateTime}.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationZonedDateTime
  extends MmBaseAttributeImplementation<MmZonedDateTime, MmConfigurationZonedDateTime, MmZonedDateTimeAnnotation, ZonedDateTime, String> {

  /** Logger of this class. */
  private static final Logger LOGGER = LogManager.getLogger(MmImplementationZonedDateTime.class);

  /**
   * Creates a new MmImplementationDateTime instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationZonedDateTime(final MmDeclarationMimic pParent) {
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
    assureInitialization();

    String formatPattern = configuration.getFormatPattern();
    if (formatPattern == null) {
      formatPattern = getMmI18nText(MmMessageType.FORMAT);
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
  protected MmConfigurationZonedDateTime onConstructConfiguration(MmZonedDateTimeAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationZonedDateTime(pAnnotation);
    } else {
      return new MmConfigurationZonedDateTime();
    }
  }

}
