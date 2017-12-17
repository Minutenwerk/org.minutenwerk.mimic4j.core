package org.minutenwerk.mimic4j.api.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTab;

/**
 * MmTab is a container mimic to represent a dialog tab.
 *
 * @author  Olaf Kossak
 */
public abstract class MmTab<MODEL> extends MmBaseContainerDeclaration<MODEL, MmImplementationTab<MODEL>> {

  /**
   * Creates a new MmTab instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmTab(MmDeclarationMimic pParent) {
    super(new MmImplementationTab<MODEL>(pParent));
  }
}
