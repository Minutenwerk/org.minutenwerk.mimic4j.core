package org.minutenwerk.mimic4j.impl.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * TODOC Mimic4jDialect.
 */
public class Mimic4jDialect extends AbstractProcessorDialect {

  /** TODOC. */
  public static final String DIALECT_NAME              = "Mimic4j Dialect";

  /** TODOC. */
  public static final String MIMIC4J_DIALECT_PREFIX_MM = "mm";

  /**
   * Creates a new Mimic4jDialect instance.
   */
  public Mimic4jDialect() {
    super(DIALECT_NAME, MIMIC4J_DIALECT_PREFIX_MM, StandardDialect.PROCESSOR_PRECEDENCE);
  }

  /**
   * TODOC.
   *
   * @param   dialectPrefix  TODOC
   *
   * @return  TODOC
   */
  @Override
  public Set<IProcessor> getProcessors(final String dialectPrefix) {
    final Set<IProcessor> processors = new HashSet<IProcessor>();
    processors.add(new MmTableProcessor());
    processors.add(new MmTableColumnProcessor());
    processors.add(new MmTableRowProcessor());
    processors.add(new MmTableCellProcessor());
    processors.add(new MmDivProcessor());

    // remove namespace xmlns:mm="http://www.minutenwerk.de"
    processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
    return processors;
  }
}
