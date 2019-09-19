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
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmInformationable;
import org.minutenwerk.mimic4j.api.MmLinkMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmNameValue;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.exception.MmModelsideConverterException;
import org.minutenwerk.mimic4j.api.link.MmReferenceParam;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.attribute.MmBaseAttributeImplementation;
import org.minutenwerk.mimic4j.impl.container.MmBaseContainerImplementation;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.referencable.MmReferenceImplementation;

/**
 * MmBaseLinkImplementation is the abstract base class for the implementation part of all link mimic classes.
 *
 * @param               <MODELSIDE_VALUE>  Modelside value delivers dynamic parts of URL.
 * @param               <LINK_MODEL>       Link model delivers text of link.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-initialization, group-override, group-i18n
 */
public abstract class MmBaseLinkImplementation<CALLBACK extends MmLinkCallback<MODELSIDE_VALUE, LINK_MODEL>,
  MODELSIDE_VALUE, LINK_MODEL, CONFIGURATION extends MmBaseLinkConfiguration, ANNOTATION extends Annotation>
  extends MmBaseImplementation<MmBaseLinkDeclaration<?, MODELSIDE_VALUE, LINK_MODEL>, CONFIGURATION, ANNOTATION>
  implements MmLinkMimic<MODELSIDE_VALUE, LINK_MODEL> {

  /** Class internal constant to control index of generic type LINK_MODEL. */
  private static final int                      GENERIC_PARAMETER_INDEX_LINK_MODEL = 2;

  /** Logger of this class. */
  private static final Logger                   LOGGER                             = LogManager.getLogger(MmBaseLinkImplementation.class);

  /** This link has a parent model. The parent model has a parent accessor. */
  protected MmModelAccessor<?, ?>               parentAccessor;

  /**
   * This link has a model of type LINK_MODEL. The model has a model accessor. Its first generic, the type of the parent model, is
   * undefined.
   */
  protected MmModelAccessor<?, MODELSIDE_VALUE> modelAccessor;

  /** TODOC. */
  protected MmModelAccessor<?, LINK_MODEL>      linkModelAccessor;

  /**
   * Creates a new MmBaseLinkImplementation instance.
   *
   * @param  pParent  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmBaseLinkImplementation(final MmDeclarationMimic pParent) {
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
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call
   * super.onInitialization()!
   *
   * @throws        IllegalStateException  In case of root accessor or model accessor is not defined.
   *
   * @jalopy.group  group-initialization
   */
  @Override
  protected void onInitialization() {
    super.onInitialization();

    // initialize parentAccessor
    parentAccessor = onInitializeParentAccessor();

    // initialize modelAccessor
    modelAccessor  = declaration.callbackMmGetAccessor(parentAccessor);
    if (modelAccessor == null) {
      throw new IllegalStateException("no definition of callbackMmGetAccessor() for " + parentPath + "." + name);
    }

    // initialize link modelAccessor
    linkModelAccessor = declaration.callbackMmGetLinkModelAccessor(parentAccessor);
    if (linkModelAccessor == null) {
      throw new IllegalStateException("no definition of callbackMmGetLinkModelAccessor() for " + parentPath + "." + name);
    }
  }

  /**
   * Evaluates accessor of component of parent container mimic.
   *
   * @return        The parent accessor.
   *
   * @throws        IllegalStateException  In case of there is no definition of a parent accessor.
   *
   * @jalopy.group  group-initialization
   */
  private MmModelAccessor<?, ?> onInitializeParentAccessor() {
    MmBaseContainerImplementation<?, ?, ?, ?> containerAncestor = getMmImplementationAncestorOfType(MmBaseContainerImplementation.class);
    if (containerAncestor == null) {
      throw new IllegalStateException("no ancestor of type MmContainerMimic for " + parentPath + "." + name);
    } else {
      MmModelAccessor<?, ?> containerAccessor = containerAncestor.onInitializeGetMmModelAccessor();
      if (containerAccessor == null) {
        throw new IllegalStateException("no definition of parentAccessor for " + parentPath + "." + name);
      }
      return containerAccessor;
    }
  }

  /**
   * Returns accessor of model.
   *
   * @return        The accessor of model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, MODELSIDE_VALUE> getMmModelAccessor() {
    assureInitialization();

    return modelAccessor;
  }

  /**
   * Returns the link's type of modelside value (LINK_MODEL).
   *
   * @return        The link's type of modelside value.
   *
   * @jalopy.group  group-override
   */
  @Override
  public Class<MODELSIDE_VALUE> getMmModelsideType() {
    assureInitialization();

    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseAttributeImplementation.class, GENERIC_PARAMETER_INDEX_LINK_MODEL);
  }

  /**
   * Returns the modelside value of the mimic. The modelside value is exchanged between model and mimic.
   *
   * @return        The modelside value of the mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MODELSIDE_VALUE getMmModelsideValue() {
    assureInitialization();

    return modelAccessor.get();
  }

  /**
   * Returns accessor of model of parent container mimic, may be null.
   *
   * @return        The accessor of model of parent container mimic, may be null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmModelAccessor<?, ?> getMmParentAccessor() {
    assureInitialization();

    return parentAccessor;
  }

  /**
   * Returns a reference to some target, either an URL or an outcome.
   *
   * @return        A reference to some target.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmReference getMmTargetReference() {
    assureInitialization();

    MmReference           targetReference = null;
    final MmMimic         targetMimic     = declaration.callbackMmGetTargetMimic(null);

    // retrieve model
    final MODELSIDE_VALUE model           = modelAccessor.get();

    // if link references another mimic without a specified data model
    if ((targetMimic != null) && (model == null)) {
      targetReference = targetMimic.getMmReference();

      // if link references another mimic for a specified referencable data model
    } else if ((targetMimic != null) && (model != null) && (model instanceof MmReferencableModel)) {
      targetReference = targetMimic.getMmReference((MmReferencableModel)model);

      // if link references another mimic for a specified raw data model
    } else if ((targetMimic != null) && (model != null)) {

      // TODO final List<MmNameValue> targetReferenceParams = targetMimic.callbackMmGetTargetReferenceParams(Collections.emptyList(),
      // model);
      return null;

      // if link references an URL without a specified data model
    } else if ((targetMimic == null) && (model == null)) {
      final String            configurationOutcome  = configuration.getTargetOutcome();
      final String            callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> emptyList             = Collections.emptyList();
      final List<MmNameValue> targetReferenceParams = declaration.callbackMmGetTargetReferenceParams(emptyList, null);
      targetReference = new MmReferenceImplementation(callbackOutcome, targetReferenceParams);

      // if link references an URL for a specified referencable data model
    } else if ((targetMimic == null) && (model != null) && (model instanceof MmReferencableModel)) {
      final String            configurationOutcome  = configuration.getTargetOutcome();
      final String            callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> modelReferenceParams  = getMmModelParams((MmReferencableModel)model);
      final List<MmNameValue> targetReferenceParams = declaration.callbackMmGetTargetReferenceParams(modelReferenceParams, model);
      targetReference = new MmReferenceImplementation(callbackOutcome, targetReferenceParams);

      // if link references an URL for a specified raw data model
    } else if ((targetMimic == null) && (model != null)) {
      final String            configurationOutcome  = configuration.getTargetOutcome();
      final String            callbackOutcome       = declaration.callbackMmGetTargetOutcome(configurationOutcome);
      final List<MmNameValue> targetReferenceParams = declaration.callbackMmGetTargetReferenceParams(Collections.emptyList(), model);
      targetReference = new MmReferenceImplementation(callbackOutcome, targetReferenceParams);
    }
    return targetReference;
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

    // retrieve model
    final MODELSIDE_VALUE model          = getMmModelsideValue();

    // retrieve modelside value
    final LINK_MODEL      modelsideValue = getMmLinkModelValue();

    String                returnString   = null;
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
    assureInitialization();

    // retrieve modelside value
    final LINK_MODEL linkModel      = getMmLinkModelValue();

    final Object     modelsideValue = (linkModel instanceof MmInformationable) //
      ? ((MmInformationable)linkModel).getInfo() //
      : linkModel;

    // if model is an array of objects
    if (modelsideValue instanceof Object[]) {

      // translate enum values to i18n strings before, because MessageFormat shall not do this
      for (int index = 0; index < ((Object[])modelsideValue).length; index++) {
        ((Object[])modelsideValue)[index] = transformObjectForFormattingByMessageSource(((Object[])modelsideValue)[index]);
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

  /**
   * Returns the link's model value.
   *
   * @return  The link's model value.
   */
  @Override
  public LINK_MODEL getMmLinkModelValue() {
    assureInitialization();

    return linkModelAccessor.get();
  }

  /**
   * TODOC.
   *
   * @param   pObject  TODOC
   *
   * @return  TODOC
   */
  protected Object transformObjectForFormattingByMessageSource(final Object pObject) {
    // return empty String for null value
    if (pObject == null) {
      return "";

      // translate enum values to i18n strings before, because MessageFormat shall not do this
    } else if (pObject instanceof Enum<?>) {
      return formatModelsideValue(pObject);

      // transform Instant values to java.util.Date
    } else if (pObject instanceof Instant) {
      return Date.from(((Instant)pObject));

      // transform LocalDate values to java.util.Date
    } else if (pObject instanceof LocalDate) {
      return Date.from(((LocalDate)pObject).atStartOfDay(ZoneId.of("UTC")).toInstant());

      // transform LocalDateTime values to java.util.Date
    } else if (pObject instanceof LocalDateTime) {
      return Date.from(((LocalDateTime)pObject).toInstant(ZoneOffset.UTC));

      // transform ZonedDateTime values to java.util.Date
    } else if (pObject instanceof ZonedDateTime) {
      return Date.from(((ZonedDateTime)pObject).toInstant());

      // all other objects pass through
    } else {
      return pObject;
    }
  }

}
