package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;

/**
 * Thymeleaf processor for {@code <div mm:leporello="${someMimic}">}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  process, leporello, panel, tab, panelTab, attribute
 */
public class MmLeporelloProcessor extends MmBaseProcessor<MmLeporello<?, ?>> {

  /**
   * Creates a new MmLeporelloProcessor instance.
   */
  public MmLeporelloProcessor() {
    super("div", "leporello");
  }

  /**
   * TODOC.
   *
   * @param         mmContext  context TODOC
   *
   * @jalopy.group  process
   */
  @Override
  protected void doProcess(final MmContext mmContext) {
    MmLeporello<?, ?> leporello = mmContext.mimic;
    addLeporello(mmContext, leporello);
    mmContext.out.setBody(mmContext.model, true);
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   * @param         leporello  TODOC
   *
   * @jalopy.group  leporello
   */
  protected void addLeporello(final MmContext mmContext, final MmLeporello<?, ?> leporello) {
    setAttributes(mmContext, "id", leporello.getMmId(), "class", "panel-group " + leporello.getMmStyleClasses());
    {
      for (MmLeporelloPanel<?> panel : leporello.getMmLeporelloPanels()) {
        addLeporelloPanel(mmContext, panel);
      }
    }
    mmContext.close();
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   * @param         panel      TODOC
   *
   * @jalopy.group  panel
   */
  protected void addLeporelloPanel(final MmContext mmContext, final MmLeporelloPanel<?> panel) {
    open(mmContext, "div", "id", panel.getMmId(), "class", "panel " + panel.getMmStyleClasses());
    {
      open(mmContext, "div", "id", panel.getMmId() + "_hd", "class", "panel-heading");
      {
        open(mmContext, "h3", "class", "panel-title");
        {
          open(mmContext, "a", "data-toggle", "collapse", "data-parent", ".panel-group", "title", panel.getMmLongDescription(), "href",
            "#" + panel.getMmId() + "_bo");
          text(mmContext, panel.getMmShortDescription());
          closeSameLine(mmContext, "a");
        }
        close(mmContext, "h3");
      }
      close(mmContext, "div");
      open(mmContext, "div", "id", panel.getMmId() + "_bo", "class", "panel-collapse collapse" + panel.getMmStyleInitiallyOpen());
      {
        open(mmContext, "div", "class", "panel-body");
        {
          open(mmContext, "ul", "class", "nav nav-tabs hidden-print");
          {
            for (MmLeporelloTab<?, ?> tab : panel.getMmLeporelloTabs()) {
              addLeporelloTab(mmContext, tab);
            }
          }
          close(mmContext, "ul");
        }
        addLeporelloPanelTab(mmContext, panel.getMmPanelTab());
        close(mmContext, "div");
      }
      close(mmContext, "div");
    }
    close(mmContext, "div");
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   * @param         tab        panel TODOC
   *
   * @jalopy.group  tab
   */
  protected void addLeporelloTab(final MmContext mmContext, final MmLeporelloTab<?, ?> tab) {
    open(mmContext, "li", "id", tab.getMmId() + "_li", "class", tab.getMmStyleClasses() + " " + tab.getMmStyleClassActive());
    {
      open(mmContext, "a", "id", tab.getMmId(), "title", tab.getMmLongDescription(), "href", tab.getMmTargetReference().toString());
      text(mmContext, tab.getMmViewValue());
      closeSameLine(mmContext, "a");
    }
    close(mmContext, "li");
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   * @param         panelTab   TODOC
   *
   * @jalopy.group  panelTab
   */
  protected void addLeporelloPanelTab(final MmContext mmContext, final MmTab<?> panelTab) {
    open(mmContext, "div", "id", panelTab.getMmId(), "class", panelTab.getMmStyleClasses());
    {
      for (MmMimic child : panelTab.getMmTabChildren()) {
        if (child instanceof MmAttributeMimic<?, ?>) {
          addLeporelloPanelTabAttribute(mmContext, (MmAttributeMimic<?, ?>)child);
        } else if (child instanceof MmLinkMimic<?, ?>) {
          addLeporelloPanelTabAttribute(mmContext, (MmLinkMimic<?, ?>)child);
        }
      }
    }
    close(mmContext, "div");
  }

  /**
   * TODOC.
   *
   * @param         mmContext    TODOC
   * @param         mmAttribute  TODOC
   *
   * @jalopy.group  attribute
   */
  protected void addLeporelloPanelTabAttribute(final MmContext mmContext, final MmAttributeMimic<?, ?> mmAttribute) {
    open(mmContext, "div", "class", "form-group");
    {
      open(mmContext, "label", "for", mmAttribute.getMmId(), "title", mmAttribute.getMmLongDescription(), "class",
        "col-lg-2 control-label");
      {
        text(mmContext, mmAttribute.getMmShortDescription());
      }
      close(mmContext, "label");
      open(mmContext, "div", "class", "col-lg-4");
      {
        open(mmContext, "input", "type", "text", "id", mmAttribute.getMmId(), "disabled", "disabled", "class", "form-control", "value",
          (String)mmAttribute.getMmViewValue());
        closeSameLine(mmContext, "input");
      }
      close(mmContext, "div");
    }
    close(mmContext, "div");
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   * @param         mmLink     TODOC
   *
   * @jalopy.group  attribute
   */
  protected void addLeporelloPanelTabAttribute(final MmContext mmContext, final MmLinkMimic<?, ?> mmLink) {
    open(mmContext, "div", "class", "form-group");
    {
      open(mmContext, "label", "for", mmLink.getMmId(), "class", "col-lg-2 control-label");
      {
        text(mmContext, mmLink.getMmShortDescription());
      }
      close(mmContext, "label");
      open(mmContext, "div", "class", "col-lg-4");
      {
        open(mmContext, "a", "id", mmLink.getMmId(), "title", mmLink.getMmLongDescription(), "class", "form-control");
        {
          text(mmContext, (String)mmLink.getMmViewValue());
        }
        closeSameLine(mmContext, "a");
      }
      close(mmContext, "div");
    }
    close(mmContext, "div");
  }
}
