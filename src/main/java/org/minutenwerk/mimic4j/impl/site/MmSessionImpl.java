package org.minutenwerk.mimic4j.impl.site;

import java.util.Locale;

import org.minutenwerk.mimic4j.api.site.MmSession;
import org.minutenwerk.mimic4j.api.site.MmTheme;

/**
 * MmSession contains information about a user's web session.
 *
 * @param   <USER_DETAILS>  Details of user, a type outside of mimic4j.
 *
 * @author  Olaf Kossak
 */
public class MmSessionImpl<USER_DETAILS> implements MmSession<USER_DETAILS> {

  /** Current session locale. */
  private Locale       locale;

  /** Current session theme. */
  private MmTheme      theme;

  /** Http session id. */
  private Long         sessionId;

  /** Details of user. */
  private USER_DETAILS userDetails;

  /** True, if the user's browser has enabled Javascript language. */
  private boolean      isJavaScriptEnabled;

  /**
   * Constructor.
   */
  public MmSessionImpl() {
  }

  /**
   * Returns current session locale.
   *
   * @return  current session locale.
   */
  @Override
  public Locale getMmLocale() {
    return locale;
  }

  /**
   * Returns http session id.
   *
   * @return  http session id.
   */
  @Override
  public Long getMmSessionId() {
    return sessionId;
  }

  /**
   * Returns current session theme.
   *
   * @return  current session theme.
   */
  @Override
  public MmTheme getMmTheme() {
    return theme;
  }

  /**
   * Returns details of user.
   *
   * @return  details of user.
   */
  @Override
  public USER_DETAILS getMmUserDetails() {
    return userDetails;
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  true, if the user's browser has enabled Javascript language.
   */
  @Override
  public boolean isMmUserAgentJavaScriptEnabled() {
    return isJavaScriptEnabled;
  }

  /**
   * TODOC.
   *
   * @param   pIsJavaScriptEnabled  TODOC
   *
   * @return  TODOC
   */
  public MmSessionImpl<USER_DETAILS> setMmJavaScriptEnabled(final boolean pIsJavaScriptEnabled) {
    isJavaScriptEnabled = pIsJavaScriptEnabled;
    return this;
  }

  /**
   * Sets specified locale for this session.
   *
   * @param   pLocale  The specified locale.
   *
   * @return  This session.
   */
  @Override
  public MmSessionImpl<USER_DETAILS> setMmLocale(final Locale pLocale) {
    locale = pLocale;
    return this;
  }

  /**
   * TODOC.
   *
   * @param   pSessionId  TODOC
   *
   * @return  TODOC
   */
  public MmSessionImpl<USER_DETAILS> setMmSessionId(final Long pSessionId) {
    sessionId = pSessionId;
    return this;
  }

  /**
   * Sets specified theme for this session.
   *
   * @param   pTheme  The specified theme.
   *
   * @return  This session.
   */
  @Override
  public MmSessionImpl<USER_DETAILS> setMmTheme(final MmTheme pTheme) {
    theme = pTheme;
    return this;
  }

  /**
   * TODOC.
   *
   * @param   pUserDetails  TODOC
   *
   * @return  TODOC
   */
  public MmSessionImpl<USER_DETAILS> setMmUserDetails(final USER_DETAILS pUserDetails) {
    userDetails = pUserDetails;
    return this;
  }
}
