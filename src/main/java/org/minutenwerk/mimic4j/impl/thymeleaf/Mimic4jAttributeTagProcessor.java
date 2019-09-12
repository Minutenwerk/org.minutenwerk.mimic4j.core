package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * TODOC Mimic4jAttributeTagProcessor.
 */
public class Mimic4jAttributeTagProcessor extends AbstractAttributeTagProcessor {

  /** TODOC. */
  private static final String  ELEMENT_NAME_ATTRIBUTE      = "attribute";

  /** TODOC. */
  private static final boolean PREFIX_ELEMENT_NAME_TRUE    = true;

  /** TODOC. */
  private static final String  ATTRIBUTE_NAME_VALUE        = "value";

  /** TODOC. */
  private static final boolean PREFIX_ATTRIBUTE_NAME_FALSE = false;

  /** TODOC. */
  private static final int     PRECEDENCE_100              = 100;

  /** TODOC. */
  private static final boolean REMOVE_ATTRIBUTE_FALSE      = false;

  /**
   * Creates a new Mimic4jAttributeTagProcessor instance.
   */
  public Mimic4jAttributeTagProcessor() {
    super(TemplateMode.HTML, Mimic4jDialect.DIALECT_PREFIX, ELEMENT_NAME_ATTRIBUTE, PREFIX_ELEMENT_NAME_TRUE, ATTRIBUTE_NAME_VALUE,
      PREFIX_ATTRIBUTE_NAME_FALSE, PRECEDENCE_100, REMOVE_ATTRIBUTE_FALSE);
  }

  /**
   * TODOC.
   *
   * @param  context           TODOC
   * @param  tag               TODOC
   * @param  attributeName     TODOC
   * @param  attributeValue    TODOC
   * @param  structureHandler  TODOC
   */
  @Override
  protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue,
    IElementTagStructureHandler structureHandler) {
    final IEngineConfiguration      configuration = context.getConfiguration();

    final IStandardExpressionParser parser        = StandardExpressions.getExpressionParser(configuration);

    final IStandardExpression       expression    = parser.parseExpression(context, attributeValue);

    final String                    result        = (String)expression.execute(context);

    structureHandler.setAttribute("value", result);
  }
}
