package org.minutenwerk.mimic4j.api;

import java.net.URI;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.reference.MmReferencableModel;

/**
 * MmLinkMimic is a mimic with two models, the data model delivers the value for dynamic parts of URL, the view model delivers the text
 * label of the link. In most cases the two models are the same.
 *
 * @param   <DATA_MODEL>  Data model delivers dynamic parts of URL.
 * @param   <VIEW_MODEL>  View model delivers view text label of link.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkMimic<DATA_MODEL extends MmReferencableModel, VIEW_MODEL> extends MmMimic {

  /**
   * Returns data model value.
   *
   * @return  The data model value.
   */
  public DATA_MODEL getMmModel();

  /**
   * Returns accessor of data model.
   *
   * @return  The accessor of data model.
   */
  public MmModelAccessor<?, DATA_MODEL> getMmModelAccessor();

  /**
   * Returns type of data model.
   *
   * @return  The type of data model.
   */
  public Class<DATA_MODEL> getMmModelType();

  /**
   * Returns URI of the link.
   *
   * @return  The URI of the link.
   */
  public URI getMmTargetReference();

  /**
   * Returns view model value.
   *
   * @return  The view model value.
   */
  public VIEW_MODEL getMmViewModel();

  /**
   * Returns model accessor of view model.
   *
   * @return  The model accessor of view model.
   */
  public MmModelAccessor<?, VIEW_MODEL> getMmViewModelAccessor();

  /**
   * Returns type of view model.
   *
   * @return  The type of view model.
   */
  public Class<VIEW_MODEL> getMmViewModelType();

  /**
   * Returns view text of the link.
   *
   * @return  The view text of the link.
   */
  public String getMmViewValue();

}
