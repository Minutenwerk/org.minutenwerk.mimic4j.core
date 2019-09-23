package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmTableColumn;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * Thymeleaf processor for {@code <th mm:tableColumn="${someMimic}">}.
 *
 * @author  Olaf Kossak
 */
public class MmTableColumnProcessor extends MmBaseProcessor<MmTableColumn<?>> {

  /**
   * Creates a new MmTableColumnProcessor instance.
   */
  public MmTableColumnProcessor() {
    super("th", "th");
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
    MmTableColumn<?> mimic = evaluateMimic(context, tag, out);
    processId(mimic, context, tag, out);
    LOGGER.info("table column id = " + mimic.getMmId());

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
  protected void processStyleClasses(MmTableColumn<?> mimic, ITemplateContext context, IProcessableElementTag tag,
    IElementTagStructureHandler out) {
    // TODO merge style classes without duplicates
    String outStyleClasses = mimic.getMmHeaderClasses();
    if (tag.hasAttribute("class")) {
      outStyleClasses = (tag.getAttributeValue("class") + " " + outStyleClasses).trim();
    }
    if (outStyleClasses.isEmpty()) {
      out.removeAttribute("class");
    } else {
      out.setAttribute("class", outStyleClasses, AttributeValueQuotes.DOUBLE);
    }
  }
}
