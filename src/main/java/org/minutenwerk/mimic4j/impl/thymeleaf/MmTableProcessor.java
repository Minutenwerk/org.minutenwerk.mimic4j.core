package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmTable;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;

/**
 * Thymeleaf processor for {@code <table mm:table="${someMimic}">}.
 *
 * @author  Olaf Kossak
 */
public class MmTableProcessor extends MmBaseProcessor<MmTable<?>> {

  /**
   * Creates a new MmTableProcessor instance.
   */
  public MmTableProcessor() {
    super("table", "table");
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
    MmTable<?> mimic = evaluateMimic(context, tag, out);
    processId(mimic, context, tag, out);
    LOGGER.info("table id = " + mimic.getMmId());

    processStyleClasses(mimic, context, tag, out);
    out.setSelectionTarget(mimic);
    out.removeAttribute(getPrefixedAttributeName());
  }

}
