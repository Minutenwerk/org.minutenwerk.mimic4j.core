package org.minutenwerk.mimic4j.api.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;
import org.minutenwerk.mimic4j.impl.container.MmLeporelloCallback;

/**
 * MmLeporello is a container mimic to represent a leporello of panels.
 *
 * @param   MODEL      The model containing the values to be set, cannot be null.
 * @param   SUB_MODEL  The sub model containing the values to be set, can be null.
 *
 * @author  Olaf Kossak
 */
public abstract class MmLeporello<MODEL, SUB_MODEL> extends MmBaseContainerDeclaration<MmImplementationLeporello<MODEL, SUB_MODEL>, MODEL>
  implements MmLeporelloCallback<MODEL, SUB_MODEL> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmLeporelloJsfTag {

    Leporello;
  }

  /**
   * Creates a new MmLeporelloPanel instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmLeporello(MmDeclarationMimic pParent) {
    super(new MmImplementationLeporello<MODEL, SUB_MODEL>(pParent));
  }

  /**
   * Creates a new MmLeporello instance.
   *
   * @param  pParent        The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pRootAccessor  This component has a model. The model is part of a model tree. The model tree has a root model. The root model
   *                        has a root accessor.
   */
  public MmLeporello(MmDeclarationMimic pParent, MmRootAccessor<MODEL> pRootAccessor) {
    super(new MmImplementationLeporello<MODEL, SUB_MODEL>(pParent, pRootAccessor));
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  @Override
  public MmLeporelloTab<?, ?> callbackMmGetSelectedTab() {
    return null;
  }

  /**
   * Returns a list of all leporello panels inside this leporello.
   *
   * @return  A list of all leporello panels inside this leporello.
   */
  public List<MmLeporelloPanel<?>> getMmLeporelloPanels() {
    final List<MmLeporelloPanel<?>> panels = new ArrayList<>();
    for (MmMimic child : MmRelationshipApi.getMmChildren(this)) {
      if (child instanceof MmLeporelloPanel<?>) {
        panels.add((MmLeporelloPanel<?>)child);
      }
    }
    return panels;
  }

  /**
   * Set specified locale.
   *
   * @param  pLocale  The specified locale.
   */
  public void setMmLocale(Locale pLocale) {
    implementation.setMmLocale(pLocale);
  }

}
