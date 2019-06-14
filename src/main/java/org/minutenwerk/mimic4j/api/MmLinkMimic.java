package org.minutenwerk.mimic4j.api;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * MmCompositeMimic defines mimics which can be nested following the composite design pattern.
 *
 * @author  Olaf Kossak
 */
public interface MmLinkMimic extends MmMimic {

  /**
   * Returns the data model.
   *
   * @return  The data model.
   */
  @Deprecated
  public MmReferencableModel getMmModel();

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator.
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

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(String pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Object[] pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Integer pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Instant pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(LocalTime pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(LocalDate pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(LocalDateTime pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(BigDecimal pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Boolean pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(BigInteger pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Double pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Duration pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Float pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Long pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(ZonedDateTime pModelsideValue, MmReferencableModel pModel);

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param  pModelsideValue  The specified modelside values.
   * @param  pModel           The specified data model which defines query parameters.
   */
  @Deprecated public void setMmModelsideValue(Enum<?> pModelsideValue, MmReferencableModel pModel);

}
