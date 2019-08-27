package org.minutenwerk.mimic4j.api;

import org.minutenwerk.mimic4j.api.accessor.MmComponentAccessor;

/**
 * MmCompositeMimic defines mimics which can be nested following the composite design pattern.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkMimic<LINK_MODEL> extends MmMimic {

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  public MmComponentAccessor<?, LINK_MODEL> getMmModelAccessor();

  /**
   * Returns the link's type of modelside value (LINK_MODEL).
   *
   * @return  The link's type of modelside value.
   */
  public Class<LINK_MODEL> getMmModelsideType();

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return  The modelside value of the mimic.
   */
  public LINK_MODEL getMmModelsideValue();

  /**
   * Returns accessor of model of parent container mimic, may be null.
   *
   * @return  The accessor of model of parent container mimic, may be null.
   */
  public MmComponentAccessor<?, ?> getMmParentAccessor();

  /**
   * Returns a reference to some target, either an URL or an outcome.
   *
   * @return  A reference to some target.
   */
  public MmReference getMmTargetReference();

  /**
   * Returns the link's viewside value of type String.
   *
   * @return  The link's viewside value of type String.
   */
  public String getMmViewsideValue();

}
