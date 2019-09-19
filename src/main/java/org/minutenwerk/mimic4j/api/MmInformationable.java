package org.minutenwerk.mimic4j.api;

/**
 * This object can return a short information about itself.
 *
 * @author  Olaf Kossak
 */
public interface MmInformationable {

  /**
   * Returns an array of objects containing short information about this instance.
   *
   * @return  An array of objects containing short information about this instance.
   */
  public Object[] getInfo();

}
