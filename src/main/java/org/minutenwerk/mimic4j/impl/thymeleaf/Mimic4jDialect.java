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
  public static final String MIMIC4J_DIALECT_NAME               = "Mimic4j Dialect";

  /** TODOC. */
  public static final String MIMIC4J_DIALECT_NAMESPACE          = "http://www.minutenwerk.org";

  /** TODOC. */
  public static final String MIMIC4J_DIALECT_PREFIX_MM          = "mm";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_ADD     = "add";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_CREATE  = "create";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_DELETE  = "delete";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_EDIT    = "edit";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_REMOVE  = "remove";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_REPLACE = "replace";

  /** Request parameter for MmCommands, used in {@link org.minutenwerk.mimic4j.api.reference.MmReferencePath}. */
  public static final String MIMIC4J_DIALECT_POST_PARAM_SAVE    = "save";

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
