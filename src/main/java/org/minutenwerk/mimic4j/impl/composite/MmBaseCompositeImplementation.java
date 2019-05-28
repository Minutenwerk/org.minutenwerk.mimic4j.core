package org.minutenwerk.mimic4j.impl.composite;

import java.lang.annotation.Annotation;

import org.minutenwerk.mimic4j.api.MmCompositeMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;

/**
 * MmBaseCompositeImplementation is the abstract base class for the implementation part of all composite mimic classes.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseCompositeImplementation<CALLBACK extends MmCompositeCallback,
  CONFIGURATION extends MmBaseConfiguration, ANNOTATION extends Annotation>
  extends MmBaseImplementation<MmBaseCompositeDeclaration<?>, CONFIGURATION, ANNOTATION> implements MmCompositeMimic {

  /**
   * Creates a new MmBaseCompositeImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseCompositeImplementation(MmDeclarationMimic pParent) {
    super(pParent);
  }

}
