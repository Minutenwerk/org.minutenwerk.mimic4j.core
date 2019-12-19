package org.minutenwerk.mimic4j.impl.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.site.MmSession;
import org.minutenwerk.mimic4j.api.site.MmSpringMimicAdapter;
import org.minutenwerk.mimic4j.api.site.MmTheme;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/**
 * Spring service implementation for mimic web site.
 *
 * @param   <USER_DETAILS>  Details of user, a type outside of mimic4j.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseSpringMimicAdapter<USER_DETAILS> implements MmSpringMimicAdapter<USER_DETAILS> {

  /** Logger of this class. */
  private static final Logger   LOGGER           = LogManager.getLogger(MmBaseSpringMimicAdapter.class);

  /** Spring message provider with support for parameters and i18n. */
  protected final MessageSource messageSource;

  /** A list of all available locales of this site. */
  protected final List<Locale>  availableLocales;

  /** A list of all available themes of this site. */
  protected final List<MmTheme> availableThemes;

  /**
   * Constructor.
   *
   * @param  pMessageSource  Spring message provider with support for parameters and i18n.
   */
  public MmBaseSpringMimicAdapter(final MessageSource pMessageSource) {
    messageSource    = pMessageSource;
    availableLocales = new ArrayList<>();
    availableThemes  = new ArrayList<>();
  }

  /**
   * Adds specified locale.
   *
   * @param  pLocale  The specified locale.
   */
  public final void addMmLocale(final Locale pLocale) {
    availableLocales.add(pLocale);
  }

  /**
   * Adds specified theme.
   *
   * @param  pTheme  The specified theme.
   */
  public final void addMmTheme(final MmTheme pTheme) {
    availableThemes.add(pTheme);
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  public final Locale getMmActiveLocale() {
    return getMmActiveSession().getMmLocale();
  }

  /**
   * Returns a currently active session.
   *
   * @return  a currently active session.
   */
  @Override
  public abstract MmSession<USER_DETAILS> getMmActiveSession();

  /**
   * Returns a list of all available locales of this site.
   *
   * @return  a list of all available locales of this site.
   */
  @Override
  public final List<Locale> getMmAvailableLocales() {
    return availableLocales;
  }

  /**
   * Returns a list of all available themes of this site.
   *
   * @return  a list of all available themes of this site.
   */
  @Override
  public final List<MmTheme> getMmAvailableThemes() {
    return availableThemes;
  }

  /**
   * Returns an internationalized version for a specified message id and type.
   *
   * @param   pMessageId    The specified id of the message to be internationalized.
   * @param   pMessageType  The specified type of the message to be internationalized.
   * @param   pArguments    Optional list of message arguments.
   *
   * @return  The internationalized message.
   *
   * @throws  RuntimeException  In case of no message for specified id and locale.
   */
  @Override
  public String getMmI18nText(String pMessageId, MmMessageType pMessageType, Object... pArguments) {
    if (messageSource != null) {
      String messageCode = pMessageId;
      if ((pMessageType != null) && (pMessageType != MmMessageType.TEXT)) {
        messageCode = messageCode + "." + pMessageType.getSuffix();
      }
      try {
        return messageSource.getMessage(messageCode, pArguments, getMmActiveLocale());
      } catch (NoSuchMessageException e) {
        if ((pMessageType == MmMessageType.TEXT) || (pMessageType == MmMessageType.SHORT) || (pMessageType == MmMessageType.LONG)) {
          LOGGER.warn("no message for >" + messageCode + "< for locale " + getMmActiveLocale());
          return messageCode;
        } else {
          throw new RuntimeException("no message for >" + messageCode + "< for locale " + getMmActiveLocale());
        }
      }

    } else {
      LOGGER.warn("getMmI18nText: {}, {}: no root message source", pMessageId, pMessageType);

      // for unit tests this root returns last part of message id
      String returnString = pMessageId;
      if (returnString.contains(".")) {
        returnString = returnString.substring(returnString.lastIndexOf(".") + 1);
      }
      return returnString;
    }
  }
}
