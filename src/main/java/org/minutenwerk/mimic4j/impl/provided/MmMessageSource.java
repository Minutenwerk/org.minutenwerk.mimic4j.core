package org.minutenwerk.mimic4j.impl.provided;

import java.util.Locale;

import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmMessageSource is an interface which defines the API of a source of internationalized messages.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmMessageSource {

  /** Constant for message id for application default date format. */
  public static final String APPLICATION_DEFAULT_DATE_FORMAT = "application-default-date-format";

  /**
   * Returns an internationalized message for a specified locale, message id and message type.
   *
   * @param   pLocale       The specified locale of the message to be internationalized.
   * @param   pMessageId    The specified id of the message to be internationalized.
   * @param   pMessageType  The specified type of the message to be internationalized.
   * @param   pArguments    Optional list of message arguments.
   *
   * @return  The internationalized message.
   *
   * @since   $maven.project.version$
   */
  public String getMmI18nText(Locale pLocale, String pMessageId, MmMessageType pMessageType, Object... pArguments);

}
