package org.minutenwerk.mimic4j.impl.composite;

import org.minutenwerk.mimic4j.api.MmCompositeMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseCompositeDeclaration is the base class for composite mimics.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseCompositeDeclaration<IMPLEMENTATION extends MmBaseCompositeImplementation<?, ?, ?>>
  extends MmBaseDeclaration<MmCompositeMimic, IMPLEMENTATION> implements MmCompositeMimic, MmCompositeCallback {

  /**
   * Creates a new MmBaseCompositeDeclaration instance.
   *
   * @param  pImplementation  The reference to the implementation part of the mimic.
   */
  protected MmBaseCompositeDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

}
