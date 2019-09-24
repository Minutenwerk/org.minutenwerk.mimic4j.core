package org.minutenwerk.mimic4j.impl.referencable;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmReferenceProvider;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerDeclaration;

/**
 * MmBaseReferencableDeclaration is the declaration part of a MmContainerMimic being referencable by an url.
 *
 * @param   <MODEL>           The type of the model, containing business data.
 * @param   <IMPLEMENTATION>  The type of the corresponding class implementing this declaration api.
 *
 * @author  Olaf Kossak
 */
public abstract class MmBaseReferencableDeclaration<IMPLEMENTATION extends MmBaseReferencableImplementation<?, MODEL, ?, ?>,
  MODEL extends MmReferencableModel> extends MmBaseContainerDeclaration<IMPLEMENTATION, MODEL> implements MmReferenceProvider,
  MmReferencableCallback<MODEL> {

  /**
   * Creates a new MmBaseReferencableDeclaration instance.
   *
   * @param  pImplementation  The implementation part of this mimic.
   */
  protected MmBaseReferencableDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns a list of path or query parameter values of the URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @param   pPassThroughValue  By default this parameter value will be returned.
   * @param   pModel             The model data, which may control the query string.
   *
   * @return  A list of path or query parameter values of the URL. Usually this is a list of ids starting by id of root dto.
   */
  @Override
  public List<String> callbackMmGetReferenceValues(List<String> pPassThroughValue, MODEL pModel) {
    return pPassThroughValue;
  }

}
