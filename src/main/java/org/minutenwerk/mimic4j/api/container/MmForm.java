package org.minutenwerk.mimic4j.api.container;

import org.minutenwerk.mimic4j.api.mimic.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationForm;

/**
 * MmForm is a container mimic to represent a HTML form containing editable attributes.
 *
 * @author  Olaf Kossak
 */
public abstract class MmForm<MODEL> extends MmBaseContainerDeclaration<MmImplementationForm<MODEL>, MODEL> {

  /**
   * Creates a new MmForm instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmForm(MmDeclarationMimic pParent) {
    super(new MmImplementationForm<MODEL>(pParent));
  }

}
