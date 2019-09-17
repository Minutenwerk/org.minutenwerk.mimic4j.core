package org.minutenwerk.mimic4j.impl.thymeleaf;

import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.AttributeValueQuotes;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * TODOC InputElementTagProcessor.
 */
public class InputElementTagProcessor extends AbstractElementTagProcessor {

  /** TODOC. */
  private static final String TAG_NAME   = "inputRow";

  /** TODOC. */
  private static final int    PRECEDENCE = 1000;

  /**
   * Creates a new InputElementTagProcessor instance.
   *
   * @param  dialectPrefix  TODOC
   */
  public InputElementTagProcessor(final String dialectPrefix) {
    super(TemplateMode.HTML, // This processor will apply only to HTML mode
      dialectPrefix, // Prefix to be applied to name for matching
      TAG_NAME, // Tag name: match specifically this tag
      true, // Apply dialect prefix to tag name
      null, // No attribute name: will match by tag name
      false, // No prefix to be applied to attribute name
      PRECEDENCE); // Precedence (inside dialect's own precedence)
  }

  /**
   * TODOC.
   *
   * @param  context           TODOC
   * @param  tag               TODOC
   * @param  structureHandler  TODOC
   */
  @Override
  protected void doProcess(ITemplateContext context, IProcessableElementTag tag, IElementTagStructureHandler structureHandler) {
    final String        name            = tag.getAttributeValue("name");
    final String        value           = tag.getAttributeValue("value");

    final IModelFactory modelFactory    = context.getModelFactory();

    final IModel        model           = modelFactory.createModel();

    // Add input
    Map<String, String> inputAttributes = new HashMap<>();
    inputAttributes.put("name", name);
    inputAttributes.put("value", value);
    model.add(modelFactory.createStandaloneElementTag("input", inputAttributes, AttributeValueQuotes.DOUBLE, false, true));

    structureHandler.replaceWith(model, false);
  }
}
