package org.minutenwerk.mimic4j.impl.command;

import org.minutenwerk.mimic4j.api.command.MmCommand;
import org.minutenwerk.mimic4j.api.command.MmCommandAnnotation;
import org.minutenwerk.mimic4j.api.mimic.MmCommandMimic;
import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkImplementation;

/**
 * MmImplementationCommand is the specific class for the implementation part of command mimics.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts and view text label of link.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationCommand<DATA_MODEL extends MmReferenceableModel>
  extends MmBaseLinkImplementation<MmCommand<DATA_MODEL>, DATA_MODEL, DATA_MODEL, MmConfigurationCommand, MmCommandAnnotation>
  implements MmCommandMimic<DATA_MODEL> {

  /**
   * Creates a new MmImplementationCommand instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationCommand(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns command button submit parameter.
   *
   * @return  command button submit parameter.
   */
  @Override
  @SuppressWarnings("unchecked")
  public String getMmSubmitParam() {
    ensureInitialization();

    return ((MmCommandCallback<DATA_MODEL>)declaration).callbackMmGetSubmitParam(configuration.getSubmitParam());
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationCommand onConstructConfiguration(MmCommandAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationCommand(pAnnotation);
    } else {
      return new MmConfigurationCommand();
    }
  }

}
