package org.minutenwerk.mimic4j.impl.container;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanelAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;

/**
 * MmImplementationLeporelloPanel is the specific class for the implementation part of leporello mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLeporelloPanel<MODEL>
  extends MmBaseContainerImplementation<MmLeporelloPanel<MODEL>, MODEL, MmConfigurationLeporelloPanel, MmLeporelloPanelAnnotation> {

  /** Reverse index of this panel, last panel's index is 1, first panel's index is count of panels. */
  protected Integer reverseIndex;

  /**
   * Creates a new MmImplementationLeporelloPanel instance.
   *
   * @param  pParent  The parent declaration mimic, containing a public final declaration of this mimic.
   */
  public MmImplementationLeporelloPanel(MmLeporello<?, ?> pParent) {
    super(pParent);
  }

  /**
   * Returns CSS selector for data parents of leporello panel, like in (data-toggle="collapse" data-target="#target1,#target2,#target3"). If
   * leporello contains panels (panel1, panel2, panel3, panel4, panel5) this method returns all siblings without this panel and its
   * follower, e.g. for panel3 it will return "#panel1,#panel2,#panel5".
   *
   * @return  The CSS selector for data parents of leporello panel.
   */
  // TODO is unused
  public String getMmDataParents() {
    String  returnString = "";
    boolean skipNext     = false;
    for (MmMimic child : ((MmImplementationLeporello<?, ?>)implementationParent).getMmChildren()) {
      if (child instanceof MmLeporelloPanel) {
        final MmLeporelloPanel<?> panel = (MmLeporelloPanel<?>)child;
        if (skipNext) {
          skipNext = false;
        } else if (panel == declaration) {
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
   * Returns leporello panel tab.
   *
   * @return  leporello panel tab.
   */
  public MmTab<?> getMmPanelTab() {
    assureInitialization();

    return declaration.getMmPanelTab();
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
    assureInitialization();

    return declaration.callbackMmGetStyleClasses("leporello-panel-" + getReverseIndex());
  }

  /**
   * Returns the CSS style class for initial opening of leporello panel.
   *
   * @return  The CSS style class for initial opening of leporello panel.
   */
  public String getMmStyleInitiallyOpen() {
    assureInitialization();

    return (getReverseIndex() <= 2) ? " in" : "";
  }

  /**
   * Returns reverse index of this panel, last panel's index is 1, first panel's index is count of panels.
   *
   * @return  Reverse index of this panel, last panel's index is 1, first panel's index is count of panels.
   */
  protected int getReverseIndex() {
    if (reverseIndex == null) {
      @SuppressWarnings("rawtypes")
      List<MmImplementationLeporelloPanel> panels    = ((MmImplementationLeporello<?, ?>)implementationParent)
          .getDirectImplementationChildrenOfType(MmImplementationLeporelloPanel.class);
      int                                  countDown = panels.size();
      for (MmImplementationLeporelloPanel<?> panel : panels) {
        panel.reverseIndex = countDown;
        countDown--;
      }
    }
    return reverseIndex;
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationLeporelloPanel onConstructConfiguration(MmLeporelloPanelAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLeporelloPanel(pAnnotation);
    } else {
      return new MmConfigurationLeporelloPanel();
    }
  }
}
