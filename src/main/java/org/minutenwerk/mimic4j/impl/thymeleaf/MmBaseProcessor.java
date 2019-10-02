package org.minutenwerk.mimic4j.impl.thymeleaf;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import static org.minutenwerk.mimic4j.impl.thymeleaf.Mimic4jDialect.MIMIC4J_DIALECT_PREFIX_MM;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IOpenElementTag;
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

  /** End of line characters of operating system in use. */
  public static final String    NL                    = System.getProperty("line.separator");

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
   * @param  mmContext    TODOC
   * @param  elementName  TODOC
   */
  protected void close(final MmContext mmContext, final String elementName) {
    mmContext.close();
    mmContext.model.add(mmContext.factory.createCloseElementTag(elementName));
  }

  /**
   * TODOC.
   *
   * @param  mmContext    TODOC
   * @param  elementName  TODOC
   */
  protected void closeSameLine(final MmContext mmContext, final String elementName) {
    mmContext.closeSameLine();
    mmContext.model.add(mmContext.factory.createCloseElementTag(elementName));
  }

  /**
   * TODOC.
   *
   * @param  mmContext  TODOC
   */
  protected abstract void doProcess(MmContext mmContext);

  /**
   * TODOC.
   *
   * @param  context  TODOC
   * @param  tag      TODOC
   * @param  out      TODOC
   */
  @Override
  protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler out) {
    MIMIC         mimic     = evaluateMimic(context, tag, out);
    IModelFactory factory   = context.getModelFactory();
    IModel        model     = factory.createModel();
    MmContext     mmContext = new MmContext(context, tag, out, mimic, factory, model, 2);

    doProcess(mmContext);

    mmContext.out.removeAttribute(getPrefixedAttributeName());
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
   * @param   mmContext    TODOC
   * @param   elementName  TODOC
   * @param   keyValues    TODOC
   *
   * @return  TODOC
   */
  protected IOpenElementTag open(final MmContext mmContext, final String elementName, String... keyValues) {
    mmContext.open();
    if (keyValues == null) {
      IOpenElementTag tag = mmContext.factory.createOpenElementTag(elementName);
      mmContext.model.add(tag);
      return tag;
    } else {
      final Map<String, String> attributes = new HashMap<>();
      for (int i = 0; (i + 1) < keyValues.length; i = i + 2) {
        attributes.put(keyValues[i], keyValues[i + 1]);
      }

      // TODO use of hash map produces random order of attributes
      IOpenElementTag tag = mmContext.factory.createOpenElementTag(elementName, attributes, AttributeValueQuotes.DOUBLE, false);
      mmContext.model.add(tag);
      return tag;
    }
  }

  /**
   * TODOC.
   *
   * @param  mmContext  mimic TODOC
   */
  protected void processId(final MmContext mmContext) {
    String externalId  = mmContext.tag.getAttributeValue("id");
    String evaluatedId = mmContext.mimic.getToJsf().evalId(externalId);
    mmContext.out.setAttribute("id", evaluatedId, AttributeValueQuotes.DOUBLE);
  }

  /**
   * TODOC.
   *
   * @param  mmContext  mimic TODOC
   */
  protected void processStyleClasses(final MmContext mmContext) {
    // TODO merge style classes without duplicates
    String outStyleClasses = mmContext.mimic.getMmStyleClasses();
    if (mmContext.tag.hasAttribute("class")) {
      outStyleClasses = (mmContext.tag.getAttributeValue("class") + " " + outStyleClasses).trim();
    }
    if (outStyleClasses.isEmpty()) {
      mmContext.out.removeAttribute("class");
    } else {
      mmContext.out.setAttribute("class", outStyleClasses, AttributeValueQuotes.DOUBLE);
    }
  }

  /**
   * TODOC.
   *
   * @param  mmContext  TODOC
   * @param  keyValues  TODOC
   */
  protected void setAttributes(final MmContext mmContext, String... keyValues) {
    if (keyValues != null) {
      for (int i = 0; (i + 1) < keyValues.length; i = i + 2) {
        mmContext.out.setAttribute(keyValues[i], keyValues[i + 1], AttributeValueQuotes.DOUBLE);
      }
    }
  }

  public class MmContext {
    public final ITemplateContext            context;
    public final IProcessableElementTag      tag;
    public final IElementTagStructureHandler out;
    public final MIMIC                       mimic;
    public final IModelFactory               factory;
    public final IModel                      model;
    public final int                         initialIndent;
    public int                               indent;

    public MmContext(final ITemplateContext context, final IProcessableElementTag tag, final IElementTagStructureHandler out,
      final MIMIC mimic, final IModelFactory factory, final IModel model, final int initialIndent) {
      this.context       = context;
      this.tag           = tag;
      this.out           = out;
      this.mimic         = mimic;
      this.factory       = factory;
      this.model         = model;
      this.initialIndent = initialIndent;
      indent             = initialIndent;
    }

    public void close() {
      indent();
      indent = Math.max(initialIndent, indent - 2);
    }

    public void closeSameLine() {
      indent = Math.max(initialIndent, indent - 2);
    }

    public void open() {
      indent += 2;
      indent();
    }

    private void indent() {
      String offset = NL;
      for (int i = 0; i < indent; i++) {
        offset += " ";
      }
      model.add(factory.createText(offset));
    }
  }
}
