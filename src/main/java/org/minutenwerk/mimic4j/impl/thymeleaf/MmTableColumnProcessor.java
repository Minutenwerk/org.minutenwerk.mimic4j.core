package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmTableColumn;

import org.thymeleaf.model.AttributeValueQuotes;

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
   * @param  mmContext  context TODOC
   */
  @Override
  protected void doProcess(final MmContext mmContext) {
    MmTableColumn<?> mimic = mmContext.mimic;

    processId(mmContext);
    LOGGER.debug("table column id = " + mimic.getMmId());

    processStyleClasses(mmContext);
    mmContext.out.setSelectionTarget(mimic);
    mmContext.out.removeAttribute(getPrefixedAttributeName());
  }

  /**
   * TODOC.
   *
   * @param  mmContext  mimic TODOC
   */
  @Override
  protected void processStyleClasses(final MmContext mmContext) {
    // TODO merge style classes without duplicates
    String outStyleClasses = mmContext.mimic.getMmHeaderClasses();
    if (mmContext.tag.hasAttribute("class")) {
      outStyleClasses = (mmContext.tag.getAttributeValue("class") + " " + outStyleClasses).trim();
    }
    if (outStyleClasses.isEmpty()) {
      mmContext.out.removeAttribute("class");
    } else {
      mmContext.out.setAttribute("class", outStyleClasses, AttributeValueQuotes.DOUBLE);
    }
  }
}
