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

    processId(mmContext);
    LOGGER.info("leporello id = " + leporello.getMmId());

    addLeporello(mmContext);
    mmContext.out.setBody(mmContext.model, true);
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   *
   * @jalopy.group  leporello
   */
  protected void addLeporello(final MmContext mmContext) {
    setAttributes(mmContext, "class", "panel-group");
    {
      for (MmLeporelloPanel<?> panel : mmContext.mimic.getMmLeporelloPanels()) {
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
    open(mmContext, "div", "id", panel.getMmId(), "class", "panel");
    {
      open(mmContext, "div", "id", panel.getMmId() + "_hd", "class", "panel-heading");
      {
        open(mmContext, "h3", "class", "panel-title");
        {
          open(mmContext, "a", "data-toggle", "collapse", "data-parent", ".panel-group", "href", "#" + panel.getMmId() + "_bo");
          mmContext.model.add(mmContext.factory.createText("some"));
          closeSameLine(mmContext, "a");
        }
        close(mmContext, "h3");
      }
      close(mmContext, "div");
      open(mmContext, "div", "id", panel.getMmId() + "_bo", "class", "panel-collapse collapse");
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
    open(mmContext, "li", "id", tab.getMmId());
    {
      open(mmContext, "a", "href", "#");
      mmContext.model.add(mmContext.factory.createText(tab.getMmShortDescription()));
      closeSameLine(mmContext, "a");
    }
    close(mmContext, "li");
  }
}
