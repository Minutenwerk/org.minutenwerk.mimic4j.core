package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.minutenwerk.mimic4j.api.container.MmTable;

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
   * @param  mmContext  context TODOC
   */
  @Override
  protected void doProcess(final MmContext mmContext) {
    MmTable<?> mimic = mmContext.mimic;

    processId(mmContext);
    LOGGER.info("table id = " + mimic.getMmId());

    processStyleClasses(mmContext);
    mmContext.out.setSelectionTarget(mimic);
  }

}
