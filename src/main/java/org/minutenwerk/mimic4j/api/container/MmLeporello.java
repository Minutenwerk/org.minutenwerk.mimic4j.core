package org.minutenwerk.mimic4j.api.container;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;
import org.minutenwerk.mimic4j.impl.container.MmLeporelloCallback;

/**
 * MmLeporello is a composite mimic to represent a leporello of panels.
 *
 * @param   MODEL      The model containing the values to be set, cannot be null.
 * @param   SUB_MODEL  The sub model containing the values to be set, can be null.
 *
 * @author  Olaf Kossak
 */
@Deprecated
public abstract class MmLeporello<MODEL, SUB_MODEL> extends MmBaseContainerDeclaration<MODEL, MmImplementationLeporello<MODEL, SUB_MODEL>>
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
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLeporello(MmDeclarationMimic pParent) {
    super(new MmImplementationLeporello<MODEL, SUB_MODEL>(pParent));
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

}
