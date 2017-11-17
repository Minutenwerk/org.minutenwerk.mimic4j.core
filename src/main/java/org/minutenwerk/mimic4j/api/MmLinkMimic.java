package org.minutenwerk.mimic4j.api;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * MmCompositeMimic defines mimics which can be nested following the composite design pattern.
 *
 * @author  Olaf Kossak
 * @see     $HeadURL: $$maven.project.version$
 */
public interface MmLinkMimic extends MmMimic {

  /**
   * Returns the data model.
   *
   * @return  The data model.
   *
   * @since   $maven.project.version$
   */
  public MmReferencableModel getMmModel();

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return  A reference to some target.
   *
   * @since   $maven.project.version$
   */
  public MmReference getMmTargetReference();

  /**
   * Returns the link's viewside value of type String.
   *
   * @return  The link's viewside value of type String.
   *
   * @since   $maven.project.version$
   */
  public String getMmViewsideValue();

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(String pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Object[] pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Integer pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(LocalDate pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(BigDecimal pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Boolean pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(BigInteger pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Double pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Float pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Long pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(LocalTime pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   *
   * @since  $maven.project.version$
   */
  public void setMmModelsideValue(Enum<?> pModelsideValue, MmReferencableModel pModel);

}
