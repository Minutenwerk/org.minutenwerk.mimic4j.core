package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmContainerMimic;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.container.MmLeporello;
import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
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
   * @param         mmContext   TODOC
   * @param         panelTable  TODOC
   *
   * @jalopy.group  panelTab
   */
  protected void addLeporelloPanelTab(final MmContext mmContext, final MmTable<?> panelTable) {
    open(mmContext, "table", "id", panelTable.getMmId(), "class", panelTable.getMmStyleClasses());
    {
      open(mmContext, "thead");
      {
        open(mmContext, "tr");
        {
          for (MmTableColumn<?> tableColumn : panelTable.getMmTableColumns()) {
            open(mmContext, "th", "id", tableColumn.getMmId(), "class", tableColumn.getMmStyleClasses());
            {
              text(mmContext, tableColumn.getMmShortDescription());
            }
            close(mmContext, "th");
          }
        }
        close(mmContext, "tr");
      }
      close(mmContext, "thead");
      open(mmContext, "tbody");
      {
        for (MmTableRow<?> tableRow : panelTable.getMmTableRows()) {
          open(mmContext, "tr", "id", panelTable.getMmId() + "-row");
          {
            for (MmMimic tableCell : tableRow.getMmTableCells()) {
              if (tableCell instanceof MmAttributeMimic<?, ?>) {
                final MmAttributeMimic<?, ?> attribute = (MmAttributeMimic<?, ?>)tableCell;
                open(mmContext, "td", "id", attribute.getMmId(), "title", attribute.getMmLongDescription());
                {
                  text(mmContext, (String)attribute.getMmViewValue());
                }
                close(mmContext, "td");
              } else if (tableCell instanceof MmLinkMimic<?, ?>) {
                open(mmContext, "td", "class", tableCell.getMmStyleClasses());
                {
                  final MmLinkMimic<?, ?> link = (MmLinkMimic<?, ?>)tableCell;
                  open(mmContext, "a", "id", link.getMmId(), "title", link.getMmLongDescription(), "href",
                    link.getMmTargetReference().toString());
                  {
                    text(mmContext, (String)link.getMmViewValue());
                  }
                  closeSameLine(mmContext, "a");
                }
                close(mmContext, "td");
              }
            }
          }
          close(mmContext, "tr");
        }
      }
      close(mmContext, "tbody");
    }
    close(mmContext, "table");
  }

  /**
   * TODOC.
   *
   * @param         mmContext  TODOC
   * @param         panelTab   TODOC
   *
   * @jalopy.group  panelTab
   */
  protected void addLeporelloPanelTab(final MmContext mmContext, final MmContainerMimic<?> panelTab) {
    if (panelTab instanceof MmTab<?>) {
      addLeporelloPanelTab(mmContext, (MmTab<?>)panelTab);
    } else if (panelTab instanceof MmTable<?>) {
      addLeporelloPanelTab(mmContext, (MmTable<?>)panelTab);
    }
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
          addLeporelloPanelTabLink(mmContext, (MmLinkMimic<?, ?>)child);
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
  protected void addLeporelloPanelTabLink(final MmContext mmContext, final MmLinkMimic<?, ?> mmLink) {
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
