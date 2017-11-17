package org.minutenwerk.mimic4j.api.link;

import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkDeclaration;
import org.minutenwerk.mimic4j.impl.link.MmImplementationLeporelloTab;

/**
 * MmLeporelloTab is a composite mimic to represent a single tab of a leporello panel.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public class MmLeporelloTab extends MmBaseLinkDeclaration<MmImplementationLeporelloTab> {

  /**
   * Enumeration of possible JSF tags of attribute in enabled state.
   *
   * @author   Olaf Kossak
   * @version  $Revision: 1123 $, $Date: 2017-04-13 21:36:12 +0200 (Do, 13 Apr 2017) $
   * @see      $HeadURL:http://saas1212sr.saas-secure.com/svn/saturn/org.minutenwerk.mimic4j.core/trunk/src/main/java/org/minutenwerk/mimic4j/api/link/MmLeporelloTab.java\$
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
   *
   * @since   $maven.project.version$
   */
  public final MmLeporelloPanel<?> getMmLeporelloPanel() {
    return (MmLeporelloPanel<?>)MmRelationshipApi.getMmParent(this);
  }

  /**
   * Returns true, if this leporello tab is selected or is a supertab of an active tab.
   *
   * @return  True, if this leporello tab is selected or is a supertab of an active tab.
   *
   * @since   $maven.project.version$
   */
  public final boolean isMmActive() {
    return this.implementation.isMmActive();
  }

  /**
   * Returns true, if this leporello tab is the selected one.
   *
   * @return  True, if this leporello tab is the selected one.
   *
   * @since   $maven.project.version$
   */
  public final boolean isMmSelected() {
    return this.implementation.isMmSelected();
  }

}
