package org.minutenwerk.mimic4j.impl.link;

import java.lang.annotation.Annotation;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.link.MmReferenceParam;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.referencable.MmReferenceImplementation;

/**
 * MmBaseLinkImplementation is the abstract base class for the implementation part of all link mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-override, group-i18n
 */
public abstract class MmBaseLinkImplementation<CALLBACK extends MmLinkCallback,
  CONFIGURATION extends MmBaseLinkConfiguration, ANNOTATION extends Annotation>
  extends MmBaseImplementation<MmBaseLinkDeclaration<?>, CONFIGURATION, ANNOTATION> implements MmLinkMimic {

  /** Logger of this class. */
  private static final Logger   LOGGER         = LogManager.getLogger(MmBaseLinkImplementation.class);

  /** The displaying text and title of the link may depend dynamically on a value. */
  protected Object              modelsideValue;

  /** The query parameters of the link my depend dynamically on a data model. */
  protected MmReferencableModel model;

  /**
   * Creates a new MmBaseLinkImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseLinkImplementation(MmDeclarationMimic pParent) {
    super(pParent);
  }

  /**
   * Returns a list of name/ value parameters for a specified list of reference values, the names are rootId, subId1, subId2, ...
   *
   * @param   pModel  A referencable data model providing a list of reference values.
   *
   * @return  A list of name/ value parameters for a specified list of reference values, the names are rootId, subId1, subId2, ...
   */
  public static List<MmNameValue> getMmModelParams(MmReferencableModel pModel) {
    final List<String> referenceValues = (pModel != null) ? pModel.getMmReferenceValues() : null;
    if ((referenceValues == null) || referenceValues.isEmpty()) {
      return Collections.emptyList();
    } else {
      final List<MmNameValue> modelReferenceValues = new ArrayList<>();
      int                     index                = 0;
      for (String referenceValue : referenceValues) {
        if (index == 0) {
          modelReferenceValues.add(new MmReferenceParam("rootId", referenceValue));
        } else {
          modelReferenceValues.add(new MmReferenceParam("subId" + index, referenceValue));
        }
        index++;
      }
      return modelReferenceValues;
    }
  }

  /**
   * Returns the data model.
   *
   * @return        The data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmReferencableModel getMmModel() {
    return model;
  }

  /**
   * Returns a reference to some target, either an URL or an outcome, to be translated by FacesNavigator.
   *
   * @return        A reference to some target.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmReference getMmTargetReference() {
    assureInitialization();

    MmReference   targetReference = null;
    final MmMimic targetMimic     = declaration.callbackMmGetTargetMimic(null);

    // if link references another mimic without a specified data model
    if ((targetMimic != null) && (model == null)) {
      targetReference = targetMimic.getMmReference();

      // if link references another mimic for a specified data model
    } else if ((targetMimic != null) && (model != null)) {
      targetReference = targetMimic.getMmReference(model);

      // if link references an URL without a specified data model
    } else if ((targetMimic == null) && (model == null)) {
      final String            configurationOutcome  = configuration.getTargetOutcome();
      final String            callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> emptyList             = Collections.emptyList();
      final List<MmNameValue> targetReferenceParams = declaration.callbackMmGetTargetReferenceParams(emptyList, null);
      targetReference = new MmReferenceImplementation(callbackOutcome, targetReferenceParams);

      // if link references an URL for a specified data model
    } else if ((targetMimic == null) && (model != null)) {
      final String            configurationOutcome  = configuration.getTargetOutcome();
      final String            callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> modelReferenceParams  = getMmModelParams(model);
      final List<MmNameValue> targetReferenceParams = declaration.callbackMmGetTargetReferenceParams(modelReferenceParams, model);
      targetReference = new MmReferenceImplementation(callbackOutcome, targetReferenceParams);
    }
    return targetReference;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(String pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Object[] pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Integer pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Instant pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(LocalTime pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(LocalDate pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(LocalDateTime pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(BigDecimal pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Boolean pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(BigInteger pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Double pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Duration pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Float pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Long pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(ZonedDateTime pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Sets specified modelside values of the link for text, title and query parameters.
   *
   * @param         pModelsideValue  The specified modelside values.
   * @param         pModel           The specified data model which defines query parameters.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public void setMmModelsideValue(Enum<?> pModelsideValue, MmReferencableModel pModel) {
    modelsideValue = pModelsideValue;
    model          = pModel;
  }

  /**
   * Returns the format pattern for formatting modelside value to viewside value.
   *
   * @return        The format pattern for formatting modelside value to viewside value.
   *
   * @throws        IllegalStateException  In case of callbackMmGetFormatPattern() returns an invalid format pattern.
   *
   * @jalopy.group  group-i18n
   */
  public String getMmFormatPattern() {
    final String i18nFormatPattern     = getMmI18nText(MmMessageType.FORMAT);
    final String callbackFormatPattern = declaration.callbackMmGetFormatPattern(i18nFormatPattern);
    if (LOGGER.isDebugEnabled()) {
      if (callbackFormatPattern == null) {
        throw new IllegalStateException("callbackMmGetFormatPattern() must return valid format pattern");
      }
    }
    return callbackFormatPattern;
  }

  /**
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @throws        IllegalStateException  In case of callbackMmGetLongDescription() returns null.
   *
   * @jalopy.group  group-i18n
   */
  @Override
  public String getMmLongDescription() {
    assureInitialization();

    String returnString = null;
    if (model == null) {
      final String i18nLongDescription = getMmI18nText(MmMessageType.LONG, modelsideValue);
      returnString = declaration.callbackMmGetLongDescription(i18nLongDescription, modelsideValue);
    } else {
      final String i18nLongDescription = getMmI18nText(MmMessageType.LONG, modelsideValue, model);
      returnString = declaration.callbackMmGetLongDescription(i18nLongDescription, modelsideValue, model);
    }
    if (LOGGER.isDebugEnabled()) {
      if (returnString == null) {
        throw new IllegalStateException("callbackMmGetLongDescription cannot return null");
      }
    }
    return returnString;
  }

  /**
   * Returns the link's viewside value of type String.
   *
   * @return        The link's viewside value of type String.
   *
   * @jalopy.group  group-i18n
   */
  @Override
  public String getMmViewsideValue() {
    // if model is an array of objects
    if (modelsideValue instanceof Object[]) {

      // translate enum values to i18n strings before, because MessageFormat shall not do this
      for (int index = 0; index < ((Object[])modelsideValue).length; index++) {
        final Object modelsideObject = ((Object[])modelsideValue)[index];
        if (modelsideObject instanceof Enum<?>) {
          final String i18nEnumValue = formatModelsideValue(modelsideObject);
          ((Object[])modelsideValue)[index] = i18nEnumValue;
        }
      }

      // modelside keeps an Object[], but because it is of type Object, java still interprets it to be just one object
      // so to put an array of objects into varargs method parameter, there must be an explicit cast to Object[]
      final String i18nViewsideValue = getMmI18nText(MmMessageType.SHORT, (Object[])modelsideValue);
      return i18nViewsideValue;

      // if model is a single object
    } else {

      // return empty String for null value
      if (modelsideValue == null) {
        return "";

        // pass through Strings
      } else if (modelsideValue instanceof String) {
        return (String)modelsideValue;

        // i18n single enums
      } else if (modelsideValue instanceof Enum<?>) {

        // translate enum values to i18n strings before, because MessageFormat shall not do this
        final String i18nViewsideValue = formatModelsideValue(modelsideValue);
        return i18nViewsideValue;

        // format number, date and time values
      } else if ((modelsideValue instanceof Number) || (modelsideValue instanceof Boolean) || (modelsideValue instanceof Duration)
          || (modelsideValue instanceof Instant) || (modelsideValue instanceof LocalTime) || (modelsideValue instanceof LocalDate)
          || (modelsideValue instanceof LocalDateTime) || (modelsideValue instanceof ZonedDateTime)) {

        final String formattedViewsideValue = formatModelsideValue(modelsideValue);
        return formattedViewsideValue;

        // all other single objects translate to i18n
      } else {
        final String i18nViewsideValue = getMmI18nText(MmMessageType.SHORT, modelsideValue);
        return i18nViewsideValue;
      }
    }
  }

  /**
   * Returns the specified modelside value, formatted depending on its type.
   *
   * @param         pModelsideValue  The specified modelside value.
   *
   * @return        The formatted modelside value, formatted depending on its type.
   *
   * @throws        MmModelsideConverterException  In case of conversion fails.
   * @throws        IllegalArgumentException       In case of conversion fails.
   *
   * @jalopy.group  group-i18n
   */
  protected String formatModelsideValue(Object pModelsideValue) {
    String formattedValue = "";
    if (pModelsideValue != null) {

      if (pModelsideValue instanceof String) {
        formattedValue = (String)pModelsideValue;

      } else if ((pModelsideValue instanceof Integer) || (pModelsideValue instanceof Long) || (pModelsideValue instanceof Float)
          || (pModelsideValue instanceof Double)) {
        try {
          final NumberFormat numberFormatter = getMmNumberFormatter(false);
          formattedValue = numberFormatter.format(pModelsideValue);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if ((pModelsideValue instanceof BigDecimal) || (pModelsideValue instanceof BigInteger)) {
        try {
          final NumberFormat numberFormatter = getMmNumberFormatter(true);
          formattedValue = numberFormatter.format(pModelsideValue);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof Duration) {
        try {
          formattedValue = ((Duration)pModelsideValue).toString();
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof Instant) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = dateTimeFormatter.format((Instant)pModelsideValue);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof LocalTime) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((LocalTime)pModelsideValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof LocalDate) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((LocalDate)pModelsideValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof LocalDateTime) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((LocalDateTime)pModelsideValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof ZonedDateTime) {
        try {
          final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter();
          formattedValue = ((ZonedDateTime)pModelsideValue).format(dateTimeFormatter);
        } catch (IllegalArgumentException e) {
          throw new MmModelsideConverterException(this,
            "Cannot format " + getClass().getSimpleName() + " " + getMmId() + ", modelside value: " + pModelsideValue + " by pattern >"
            + getMmFormatPattern() + "<");
        }

      } else if (pModelsideValue instanceof Enum<?>) {
        final Enum<?> enumValue    = (Enum<?>)pModelsideValue;
        final String  enumTypeName = enumValue.getClass().getSimpleName();
        formattedValue = root.getMmI18nText(enumTypeName + "." + enumValue.name(), MmMessageType.SHORT);

      } else if (pModelsideValue instanceof Boolean) {
        if ((Boolean)pModelsideValue) {
          formattedValue = root.getMmI18nText(getMmId() + ".true", MmMessageType.SHORT);
        } else {
          formattedValue = root.getMmI18nText(getMmId() + ".false", MmMessageType.SHORT);
        }
      } else {
        throw new IllegalArgumentException("unknown modelside value type cannot be formatted " + pModelsideValue);
      }
    }
    return formattedValue;
  }

  /**
   * Returns the initialized date formatter of this mimic.
   *
   * @return        The initialized date formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected DateTimeFormatter getMmDateTimeFormatter() {
    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(getMmFormatPattern());
    return returnDateFormatter;
  }

  /**
   * Returns the initialized number formatter of this mimic.
   *
   * @param         pParseBigDecimal  True, if
   *
   * @return        The initialized number formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected NumberFormat getMmNumberFormatter(boolean pParseBigDecimal) {
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(pParseBigDecimal);
    returnNumberFormatter.applyPattern(getMmFormatPattern());
    return returnNumberFormatter;
  }

}
