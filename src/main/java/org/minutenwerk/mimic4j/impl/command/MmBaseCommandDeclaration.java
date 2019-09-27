package org.minutenwerk.mimic4j.impl.command;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmExecutableMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseCommandDeclaration is an abstract base class for command mimics.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseCommandDeclaration extends MmBaseDeclaration<MmExecutableMimic, MmImplementationCommand>
  implements MmExecutableMimic, MmCommandCallback {

  /**
   * Creates a new MmBaseCommandDeclaration instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  protected MmBaseCommandDeclaration(MmDeclarationMimic pParent) {
    super(new MmImplementationCommand(pParent));
  }

  /**
   * Returns command button submit parameter.
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   *
   * @return  command button submit parameter.
   */
  @Override
  public String callbackMmGetSubmitParam(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  @Override
  public final String getMmSubmitParam() {
    return implementation.getMmSubmitParam();
  }

}
