package org.minutenwerk.mimic4j.impl.referencable;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;
import org.minutenwerk.mimic4j.impl.link.MmBaseLinkImplementation;

/**
 * MmBaseReferencableImplementation is the abstract base class for the implementation part of all container mimic classes.
 *
 * @author              Olaf Kossak
 * @see                 $HeadURL: $$maven.project.version$
 *
 * @jalopy.group-order  group-initialization, group-do, group-override
 */
public abstract class MmBaseReferencableImplementation<DECLARATION extends MmBaseReferencableDeclaration<MODEL, ?>,
  MODEL extends MmReferencableModel, CONFIGURATION extends MmBaseConfiguration>
  extends MmBaseContainerImplementation<DECLARATION, MODEL, CONFIGURATION> implements MmReferencableMimic<MODEL> {

  /** Cached instance of self reference. */
  protected MmReference cachedReference;

  /** Cached instance of foreign reference. */
  protected MmReference foreignReference;

  /** Cached instance of foreign model. */
  protected MODEL       foreignModel;

  /**
   * Creates a new MmBaseReferencableImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseReferencableImplementation(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Sets the values from model to modelside of mimic.
   *
   * @param         pModel  The model to set.
   *
   * @jalopy.group  group-do
   */
  @Override public void doMmSetModelsideFromModel(MODEL pModel) {
    // super method will ensure initialization
    super.doMmSetModelsideFromModel(pModel);
    this.evaluateAndCacheReference();
  }

  /**
   * Returns the self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @return        The self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @jalopy.group  group-override
   */
  @Override public MmReference getMmReference() {
    this.ensureInitialization();

    if (this.cachedReference == null) {
      this.evaluateAndCacheReference();
    }
    return this.cachedReference;
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
  @Override public MmReference getMmReference(MmReferencableModel pModel) {
    this.ensureInitialization();

    @SuppressWarnings("unchecked")
    final MODEL castedModel = (MODEL)pModel;

    if (!pModel.equals(this.foreignModel)) {
      final String            path            = this.getMmReferencePath();
      final String            file            = this.getMmReferenceFile();
      final List<MmNameValue> modelParams     = MmBaseLinkImplementation.getMmModelParams(castedModel);
      final List<MmNameValue> callbackParams  = this.declaration.callbackMmGetReferenceParams(modelParams, this.model);
      final MmReference       returnReference = new MmReferenceImplementation(path, file, callbackParams);

      this.foreignModel     = castedModel;
      this.foreignReference = returnReference;
    }
    return foreignReference;
  }

  /**
   * Evaluates and caches the self reference of this mimic.
   */
  protected void evaluateAndCacheReference() {
    final String            path           = this.getMmReferencePath();
    final String            file           = this.getMmReferenceFile();
    final List<MmNameValue> modelParams    = MmBaseLinkImplementation.getMmModelParams(this.model);
    final List<MmNameValue> callbackParams = this.declaration.callbackMmGetReferenceParams(modelParams, this.model);

    this.cachedReference = new MmReferenceImplementation(path, file, callbackParams);
  }

}
