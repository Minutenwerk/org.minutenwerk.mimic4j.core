package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

import org.thymeleaf.model.AttributeValueQuotes;

/**
 * Thymeleaf processor for {@code <tr mm:tableRow="${someMimic}">}.
 *
 * @author  Olaf Kossak
 */
public class MmTableCellTextProcessor extends MmBaseProcessor<MmBaseDeclaration<?, ?>> {

  /**
   * Creates a new MmTableRowProcessor instance.
   */
  public MmTableCellTextProcessor() {
    super("div", "div");
  }

  /**
   * TODOC.
   *
   * @param  mmContext  context TODOC
   */
  @Override
  protected void doProcess(final MmContext mmContext) {
    MmBaseDeclaration<?, ?> mimic = mmContext.mimic;

    processId(mmContext);
    LOGGER.info("div id = " + mimic.getMmId());

    processStyleClasses(mmContext);
    mmContext.out.setSelectionTarget(mimic.getToJsf());

    if ((mimic instanceof MmAttributeMimic) || (mimic instanceof MmLinkMimic<?, ?>)) {
      mmContext.out.setAttribute("th:title", "*{title}", AttributeValueQuotes.DOUBLE);
      mmContext.out.setAttribute("th:text", "*{value}", AttributeValueQuotes.DOUBLE);
    }

    mmContext.out.removeAttribute(getPrefixedAttributeName());
  }
}
