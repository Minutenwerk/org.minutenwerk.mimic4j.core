package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloAnnotation;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLeporello;

/**
 * MmImplementationLeporello is the specific class for the implementation part of leporello mimics.
 *
 * @param   MODEL      The model containing the values to be set, cannot be null.
 * @param   SUB_MODEL  The sub model containing the values to be set, can be null.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLeporello<MODEL, SUB_MODEL>
  extends MmBaseContainerImplementation<MmLeporello<MODEL, SUB_MODEL>, MODEL, MmConfigurationLeporello, MmLeporelloAnnotation> {

  /**
   * Creates a new MmImplementationLeporello instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationLeporello(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Creates a new MmImplementationLeporello instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmImplementationLeporello(MmDeclarationMimic pParent, MmRootAccessor<MODEL> pRootAccessor) {
    super(pParent, pRootAccessor);
  }

  /**
   * Returns the currently selected leporello tab, may be null.
   *
   * @return  the currently selected leporello tab, may be null.
   */
  public MmLeporelloTab<?, ?> getMmSelectedTab() {
    assureInitialization();

    return declaration.callbackMmGetSelectedTab();
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationLeporello onConstructConfiguration(MmLeporelloAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLeporello(pAnnotation);
    } else {
      return new MmConfigurationLeporello();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeLeporello(this);
  }

}
