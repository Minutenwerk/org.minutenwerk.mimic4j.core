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
public class MmDivProcessor extends MmBaseProcessor<MmBaseDeclaration<?, ?>> {

  /**
   * Creates a new MmTableRowProcessor instance.
   */
  public MmDivProcessor() {
    super("div", "div");
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
    LOGGER.info("div id = " + mimic.getMmId());

    processStyleClasses(mimic, context, tag, out);
    out.setSelectionTarget(mimic.getToJsf());

    if ((mimic instanceof MmAttributeMimic) || (mimic instanceof MmLinkMimic<?, ?>)) {
      out.setAttribute("th:title", "*{title}", AttributeValueQuotes.DOUBLE);
      out.setAttribute("th:text", "*{value}", AttributeValueQuotes.DOUBLE);
    }

    out.removeAttribute(getPrefixedAttributeName());
  }
}
