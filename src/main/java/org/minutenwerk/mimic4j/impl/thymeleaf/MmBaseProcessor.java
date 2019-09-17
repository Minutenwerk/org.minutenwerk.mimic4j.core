package org.minutenwerk.mimic4j.impl.thymeleaf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_PREFIX_MM;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * TODOC MmBaseProcessor.
 */
public abstract class MmBaseProcessor<MIMIC extends MmBaseDeclaration<?, ?>> extends AbstractElementTagProcessor {

  /** The logger of this class. */
  protected static final Logger LOGGER                = LogManager.getLogger(MmBaseProcessor.class);

  /** Prefix of tag is not considered. */
  private static final boolean  PREFIX_TAG_FALSE      = false;

  /** Prefix of attribute is considered. */
  private static final boolean  PREFIX_ATTRIBUTE_TRUE = true;

  /** Precedence of processor in dialect. */
  private static final int      PRECEDENCE_100        = 100;

  /** Name of tag in {@code <tag prefix:attribute="..." >}. */
  protected final String        tagName;

  /** Name of attribute in {@code <tag prefix:attribute="..." >}. */
  protected final String        attributeName;

  /**
   * Creates a new MmBaseProcessor instance.
   *
   * @param  tagName        TODOC
   * @param  attributeName  TODOC
   */
  public MmBaseProcessor(final String tagName, final String attributeName) {
    super(TemplateMode.HTML, MIMIC4J_DIALECT_PREFIX_MM, tagName, PREFIX_TAG_FALSE, attributeName, PREFIX_ATTRIBUTE_TRUE, PRECEDENCE_100);
    this.tagName       = tagName;
    this.attributeName = attributeName;
  }

  /**
   * TODOC.
   *
   * @param   context  TODOC
   * @param   tag      TODOC
   * @param   out      TODOC
   *
   * @return  TODOC
   */
  @SuppressWarnings("unchecked")
  protected MIMIC evaluateMimic(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler out) {
    IEngineConfiguration      configuration  = context.getConfiguration();
    IStandardExpressionParser parser         = StandardExpressions.getExpressionParser(configuration);
    String                    attributeValue = tag.getAttributeValue(getPrefixedAttributeName());
    IStandardExpression       expression     = parser.parseExpression(context, attributeValue);
    Object                    object         = expression.execute(context);
    return (MIMIC)object;
  }

  /**
   * Returns prefix and attribute like {@code mm:attribute}.
   *
   * @return  Prefix and attribute like {@code mm:attribute}.
   */
  protected String getPrefixedAttributeName() {
    return MIMIC4J_DIALECT_PREFIX_MM + ":" + attributeName;
  }

  /**
   * TODOC.
   *
   * @param  mimic    TODOC
   * @param  context  TODOC
   * @param  tag      TODOC
   * @param  out      TODOC
   */
  protected void processId(MIMIC mimic, ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler out) {
    String externalId  = tag.getAttributeValue("id");
    String evaluatedId = mimic.getToJsf().evalId(externalId);
    out.setAttribute("id", evaluatedId, AttributeValueQuotes.DOUBLE);
  }

  /**
   * TODOC.
   *
   * @param  mimic    TODOC
   * @param  context  TODOC
   * @param  tag      TODOC
   * @param  out      TODOC
   */
  protected void processStyleClasses(MIMIC mimic, ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler out) {
    // TODO merge style classes without duplicates
    String outStyleClasses = mimic.getMmStyleClasses();
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
