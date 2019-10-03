package org.minutenwerk.mimic4j.impl.link;

import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTabAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;

/**
 * MmImplementationLeporelloTab is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model
 * delivers the text label of the link. In most cases the two models are the same.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization
 */
public class MmImplementationLeporelloTab<DATA_MODEL extends MmReferencableModel, VIEW_MODEL>
  extends MmBaseLinkImplementation<MmLeporelloTab<DATA_MODEL, VIEW_MODEL>,
    DATA_MODEL, VIEW_MODEL, MmConfigurationLeporelloTab, MmLeporelloTabAnnotation> {

  /** The super tab is the logical parent tab in the parent panel. */
  protected final MmLeporelloTab<DATA_MODEL, VIEW_MODEL> superTab;

  /** The parent leporello of this leporello tab. */
  protected MmImplementationLeporello<?, ?>              parentLeporello;

  /**
   * Creates a new MmImplementationLeporelloTab instance.
   *
   * @param  pParentPanel  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationLeporelloTab(MmLeporelloPanel<?> pParentPanel) {
    this(pParentPanel, null);
  }

  /**
   * Creates a new MmImplementationLeporelloTab instance.
   *
   * @param  pParentPanel  The parent declaration mimic, containing a public final declaration of this mimic.
   * @param  pSuperTab     The super tab is the logical parent tab in the parent panel.
   */
  public MmImplementationLeporelloTab(MmLeporelloPanel<?> pParentPanel, MmLeporelloTab<DATA_MODEL, VIEW_MODEL> pSuperTab) {
    super(pParentPanel);
    superTab        = pSuperTab;
    parentLeporello = getMmImplementationAncestorOfType(MmImplementationLeporello.class);
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param         pAnnotation  The specified annotation, may be null.
   *
   * @return        Configuration of this mimic.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected MmConfigurationLeporelloTab onConstructConfiguration(MmLeporelloTabAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLeporelloTab(pAnnotation);
    } else {
      return new MmConfigurationLeporelloTab();
    }
  }

  /**
   * Returns the CSS style class if this leporello tab is active.
   *
   * @return  The CSS style class if this leporello tab is active.
   */
  public String getMmStyleClassActive() {
    assureInitialization();

    return isMmActive() ? "active" : "";
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   */
  @Override
  public String getMmStyleClasses() {
    assureInitialization();

    return isMmEnabled() ? super.getMmStyleClasses() : ("disabled " + super.getMmStyleClasses());
  }

  /**
   * Returns <code>true</code>, if the tab is active.
   *
   * @return  <code>True</code>, if the tab is active.
   */
  // TODO isMmActive
  public boolean isMmActive() {
    assureInitialization();

    return isMmSelected();
  }

  /**
   * Returns <code>true</code>, if the tab is selected.
   *
   * @return  <code>True</code>, if the tab is selected.
   */
  public boolean isMmSelected() {
    assureInitialization();

    return parentLeporello.getMmReferencePath().getPath().equals(getConfiguration().targetReferencePath.getPath());
  }

}
