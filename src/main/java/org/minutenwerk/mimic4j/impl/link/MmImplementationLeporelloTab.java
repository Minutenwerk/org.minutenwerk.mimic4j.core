package org.minutenwerk.mimic4j.impl.link;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import java.util.Date;

import org.minutenwerk.mimic4j.api.container.MmLeporelloPanel;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTab;
import org.minutenwerk.mimic4j.api.link.MmLeporelloTabAnnotation;
import org.minutenwerk.mimic4j.impl.MmBaseCallback;
import org.minutenwerk.mimic4j.impl.MmBaseConfiguration;
import org.minutenwerk.mimic4j.impl.MmBaseImplementation;
import org.minutenwerk.mimic4j.impl.container.MmImplementationLeporello;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridgeLeporelloTab;

/**
 * MmImplementationLeporelloTab is the specific class for the implementation part of tab set mimics.
 *
 * @author  Olaf Kossak
 */
public class MmImplementationLeporelloTab
  extends MmBaseLinkImplementation<MmLeporelloTab, MmConfigurationLeporelloTab, MmLeporelloTabAnnotation> {

  /** The super tab is the logical parent tab in the parent panel. */
  protected final MmLeporelloTab            superTab;

  /** The parent leporello of this leporello tab. */
  protected MmImplementationLeporello<?, ?> parentLeporello;

  /**
   * Creates a new MmImplementationLeporelloTab instance.
   *
   * @param  pParentPanel  The parent declaration mimic, declaring a static final instance of this mimic.
   */
  public MmImplementationLeporelloTab(MmLeporelloPanel<?> pParentPanel) {
    this(pParentPanel, null);
  }

  /**
   * Creates a new MmImplementationLeporelloTab instance.
   *
   * @param  pParentPanel  The parent declaration mimic, declaring a static final instance of this mimic.
   * @param  pSuperTab     The super tab is the logical parent tab in the parent panel.
   */
  public MmImplementationLeporelloTab(MmLeporelloPanel<?> pParentPanel, MmLeporelloTab pSuperTab) {
    super(pParentPanel);
    superTab = pSuperTab;
  }

  /**
   * Returns the CSS styleclass if this leporello tab is active.
   *
   * @return  The CSS styleclass if this leporello tab is active.
   */
  public String getMmStyleClassActive() {
    assureInitialization();

    if (isMmActive()) {
      return "active";
    } else {
      return "";
    }
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}. If {@link MmBaseCallback#callbackMmGetStyleClasses()} returns null, the style
   * classes are evaluated from configuration attribute {@link MmBaseConfiguration#styleClasses()}.
   *
   * @return  A String containing space delimited <code>CSS</code> style classes.
   */
  @Override
  public String getMmStyleClasses() {
    assureInitialization();

    String styleClasses = super.getMmStyleClasses();
    if (!isMmEnabled()) {
      return "disabled " + styleClasses;
    }
    return styleClasses;
  }

  /**
   * Returns the link's viewside value of type String.
   *
   * @return  The link's viewside value of type String.
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

        // format Instant values
      } else if (modelsideValue instanceof Instant) {

        final Date   modelsideValueAsJavaUtilDate = Date.from(((Instant)modelsideValue));
        final String formattedViewsideValue       = formatModelsideValue(modelsideValueAsJavaUtilDate);
        return formattedViewsideValue;

        // format LocalDate values
      } else if (modelsideValue instanceof LocalDate) {

        final Date   modelsideValueAsJavaUtilDate = Date.from(((LocalDate)modelsideValue).atStartOfDay(ZoneId.of("UTC")).toInstant());
        final String formattedViewsideValue       = formatModelsideValue(modelsideValueAsJavaUtilDate);
        return formattedViewsideValue;

        // format LocalDateTime values
      } else if (modelsideValue instanceof LocalDateTime) {

        final Date   modelsideValueAsJavaUtilDate = Date.from(((LocalDateTime)modelsideValue).toInstant(ZoneOffset.UTC));
        final String formattedViewsideValue       = formatModelsideValue(modelsideValueAsJavaUtilDate);
        return formattedViewsideValue;

        // format ZonedDateTime values
      } else if (modelsideValue instanceof ZonedDateTime) {

        final Date   modelsideValueAsJavaUtilDate = Date.from(((ZonedDateTime)modelsideValue).toInstant());
        final String formattedViewsideValue       = formatModelsideValue(modelsideValueAsJavaUtilDate);
        return formattedViewsideValue;

        // all other single objects translate to i18n
      } else {
        final String i18nViewsideValue = getMmI18nText(MmMessageType.SHORT, modelsideValue);
        return i18nViewsideValue;
      }
    }
  }

  /**
   * Returns the view tab of this leporello panel tab.
   *
   * @return  The view tab of this leporello panel tab.
   */
  public MmTab<?> getMmViewTab() {
    assureInitialization();

    // TODO MmImplementationLeporelloTab getMmViewTab
    return null;
  }

  /**
   * Returns <code>true</code>, if the tab is active.
   *
   * @return  <code>True</code>, if the tab is active.
   */
  public boolean isMmActive() {
    assureInitialization();

    final MmLeporelloTab thisDeclaration = (MmLeporelloTab)declaration;
    final MmLeporelloTab selectedTab     = parentLeporello.getMmSelectedTab();
    if (selectedTab == null) {
      return false;
    } else if (selectedTab == thisDeclaration) {
      return true;
    } else {
      final MmImplementationLeporelloTab selectedTabImplementation = MmBaseImplementation.getImplementation(selectedTab);
      MmLeporelloTab                     superTabOfSelected        = selectedTabImplementation.superTab;
      while (superTabOfSelected != null) {
        if (thisDeclaration == superTabOfSelected) {
          return true;
        }

        final MmImplementationLeporelloTab superTabOfSelectedImplementation = MmBaseImplementation.getImplementation(superTabOfSelected);
        superTabOfSelected = superTabOfSelectedImplementation.superTab;
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code>, if the tab is selected.
   *
   * @return  <code>True</code>, if the tab is selected.
   */
  public boolean isMmSelected() {
    assureInitialization();

    final MmLeporelloTab thisDeclaration = (MmLeporelloTab)declaration;
    final MmLeporelloTab selectedTab     = parentLeporello.getMmSelectedTab();
    return (thisDeclaration == selectedTab);
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  @Override
  protected MmConfigurationLeporelloTab onConstructConfiguration(MmLeporelloTabAnnotation pAnnotation) {
    if (pAnnotation != null) {
      return new MmConfigurationLeporelloTab(pAnnotation);
    } else {
      return new MmConfigurationLeporelloTab();
    }
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return new MmJsfBridgeLeporelloTab(this);
  }

  /**
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call
   * super.onInitialization()!
   */
  @Deprecated
  @Override
  protected void onInitialization() {
    super.onInitialization();

    // TODO MmImplementationLeporelloTab move this into constructor and delete initialize method here
    parentLeporello = getMmImplementationAncestorOfType(MmImplementationLeporello.class);
  }

}
