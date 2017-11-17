package org.minutenwerk.mimic4j.impl.provided;

import java.util.Locale;

/**
 * MmSessionContext is an interface which defines the information the mimic layer needs about a user's session.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmSessionContext {

  /**
   * Returns the locale of the user's session.
   *
   * @return  The locale of the user's session.
   *
   * @since   $maven.project.version$
   */
  public Locale getMmLocale();

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   *
   * @since   $maven.project.version$
   */
  public boolean isMmJsEnabled();

}
