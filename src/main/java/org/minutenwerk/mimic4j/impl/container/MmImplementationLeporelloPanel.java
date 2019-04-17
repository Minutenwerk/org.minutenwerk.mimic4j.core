package org.minutenwerk.mimic4j.impl.container;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanelAnnotation;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLeporelloPanel;

/**
 * MmImplementationLeporelloPanel is the specific class for the implementation part of leporello mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLeporelloPanel<MODEL>
  extends MmBaseContainerImplementation<MmLeporelloPanel<MODEL>, MODEL, MmConfigurationLeporelloPanel> {

  /**
   * Creates a new MmImplementationLeporelloPanel instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLeporelloPanel(MmLeporello<?, ?> pParent) {
    super(pParent);
  }

  /**
   * Returns CSS selector for data parents of leporello panel, like in (data-toggle="collapse" data-target="#target1,#target2,#target3"). If
   * leporello contains panels (panel1, pnale2, panel3, panel4, panel5) this method returns all siblings without this panel and its
   * follower, e.g. for panel3 it will return "#panel1,#panel2,#panel5".
   *
   * @return  The CSS selector for data parents of leporello panel.
   */
  public String getMmDataParents() {
    String  returnString = "";
    boolean skipNext     = false;
    for (MmMimic child : this.getMmImplementationLeporello().getMmChildren()) {
      if (child instanceof MmLeporelloPanel) {
        final MmLeporelloPanel<?> panel = (MmLeporelloPanel<?>)child;
        if (skipNext) {
          skipNext = false;
        } else if (panel == this.declaration) {
          skipNext = true;
        } else {
          if (returnString.isEmpty()) {
            returnString = returnString + "#" + panel.getMmId();
          } else {
            returnString = returnString + ", #" + panel.getMmId();
          }
        }
      }
    }
    return returnString;
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmStyleClasses() {
    this.ensureInitialization();

    final int    reverseIndex = this.getReverseIndex();
    final String returnString = this.declaration.callbackMmGetStyleClasses("leporello-panel-" + reverseIndex);
    if (returnString == null) {
      return "";
    } else {
      return returnString;
    }
  }

  /**
   * Returns the CSS style class for initial opening of leporello panel.
   *
   * @return  The CSS style class for initial opening of leporello panel.
   */
  public String getMmStyleInitiallyOpen() {
    this.ensureInitialization();
    if (this.getReverseIndex() <= 2) {
      return "in";
    } else {
      return "";
    }
  }

  /**
   * Returns the leporello panel's viewside value of type String.
   *
   * @return  The leporello panel's viewside value of type String.
   */
  public String getMmViewsideValue() {
    // TODO MmImplementationLeporelloPanel.getMmViewsideValue()
    return this.getMmShortDescription();
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeLeporelloPanel(this);
  }

  /**
   * TODOC.
   *
   * @return  TODOC
   */
  protected MmImplementationLeporello<?, ?> getMmImplementationLeporello() {
    return (MmImplementationLeporello<?, ?>)this.implementationParent;
  }

  /**
   * Returns the reverse index of this panel, which means the last panel's index is 1, the first panel's index is the count of all panels.
   *
   * @return  The reverse index of this panel.
   */
  protected int getReverseIndex() {
    final MmLeporelloTab      selectedTab   = this.getMmImplementationLeporello().getMmSelectedTab();
    final MmLeporelloPanel<?> selectedPanel = selectedTab.getMmLeporelloPanel();
    int                       index         = 0;
    int                       count         = 0;
    boolean                   found         = false;
    for (MmMimic mimic : this.getMmImplementationLeporello().getMmChildren()) {
      if (mimic instanceof MmLeporelloPanel<?>) {
        count++;
        if (mimic == this.declaration) {
          index++;
          found = true;
        }
        if (!found) {
          index++;
        }
        if (mimic == selectedPanel) {
          break;
        }
      }
    }

    return count - index + 1;
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override
  protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmLeporelloPanelAnnotation.class);

    MmLeporelloPanelAnnotation annotation = this.findAnnotation(this.declaration, MmLeporelloPanelAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationLeporelloPanel();
    } else {
      this.configuration = new MmConfigurationLeporelloPanel(annotation);
    }
  }

}
