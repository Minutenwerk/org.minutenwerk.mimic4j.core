package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmTableRow;

import org.thymeleaf.model.AttributeValueQuotes;

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
   * @param  mmContext  context TODOC
   */
  @Override
  protected void doProcess(final MmContext mmContext) {
    MmTableRow<?> mimic = mmContext.mimic;

    processId(mmContext);
    // LOGGER.info("table row id = " + mimic.getMmId());

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
  protected void processId(final MmContext mmContext) {
// String externalId  = mmContext.tag.getAttributeValue("id");
// String evaluatedId = mmContext.mimic.getToJsf().evalId(externalId);

    String evaluatedId = mmContext.mimic.getMmId() + mmContext.mimic.getMmRowIndex();
    mmContext.out.setAttribute("id", evaluatedId, AttributeValueQuotes.DOUBLE);
  }

}
