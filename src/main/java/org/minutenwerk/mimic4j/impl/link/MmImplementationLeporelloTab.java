package org.minutenwerk.mimic4j.impl.link;

import java.time.LocalDateTime;
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
public class MmImplementationLeporelloTab extends MmBaseLinkImplementation<MmLeporelloTab, MmConfigurationLeporelloTab> {

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
    this.superTab = pSuperTab;
  }

  /**
   * Returns the CSS styleclass if this leporello tab is active.
   *
   * @return  The CSS styleclass if this leporello tab is active.
   */
  public String getMmStyleClassActive() {
    this.ensureInitialization();

    if (this.isMmActive()) {
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
  @Override public String getMmStyleClasses() {
    this.ensureInitialization();

    String styleClasses = super.getMmStyleClasses();
    if (!this.isMmEnabled()) {
      return "disabled " + styleClasses;
    }
    return styleClasses;
  }

  /**
   * Returns the link's viewside value of type String.
   *
   * @return  The link's viewside value of type String.
   */
  @Override public String getMmViewsideValue() {
    // if model is an array of objects
    if (this.modelsideValue instanceof Object[]) {

      // translate enum values to i18n strings before, because MessageFormat shall not do this
      for (int index = 0; index < ((Object[])this.modelsideValue).length; index++) {
        final Object modelsideObject = ((Object[])this.modelsideValue)[index];
        if (modelsideObject instanceof Enum<?>) {
          final String i18nEnumValue = this.formatModelsideValue(modelsideObject);
          ((Object[])this.modelsideValue)[index] = i18nEnumValue;
        }
      }

      // this.modelside keeps an Object[], but because it is of type Object, java still interprets it to be just one object
      // so to put an array of objects into varargs method parameter, there must be an explicit cast to Object[]
      final String i18nViewsideValue = this.getMmI18nText(MmMessageType.SHORT, (Object[])this.modelsideValue);
      return i18nViewsideValue;

      // if model is a single object
    } else {

      // return empty String for null value
      if (this.modelsideValue == null) {
        return "";

        // pass through Strings
      } else if (this.modelsideValue instanceof String) {
        return (String)this.modelsideValue;

        // i18n single enums
      } else if (this.modelsideValue instanceof Enum<?>) {

        // translate enum values to i18n strings before, because MessageFormat shall not do this
        final String i18nViewsideValue = this.formatModelsideValue(this.modelsideValue);
        return i18nViewsideValue;

        // format date values
      } else if (this.modelsideValue instanceof LocalDateTime) {

        final Date   modelsideValueAsJavaUtilDate = Date.from(((ZonedDateTime)this.modelsideValue).toInstant());
        final String formattedViewsideValue       = this.formatModelsideValue(modelsideValueAsJavaUtilDate);
        return formattedViewsideValue;

        // format time values
      } else if (this.modelsideValue instanceof ZonedDateTime) {

        final Date   modelsideValueAsJavaUtilDate = Date.from(((ZonedDateTime)this.modelsideValue).toInstant());
        final String formattedViewsideValue       = this.formatModelsideValue(modelsideValueAsJavaUtilDate);
        return formattedViewsideValue;

        // all other single objects translate to i18n
      } else {
        final String i18nViewsideValue = this.getMmI18nText(MmMessageType.SHORT, this.modelsideValue);
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
    this.ensureInitialization();

    // TODO MmImplementationLeporelloTab getMmViewTab
    return null;
  }

  /**
   * Returns <code>true</code>, if the tab is active.
   *
   * @return  <code>True</code>, if the tab is active.
   */
  public boolean isMmActive() {
    this.ensureInitialization();

    final MmLeporelloTab thisDeclaration = (MmLeporelloTab)this.declaration;
    final MmLeporelloTab selectedTab     = this.parentLeporello.getMmSelectedTab();
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
    this.ensureInitialization();

    final MmLeporelloTab thisDeclaration = (MmLeporelloTab)this.declaration;
    final MmLeporelloTab selectedTab     = this.parentLeporello.getMmSelectedTab();
    return (thisDeclaration == selectedTab);
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return  A new MmJsfBridge for this mimic.
   */
  @Override protected MmJsfBridge<?, ?, ?> createMmJsfBridge() {
    return new MmJsfBridgeLeporelloTab(this);
  }

  /**
   * Initializes this mimic after constructor phase, calls super.initialize(), if you override this method, you must call
   * super.initialize()!
   */
  @Override protected void initialize() {
    super.initialize();
    this.parentLeporello = this.getImplementationAncestorOfType(MmImplementationLeporello.class);
  }

  /**
   * Initialize this mimic after constructor phase.
   */
  @Override protected void initializeConfiguration() {
    // evaluate annotation
    this.checkForIllegalAnnotationsOtherThan(this.declaration, MmLeporelloTabAnnotation.class);

    MmLeporelloTabAnnotation annotation = this.findAnnotation(this.declaration, MmLeporelloTabAnnotation.class);

    if (annotation == null) {

      // if there is no annotation, set default configuration
      this.configuration = new MmConfigurationLeporelloTab();
    } else {
      this.configuration = new MmConfigurationLeporelloTab(annotation);
    }
  }

}
