package org.minutenwerk.mimic4j.impl.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * TODOC Mimic4jDialect.
 */
public class Mimic4jDialect extends AbstractProcessorDialect {

  /** TODOC. */
  public static final String DIALECT_NAME   = "Mimic4j Dialect";

  /** TODOC. */
  public static final String DIALECT_PREFIX = "mm";

  /**
   * Creates a new Mimic4jDialect instance.
   */
  public Mimic4jDialect() {
    super(DIALECT_NAME, DIALECT_PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
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
    processors.add(new InputElementTagProcessor(dialectPrefix));
    processors.add(new Mimic4jAttributeTagProcessor());
    return processors;
  }
}
