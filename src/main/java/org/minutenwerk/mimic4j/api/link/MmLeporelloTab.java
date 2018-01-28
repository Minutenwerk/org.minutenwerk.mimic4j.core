package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLeporelloTab;

/**
 * MmLeporelloTab is a composite mimic to represent a single tab of a leporello panel.
 *
 * @author  Olaf Kossak
 */
public class MmLeporelloTab extends MmBaseLinkDeclaration<MmImplementationLeporelloTab> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author  Olaf Kossak
   */
  public enum MmLeporelloTabJsfTag {

    LeporelloTab;
  }

  /**
   * Creates a new MmLeporelloTab instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmLeporelloTab(MmLeporelloPanel<?> pParent) {
    this(pParent, null);
  }

  /**
   * Creates a new MmLeporelloTab instance.
   *
   * @param  pParent    The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pSuperTab  The super tab of this leporello tab.
   */
  public MmLeporelloTab(MmLeporelloPanel<?> pParent, MmLeporelloTab pSuperTab) {
    super(new MmImplementationLeporelloTab(pParent, pSuperTab));
  }

  /**
   * Returns the parent leporello panel of this leporello tab.
   *
   * @return  The parent leporello panel of this leporello tab.
   */
  public final MmLeporelloPanel<?> getMmLeporelloPanel() {
    return (MmLeporelloPanel<?>)MmRelationshipApi.getMmParent(this);
  }

  /**
   * Returns true, if this leporello tab is selected or is a supertab of an active tab.
   *
   * @return  True, if this leporello tab is selected or is a supertab of an active tab.
   */
  public final boolean isMmActive() {
    return this.implementation.isMmActive();
  }

  /**
   * Returns true, if this leporello tab is the selected one.
   *
   * @return  True, if this leporello tab is the selected one.
   */
  public final boolean isMmSelected() {
    return this.implementation.isMmSelected();
  }

}
