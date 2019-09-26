package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLeporelloTab;

/**
 * MmLeporelloTab is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the text
 * label of the link. In most cases the two models are the same.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param   <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author  Olaf Kossak
 */
public class MmLeporelloTab<DATA_MODEL extends MmReferencableModel, VIEW_MODEL>
  extends MmBaseLinkDeclaration<MmImplementationLeporelloTab<DATA_MODEL, VIEW_MODEL>, DATA_MODEL, VIEW_MODEL> {

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
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmLeporelloTab(MmLeporelloPanel<?> pParent) {
    this(pParent, null);
  }

  /**
   * Creates a new MmLeporelloTab instance.
   *
   * @param  pParent    The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pSuperTab  The super tab of this leporello tab.
   */
  public MmLeporelloTab(MmLeporelloPanel<?> pParent, MmLeporelloTab<DATA_MODEL, VIEW_MODEL> pSuperTab) {
    super(new MmImplementationLeporelloTab<DATA_MODEL, VIEW_MODEL>(pParent, pSuperTab));
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
    return implementation.isMmActive();
  }

  /**
   * Returns true, if this leporello tab is the selected one.
   *
   * @return  True, if this leporello tab is the selected one.
   */
  public final boolean isMmSelected() {
    return implementation.isMmSelected();
  }

}
