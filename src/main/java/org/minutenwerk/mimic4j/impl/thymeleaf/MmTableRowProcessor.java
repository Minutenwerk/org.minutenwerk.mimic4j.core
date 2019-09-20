package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmTableRow;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * Thymeleaf processor for {@code <tr mm:tableRow="${someMimic}">}.
 *
 * @author  Olaf Kossak
 */
public class MmTableRowProcessor extends MmBaseProcessor<MmTableRow<?>> {

  /**
   * Creates a new MmTableRowProcessor instance.
   */
  public MmTableRowProcessor() {
    super("tr", "tr");
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
    MmTableRow<?> mimic = evaluateMimic(context, tag, out);
    processId(mimic, context, tag, out);
    // LOGGER.info("table row id = " + mimic.getMmId());

    processStyleClasses(mimic, context, tag, out);
    out.setSelectionTarget(mimic);
    out.removeAttribute(getPrefixedAttributeName());
  }

  /**
   * TODOC.
   *
   * @param  mimic    TODOC
   * @param  context  TODOC
   * @param  tag      TODOC
   * @param  out      TODOC
   */
  @Override
  protected void processId(MmTableRow<?> mimic, ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler out) {
    String externalId  = tag.getAttributeValue("id");
    String evaluatedId = mimic.getToJsf().evalId(externalId);

    // evaluatedId = evaluatedId + mimic.getMmRowIndex();
    out.setAttribute("id", evaluatedId, AttributeValueQuotes.DOUBLE);
  }

}
