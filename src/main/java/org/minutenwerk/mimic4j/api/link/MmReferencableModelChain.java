package org.minutenwerk.mimic4j.api.link;

import java.util.ArrayList;
import java.util.List;

import org.minutenwerk.mimic4j.api.MmReferencableModel;

/**
 * MmReferencableModelChain is a referencable model, which chains the reference values of several models.
 *
 * @author  Olaf Kossak
 */
public class MmReferencableModelChain implements MmReferencableModel {

  /** The chained list of reference values of all parameters. */
  private final List<String> referenceValues = new ArrayList<>();

  /**
   * Constructor for specified array of referencable models.
   *
   * @param  pModels  The specified array of referencable models.
   */
  public MmReferencableModelChain(MmReferencableModel... pModels) {
    for (MmReferencableModel model : pModels) {
      if (model != null) {
        this.referenceValues.addAll(model.getMmReferenceValues());
      }
    }
  }

  /**
   * Returns a list of query parameter values of the URL, like "123", "4711" in "person/wohnort/display.html#plz?id1=123&id2=4711".
   *
   * @return  A list of query parameter values of the URL.
   */
  @Override public List<String> getMmReferenceValues() {
    return this.referenceValues;
  }
}
