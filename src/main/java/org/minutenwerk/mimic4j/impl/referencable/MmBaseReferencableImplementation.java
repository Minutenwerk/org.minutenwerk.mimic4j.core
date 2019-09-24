package org.minutenwerk.mimic4j.impl.referencable;

import java.lang.annotation.Annotation;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmReferenceProvider;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;

import org.springframework.web.util.UriComponents;

/**
 * MmBaseReferencableImplementation is the implementation part of a MmContainerMimic being referencable by an url.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-do, group-override
 */
public abstract class MmBaseReferencableImplementation<DECLARATION extends MmBaseReferencableDeclaration<?, MODEL>,
  MODEL extends MmReferencableModel, //
  CONFIGURATION extends MmBaseConfiguration, ANNOTATION extends Annotation>
  extends MmBaseContainerImplementation<DECLARATION, MODEL, CONFIGURATION, ANNOTATION> implements MmReferenceProvider {

  /** Cached instance of self reference. */
  protected URI   cachedReference;

  /** Cached instance of foreign reference. */
  protected URI   foreignReference;

  /** Cached instance of foreign model. */
  protected MODEL foreignModel;

  /**
   * Creates a new MmBaseReferencableImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseReferencableImplementation(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Sets the values from model to data model of mimic.
   *
   * @jalopy.group  group-do
   */
  public void toDo() {
    // TODO MmBaseReferencableImplementation toDo when?
    evaluateAndCacheReference();
  }

  /**
   * Returns the self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @return        The self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public URI getMmReference() {
    assureInitialization();

    if (cachedReference == null) {
      evaluateAndCacheReference();
    }
    return cachedReference;
  }

  /**
   * Returns the self reference of this object for a specified data model.
   *
   * @param         pModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return        The self reference of this object for a specified data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public URI getMmReference(MmReferencableModel pModel) {
    assureInitialization();

    @SuppressWarnings("unchecked")
    final MODEL castedModel = (MODEL)pModel;

    if (!pModel.equals(foreignModel)) {
      final UriComponents path            = getMmReferencePath();
      final List<String>  modelParams     = castedModel.getMmReferenceValues();
      final List<String>  callbackParams  = declaration.callbackMmGetReferenceValues(modelParams, modelAccessor.get());
      final URI           returnReference = path.expand(callbackParams).toUri();

      foreignModel     = castedModel;
      foreignReference = returnReference;
    }
    return foreignReference;
  }

  /**
   * Evaluates and caches the self reference of this mimic.
   */
  protected void evaluateAndCacheReference() {
    final UriComponents path           = getMmReferencePath();
    final List<String>  modelParams    = modelAccessor.get().getMmReferenceValues();
    final List<String>  callbackParams = declaration.callbackMmGetReferenceValues(modelParams, modelAccessor.get());

    cachedReference = path.expand(callbackParams).toUri();
  }

}
