package org.minutenwerk.mimic4j.api.command;

import org.minutenwerk.mimic4j.api.MmCommandMimic;
import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.command.MmCommandCallback;
import org.minutenwerk.mimic4j.impl.command.MmImplementationCommand;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;

/**
 * MmCommand is the base class for mimics which execute an action. Commands are usually related to view buttons or view links.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts and view text label of link.
 *
 * @author  Olaf Kossak
 */
public class MmCommand<DATA_MODEL extends MmReferencableModel>
  extends MmBaseLinkDeclaration<MmImplementationCommand<DATA_MODEL>, DATA_MODEL, DATA_MODEL> implements MmCommandMimic<DATA_MODEL>,
    MmCommandCallback<DATA_MODEL> {

  /**
   * Enumeration of possible JSF tags of attribute in disabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmCommandJsfDisabled {

    SameAsEnabled,

    TextOutput,

    TextPlain;
  }

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmCommandJsfTag {

    CommandButton,

    Link;
  }

  /**
   * Creates a new MmCommand instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmCommand(MmDeclarationMimic pParent) {
    super(new MmImplementationCommand<DATA_MODEL>(pParent));
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
