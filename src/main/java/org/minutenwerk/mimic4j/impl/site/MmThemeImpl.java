package org.minutenwerk.mimic4j.impl.site;

import org.minutenwerk.mimic4j.api.site.MmTheme;

/**
 * MmTheme defines a frontend layout.
 *
 * @author  Olaf Kossak
 */
public class MmThemeImpl implements MmTheme {

  /** Name of theme. */
  private final String name;

  /**
   * Constructor.
   *
   * @param  pName  Specified name of theme.
   */
  public MmThemeImpl(String pName) {
    name = pName;
  }

  /**
   * Returns name of theme.
   *
   * @return  name of theme.
   */
  @Override
  public final String getMmName() {
    return name;
  }
}
