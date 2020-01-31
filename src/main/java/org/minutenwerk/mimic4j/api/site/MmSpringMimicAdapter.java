package org.minutenwerk.mimic4j.api.site;

import java.util.List;
import java.util.Locale;

import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * Spring service definition for mimic web site.
 *
 * @param   <USER_DETAILS>  Details of user, a type outside of mimic4j.
 *
 * @author  Olaf Kossak
 */
public interface MmSpringMimicAdapter<USER_DETAILS> {

  /**
   * Returns server path of deployed application.
   *
   * @return  The server path of deployed application.
   * 
   * @throws  IllegalStateException  In case of server path is not evaluated yet.
   */
  public String getMmServerPath() throws IllegalStateException;

  /**
   * Returns a currently active session.
   *
   * @return  a currently active session.
   */
  public MmSession<USER_DETAILS> getMmActiveSession();

  /**
   * Returns a list of all available locales of this site.
   *
   * @return  a list of all available locales of this site.
   */
  public List<Locale> getMmAvailableLocales();

  /**
   * Returns a list of all available themes of this site.
   *
   * @return  a list of all available themes of this site.
   */
  public List<MmTheme> getMmAvailableThemes();

  /**
   * Returns an internationalized version for a specified message id and type.
   *
   * @param   pMessageId    The specified id of the message to be internationalized.
   * @param   pMessageType  The specified type of the message to be internationalized.
   * @param   pArguments    Optional list of message arguments.
   *
   * @return  The internationalized message.
   */
  public String getMmI18nText(String pMessageId, MmMessageType pMessageType, Object... pArguments);

}
