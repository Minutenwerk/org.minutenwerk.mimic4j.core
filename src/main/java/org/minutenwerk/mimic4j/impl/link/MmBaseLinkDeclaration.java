package org.minutenwerk.mimic4j.impl.link;

import java.net.URI;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

import org.springframework.web.util.UriComponents;

/**
 * MmBaseLinkDeclaration is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the
 * text label of the link.
 *
 * @param               <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param               <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseLinkDeclaration<IMPLEMENTATION extends MmBaseLinkImplementation<?, DATA_MODEL, VIEW_MODEL, ?, ?>,
  DATA_MODEL extends MmReferencableModel, VIEW_MODEL> extends MmBaseDeclaration<MmLinkMimic<DATA_MODEL, VIEW_MODEL>, IMPLEMENTATION>
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
   * Returns the link's model accessor to corresponding data model. The data model delivers dynamic parts of URL. The data model accessor
   * can be derived from specified parent component accessor.
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
  public MmModelAccessor<?, DATA_MODEL> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      @SuppressWarnings("unchecked")
      MmModelAccessor<?, DATA_MODEL> modelAccessor = (MmModelAccessor<?, DATA_MODEL>)pParentAccessor;
      return modelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to modelAccessor. You must redefine callbackMmGetAccessor() for " + this);
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
   * Returns the path part of the target URL like "city/{id0}/person/{id1}/display" in "city/123/person/4711/display".
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The path part of the target URL.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public UriComponents callbackMmGetTargetReferencePath(UriComponents pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of path or query parameter values of the target URL, like "123", "4711" in "city/123/person/4711/display".
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   * @param         pModel             The model data, which may control the query string.
   *
   * @return        A list of path or query parameter values of the target URL. Usually this is a list of ids starting by id of root dto.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public List<String> callbackMmGetTargetReferenceValues(List<String> pPassThroughValue, DATA_MODEL pModel) {
    return pPassThroughValue;
  }

  /**
   * Returns the link's format pattern for displaying view value. It is used during conversion from view model to view value and vice versa.
   * It is dependent on the user's locale.
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
   * Returns the link's model accessor to corresponding view model. The view model delivers dynamic parts of URL. The view model accessor
   * can be derived from specified parent component accessor.
   *
   * @param         pParentAccessor  The specified parent component accessor.
   *
   * @return        The view model accessor.
   *
   * @throws        ClassCastException  IllegalStateException In case of view model accessor is not defined.
   *
   * @jalopy.group  group-callback
   */
  @Override
  public MmModelAccessor<?, VIEW_MODEL> callbackMmGetViewModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    try {
      @SuppressWarnings("unchecked")
      MmModelAccessor<?, VIEW_MODEL> viewModelAccessor = (MmModelAccessor<?, VIEW_MODEL>)pParentAccessor;
      return viewModelAccessor;
    } catch (ClassCastException e) {
      throw new ClassCastException("Parent accessor " + pParentAccessor
        + " cannot be casted to viewModelAccessor. You must redefine callbackMmGetViewModelAccessor() for " + this);
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
   * Returns model accessor of link mimic parent, may be null.
   *
   * @return        The model accessor of link mimic parent, may be null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public final MmModelAccessor<?, ?> getMmParentAccessor() {
    return implementation.getMmParentAccessor();
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
}
