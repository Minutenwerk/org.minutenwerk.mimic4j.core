package org.minutenwerk.mimic4j.api.container;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporelloPanel;

/**
 * MmLeporelloPanel is a container mimic to represent a leporello of tabsets.
 *
 * @author  Olaf Kossak
 */
public abstract class MmLeporelloPanel<MODEL> extends MmBaseContainerDeclaration<MODEL, MmImplementationLeporelloPanel<MODEL>> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmLeporelloPanelJsfTag {

    LeporelloPanel;
  }

  /**
   * Creates a new MmTabSet instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLeporelloPanel(final MmLeporello<?, ?> pParent) {
    super(new MmImplementationLeporelloPanel<MODEL>(pParent));
  }

  /**
   * Returns the parent leporello of this leporello panel.
   *
   * @return  The parent leporello of this leporello panel.
   */
  public final MmLeporello<?, ?> getMmLeporello() {
    return (MmLeporello<?, ?>)MmRelationshipApi.getMmParent(this);
  }

  /**
   * Returns a list of all leporello tabs inside this leporello panel.
   *
   * @return  A list of all leporello tabs inside this leporello panel.
   */
  public List<MmLeporelloTab<?, ?>> getMmLeporelloTabs() {
    final List<MmLeporelloTab<?, ?>> tabs = new ArrayList<>();
    for (MmMimic child : MmRelationshipApi.getMmChildren(this)) {
      if (child instanceof MmLeporelloTab) {
        tabs.add((MmLeporelloTab<?, ?>)child);
      }
    }
    return tabs;
  }

  /**
   * Returns the leporello panel's viewside value of type String.
   *
   * @return  The leporello panel's viewside value of type String.
   */
  public String getMmViewsideValue() {
    return implementation.getMmViewsideValue();
  }

}
