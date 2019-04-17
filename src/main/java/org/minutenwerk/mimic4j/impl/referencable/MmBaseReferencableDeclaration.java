package org.minutenwerk.mimic4j.impl.referencable;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;

/**
 * MmBaseReferencableDeclaration is the abstract base class for MmBaseContainerDeclaration (containing other mimics of type MmEditableMimic)
 * and being referencable by an url.
 *
 * @param   <MODEL>           The type of the model, containing business data.
 * @param   <IMPLEMENTATION>  The type of the corresponding class implementing this declaration api.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseReferencableDeclaration<MODEL extends MmReferencableModel,
  IMPLEMENTATION extends MmBaseReferencableImplementation<?, MODEL, ?>> extends MmBaseContainerDeclaration<MODEL, IMPLEMENTATION>
  implements MmReferencableMimic<MODEL>, MmReferencableCallback<MODEL> {

  /**
   * Creates a new MmBaseReferencableDeclaration instance.
   *
   * @param  pImplementation  The implementation part of this mimic.
   */
  protected MmBaseReferencableDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the list of query parameters of the URL, like "rootId 1", "subId 2" in "person/wohnort/display.html#plz?rootId=1&subId=2".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The model data, which may control the query string.
   *
   * @return  The list of query parameters of the URL.
   */
  @Override
  public List<MmNameValue> callbackMmGetReferenceParams(List<MmNameValue> pPassThroughValue, MODEL pModel) {
    return pPassThroughValue;
  }

}
