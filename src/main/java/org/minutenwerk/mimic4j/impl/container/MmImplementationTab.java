package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeTab;

/**
 * MmImplementationTab is the specific class for the implementation part of tab mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationTab<MODEL> extends MmBaseContainerImplementation<MmTab<MODEL>, MODEL, MmConfigurationTab, MmTabAnnotation> {

  /**
   * Creates a new MmImplementationTab instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationTab(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Creates a new MmImplementationTab instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmImplementationTab(MmDeclarationMimic pParent, final MmRootAccessor<MODEL> pRootAccessor) {
    super(pParent, pRootAccessor);
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationTab onConstructConfiguration(MmTabAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationTab(pAnnotation);
    } else {
      return new MmConfigurationTab();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeTab<MODEL>(this);
  }

}
