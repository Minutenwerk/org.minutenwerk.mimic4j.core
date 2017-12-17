package org.minutenwerk.mimic4j.impl.provided;

import java.util.Locale;

/**
 * MmSessionContext is an interface which defines the information the mimic layer needs about a user's session.
 *
 * @author  Olaf Kossak
 */
public interface MmSessionContext {

  /**
   * Returns the locale of the user's session.
   *
   * @return  The locale of the user's session.
   */
  public Locale getMmLocale();

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  public boolean isMmJsEnabled();

}
