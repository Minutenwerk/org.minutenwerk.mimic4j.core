package org.minutenwerk.mimic4j.impl;

import java.io.StringWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.URI;

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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmInformationableModel;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReferencePathProvider;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.api.exception.MmDataModelConverterException;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.CONSTRUCTION_COMPLETE;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.INITIALIZED;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.IN_CONSTRUCTION;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.provided.MmSessionContext;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * MmBaseImplementation is the abstract base class for the implementation part of all mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-construction, group-postconstruct, group-initialization, group-override, group-i18n, group-helper
 */
public abstract class MmBaseImplementation<DECLARATION extends MmBaseDeclaration<?, ?>,
  CONFIGURATION extends MmBaseConfiguration, ANNOTATION extends Annotation> implements MmMimic {

  /** End of line characters of operating system in use. */
  public static final String                          NL                            = System.getProperty("line.separator");

  /** Logger of this class. */
  private static final Logger                         LOGGER                        = LogManager.getLogger(MmBaseImplementation.class);

  /** Constant for default root locale in case of no session context. */
  public static final Locale                          NO_SESSION_CONTEXT_LOCALE     = Locale.GERMAN;

  /** The state of initialization during constructor and initialization phase. */
  protected final MmInitialState                      initialState;

  /** The declaration parent of this mimic, is set in constructor phase. */
  protected final MmBaseDeclaration<?, ?>             declarationParent;

  /** The implementation parent of this mimic, is set in constructor phase. */
  protected final MmBaseImplementation<?, ?, ?>       implementationParent;

  /** The type of first optional generic parameter of this mimic, is set in constructor phase. */
  protected final Type                                typeOfFirstGenericParameter;

  /** The configuration of fixed values. */
  protected final CONFIGURATION                       configuration;

  /** The name of this mimic, is set in constructor phase. */
  protected final String                              name;

  /** The parentPath of this mimic is evaluated during initialization phase of its parent mimic. */
  protected final String                              parentPath;

  /** The root ancestor of this mimic, is set in constructor phase. */
  protected final MmBaseImplementation<?, ?, ?>       root;

  /** <code>True</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}. Is set in constructor phase. */
  protected final boolean                             isRuntimeMimic;

  /** All direct children are of type <code>MmBaseImplementation</code>. */
  protected final List<MmBaseImplementation<?, ?, ?>> implementationChildren;

  /** All direct children of the declaration are of type <code>MmMimic</code>. */
  protected final List<MmMimic>                       declarationChildren;

  /** All runtime children are of type <code>MmBaseImplementation</code>. Children are added at runtime. */
  protected final List<MmBaseImplementation<?, ?, ?>> runtimeImplementationChildren;

  /** All runtime declaration children are of type <code>MmMimic</code>. Children are added at runtime. */
  protected final List<MmMimic>                       runtimeDeclarationChildren;

  /** The MmJsfBridge of this mimic, which connects it to a JSF view component, is set in constructor phase. */
  protected final MmJsfBridge<?, ?, ?>                mmJsfBridge;

  /** This or an ancestor mimic, which delivers a reference path, may be null. Is set in initialization phase. */
  protected final MmReferencePathProvider             referencableAncestor;

  /** The declaration part of this implementation is the declaration. Is set in postconstruct phase. */
  protected DECLARATION                               declaration;

  /** Spring message provider with support for parameters and i18n. */
  protected MessageSource                             messageSource;

  /** The user's session context. */
  protected MmSessionContext                          sessionContext;

  /** The current locale. */
  protected Locale                                    locale;

  /** Iterator of fields of children of declaration part, is used only during constructor phase. */
  private Iterator<Field>                             declarationChildrenFields;

  /**
   * Creates a new MmBaseImplementation instance.
   *
   * @param  pDeclarationParent  The declaration part of the parent mimic.
   */
  public MmBaseImplementation(final MmDeclarationMimic pDeclarationParent) {
    this(pDeclarationParent, null);
  }

  /**
   * Creates a new MmCompositeImplementation instance. After this constructor assigned values are:
   *
   * <ul>
   *   <li>initialState is IN_CONSTRUCTION</li>
   *   <li>declarationParent is assigned, for root it is null</li>
   *   <li>implementationParent is assigned, for root it is null</li>
   *   <li>typeOfFirstGenericParameter is assigned, may be null</li>
   *   <li>configuration is assigned</li>
   *   <li>name is assigned, may be blank</li>
   *   <li>parentPath is assigned, may be blank</li>
   *   <li>id is assigned, and saved in configuration</li>
   *   <li>root is assigned, for root itself root is null</li>
   *   <li>isRuntimeMimic is assigned</li>
   *   <li>this mimic is added as implementationChildren of parent</li>
   *   <li>this mimic is added as declarationChildren of parent</li>
   *   <li>referencableAncestor is assigned</li>
   *   <li>mmJsfBridge is assigned</li>
   * </ul>
   *
   * <p>This constructor has been called by constructor of declaration part. After constructor the constructor of declaration part calls
   * method onPostConstruct(), which assigns:</p>
   *
   * <ul>
   *   <li>declaration is assigned</li>
   *   <li>initialState is CONSTRUCTION_COMPLETE</li>
   * </ul>
   *
   * <p>After constructor of this mimic all constructors of child fields are being called, which assign:</p>
   *
   * <ul>
   *   <li>children add themselves as implementationChildren of parent</li>
   *   <li>children add themselves as declarationChildren of parent</li>
   * </ul>
   *
   * <p>At first call of any mimic method, assureInitialization() calls method initialize(). After initialization:</p>
   *
   * <ul>
   *   <li>initialState is INITIALIZED</li>
   * </ul>
   *
   * <p>Method initialize() calls method initialize() for all children:</p>
   *
   * @param   pDeclarationParent  The declaration part of the parent mimic.
   * @param   pRuntimeIndex       An integer index if this mimic is a runtime mimic, e.g. a {@link MmTableRow}.
   *
   * @throws  IllegalStateException  in case of parameter pDeclarationParent does not have an implementation part, or root ancestor of
   *                                 subtree is not of type {@link MmImplementationRoot}.
   */
  public MmBaseImplementation(final MmDeclarationMimic pDeclarationParent, final Integer pRuntimeIndex) {
    initialState = new MmInitialState();
    if (LOGGER.isDebugEnabled()) {
      if (pDeclarationParent != null) {
        if (!(pDeclarationParent instanceof MmBaseDeclaration<?, ?>)) {
          throw new IllegalStateException("Parameter pDeclarationParent " + pDeclarationParent + " must be of type MmBaseDeclaration<?,?>");
        }
        if (((MmBaseDeclaration<?, ?>)pDeclarationParent).implementation == null) {
          throw new IllegalStateException("Parameter pDeclarationParent " + pDeclarationParent + " must have an implementation");
        }
        if (((MmBaseDeclaration<?, ?>)pDeclarationParent).implementation.initialState.isNotEqualOrLater(CONSTRUCTION_COMPLETE)) {
          throw new IllegalStateException("Parameter implementationParent " + ((MmBaseDeclaration<?, ?>)pDeclarationParent).implementation
            + " must be at least in state CONSTRUCTION_COMPLETE");
        }
      }
    }
    if (pDeclarationParent == null) {

      // if there is no parent, this is the root
      // set reference to declaration part of parent mimic to null
      declarationParent           = null;

      // set reference to implementation part of parent mimic to null
      implementationParent        = null;

      // evaluate typeOfFirstGenericParameter
      typeOfFirstGenericParameter = null;

      // evaluate annotation
      final ANNOTATION annotation = null;
      // TODO evaluate anntotation on class

      // evaluate configuration
      configuration = onConstructConfiguration(annotation);

      // set name
      name          = "";

      // set parentPath
      parentPath    = "";

      // evaluate id
      onConstructId(configuration, name, parentPath);

      // set reference to root to this
      root           = this;

      // root is compiletime mimic
      isRuntimeMimic = false;

    } else {

      // evaluate reference to declaration part of parent mimic
      declarationParent    = (MmBaseDeclaration<?, ?>)pDeclarationParent;

      // evaluate reference to implementation part of parent
      implementationParent = declarationParent.implementation;

      // evaluate field of this mimic's declaration in declarationParent
      final Field field    = onConstructField(implementationParent);

      // evaluate type of first generic, if exists, e.g. MmEnum<ENUM_TYPE>
      typeOfFirstGenericParameter = MmJavaHelper.getFirstGenericType(field);

      // evaluate annotation
      final ANNOTATION annotation = onConstructAnnotation(field);

      // evaluate configuration
      configuration = onConstructConfiguration(annotation);

      // evaluate name
      name          = onConstructName(field, pRuntimeIndex);

      // evaluate parentPath
      parentPath    = onConstructParentPath(implementationParent);

      // evaluate id
      onConstructId(configuration, name, parentPath);

      // evaluate reference to root ancestor
      root           = onConstructRoot(this);

      // evaluate is runtime
      isRuntimeMimic = onConstructRuntimeMimic(implementationParent, pRuntimeIndex);
    }

    // create lists for child mimics
    implementationChildren        = new ArrayList<>();
    declarationChildren           = new ArrayList<>();
    runtimeImplementationChildren = new ArrayList<>();
    runtimeDeclarationChildren    = new ArrayList<>();

    // evaluate ancestor for reference path, file and params
    referencableAncestor          = getMmImplementationAncestorOfType(MmReferencePathProvider.class);

    // evaluate bridge for jsf tags
    mmJsfBridge                   = onConstructJsfBridge();
  }

  /**
   * Returns the implementation part of the specified declaration.
   *
   * @param         pDeclaration  The specified declaration.
   *
   * @return        The implementation part of the specified declaration.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  protected static <T extends MmBaseImplementation<?, ?, ?>> T getImplementation(MmBaseDeclaration<?, ?> pDeclaration) {
    return (T)pDeclaration.implementation;
  }

  /**
   * Evaluates and returns the annotation on a specified mimic.
   *
   * @param         pField  The specified mimic.
   *
   * @return        The annotation.
   *
   * @jalopy.group  group-construction
   */
  @SuppressWarnings("unchecked")
  protected ANNOTATION onConstructAnnotation(final Field pField) {
    if (pField != null) {
      for (Annotation annotation : pField.getAnnotations()) {
        if (annotation.annotationType().isAnnotationPresent(MmMetaAnnotation.class)) {
          return (ANNOTATION)annotation;
        }
      }
    }

    // TODO MmBaseImplementation find annotation on class!
    return null;
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param         pAnnotation  The specified annotation, may be null.
   *
   * @return        Configuration of this mimic.
   *
   * @jalopy.group  group-construction
   */
  protected abstract CONFIGURATION onConstructConfiguration(ANNOTATION pAnnotation);

  /**
   * Evaluates and returns the field (meta information of a mimic) on a specified parent.
   *
   * @param         pImplementationParent  The specified parent.
   *
   * @return        The field of this mimic.
   *
   * @jalopy.group  group-construction
   */
  protected Field onConstructField(final MmBaseImplementation<?, ?, ?> pImplementationParent) {
    if (pImplementationParent.declarationChildrenFields == null) {
      pImplementationParent.declarationChildrenFields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(
          pImplementationParent.declaration.getClass()).iterator();
    }
    if (pImplementationParent.declarationChildrenFields.hasNext()) {
      return pImplementationParent.declarationChildrenFields.next();
    } else {
      return null;
    }
  }

  /**
   * Evaluates and returns the id of a mimic.
   *
   * @param         pConfiguration  The specified configuration of the mimic.
   * @param         pName           The name of the mimic.
   * @param         pParentPath     The parent path of the mimic.
   *
   * @jalopy.group  group-construction
   */
  protected void onConstructId(final MmBaseConfiguration pConfiguration, final String pName, final String pParentPath) {
    // if id is not defined yet, set id to full name
    if (pConfiguration.getId().equals(MmBaseConfiguration.UNDEFINED_ID) && !pName.isEmpty()) {
      String newId = pName;
      if (!pParentPath.isEmpty()) {
        newId = pParentPath.replaceAll("\\.", "-") + "-" + pName;
      }
      pConfiguration.setId(newId);
    }
  }

  /**
   * Evaluates and returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return        A new MmJsfBridge for this mimic.
   *
   * @jalopy.group  group-construction
   */
  protected MmJsfBridge<?, ?, ?> onConstructJsfBridge() {
    return null;
  }

  /**
   * Evaluates and returns the name of this mimic. The name is derived from the specified field, if the mimic is declared as a field of
   * another mimic. If there is a runtime index, the name is derived from the index value. Otherwise the name is an empty string.
   *
   * @param         pField         The specified field, or null.
   * @param         pRuntimeIndex  The specified runtime index, or null.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-construction
   */
  protected String onConstructName(final Field pField, final Integer pRuntimeIndex) {
    if (pField != null) {
      return pField.getName();
    } else if (pRuntimeIndex != null) {
      return "run" + pRuntimeIndex;
    } else {
      return "";
    }
  }

  /**
   * Evaluates and returns the parent path of specified mimic.
   *
   * @param         pImplementationParent  The specified mimic.
   *
   * @return        The parent path.
   *
   * @jalopy.group  group-construction
   */
  protected String onConstructParentPath(final MmBaseImplementation<?, ?, ?> pImplementationParent) {
    if (pImplementationParent.parentPath.isEmpty()) {
      return pImplementationParent.name;
    } else {
      return pImplementationParent.parentPath + "." + pImplementationParent.name;
    }
  }

  /**
   * Evaluates and returns reference to root ancestor for specified mimic.
   *
   * @param         pMm  The specified mimic.
   *
   * @return        The reference to root ancestor.
   *
   * @throws        IllegalStateException  In case of root ancestor of subtree is not of type MmImplementationRoot.
   *
   * @jalopy.group  group-construction
   */
  protected MmBaseImplementation<?, ?, ?> onConstructRoot(final MmBaseImplementation<?, ?, ?> pMm) {
    if (pMm.initialState.isNotInConstruction()) {
      throw new IllegalStateException("Initial state must be IN_CONSTRUCTION");
    }

    MmBaseImplementation<?, ?, ?> tempParent = pMm.implementationParent;
    MmBaseImplementation<?, ?, ?> tempRoot   = tempParent.root;
    if (tempRoot != null) {
      return tempRoot;
    }

    while (tempParent.implementationParent != null) {
      tempParent = tempParent.implementationParent;
      tempRoot   = tempParent.root;
      if (tempRoot != null) {
        return tempRoot;
      }
    }

    return tempParent;
  }

  /**
   * Evaluates whether and returns True, if this mimic is created at runtime.
   *
   * @param         pImplementationParent  The specified implementationParent of this mimic, may be null.
   * @param         pRuntimeIndex          The runtime index of this mimic, may be null.
   *
   * @return        True, if this mimic is created at runtime.
   *
   * @jalopy.group  group-construction
   */
  protected boolean onConstructRuntimeMimic(final MmBaseImplementation<?, ?, ?> pImplementationParent, final Integer pRuntimeIndex) {
    return (pRuntimeIndex != null) || ((pImplementationParent != null) && pImplementationParent.isRuntimeMimic);
  }

  /**
   * Adds a specified child to runtime children.
   *
   * @param         pChild  The specified child to be added.
   *
   * @throws        IllegalStateException     In case of initial state is not INITIALIZED.
   * @throws        IllegalArgumentException  In case of specified child is null or doesn't have an implementation.
   *
   * @jalopy.group  group-postconstruct
   */
  protected void addDeclarationAsChildToParent(MmBaseDeclaration<?, ?> pChild) {
    if (implementationParent != null) {
      if (LOGGER.isDebugEnabled()) {
        if ((implementationParent.initialState.isNot(CONSTRUCTION_COMPLETE)) && (implementationParent.initialState.isNotInitialized())) {
          throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or INITIALIZED");
        }
        if (pChild == null) {
          throw new IllegalArgumentException("Parameter pChild cannot be null");
        }
        if (pChild.implementation == null) {
          throw new IllegalStateException("Parameter pChild " + pChild + " must have an implementation");
        }
      }

      // get implementation part of child
      MmBaseImplementation<?, ?, ?> childImplementation = pChild.implementation;

      if (pChild.isMmRuntimeMimic()) {

        // if child not in list yet, add child to list of
        // runtime declarationChildren and list of runtime implementationChildren
        if (!implementationParent.runtimeDeclarationChildren.contains(pChild)) {
          implementationParent.runtimeDeclarationChildren.add(pChild);
          implementationParent.runtimeImplementationChildren.add(childImplementation);
        }

      } else {

        // if child not in list yet, add child to list of
        // declarationChildren and list of implementationChildren
        if (!implementationParent.declarationChildren.contains(pChild)) {
          implementationParent.declarationChildren.add(pChild);
          implementationParent.implementationChildren.add(childImplementation);
        }
      }
    }
  }

  /**
   * Set declaration part of this mimic. This method onPostConstruct() is called from constructor of class {@link MmBaseDeclaration} after
   * construction phase of implementation part is finished. This method transistions initialState from {@link IN_CONSTRUCTION} to
   * {@link CONSTRUCTION_COMPLETE}. This method is <code>package final</code> so it can be not called or overridden from anywhere else.
   *
   * @param         pDeclaration  The declaration part to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link IN_CONSTRUCTION}, or reference to declaration part is NOT
   *                                          null.
   * @throws        IllegalArgumentException  in case of parameter pDeclaration is null, or reverse reference of declaration is not this.
   *
   * @jalopy.group  group-postconstruct
   */
  @SuppressWarnings("unchecked")
  /* package */ final void onPostConstruct(MmBaseDeclaration<?, ?> pDeclaration) {
    if (LOGGER.isDebugEnabled()) {
      if (initialState.isNot(IN_CONSTRUCTION)) {
        throw new IllegalStateException("Initial state must be IN_CONSTRUCTION");
      }
      if (pDeclaration == null) {
        throw new IllegalArgumentException("Parameter pDeclaration cannot be null");
      }
      if (pDeclaration.implementation != this) {
        throw new IllegalArgumentException("Parameter pDeclaration " + pDeclaration + " must reference this implementation");
      }
      if (declaration != null) {
        throw new IllegalStateException("Reference to declaration part must be null");
      }
    }

    // set declaration part
    declaration = (DECLARATION)pDeclaration;

    // if there is an implementation parent, add this mimic as declaration child
    addDeclarationAsChildToParent(declaration);

    initialState.set(CONSTRUCTION_COMPLETE);

    if (LOGGER.isDebugEnabled()) {
      if (!declarationChildren.isEmpty()) {
        throw new IllegalStateException("declarationChildren must be empty");
      }
      if (!implementationChildren.isEmpty()) {
        throw new IllegalStateException("implementationChildren must be empty");
      }
      if (!runtimeDeclarationChildren.isEmpty()) {
        throw new IllegalStateException("runtimeDeclarationChildren must be empty");
      }
      if (!runtimeImplementationChildren.isEmpty()) {
        throw new IllegalStateException("runtimeImplementationChildren must be empty");
      }
    }
  }

  /**
   * Initializes this mimic after constructor phase, calls super.initialize(), if you override this method, you must call
   * super.initialize()!
   *
   * @throws        IllegalStateException  in case of initial state is not {@link CONSTRUCTION_COMPLETE} or reference to declaration part of
   *                                       mimic is not defined.
   *
   * @jalopy.group  group-initialization
   */
  public final void initialize() {
    if (LOGGER.isDebugEnabled()) {
      if (initialState.isNot(CONSTRUCTION_COMPLETE)) {
        throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE");
      }
      if (declaration == null) {
        throw new IllegalStateException("Reference to declaration part must be defined");
      }
    }

    // initialize this mimic
    onInitialization();

    // set state to INITIALIZED
    initialState.set(INITIALIZED);

    // initialize children
    for (MmBaseImplementation<?, ?, ?> implementationChild : implementationChildren) {
      implementationChild.initialize();
    }
    for (MmBaseImplementation<?, ?, ?> runtimeImplementationChild : runtimeImplementationChildren) {
      runtimeImplementationChild.initialize();
    }
  }

  /**
   * Assures that mimic will be initialized, if it is not initialized yet.
   *
   * @jalopy.group  group-initialization
   */
  protected void assureInitialization() {
    if (initialState.isNotInitialized()) {
      if ((implementationParent != null) && implementationParent.initialState.isNotInitialized()) {
        implementationParent.assureInitialization();
      } else {
        initialize();
      }
    }
  }

  /**
   * Initializes this mimic after constructor phase, calls super.onInitialization(), if you override this method, you must call
   * super.onInitialization()!
   *
   * @jalopy.group  group-initialization
   */
  protected void onInitialization() {
  }

  /**
   * Returns the full name of this mimic including the path of its ancestors' names like <code>grandparent.parent.child</code>, or an empty
   * String if the full name is undefined. The full name is evaluated during initialization phase and derived from the field declaration
   * name in its parent class.
   *
   * @return        The full name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmFullName() {
    assureInitialization();

    if (parentPath.isEmpty()) {
      return name;
    } else {
      return parentPath + "." + name;
    }
  }

  /**
   * Returns id of this mimic. The id is unique within the subtree of a MmRoot.
   *
   * @return        The id of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmId() {
    assureInitialization();

    return getConfiguration().getId();
  }

  /**
   * Returns a long description. The long description is evaluated from declaration method <code>callbackMmGetLongDescription</code>. If
   * <code>callbackMmGetLongDescription</code> is not overridden, the long description is evaluated from configuration attribute <code>
   * MmConfiguration.longDescription</code>.
   *
   * @return        A long description.
   *
   * @throws        IllegalStateException  In case of callbackMmGetLongDescription returns null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmLongDescription() {
    assureInitialization();

    // retrieve referencable model
    final MmReferencableModel dataModel = getMmReferencableModel();

    if (dataModel instanceof MmInformationableModel) {
      return getMmDescription((MmInformationableModel)dataModel, MmMessageType.LONG);
    } else if (dataModel != null) {
      return getMmDescription(dataModel, MmMessageType.LONG);
    } else {
      final String i18nLongDescription = getMmI18nText(MmMessageType.LONG);
      final String returnString        = declaration.callbackMmGetLongDescription(i18nLongDescription);
      if (LOGGER.isDebugEnabled()) {
        if (returnString == null) {
          throw new IllegalStateException("callbackMmGetLongDescription cannot return null for " + this);
        }
      }
      return returnString;
    }
  }

  /**
   * Returns the name of this mimic, or an empty String if the name is undefined. The name is evaluated during initialization phase and
   * derived from the field declaration name in its parent class.
   *
   * @return        The name of this mimic.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmName() {
    assureInitialization();

    return name;
  }

  /**
   * Returns the URI of this mimic for current data model, or a fixed URI if there is no current data model.
   *
   * @return        The URI of this mimic for current data model, or a fixed URI if there is no current data model.
   *
   * @throws        IllegalStateException  TODOC
   *
   * @jalopy.group  group-override
   */
  @Override
  public URI getMmSelfReference() {
    assureInitialization();

    // retrieve self reference path
    if (referencableAncestor == null) {
      throw new IllegalStateException("no definition of referencable ancestor for " + this
        + " This mimic or one of its parents must implement MmReferencePathProvider");
    }

    UriComponents selfReferencePath = referencableAncestor.getMmReferencePath();
    if (selfReferencePath == null) {
      throw new IllegalStateException("no definition of self reference path for " + this
        + ", MmReferencePathProvider of this mimic must provide self reference path.");
    }

    // add anchor to self reference path
    if (configuration.id != "") {
      selfReferencePath = UriComponentsBuilder.fromPath(selfReferencePath.getPath()).path("#" + configuration.id).build();
    }

    // retrieve data model, may be null
    final MmReferencableModel dataModel = getMmReferencableModel();

    // if self reference is an URI for a specified referencable data model
    if ((dataModel != null) && (dataModel instanceof MmReferencableModel)) {
      final List<String> modelReferenceParams = ((MmReferencableModel)dataModel).getMmReferenceParams();
      return declaration.callbackMmGetSelfReference(selfReferencePath, dataModel, modelReferenceParams);

    } else {
      return declaration.callbackMmGetSelfReference(selfReferencePath, dataModel, Collections.emptyList());
    }
  }

  /**
   * Returns the self reference (aka link) of this object for a specified data model.
   *
   * @param         pDataModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return        The self reference (aka link) of this object for a specified data model.
   *
   * @throws        IllegalStateException  TODOC
   *
   * @jalopy.group  group-override
   */
  @Override
  public URI getMmSelfReferenceForModel(MmReferencableModel pDataModel) {
    assureInitialization();

    // retrieve self reference path
    if (referencableAncestor == null) {
      throw new IllegalStateException("no definition of referencable ancestor for " + this
        + " This mimic or one of its parents must implement MmReferencePathProvider");
    }

    UriComponents selfReferencePath = referencableAncestor.getMmReferencePath();
    if (selfReferencePath == null) {
      throw new IllegalStateException("no definition of self reference path for " + this
        + ", MmReferencePathProvider of this mimic must provide self reference path.");
    }

    // add anchor to self reference path
    if (configuration.id != "") {
      selfReferencePath = UriComponentsBuilder.fromPath(selfReferencePath.getPath()).path("#" + configuration.id).build();
    }

    // if self reference is an URI for a specified referencable data model
    if ((pDataModel != null) && (pDataModel instanceof MmReferencableModel)) {
      final List<String> modelReferenceParams = ((MmReferencableModel)pDataModel).getMmReferenceParams();
      return declaration.callbackMmGetSelfReference(selfReferencePath, pDataModel, modelReferenceParams);

    } else {
      return declaration.callbackMmGetSelfReference(selfReferencePath, pDataModel, Collections.emptyList());
    }
  }

  /**
   * Returns a short description. The short description is evaluated from declaration method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> is not overridden, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @throws        IllegalStateException  In case of callbackMmGetShortDescription returns null.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmShortDescription() {
    assureInitialization();

    // retrieve referencable model
    final MmReferencableModel dataModel = getMmReferencableModel();

    if (dataModel instanceof MmInformationableModel) {
      return getMmDescription((MmInformationableModel)dataModel, MmMessageType.SHORT);
    } else if (dataModel != null) {
      return getMmDescription(dataModel, MmMessageType.SHORT);
    } else {
      final String i18nLongDescription = getMmI18nText(MmMessageType.SHORT);
      final String returnString        = declaration.callbackMmGetShortDescription(i18nLongDescription);
      if (LOGGER.isDebugEnabled()) {
        if (returnString == null) {
          throw new IllegalStateException("callbackMmGetShortDescription cannot return null for " + this);
        }
      }
      return returnString;
    }
  }

  /**
   * Returns a String containing space delimited <code>CSS</code> style classes. The style classes are evaluated from callback method
   * {@link MmBaseCallback#callbackMmGetStyleClasses()}.
   *
   * @return        A String containing space delimited <code>CSS</code> style classes.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmStyleClasses() {
    assureInitialization();

    final String returnString = declaration.callbackMmGetStyleClasses("");
    if (returnString == null) {
      return "";
    } else {
      return returnString;
    }
  }

  /**
   * Returns <code>true</code>, if the mimic is enabled (default is <code>false</code>). Is controlled by parents state of enabled and
   * callback method {@link MmBaseCallback#callbackMmIsEnabled()}. Callback method returns configuration of annotation attribute <code>
   * enabled</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is enabled.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmEnabled() {
    assureInitialization();

    if (implementationParent == null) {
      return declaration.callbackMmIsEnabled(configuration.isEnabled());
    } else {
      return implementationParent.isMmEnabled() && declaration.callbackMmIsEnabled(configuration.isEnabled());
    }
  }

  /**
   * Returns <code>true</code>, if the mimic is readOnly (default is <code>false</code>). Is controlled by parents state of readonly and
   * callback method {@link MmBaseCallback#callbackMmIsReadOnly()}. Callback method returns configuration of annotation attribute <code>
   * readonly</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is read only.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmReadOnly() {
    assureInitialization();

    if (implementationParent == null) {
      return declaration.callbackMmIsReadOnly(configuration.isReadOnly());
    } else {
      return implementationParent.isMmReadOnly() || declaration.callbackMmIsReadOnly(configuration.isReadOnly());
    }
  }

  /**
   * Returns <code>true</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}.
   *
   * @return        <code>True</code>, if the mimic has been created at runtime.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmRuntimeMimic() {
    // do NOT insert assureInitialization() here, because this method isMmRuntimeMimic() is called during constructor phase.
    return isRuntimeMimic;
  }

  /**
   * Returns <code>true</code>, if the mimic is visible (default is <code>false</code>). Is controlled by parents state of visible and
   * callback method {@link MmBaseCallback#callbackMmIsVisible()}. Callback method returns configuration of annotation attribute <code>
   * visible</code> on this mimic. Developer can configure annotation and can override callback method.
   *
   * @return        <code>True</code>, if the mimic is visible.
   *
   * @jalopy.group  group-override
   */
  @Override
  public boolean isMmVisible() {
    assureInitialization();

    if (implementationParent == null) {
      return declaration.callbackMmIsVisible(configuration.isVisible());
    } else {
      return implementationParent.isMmVisible() && declaration.callbackMmIsVisible(configuration.isVisible());
    }
  }

  /**
   * Returns some information about this object for development purposes like debugging and logging. Doesn't have side effects. May change
   * at any time.
   *
   * @return        Some information about this object for development purposes like debugging and logging.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(");

    Class<?> clazz = declaration.getClass();
    while (clazz.getSimpleName().isEmpty()) {
      clazz = clazz.getSuperclass();
    }
    sb.append(clazz.getSimpleName());
    if (getConfiguration() != null) {
      sb.append(" id=");
      sb.append(configuration.id);
    } else if (!parentPath.isEmpty()) {
      sb.append(" path=");
      sb.append(parentPath);
      sb.append(".");
      sb.append(name);
    } else if (!name.isEmpty()) {
      sb.append(" name=");
      sb.append(name);
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * Returns the format pattern for formatting data model value to description value.
   *
   * @return        The format pattern for formatting data model value to description value.
   *
   * @jalopy.group  group-i18n
   */
  public String getMmFormatPattern() {
    return getMmI18nText(MmMessageType.FORMAT);
  }

  /**
   * Returns an internationalized version of the message of this mimic for a specified message type.
   *
   * @param         pMessageType  The specified message type.
   * @param         pArguments    Optional list of message arguments.
   *
   * @return        The internationalized version of a specified message.
   *
   * @jalopy.group  group-i18n
   */
  public String getMmI18nText(MmMessageType pMessageType, Object... pArguments) {
    return getMmI18nText(getMmId(), pMessageType, pArguments);
  }

  /**
   * Returns an internationalized version for a specified message id and type.
   *
   * @param         pMessageId    The specified id of the message to be internationalized.
   * @param         pMessageType  The specified type of the message to be internationalized.
   * @param         pArguments    Optional list of message arguments.
   *
   * @return        The internationalized message.
   *
   * @throws        RuntimeException  TODOC
   *
   * @jalopy.group  group-i18n
   */
  public String getMmI18nText(String pMessageId, MmMessageType pMessageType, Object... pArguments) {
    assureInitialization();

    if (root.messageSource != null) {
      String messageCode = pMessageId;
      if ((pMessageType != null) && (pMessageType != MmMessageType.TEXT)) {
        messageCode = messageCode + "." + pMessageType.getSuffix();
      }
      try {
        return root.messageSource.getMessage(messageCode, pArguments, getMmLocale());
      } catch (NoSuchMessageException e) {
        if ((pMessageType == MmMessageType.TEXT) || (pMessageType == MmMessageType.SHORT) || (pMessageType == MmMessageType.LONG)) {
          LOGGER.warn("no message for >" + messageCode + "< for locale " + getMmLocale());
          return messageCode;
        } else {
          throw new RuntimeException("no message for >" + messageCode + "< for locale " + getMmLocale());
        }
      }

    } else {
      LOGGER.warn("getMmI18nText: {}, {}: no root message source", pMessageId, pMessageType);

      // for unit tests this root returns last part of message id
      String returnString = pMessageId;
      if (returnString.contains(".")) {
        returnString = returnString.substring(returnString.lastIndexOf(".") + 1);
      }
      return returnString;
    }
  }

  /**
   * Returns the formatted specified view model value, formatted depending on its type, returns otherwise the unformatted view model value.
   *
   * @param         pViewModelValue  The specified view model value.
   * @param         pFormatPattern   The specified format pattern.
   *
   * @return        The formatted view model value, formatted depending on its type.
   *
   * @throws        MmDataModelConverterException  In case of conversion fails.
   *
   * @jalopy.group  group-i18n
   */
  protected Object formatViewModelValue(Object pViewModelValue, String pFormatPattern) {
    if (pViewModelValue == null) {
      return null;

    } else if (pViewModelValue instanceof String) {
      return pViewModelValue;

    } else if ((pViewModelValue instanceof Integer) || (pViewModelValue instanceof Long) || (pViewModelValue.getClass().equals(long.class))
        || (pViewModelValue instanceof Float) || (pViewModelValue.getClass().equals(float.class)) || (pViewModelValue instanceof Double)
        || (pViewModelValue.getClass().equals(double.class))) {
      try {
        final NumberFormat numberFormatter = getMmNumberFormatter(pFormatPattern, false);
        return numberFormatter.format(pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if ((pViewModelValue instanceof BigDecimal) || (pViewModelValue instanceof BigInteger)) {
      try {
        final NumberFormat numberFormatter = getMmNumberFormatter(pFormatPattern, true);
        return numberFormatter.format(pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof Duration) {
      try {
        return ((Duration)pViewModelValue).toString();
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof Instant) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return dateTimeFormatter.format((Instant)pViewModelValue);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof LocalTime) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((LocalTime)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof LocalDate) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((LocalDate)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof LocalDateTime) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((LocalDateTime)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof ZonedDateTime) {
      try {
        final DateTimeFormatter dateTimeFormatter = getMmDateTimeFormatter(pFormatPattern);
        return ((ZonedDateTime)pViewModelValue).format(dateTimeFormatter);
      } catch (IllegalArgumentException e) {
        throw new MmDataModelConverterException(this,
          "Cannot format view model value: " + pViewModelValue + " by pattern >" + pFormatPattern + "<");
      }

    } else if (pViewModelValue instanceof Enum<?>) {
      final Enum<?> enumValue    = (Enum<?>)pViewModelValue;
      final String  enumTypeName = enumValue.getClass().getSimpleName();
      return root.getMmI18nText(enumTypeName + "." + enumValue.name(), MmMessageType.SHORT);

    } else if ((pViewModelValue instanceof Boolean) || (pViewModelValue.getClass().equals(boolean.class))) {
      if ((Boolean)pViewModelValue) {
        return root.getMmI18nText(getMmId() + ".true", MmMessageType.SHORT);
      } else {
        return root.getMmI18nText(getMmId() + ".false", MmMessageType.SHORT);
      }
    } else {
      return pViewModelValue;
    }
  }

  /**
   * Returns the initialized date formatter of this mimic for specified format pattern..
   *
   * @param         pFormatPattern  The specified format pattern.
   *
   * @return        The initialized date formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected DateTimeFormatter getMmDateTimeFormatter(final String pFormatPattern) {
    final DateTimeFormatter returnDateFormatter = DateTimeFormatter.ofPattern(pFormatPattern);
    return returnDateFormatter;
  }

  /**
   * Returns view text of the link for specified MmInformationableModel.
   *
   * @param         pInformationable  The specified MmInformationableModel.
   * @param         pMessageType      The specified pMessageType e.g. SHORT or LONG.
   *
   * @return        The view text of the link.
   *
   * @jalopy.group  group-i18n
   */
  protected String getMmDescription(final MmInformationableModel pInformationable, final MmMessageType pMessageType) {
    // if view value is MmInformationableModel, transform into an array of formatted objects
    final Object[] viewValueArray     = ((MmInformationableModel)pInformationable).getMmInfo();

    // retrieve array of format patterns
    final String[] formatPatternArray = getMmFormatPattern().split("\\|");

    // apply each format pattern to corresponding view value
    for (int index = 0; index < ((Object[])viewValueArray).length; index++) {
      final Object viewValue = ((Object[])viewValueArray)[index];
      if ((viewValue != null) && !(viewValue instanceof String)) {
        final String formatPattern = formatPatternArray[index];
        viewValueArray[index] = formatViewModelValue(viewValue, formatPattern);
      }
    }

    // viewValueArray keeps an Object[], but as this is an Object as well, java still interprets it to be just one object
    // so to put an array of objects into varargs method parameter, there must be an explicit cast to Object[]
    final String i18nViewValue = getMmI18nText(pMessageType, (Object[])viewValueArray);
    return i18nViewValue;
  }

  /**
   * Returns view text of the link for specified single object.
   *
   * @param         pViewValue    The specified single object.
   * @param         pMessageType  The specified pMessageType e.g. SHORT or LONG.
   *
   * @return        The view text of the link.
   *
   * @jalopy.group  group-i18n
   */
  protected String getMmDescription(final Object pViewValue, final MmMessageType pMessageType) {
    // if view value is a single object

    // return empty String for null value
    if (pViewValue == null) {
      return "";

      // format number, boolean, duration, date, time and enum values
    } else if ((pViewValue instanceof Number) || (pViewValue instanceof Boolean) || (pViewValue instanceof Duration)
        || (pViewValue instanceof Instant) || (pViewValue instanceof LocalTime) || (pViewValue instanceof LocalDate)
        || (pViewValue instanceof LocalDateTime) || (pViewValue instanceof ZonedDateTime) || (pViewValue instanceof Enum<?>)) {

      // retrieve format pattern and format view value
      final String formatPattern           = getMmFormatPattern();
      final String formattedViewModelValue = (String)formatViewModelValue(pViewValue, formatPattern);

      // i18nize view value
      final String i18nViewValue           = getMmI18nText(pMessageType, formattedViewModelValue);
      return i18nViewValue;

      // all other types just i18nize
    } else {
      final String i18nalizedViewValue = getMmI18nText(pMessageType, pViewValue);
      return i18nalizedViewValue;
    }
  }

  /**
   * Returns the initialized number formatter of this mimic for specified format pattern.
   *
   * @param         pFormatPattern    The specified format pattern.
   * @param         pParseBigDecimal  True, if
   *
   * @return        The initialized number formatter of this mimic.
   *
   * @jalopy.group  group-i18n
   */
  protected NumberFormat getMmNumberFormatter(final String pFormatPattern, boolean pParseBigDecimal) {
    final Locale        locale                = root.getMmLocale();
    final NumberFormat  numberFormat          = NumberFormat.getNumberInstance(locale);
    final DecimalFormat returnNumberFormatter = (DecimalFormat)numberFormat;
    returnNumberFormatter.setParseBigDecimal(pParseBigDecimal);
    returnNumberFormatter.applyPattern(pFormatPattern);
    return returnNumberFormatter;
  }

  /**
   * Adds a specified child to runtime children, e.g. a MmTableRow.
   *
   * @param         pChild  The specified child to be added.
   *
   * @throws        IllegalStateException     In case of initial state is not INITIALIZED.
   * @throws        IllegalArgumentException  In case of specified child is null or doesn't have an implementation.
   *
   * @jalopy.group  group-helper
   */
  public void addRuntimeChild(MmBaseDeclaration<?, ?> pChild) {
    if (LOGGER.isDebugEnabled()) {
      if (initialState.isNotInitialized()) {
        throw new IllegalStateException("Initial state must be INITIALIZED");
      }
      if (pChild == null) {
        throw new IllegalArgumentException("Parameter pChild cannot be null");
      }
      if (pChild.implementation == null) {
        throw new IllegalArgumentException("Parameter pChild " + pChild + " must have an implementation");
      }
    }

    // get implementation part of child
    MmBaseImplementation<?, ?, ?> childImplementation = pChild.implementation;

    if (!runtimeDeclarationChildren.contains(pChild)) {
      runtimeDeclarationChildren.add(pChild);
      runtimeImplementationChildren.add(childImplementation);
    }
  }

  /**
   * Clears the list of runtime children of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public void clearRuntimeChildrenList() {
    runtimeDeclarationChildren.clear();
    runtimeImplementationChildren.clear();
  }

  /**
   * Returns the configuration of this mimic.
   *
   * @return        The configuration of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public CONFIGURATION getConfiguration() {
    return configuration;
  }

  /**
   * Returns the declaration part of this mimic.
   *
   * @return        The declaration part of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public DECLARATION getDeclaration() {
    return declaration;
  }

  /**
   * Returns the list of all direct children of specified mimic, which are instances of class <code>MmBaseDeclaration</code>, including
   * runtime children.
   *
   * @param         pDeclarationType  The specified mimic.
   *
   * @return        The list of all direct children of specified mimic of type <code>MmBaseImplementation</code>, including runtime
   *                children.
   *
   * @throws        IllegalArgumentException  In case of parameter is not a subclass of MmBaseDeclaration.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  public <T extends MmMimic> List<T> getDirectDeclarationChildrenOfType(Class<T> pDeclarationType) {
    if (!(MmBaseDeclaration.class.isAssignableFrom(pDeclarationType))) {
      throw new IllegalArgumentException("Class " + pDeclarationType.getSimpleName() + " is not a subclass of MmBaseDeclaration");
    }
    return (List<T>)Stream.concat(declarationChildren.stream(), runtimeDeclarationChildren.stream()) //
    .filter(child -> pDeclarationType.isAssignableFrom(child.getClass())) //
    .collect(Collectors.toList());
  }

  /**
   * Returns the list of all direct implementation children of specified mimic, which are instances of class <code>
   * MmBaseImplementation</code>, including runtime children.
   *
   * @param         pImplementationType  The specified mimic.
   *
   * @return        The list of all direct children of specified mimic of type <code>MmBaseImplementation</code>, including runtime
   *                children.
   *
   * @throws        IllegalArgumentException  In case of parameter is not a subclass of MmBaseImplementation.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  public <T extends MmMimic> List<T> getDirectImplementationChildrenOfType(Class<T> pImplementationType) {
    if (!pImplementationType.isInterface() && !(MmBaseImplementation.class.isAssignableFrom(pImplementationType))) {
      throw new IllegalArgumentException("Class " + pImplementationType.getSimpleName() + " is not a subclass of MmBaseImplementation");
    }
    return (List<T>)Stream.concat(implementationChildren.stream(), runtimeImplementationChildren.stream()) //
    .filter(child -> pImplementationType.isAssignableFrom(child.getClass())) //
    .collect(Collectors.toList());
  }

  /**
   * Returns the MmJsfBridge of this mimic, which connects it to a JSF view component..
   *
   * @return        The MmJsfBridge of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public MmJsfBridge<?, ?, ?> getJsfBridge() {
    return mmJsfBridge;
  }

  /**
   * Returns the current JSF tag of this mimic, dependent on enabled state and configuration.
   *
   * @return        The current JSF tag of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public String getJsfTag() {
    if ((isMmEnabled() && !isMmReadOnly()) || configuration.getJsfTagDisabled().equals("SameAsEnabled")) {
      return configuration.getJsfTagEnabled();
    } else {
      return configuration.getJsfTagDisabled();
    }
  }

  /**
   * Returns a list of all ancestor mimics of this mimic. Top most ancestor will be first, direct parent will be last.
   *
   * @return        A list of all ancestor mimics of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public List<MmMimic> getMmAncestors() {
    assureInitialization();

    List<MmMimic> ancestors = new ArrayList<>();
    if ((implementationParent != null) && (implementationParent.declaration != null)) {
      ancestors.addAll(implementationParent.getMmAncestors());
      ancestors.add(implementationParent.declaration);
    }
    return ancestors;
  }

  /**
   * Returns a direct child mimic of specified name, or <code>null</code> if it doesn't exist.
   *
   * @param         pChildName  The name of the child to search for.
   *
   * @return        A direct child mimic of specified name, or <code>null</code> if it doesn't exist.
   *
   * @jalopy.group  group-helper
   */
  public MmMimic getMmChildByName(String pChildName) {
    assureInitialization();

    for (MmMimic child : getMmChildren()) {
      if (child.getMmName().equals(pChildName)) {
        return child;
      }
    }
    return null;
  }

  /**
   * Returns a list of all direct child mimics of this mimic.
   *
   * @return        A list of all direct child mimics of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public List<MmMimic> getMmChildren() {
    assureInitialization();

    final int     size       = declarationChildren.size() + runtimeDeclarationChildren.size();
    List<MmMimic> returnList = new ArrayList<>(size);
    returnList.addAll(declarationChildren);
    returnList.addAll(runtimeDeclarationChildren);
    return returnList;
  }

  /**
   * Returns a descendant mimic of specified full name, or <code>null</code> if it doesn't exist. The name is a path of ancestors' names
   * like <code>grandparent.parent.child</code>.
   *
   * @param         pFullName  The full name of the mimic to search for.
   *
   * @return        A descendant mimic of specified full name, or <code>null</code> if it doesn't exist.
   *
   * @jalopy.group  group-helper
   */
  public MmMimic getMmDescendantByFullName(String pFullName) {
    assureInitialization();

    final String fullName = getMmFullName();
    if (fullName.equals(pFullName)) {
      return this;
    }

    for (MmMimic child : getMmChildren()) {
      MmMimic found = MmRelationshipApi.getMmDescendantByFullName(child, pFullName);
      if (found != null) {
        return found;
      }
    }
    return null;
  }

  /**
   * Returns a list of all descendant mimics of this mimic. Each child is followed by own children, before followed by siblings.
   *
   * @return        A list of all descendant mimics of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public List<MmMimic> getMmDescendants() {
    assureInitialization();

    final List<MmMimic> descendants = new ArrayList<>();
    for (MmMimic child : getMmChildren()) {
      descendants.add(child);
      descendants.addAll(MmRelationshipApi.getMmDescendants(child));
    }
    return descendants;
  }

  /**
   * Returns an ancestor of this mimic of specified type, if exists, otherwise null. This method may be called at any time, even in
   * constructor.
   *
   * @param         pType  The specified type.
   *
   * @return        An ancestor of this mimic of specified type, if exists, otherwise null.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  public <T> T getMmImplementationAncestorOfType(Class<T> pType) {
    if (implementationParent != null) {
      if (pType.isAssignableFrom(implementationParent.getClass())) {
        return (T)implementationParent;
      } else {
        return implementationParent.getMmImplementationAncestorOfType(pType);
      }
    } else {
      return null;
    }
  }

  /**
   * Returns the parent mimic of this mimic, may be null in case of <code>MmRoot</code>.
   *
   * @return        The parent mimic of this mimic, may be null.
   *
   * @jalopy.group  group-helper
   */
  public MmMimic getMmParent() {
    assureInitialization();

    return implementationParent.declaration;
  }

  /**
   * Returns root mimic of mimic tree.
   *
   * @return        The root mimic of mimic tree.
   *
   * @jalopy.group  group-helper
   */
  public MmBaseImplementation<?, ?, ?> getMmRoot() {
    return root;
  }

  /**
   * Returns true, if this mimic is the root mimic.
   *
   * @return        True, if this mimic is the root mimic.
   *
   * @jalopy.group  group-helper
   */
  public boolean isMmThisTheRoot() {
    return false;
  }

  /**
   * Returns data model for self reference. The data model delivers parameters of the target URL, like "123", "4711" in
   * "city/123/person/4711/display".
   *
   * @return        The data model for self reference.
   *
   * @jalopy.group  group-helper
   */
  protected abstract MmReferencableModel getMmReferencableModel();

  /**
   * Logs debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param         pMm           The specific mimic to log.
   * @param         pIndentation  Level of indentation in log.
   *
   * @jalopy.group  group-helper
   */
  protected void logSubtree(MmBaseImplementation<?, ?, ?> pMm, String pIndentation) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(pIndentation + pMm.toString());
      for (MmBaseImplementation<?, ?, ?> child : pMm.implementationChildren) {
        logSubtree(child, pIndentation + "  ");
      }
      for (MmBaseImplementation<?, ?, ?> child : pMm.runtimeImplementationChildren) {
        logSubtree(child, pIndentation + "  ");
      }
    }
  }

  /**
   * Returns debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param         pIndentation  Level of indentation in log.
   *
   * @return        Debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @jalopy.group  group-helper
   */
  protected String toStringSubtree(String pIndentation) {
    StringWriter writer = new StringWriter();
    writer.append(pIndentation + toString());
    writer.append(NL);
    for (MmBaseImplementation<?, ?, ?> child : implementationChildren) {
      writer.append(child.toStringSubtree(pIndentation + "  "));
    }
    for (MmBaseImplementation<?, ?, ?> child : runtimeImplementationChildren) {
      writer.append(child.toStringSubtree(pIndentation + "  "));
    }
    return writer.toString();
  }

  /**
   * Returns the {@link Locale} of this root.
   *
   * @return  The locale of this root.
   */
  public Locale getMmLocale() {
    assureInitialization();

    if (root.locale == null) {
      LOGGER.warn("getMmLocale: undefined root locale is set to " + NO_SESSION_CONTEXT_LOCALE);
      root.locale = NO_SESSION_CONTEXT_LOCALE;
    }
    return root.locale;
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return  True, if the user's browser has enabled Javascript language.
   */
  public boolean isMmJsEnabled() {
    assureInitialization();

    return root.sessionContext.isMmJsEnabled();
  }

  /**
   * Set specified locale.
   *
   * @param  pLocale  The specified locale.
   */
  public void setMmLocale(Locale pLocale) {
    assureInitialization();

    root.locale = pLocale;
  }

  /**
   * Sets specified message source.
   *
   * @param  pMessageSource  The specified message source.
   */
  public void setMmMessageSource(MessageSource pMessageSource) {
    root.messageSource = pMessageSource;
  }

  /**
   * Sets the {@link MmSessionContext} of this root, which provides information about the user's session.
   *
   * @param  pSessionContext  The session context to be set.
   */
  public void setSessionContext(MmSessionContext pSessionContext) {
    root.sessionContext = pSessionContext;
  }

}
