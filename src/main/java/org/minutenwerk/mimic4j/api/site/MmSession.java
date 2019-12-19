package org.minutenwerk.mimic4j.api.site;

import java.util.Locale;

import org.minutenwerk.mimic4j.impl.site.MmSessionImpl;

/**
 * MmSession contains information about a user's web session.
 *
 * @param   <USER_DETAILS>  Details of user, a type outside of mimic4j.
 *
 * @author  Olaf Kossak
 */
public interface MmSession<USER_DETAILS> {

  /**
   * Returns current session locale.
   *
   * @return  current session locale.
   */
  public Locale getMmLocale();

  /**
   * Returns http session id.
   *
   * @return  http session id.
   */
  public Long getMmSessionId();

  /**
   * Returns current session theme.
   *
   * @return  current session theme.
   */
  public MmTheme getMmTheme();

  /**
   * Returns details of user.
   *
   * @return  details of user.
   */
  public USER_DETAILS getMmUserDetails();

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  true, if the user's browser has enabled Javascript language.
   */
  public boolean isMmUserAgentJavaScriptEnabled();

  /**
   * Sets specified locale for this session.
   *
   * @param   pLocale  The specified locale.
   *
   * @return  This session.
   */
  public MmSessionImpl<USER_DETAILS> setMmLocale(Locale pLocale);

  /**
   * Sets specified theme for this session.
   *
   * @param   pTheme  The specified theme.
   *
   * @return  This session.
   */
  public MmSessionImpl<USER_DETAILS> setMmTheme(MmTheme pTheme);

}
