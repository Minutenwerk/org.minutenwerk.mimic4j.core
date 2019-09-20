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
  public static final String MIMIC4J_DIALECT_NAME              = "Mimic4j Dialect";

  public static final String MIMIC4J_DIALECT_NAMESPACE              = "http://www.minutenwerk.org";

  /** TODOC. */
  public static final String MIMIC4J_DIALECT_PREFIX_MM = "mm";

  /**
   * Creates a new Mimic4jDialect instance.
   */
  public Mimic4jDialect() {
    super(MIMIC4J_DIALECT_NAME, MIMIC4J_DIALECT_PREFIX_MM, StandardDialect.PROCESSOR_PRECEDENCE);
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
    processors.add(new MmTableCellLinkProcessor());
    processors.add(new MmTableCellTextProcessor());

    // remove namespace xmlns:mm="http://www.minutenwerk.de"
    processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
    return processors;
  }
}
