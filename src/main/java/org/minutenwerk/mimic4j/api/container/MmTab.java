package org.minutenwerk.mimic4j.api.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationTab;

/**
 * MmTab is a container mimic to represent a dialog tab.
 *
 * @author  Olaf Kossak
 */
public class MmTab<MODEL> extends MmBaseContainerDeclaration<MmImplementationTab<MODEL>, MODEL> {

  /**
   * Creates a new MmTab instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmTab(MmDeclarationMimic pParent) {
    super(new MmImplementationTab<MODEL>(pParent));
  }

  /**
   * Creates a new MmTab instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmTab(MmDeclarationMimic pParent, final MmRootAccessor<MODEL> pRootAccessor) {
    super(new MmImplementationTab<MODEL>(pParent, pRootAccessor));
  }
}
