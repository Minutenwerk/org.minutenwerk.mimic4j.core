package org.minutenwerk.mimic4j.impl.attribute;

import java.time.ZonedDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTimeAnnotation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeAttribute;

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
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationZonedDateTime(final MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @return  The attribute's format pattern for displaying viewside value.
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
   * Returns <code>true</code> if the viewside value of this mimic is empty.
   *
   * @return  <code>True</code> if the viewside value of this mimic is empty.
   */
  @Override
  public boolean isMmEmpty() {
    assureInitialization();

    return ((viewsideValue == null) || viewsideValue.trim().isEmpty());
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

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeAttribute<String>(this);
  }

}
