package org.minutenwerk.mimic4j.api.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationForm;

/**
 * MmForm is a container mimic to represent a HTML form containing editable attributes.
 *
 * @author  Olaf Kossak
 */
public abstract class MmForm<MODEL> extends MmBaseContainerDeclaration<MODEL, MmImplementationForm<MODEL>> {

  /**
   * Creates a new MmForm instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmForm(MmDeclarationMimic pParent) {
    super(new MmImplementationForm<MODEL>(pParent));
  }

}
