package org.minutenwerk.mimic4j.impl.link;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import java.util.List;

import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

/**
 * MmBaseLinkDeclaration is the base class for link mimics.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-callback, group-override
 */
public abstract class MmBaseLinkDeclaration<IMPLEMENTATION extends MmBaseLinkImplementation<?, ?>>
  extends MmBaseDeclaration<MmLinkMimic, IMPLEMENTATION> implements MmLinkMimic, MmLinkCallback {

  /**
   * Creates a new MmBaseLinkDeclaration instance.
   *
   * @param  pImplementation  The reference to the implementation part of the mimic.
   */
  public MmBaseLinkDeclaration(IMPLEMENTATION pImplementation) {
    super(pImplementation);
  }

  /**
   * Returns the attribute's format pattern for displaying viewside value in view. It is used during conversion from modelside to viewside
   * value and vice versa. It is dependent on the user's locale.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        The attribute's format pattern for displaying viewside value.
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmGetFormatPattern(String pPassThroughValue) {
    return pPassThroughValue;
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
  @Override public MmMimic callbackMmGetTargetMimic(MmMimic pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a string referencing a target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   *
   * @return        A string referencing a target, either an URL or an outcome
   *
   * @jalopy.group  group-callback
   */
  @Override public String callbackMmGetTargetOutcome(String pPassThroughValue) {
    return pPassThroughValue;
  }

  /**
   * Returns a list of URL parameters to be concatenated to target reference. May be used in combination with callbackMmGetTargetOutcome().
   *
   * @param         pPassThroughValue  By default this parameter value will be returned.
   * @param         pModel             A referencable data model providing a list of reference values.
   *
   * @return        A list of URL parameters to be concatenated to target reference.
   *
   * @jalopy.group  group-callback
   */
  @Override public List<MmNameValue> callbackMmGetTargetReferenceParams(List<MmNameValue> pPassThroughValue, MmReferencableModel pModel) {
    return pPassThroughValue;
  }

  /**
   * Returns the data model.
   *
   * @return        The data model.
   *
   * @jalopy.group  group-override
   */
  @Override public MmReferencableModel getMmModel() {
    return this.implementation.getMmModel();
  }

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return        A reference to some target.
   *
   * @jalopy.group  group-override
   */
  @Override public final MmReference getMmTargetReference() {
    return this.implementation.getMmTargetReference();
  }

  /**
   * Returns the link's viewside value of type String.
   *
   * @return        The link's viewside value of type String.
   *
   * @jalopy.group  group-override
   */
  @Override public final String getMmViewsideValue() {
    return this.implementation.getMmViewsideValue();
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(String pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Object[] pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Integer pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Instant pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(LocalTime pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(LocalDate pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(LocalDateTime pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(BigDecimal pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Boolean pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(BigInteger pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Double pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Duration pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Float pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Long pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(ZonedDateTime pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Override public final void setMmModelsideValue(Enum<?> pModelsideValue, MmReferencableModel pModel) {
    this.implementation.setMmModelsideValue(pModelsideValue, pModel);
  }
}
