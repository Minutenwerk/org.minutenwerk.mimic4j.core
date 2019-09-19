package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.MmAttributeMimic;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * Thymeleaf processor for {@code <tr mm:tableRow="${someMimic}">}.
 *
 * @author  Olaf Kossak
 */
public class MmTableCellProcessor extends MmBaseProcessor<MmBaseDeclaration<?, ?>> {

  /**
   * Creates a new MmTableRowProcessor instance.
   */
  public MmTableCellProcessor() {
    super("td", "tableCell");
  }

  /**
   * TODOC.
   *
   * @param  context  TODOC
   * @param  tag      TODOC
   * @param  out      TODOC
   */
  @Override
  protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler out) {
    MmBaseDeclaration<?, ?> mimic = evaluateMimic(context, tag, out);
    processId(mimic, context, tag, out);
    LOGGER.info("table cell id = " + mimic.getMmId());

    processStyleClasses(mimic, context, tag, out);
    // out.setSelectionTarget(mimic);

    if (mimic instanceof MmAttributeMimic) {
      out.setAttribute("th:text", "*{" + mimic.getMmName() + ".toJsf.value}", AttributeValueQuotes.DOUBLE);
    } else if (mimic instanceof MmLinkMimic<?, ?>) {
      out.setAttribute("th:text", "*{" + mimic.getMmName() + ".toJsf.value}", AttributeValueQuotes.DOUBLE);
    }

    out.removeAttribute(getPrefixedAttributeName());
  }
}
