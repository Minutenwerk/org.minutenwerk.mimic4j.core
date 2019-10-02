package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;

/**
 * Thymeleaf processor for {@code <div mm:leporello="${someMimic}">}.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  process, leporello, panel, tab
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
          mmContext.model.add(mmContext.factory.createText(panel.getMmShortDescription()));
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
      mmContext.model.add(mmContext.factory.createText(tab.getMmViewValue()));
      closeSameLine(mmContext, "a");
    }
    close(mmContext, "li");
  }
}
