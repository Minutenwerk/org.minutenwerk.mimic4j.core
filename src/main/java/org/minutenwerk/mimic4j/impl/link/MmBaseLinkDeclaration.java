package org.minutenwerk.mimic4j.impl.link;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.mimic.MmLinkMimic;
import org.minutenwerk.mimic4j.api.mimic.MmReferenceableModel;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

import org.springframework.web.util.UriComponents;

/**
 * MmBaseLinkDeclaration is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the text label of the
 * link. In most cases the two models are the same.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseLinkDeclaration<IMPLEMENTATION extends MmBaseLinkImplementation<?, DATA_MODEL, VIEW_MODEL, ?, ?>,
  DATA_MODEL extends MmReferenceableModel, VIEW_MODEL> extends MmBaseDeclaration<MmLinkMimic<DATA_MODEL, VIEW_MODEL>, IMPLEMENTATION>
  implements MmLinkMimic<DATA_MODEL, VIEW_MODEL>, MmLinkCallback<DATA_MODEL, VIEW_MODEL> {

  /**
   * Creates a new MmBaseLinkDeclaration instance.
   *
   * @param  pImplementation  The reference to the implementation part of the mimic.
   */
  protected MmBaseLinkDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the link's model accessor to corresponding data model. The data model delivers dynamic parts of URL. The data model accessor can be derived from
   * specified parent component accessor.
   *
   * @param         pParentAccessor  The specified parent component accessor.
   *
   * @return        The data model accessor.
   *
   * @throws        ClassCastException  IllegalStateException In case of data model accessor is not defined.
   *
   * @jalopy.group  group-callback
   */
  @Override
  @SuppressWarnings("unchecked")
  public MmModelAccessor<?, DATA_MODEL> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      return (MmModelAccessor<?, DATA_MODEL>)pParentAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to modelAccessor. callbackMmGetModelAccessor() must be defined for " + this);
    }
  }

  /**
   * Returns the expanded target URL of this mimic, city/123/person/4711/display".
   *
   * @param         pEvaluatedTargetReferencePath  The evaluated target reference path of this mimic, like "city/{id0}/person/{id1}/display".
   * @param         pDataModel                     The data model, which evaluated target reference path and parameters.
   * @param         pEvaluatedReferenceParams      The evaluated parameters of the target URL, like {"123", "4711" }.
   *
   * @return        The expanded target URL of this mimic, city/123/person/4711/display".
   *
   * @throws        RuntimeException  In case of expand failed.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public URI callbackMmGetTargetReference(UriComponents pEvaluatedTargetReferencePath, DATA_MODEL pDataModel, List<String> pEvaluatedReferenceParams) {
    try {
      if (pEvaluatedReferenceParams.isEmpty()) {
        if (pDataModel == null) {
          return pEvaluatedTargetReferencePath.toUri();
        } else {
          return pEvaluatedTargetReferencePath.expand(pDataModel).toUri();
        }
      } else if (pEvaluatedReferenceParams.size() == 1) {
        return pEvaluatedTargetReferencePath.expand(pEvaluatedReferenceParams.get(0)).toUri();
      } else {
        return pEvaluatedTargetReferencePath.expand(pEvaluatedReferenceParams.toArray()).toUri();
      }
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("targetReferencePath: " + pEvaluatedTargetReferencePath + ", dataModel: " + pDataModel + ", targetReferenceParams: "
        + pEvaluatedReferenceParams, e);
    }
  }

  /**
   * Returns a mimic, which is the target reference of this link mimic.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A mimic, which is the target reference of this link mimic.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmMimic callbackMmGetTargetReferenceMimic(MmMimic pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the evaluated target reference path of this mimic, like "city/{id0}/person/{id1}/display".
   *
   * @param         pPassThroughValue  The configured path of the target URL like "city/{id0}/person/{id1}/display".
   * @param         pDataModel         The returned target reference path might depend on the data model, e.g. the data model "person" is a "mayor", the
   *                                   reference path is changed to "city/{id0}/mayor/{id1}/display".
   *
   * @return        The evaluated target reference path of this mimic, like "city/{id0}/person/{id1}/display" or "city/{id0}/mayor/{id1}/display".
   *
   * @jalopy.group  group-callback
   */
  @Override
  public UriComponents callbackMmGetTargetReferencePath(UriComponents pPassThroughValue, DATA_MODEL pDataModel) {
    return pPassThroughValue;
  }

  /**
   * Returns the link's format pattern for displaying view value. It is used during conversion from view model to view value and vice versa. It is dependent
   * on the user's locale.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's format pattern for displaying view value.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public String callbackMmGetViewFormatPattern(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns the link's model accessor to corresponding view model. The view model delivers dynamic parts of URL. The view model accessor can be derived from
   * specified parent component accessor.
   *
   * @param         pParentAccessor  The specified parent component accessor.
   *
   * @return        The view model accessor.
   *
   * @throws        ClassCastException  In case of view model accessor is not defined.
   *
   * @jalopy.group  group-callback
   */
  @Override
  @SuppressWarnings("unchecked")
  public MmModelAccessor<?, VIEW_MODEL> callbackMmGetViewModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    MmModelAccessor<?, DATA_MODEL> dataModelAccessor = callbackMmGetModelAccessor(pParentAccessor);
    try {
      return (MmModelAccessor<?, VIEW_MODEL>)dataModelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("View model accessor cannot be derived from data model accessor." + dataModelAccessor
        + " callbackMmGetViewModelAccessor() must be defined for " + this);
    }
  }

  /**
   * Returns data model value.
   *
   * @return        The data model value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final DATA_MODEL getMmModel() {
    return implementation.getMmModel();
  }

  /**
   * Returns accessor of data model.
   *
   * @return        The accessor of data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final MmModelAccessor<?, DATA_MODEL> getMmModelAccessor() {
    return implementation.getMmModelAccessor();
  }

  /**
   * Returns type of data model.
   *
   * @return        The type of data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final Class<DATA_MODEL> getMmModelType() {
    return implementation.getMmModelType();
  }

  /**
   * Returns URI of the link.
   *
   * @return        The URI of the link.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final URI getMmTargetReference() {
    return implementation.getMmTargetReference();
  }

  /**
   * Returns view model value.
   *
   * @return        The view model value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final VIEW_MODEL getMmViewModel() {
    return implementation.getMmViewModel();
  }

  /**
   * Returns model accessor of view model.
   *
   * @return        The model accessor of view model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final MmModelAccessor<?, VIEW_MODEL> getMmViewModelAccessor() {
    return implementation.getMmViewModelAccessor();
  }

  /**
   * Returns type of view model.
   *
   * @return        The type of view model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final Class<VIEW_MODEL> getMmViewModelType() {
    return implementation.getMmViewModelType();
  }

  /**
   * Returns view text of the link.
   *
   * @return        The view text of the link.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final String getMmViewValue() {
    return implementation.getMmViewValue();
  }

  /**
   * Returns icon after text.
   *
   * @return  The icon after text.
   */
  @Override
  public final String getIconAfter() {
    return implementation.getIconAfter();
  }

  /**
   * Returns icon before text.
   *
   * @return  The icon before text.
   */
  @Override
  public final String getIconBefore() {
    return implementation.getIconBefore();
  }
}
