package org.minutenwerk.mimic4j.api;

import java.net.URI;

import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;

/**
 * MmLinkMimic is a mimic with two models, one model delivers the modelside value for dynamic parts of URL, the link model delivers the text
 * of the link.
 *
 * @param   <MODELSIDE_VALUE>  Modelside value delivers dynamic parts of URL.
 * @param   <LINK_MODEL>       Link model delivers text of link.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkMimic<MODELSIDE_VALUE, LINK_MODEL> extends MmMimic {

  /**
   * Returns the link's model value.
   *
   * @return  The link's model value.
   */
  public LINK_MODEL getMmLinkModelValue();

  /**
   * Returns accessor of model.
   *
   * @return  The accessor of model.
   */
  public MmModelAccessor<?, MODELSIDE_VALUE> getMmModelAccessor();

  /**
   * Returns the link's type of modelside value (LINK_MODEL).
   *
   * @return  The link's type of modelside value.
   */
  public Class<MODELSIDE_VALUE> getMmModelsideType();

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return  The modelside value of the mimic.
   */
  public MODELSIDE_VALUE getMmModelsideValue();

  /**
   * Returns accessor of model of parent container mimic, may be null.
   *
   * @return  The accessor of model of parent container mimic, may be null.
   */
  public MmModelAccessor<?, ?> getMmParentAccessor();

  /**
   * Returns a reference to some target, either an URL or an outcome.
   *
   * @return  A reference to some target.
   */
  public URI getMmTargetReference();

  /**
   * Returns the link's viewside value of type String.
   *
   * @return  The link's viewside value of type String.
   */
  public String getMmViewsideValue();

}
