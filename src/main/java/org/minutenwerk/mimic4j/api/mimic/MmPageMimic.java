package org.minutenwerk.mimic4j.api.mimic;

import java.util.List;
import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.site.MmSpringMimicAdapter;
import org.minutenwerk.mimic4j.api.site.MmTheme;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;

/**
 * MmPageMimic mimics a web page.
 *
 * @param   <MODEL>  The type of the model, containing business data.
 *
 * @author  Olaf Kossak
 */
public interface MmPageMimic<MODEL> extends MmContainerMimic<MODEL> {

  /**
   * Returns a list of direct mimic children of this page.
   *
   * @return  a list of direct mimic children of this page.
   */
  public List<MmMimic> getMmElements();

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

  /**
   * Returns current session locale.
   *
   * @return  current session locale.
   */
  public Locale getMmLocale();

  /**
   * Returns page title.
   *
   * @return  page title.
   */
  public String getMmPageTitle();

  /**
   * Returns Spring mimic adapter of this page.
   *
   * @return  Spring mimic adapter of this page.
   */
  public <USER_DETAILS> MmSpringMimicAdapter<USER_DETAILS> getMmSpringMimicAdapter();

  /**
   * Returns current session theme.
   *
   * @return  current session theme.
   */
  public MmTheme getMmTheme();

  /**
   * Returns true, if the user's browser has disabled Javascript language.
   *
   * @return  true, if the user's browser has disabled Javascript language.
   */
  public boolean isMmUserAgentJavaScriptDisabled();

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  true, if the user's browser has enabled Javascript language.
   */
  public boolean isMmUserAgentJavaScriptEnabled();

}
