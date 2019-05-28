package org.minutenwerk.mimic4j.impl;

import org.minutenwerk.mimic4j.api.MmMimic;

/**
 * Methods for internal use only.
 *
 * @author  Olaf Kossak
 */
public class MmInternal {

  /**
   * Constructor is not for use.
   */
  private MmInternal() {
  }

  /**
   * Returns the implementation part of a specified mimic, for internal use only.
   *
   * @param   pMimic  The specified mimic.
   *
   * @return  The implementation part of a specified mimic.
   *
   * @throws  IllegalArgumentException  In case of mimic is not of type MmBaseImplementation or MmBaseImplementation.
   */
  public static MmBaseImplementation<?, ?, ?> getMmImplementation(MmMimic pMimic) {
    if (pMimic instanceof MmBaseImplementation<?, ?, ?>) {
      return (MmBaseImplementation<?, ?, ?>)pMimic;
    } else if (pMimic instanceof MmBaseDeclaration<?, ?>) {
      final MmBaseImplementation<?, ?, ?> implementationPartOfMimic = ((MmBaseDeclaration<?, ?>)pMimic).implementation;
      return implementationPartOfMimic;
    } else {
      throw new IllegalArgumentException("pMimic must be of type MmBaseImplementation or MmBaseImplementation");
    }
  }
}
