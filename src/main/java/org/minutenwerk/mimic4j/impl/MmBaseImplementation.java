package org.minutenwerk.mimic4j.impl;

import java.io.StringWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.minutenwerk.mimic4j.api.MmDeclarationMimic;
import org.minutenwerk.mimic4j.api.MmMimic;
import org.minutenwerk.mimic4j.api.MmReferencableMimic;
import org.minutenwerk.mimic4j.api.MmReferencableModel;
import org.minutenwerk.mimic4j.api.MmReference;
import org.minutenwerk.mimic4j.api.MmRelationshipApi;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.CONSTRUCTION_COMPLETE;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.INITIALIZED;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.IN_CONSTRUCTION;
import static org.minutenwerk.mimic4j.impl.MmInitialState.MmState.IN_INITIALIZATION;
import org.minutenwerk.mimic4j.impl.MmJavaHelper.ChildAndNameAndGeneric;
import org.minutenwerk.mimic4j.impl.composite.MmImplementationRoot;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.referencable.MmReferenceImplementation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

/**
 * MmBaseImplementation is the abstract base class for the implementation part of all mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-construction, group-postconstruct, group-initialization, group-override, group-helper
 */
public abstract class MmBaseImplementation<DECLARATION extends MmBaseDeclaration<?, ?>,
  CONFIGURATION extends MmBaseConfiguration, ANNOTATION extends Annotation> implements MmMimic {

  /** End of line characters of operating system in use. */
  public static final String                          NL                                    = System.getProperty("line.separator");

  /** Constant for index of generic type of configuration. */
  private static final int                            GENERIC_PARAMETER_INDEX_CONFIGURATION = 2;

  /** Constant for index of generic type of annotation. */
  private static final int                            GENERIC_PARAMETER_INDEX_ANNOTATION    = 3;

  /** Logger of this class. */
  private static final Logger                         LOGGER                                = LogManager.getLogger(
      MmBaseImplementation.class);

  /** The state of initialization during initialization phase. */
  protected final MmInitialState                      initialState;

  /** The declaration parent of this mimic, is set in constructor phase. */
  protected final MmBaseDeclaration<?, ?>             declarationParent;

  /** The implementation parent of this mimic, is set in constructor phase. */
  protected final MmBaseImplementation<?, ?, ?>       implementationParent;

  /** The name of this mimic, is set in constructor phase. */
  protected final String                              name;

  /** The parentPath of this mimic is evaluated during initialization phase of its parent mimic. */
  protected final String                              parentPath;

  /** The type of first optional generic parameter of this mimic, is set in constructor phase. */
  protected Type                                      typeOfFirstGenericParameter;

  /** The configuration of fixed values. */
  protected final CONFIGURATION                       configuration;

  /** The root ancestor of this mimic, is set in constructor phase. */
  protected final MmImplementationRoot                root;

  /**
   * All direct children are of type <code>MmBaseImplementation</code>. Children are added in constructor phase, and named in initialize
   * phase.
   */
  protected final List<MmBaseImplementation<?, ?, ?>> implementationChildren;

  /**
   * All direct children of the declaration are of type <code>MmMimic</code>. Children are added in constructor phase, and named in
   * initialize phase.
   */
  protected final List<MmMimic>                       declarationChildren;

  /** All runtime children are of type <code>MmBaseImplementation</code>. Children are added at runtime. */
  protected final List<MmBaseImplementation<?, ?, ?>> runtimeImplementationChildren;

  /** All runtime declaration children are of type <code>MmMimic</code>. Children are added at runtime. */
  protected final List<MmMimic>                       runtimeDeclarationChildren;

  /** The MmJsfBridge of this mimic, which connects it to a JSF view component, is set in constructor phase. */
  protected final MmJsfBridge<?, ?, ?>                mmJsfBridge;

  /** <code>True</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}. Is set in constructor phase. */
  protected final boolean                             isRuntimeMimic;

  /** This or an ancestor mimic, which delivers a reference path, file and params. May be null. Is set in initialize phase. */
  protected MmReferencableMimic<?>                    referencableAncestor;

  /** The declaration part of this implementation is the declaration. Is set in postConstruct phase. */
  protected DECLARATION                               declaration;

  /** TODOC. */
  private Iterator<Field>                             declarationChildrenFields;

  /**
   * Creates a new MmBaseImplementation instance.
   *
   * @param  pDeclarationParent  TODOC
   */
  public MmBaseImplementation(final MmDeclarationMimic pDeclarationParent) {
    this(pDeclarationParent, null);
  }

  /**
   * Creates a new MmCompositeImplementation instance. After this constructor assigned values are:
   *
   * <ul>
   *   <li>initialState is IN_CONSTRUCTION</li>
   *   <li>root is assigned, for root itself root is null</li>
   *   <li>implementationParent is assigned, for root it is null</li>
   *   <li>declarationParent is assigned, for root it is null</li>
   *   <li>isRuntimeMimic is assigned</li>
   *   <li>referencableAncestor is assigned</li>
   *   <li>mmJsfBridge is assigned</li>
   * </ul>
   *
   * <p>This constructor has been called by constructor of declaration part. Declaration constructor calls method onPostConstruct(), which
   * assigns:</p>
   *
   * <ul>
   *   <li>declaration is assigned</li>
   *   <li>this mimic is added as unnamed implementationChildren of parent</li>
   *   <li>this mimic is added as unnamed declarationChildren of parent</li>
   *   <li>initialState is CONSTRUCTION_COMPLETE</li>
   * </ul>
   *
   * <p>After constructor of this mimic all constructors of child fields are being called, which assign:</p>
   *
   * <ul>
   *   <li>children add themselves as unnamed implementationChildren of parent</li>
   *   <li>children add themselves as unnamed declarationChildren of parent</li>
   * </ul>
   *
   * <p>At first call of mimic method assureInitialization() calls method initialize(). After initialization:</p>
   *
   * <ul>
   * </ul>
   *
   * <p>Method initialize() calls method addFieldOfTypeMmToChildren() calls method addChild(), which assigns:</p>
   *
   * <ul>
   *   <li>name</li>
   *   <li>parentPath</li>
   *   <li>runtimeImplementationChildren</li>
   *   <li>runtimeDeclarationChildren</li>
   * </ul>
   *
   * <p>Method initialize() calls method initializeConfiguration():</p>
   *
   * <ul>
   *   <li>configuration is assigned</li>
   *   <li>initialState is INITIALIZED</li>
   * </ul>
   *
   * <p>Method initialize() calls method initialize() for all children:</p>
   *
   * @param   pDeclarationParent  The declaration part of the parent mimic.
   * @param   pRuntimeIndex       TODOC
   *
   * @throws  IllegalStateException  in case of parameter pDeclarationParent does not have an implementation part, or root ancestor of
   *                                 subtree is not of type {@link MmImplementationRoot}.
   */
  public MmBaseImplementation(final MmDeclarationMimic pDeclarationParent, final Integer pRuntimeIndex) {
    initialState = new MmInitialState();
    if (LOGGER.isDebugEnabled()) {
      if (pDeclarationParent == null) {
        if (!(this instanceof MmImplementationRoot)) {
          throw new IllegalStateException("Mimic " + pDeclarationParent + " must have an pDeclarationParent or be of type MmRoot");
        }
      } else {
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

      // if there is no parent, this is a root
      // set reference to declaration part of parent mimic to null
      declarationParent           = null;

      // set reference to implementation part of parent mimic to null
      implementationParent        = null;

      // set name
      name                        = "";

      // set parentPath
      parentPath                  = "";

      // evaluate typeOfFirstGenericParameter
      typeOfFirstGenericParameter = null;

      // evaluate configuration
      configuration               = onConstructConfiguration(null);

      // set reference to root to null
      root                        = null;

      // root is compiletime mimic
      isRuntimeMimic              = false;

    } else {

      // evaluate reference to declaration part of parent mimic
      declarationParent    = (MmBaseDeclaration<?, ?>)pDeclarationParent;

      // evaluate reference to implementation part of parent
      implementationParent = declarationParent.implementation;

      // evaluate field of this mimic's declaration in declarationParent
      final Field field    = onConstructField(implementationParent);
      if (MmJavaHelper.getFirstGenericType(field) != null) {
        typeOfFirstGenericParameter = MmJavaHelper.getFirstGenericType(field);
      }

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
      isRuntimeMimic = ((implementationParent != null) && implementationParent.isRuntimeMimic());
    }

    // create lists for child mimics
    implementationChildren        = new ArrayList<>();
    declarationChildren           = new ArrayList<>();
    runtimeImplementationChildren = new ArrayList<>();
    runtimeDeclarationChildren    = new ArrayList<>();

    // evaluate ancestor for reference path, file and params
    referencableAncestor          = getImplementationAncestorOfType(MmReferencableMimic.class);

    // evaluate bridge for jsf tags
    mmJsfBridge                   = onConstructJsfBridge();
  }

  /**
   * Returns the implementation part of the specified declaration.
   *
   * @param   pDeclaration  The specified declaration.
   *
   * @return  The implementation part of the specified declaration.
   */
  @SuppressWarnings("unchecked")
  protected static <T extends MmBaseImplementation<?, ?, ?>> T getImplementation(MmBaseDeclaration<?, ?> pDeclaration) {
    return (T)pDeclaration.implementation;
  }

  /**
   * Logs debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param  pMm           The specific mimic to log.
   * @param  pIndentation  Level of indentation in log.
   */
  protected static void logSubtree(MmBaseImplementation<?, ?, ?> pMm, String pIndentation) {
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
   * @param   pMm           The specific mimic to log.
   * @param   pIndentation  Level of indentation in log.
   *
   * @return  Debug information about a specified mimic and subtree of all its children and runtime children.
   */
  /* package */ static String toStringSubtree(MmBaseImplementation<?, ?, ?> pMm, String pIndentation) {
    StringWriter writer = new StringWriter();
    if (pMm != null) {
      writer.append(pIndentation + pMm.toString());
      writer.append(NL);
      for (MmBaseImplementation<?, ?, ?> child : pMm.implementationChildren) {
        writer.append(toStringSubtree(child, pIndentation + "  "));
      }
      for (MmBaseImplementation<?, ?, ?> child : pMm.runtimeImplementationChildren) {
        writer.append(toStringSubtree(child, pIndentation + "  "));
      }
    }
    return writer.toString();
  }

  /**
   * Searches for an annotation within the inheritance tree of a class.
   *
   * @param   pDeclaration      pObjectToAnalyze <ANNOTATION> The annotation type.
   * @param   pAnnotationClass  The runtime class of the annotation.
   *
   * @throws  IllegalArgumentException  in case othe annotation is not allowed for this type of mimic.
   */
  private static <ANNOTATION extends Annotation> void checkForIllegalAnnotationsOtherThan(MmBaseDeclaration<?, ?> pDeclaration,
    Class<ANNOTATION> pAnnotationClass) {
    // if implementation part has a name
    // search for all annotations annotated by MmMetaAnnotation in field
    // declaration of parent mimic
    if ((pDeclaration.implementation.name != null) && (pDeclaration.implementation.declarationParent != null)) {
      Class<?> declarationParentClass = pDeclaration.implementation.declarationParent.getClass();
      try {
        Field declaredFieldOfParent = declarationParentClass.getField(pDeclaration.implementation.name);
        for (Annotation annotation : declaredFieldOfParent.getAnnotations()) {
          Class<?>   annotationClass = annotation.annotationType();
          Annotation metaAnnotation  = annotation.annotationType().getAnnotation(MmMetaAnnotation.class);
          if (metaAnnotation != null) {
            if (!annotationClass.equals(pAnnotationClass)) {
              throw new IllegalArgumentException("Annotation " + annotation.annotationType().getSimpleName() + " is not allowed for "
                + pDeclaration.getClass().getSimpleName() + ", use " + pAnnotationClass.getSimpleName() + ".");
            }
          }
        }
      } catch (NoSuchFieldException e) {
        // attribute may use getter method
      }
    }

    // search for all annotations annotated by MmMetaAnnotation in class tree of
    // pDeclaration
    Class<?> declarationClass = pDeclaration.getClass();

    do {
      for (Annotation annotation : declarationClass.getAnnotations()) {
        if (annotation.annotationType().getAnnotation(MmMetaAnnotation.class) != null) {
          Class<?> annotationClass = annotation.annotationType();
          if (!annotationClass.equals(pAnnotationClass)) {
            throw new IllegalArgumentException("Annotation " + annotation.annotationType().getSimpleName() + " is not allowed for "
              + pDeclaration.getClass().getSimpleName() + ", use " + pAnnotationClass.getSimpleName() + ".");
          }
        }
      }
      declarationClass = declarationClass.getSuperclass();
    } while ((declarationClass != null) && !declarationClass.equals(Object.class));
  }

  /**
   * Searches for an annotation within the inheritance tree of a class.
   *
   * @param   pDeclaration      pObjectToAnalyze <ANNOTATION> The annotation type.
   * @param   pAnnotationClass  The runtime class of the annotation.
   *
   * @return  The found annotation or <code>null</code>.
   */
  private static <ANNOTATION extends Annotation> ANNOTATION findAnnotation( //
    MmBaseDeclaration<?, ?> pDeclaration, Class<ANNOTATION> pAnnotationClass) {
    //
    ANNOTATION returnAnnotation = null;

    // if implementation part has a name
    // search for annotation in field declaration of parent mimic
    if ((pDeclaration.implementation.name != null) && (pDeclaration.implementation.declarationParent != null)) {
      Class<?> declarationParentClass = pDeclaration.implementation.declarationParent.getClass();
      try {
        Field declaredFieldOfParent = declarationParentClass.getField(pDeclaration.implementation.name);
        returnAnnotation = declaredFieldOfParent.getAnnotation(pAnnotationClass);
      } catch (NoSuchFieldException e) {
        // attribute may use getter method
      }
    }

    // search for annotation in class tree of pDeclaration
    if (returnAnnotation == null) {
      Class<?> declarationClass = pDeclaration.getClass();

      while (returnAnnotation == null) {
        returnAnnotation = declarationClass.getAnnotation(pAnnotationClass);
        if (returnAnnotation == null) {
          declarationClass = declarationClass.getSuperclass();
          if ((declarationClass == null) || declarationClass.equals(Object.class)) {
            break;
          }
        }
      }
    }
    return returnAnnotation;
  }

  /**
   * TODOC.
   *
   * @param   pImplementationParent  TODOC
   *
   * @return  TODOC
   */
  private static Field onConstructField(final MmBaseImplementation<?, ?, ?> pImplementationParent) {
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
   * TODOC.
   *
   * @param  pConfiguration  TODOC
   * @param  pName           TODOC
   * @param  pParentPath     TODOC
   */
  private static void onConstructId(final MmBaseConfiguration pConfiguration, final String pName, final String pParentPath) {
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
   * TODOC.
   *
   * @param   pField         TODOC
   * @param   pRuntimeIndex  TODOC
   *
   * @return  TODOC
   */
  private static String onConstructName(final Field pField, final Integer pRuntimeIndex) {
    if (pField != null) {
      return pField.getName();
    } else if (pRuntimeIndex != null) {
      return "r" + pRuntimeIndex;
    } else {
      return "";
    }
  }

  /**
   * TODOC.
   *
   * @param   pImplementationParent  TODOC
   *
   * @return  TODOC
   */
  private static String onConstructParentPath(final MmBaseImplementation<?, ?, ?> pImplementationParent) {
    if (pImplementationParent.parentPath.isEmpty()) {
      return pImplementationParent.name;
    } else {
      return pImplementationParent.parentPath + "." + pImplementationParent.name;
    }
  }

  /**
   * Evaluates and returns reference to root ancestor for specified mimic.
   *
   * @param   pMm  The specified mimic.
   *
   * @return  The reference to root ancestor.
   *
   * @throws  IllegalStateException  In case of root ancestor of subtree is not of type MmImplementationRoot.
   */
  private static MmImplementationRoot onConstructRoot(final MmBaseImplementation<?, ?, ?> pMm) {
    if (pMm.initialState.isNot(IN_CONSTRUCTION)) {
      throw new IllegalStateException("Initial state must be IN_CONSTRUCTION");
    }

    MmBaseImplementation<?, ?, ?> tempParent = pMm.implementationParent;
    MmImplementationRoot          tempRoot   = tempParent.root;
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

    if (MmImplementationRoot.class.isAssignableFrom(tempParent.getClass())) {
      return (MmImplementationRoot)tempParent;
    } else {
      throw new IllegalStateException("root ancestor of subtree must be of type MmImplementationRoot");
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
   * @throws        IllegalArgumentException  in case of parameter pDeclaration is null.
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
        throw new IllegalArgumentException("Parameter pDeclaration " + pDeclaration + " must reference this");
      }
      if (declaration != null) {
        throw new IllegalStateException("Reference to declaration part must be null");
      }
    }

    // set declaration part
    declaration = (DECLARATION)pDeclaration;

// // evaluate typeOfFirstGenericParameter
// typeOfFirstGenericParameter = MmJavaHelper.getFirstGenericType(declaration.getClass());

    // if there is an implementation parent, add this mimic as unnamed declaration child and implementation child
    if (implementationParent != null) {
      implementationParent.addChild(declaration, null, null);
    }

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
   * Assures that mimic will be initialized, if it is not initialized yet.
   *
   * @jalopy.group  group-initialization
   */
  protected void assureInitialization() {
    if (initialState.isNot(INITIALIZED)) {
      if (root == null) {

        // if this mimic is not initialized, and this mimic is the root
        // then initialize this mimic, and log sub tree of this mimic
        initialize();
        if (LOGGER.isDebugEnabled()) {
          logSubtree(this, "");
        }

      } else if (root.initialState.is(INITIALIZED)) {

        // if this mimic is not initialized, and is not the root, and the root
        // is initialized
        // then initialize this mimic
        initialize();

      } else {

        // if this mimic is not initialized, and is not the root, and the root
        // is not initialized
        // then initialize the root, and log sub tree of root
        root.initialize();
        if (LOGGER.isDebugEnabled()) {
          logSubtree(root, "");
        }
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
  protected void initialize() {
    if (LOGGER.isDebugEnabled()) {
      if (initialState.isNot(CONSTRUCTION_COMPLETE)) {
        throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE");
      }
      if (declaration == null) {
        throw new IllegalStateException("Reference to declaration part must be defined");
      }
    }

    // set state to IN_INITIALIZATION
    initialState.set(IN_INITIALIZATION);

    // evaluate typeOfFirstGenericParameter
    // typeOfFirstGenericParameter = MmJavaHelper.getFirstGenericType(declaration.getClass());

    // evaluate all public static final mimic fields
// List<Field>                  publicStaticFinalMimicFields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(
// declaration.getClass());

    // apply fields to declaration part and get children, including field name and generic type
// List<ChildAndNameAndGeneric> childrenByParentAndFields    = MmJavaHelper.getChildrenByParentAndFields(declaration,
// publicStaticFinalMimicFields);

    // add all public not static fields of type MmBaseDeclaration as children
// addChildren(childrenByParentAndFields);

    // set state to INITIALIZED
    initialState.set(INITIALIZED);

    // initialize children
    for (MmBaseImplementation<?, ?, ?> implementationChild : implementationChildren) {
      implementationChild.initialize();
    }
  }

  /**
   * Implementation internal method for initialization. Returns <code>true</code>, if the mimic has been created at runtime, e.g. a
   * {@link org.minutenwerk.mimic4j.api.container.MmTableRow}.
   *
   * @return        <code>True</code>, if the mimic has been created at runtime.
   *
   * @jalopy.group  group-initialization
   */
  protected boolean isRuntimeMimic() {
    return false;
  }

  /**
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return        A new MmJsfBridge for this mimic.
   *
   * @jalopy.group  group-initialization
   */
  protected abstract MmJsfBridge<?, ?, ?> onConstructJsfBridge();

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
   * @jalopy.group  group-override
   */
  @Override
  public String getMmLongDescription() {
    assureInitialization();

    final Object   initialParams         = null;
    final Object[] longDescriptionParams = declaration.callbackMmGetLongDescriptionParams(initialParams);
    final String   i18nLongDescription   = getMmI18nText(MmMessageType.LONG, longDescriptionParams);
    final String   returnString          = declaration.callbackMmGetLongDescription(i18nLongDescription, longDescriptionParams);
    assert returnString != null : "callbackMmGetLongDescription cannot return null";
    return returnString;
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
   * Returns the self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @return        The self reference of this object for the current data model, or the static part if there is no current data model.
   *
   * @jalopy.group  group-override
   */
  @Override
  public MmReference getMmReference() {
    assureInitialization();

    // if no referencable ancestor is available, no reference can be returned
    if (referencableAncestor == null) {
      return null;

      // create an own reference from anchestor reference and own anchor
    } else {
      final MmReference ancestorReference = referencableAncestor.getMmReference();
      if (ancestorReference.isMmJsfOutcome()) {
        return ancestorReference;
      } else {
        final String      anchor          = "#" + getMmId();
        final MmReference returnReference = new MmReferenceImplementation(ancestorReference, anchor);
        return returnReference;
      }
    }
  }

  /**
   * Returns the self reference of this object for a specified data model.
   *
   * @param         pModel  The specified instance of a data model, which is referencable by an URL.
   *
   * @return        The self reference of this object for a specified data model.
   *
   * @jalopy.group  group-override
   */
  @Deprecated
  @Override
  public MmReference getMmReference(MmReferencableModel pModel) {
    assureInitialization();

    // if no referencable ancestor is available, no reference can be returned
    if (referencableAncestor == null) {
      return null;

      // create an own reference from anchestor reference for specified model,
      // and own anchor
    } else {
      final MmReference ancestorReference = referencableAncestor.getMmReference(pModel);
      if (ancestorReference.isMmJsfOutcome()) {
        return ancestorReference;
      } else {
        final String      anchor          = "#" + getMmId();
        final MmReference returnReference = new MmReferenceImplementation(ancestorReference, anchor);
        return returnReference;
      }
    }
  }

  /**
   * Returns a short description. The short description is evaluated from declaration method <code>callbackMmGetShortDescription</code>. If
   * <code>callbackMmGetShortDescription</code> is not overridden, the short description is evaluated from configuration attribute <code>
   * MmConfiguration.shortDescription</code>.
   *
   * @return        A short description.
   *
   * @jalopy.group  group-override
   */
  @Override
  public String getMmShortDescription() {
    assureInitialization();

    final String i18nShortDescription = getMmI18nText(MmMessageType.SHORT);
    final String returnString         = declaration.callbackMmGetShortDescription(i18nShortDescription);
    assert returnString != null : "callbackMmGetShortDescription cannot return null";
    return returnString;
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
   * Returns <code>true</code>, if the mimic is enabled. This mimic is enabled, if its parent is enabled and its declaration method
   * callbackMmIsEnabled returns <code>true</code>.
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
   * Returns <code>true</code>, if the mimic is read only. This mimic is read only, if its parent is read only or its declaration method
   * callbackMmIsReadOnly returns <code>true</code>.
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
    // do NOT insert assureInitialization() here, because this method
    // isMmRuntimeMimic() is called
    // during initialization phase. It is allowed to callthis method
    // isMmRuntimeMimic()
    // during initialization phase, because variable isRuntimeMimic is assigned
    // in constructor.
    return isRuntimeMimic;
  }

  /**
   * Returns <code>true</code>, if the mimic is visible. This mimic is visible, if its parent is visible and its declaration method
   * callbackMmIsVisible returns <code>true</code>.
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
    if (!parentPath.isEmpty()) {
      sb.append(" path=");
      sb.append(parentPath);
      sb.append(".");
      sb.append(name);
    } else if (!name.isEmpty()) {
      sb.append(" name=");
      sb.append(name);
    }
    if (getConfiguration() != null) {
      sb.append(" id=");
      sb.append(configuration.id);
    }
    sb.append(")");
    return sb.toString();
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
   * Returns the MmJsfBridge of this mimic, which connects it to a JSF view component..
   *
   * @return        The MmJsfBridge of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public final MmJsfBridge<?, ?, ?> getJsfBridge() {
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
  public final MmMimic getMmChildByName(String pChildName) {
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
   * Returns an internationalized version of the message of this mimic for a specified message type.
   *
   * @param         pMessageType  The specified message type.
   * @param         pArguments    Optional list of message arguments.
   *
   * @return        The internationalized version of a specified message.
   *
   * @jalopy.group  group-helper
   */
  public String getMmI18nText(MmMessageType pMessageType, Object... pArguments) {
    return root.getMmI18nText(getMmId(), pMessageType, pArguments);
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
   * Returns the <code>MmRoot</code> of this mimic. The <code>MmRoot</code> is a root element of a subtree.
   *
   * @return        The <code>MmRoot</code> of this mimic.
   *
   * @jalopy.group  group-helper
   */
  public MmRoot getMmRoot() {
    assureInitialization();

    return (MmRoot)root.declaration;
  }

  /**
   * Returns true, if the user's browser has enabled Javascript language.
   *
   * @return        True, if the user's browser has enabled Javascript language.
   *
   * @jalopy.group  group-helper
   */
  public boolean isMmJsEnabled() {
    return root.isMmJsEnabled();
  }

  /**
   * Add child of type <code>MmBaseDeclaration</code> to list of declarationChildren. Name implementation part of child and add to list of
   * children.
   *
   * @param         pChild                        The child to add (cannot be null).
   * @param         pNameOfChild                  The name of the child (may be null).
   * @param         pTypeOfFirstGenericParameter  The type of first generic parameter of the child.
   *
   * @throws        IllegalStateException     in case of state is not in {@link CONSTRUCTION_COMPLETE}, {@link IN_INITIALIZATION} or
   *                                          {@link INITIALIZED}, or parameter pChild does not have an implementation part.
   * @throws        IllegalArgumentException  in case of parameter pChild is null.
   *
   * @jalopy.group  group-helper
   */
  protected void addChild(MmBaseDeclaration<?, ?> pChild, String pNameOfChild, Type pTypeOfFirstGenericParameter) {
    if (LOGGER.isDebugEnabled()) {
      if ((initialState.isNot(CONSTRUCTION_COMPLETE)) && (initialState.isNot(IN_INITIALIZATION)) && (initialState.isNot(INITIALIZED))) {
        throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or IN_INITIALIZATION or INITIALIZED");
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

    // set generic type of implementation part of child
    if (pTypeOfFirstGenericParameter != null) {
      childImplementation.setTypeOfFirstGenericParameter(pTypeOfFirstGenericParameter);
    }

    if (pChild.isMmRuntimeMimic()) {

      // if child not in list yet, add child to list of
      // runtime declarationChildren and list of runtime implementationChildren
      if (!runtimeDeclarationChildren.contains(pChild)) {
        runtimeDeclarationChildren.add(pChild);
        runtimeImplementationChildren.add(childImplementation);
      }

    } else {

      // if child not in list yet, add child to list of
      // declarationChildren and list of implementationChildren
      if (!declarationChildren.contains(pChild)) {
        declarationChildren.add(pChild);
        implementationChildren.add(childImplementation);
      }
    }
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
  protected <T> T getImplementationAncestorOfType(Class<T> pType) {
    if (implementationParent != null) {
      if (pType.isAssignableFrom(implementationParent.getClass())) {
        return (T)implementationParent;
      } else {
        return implementationParent.getImplementationAncestorOfType(pType);
      }
    } else {
      return null;
    }
  }

  /**
   * Returns the list of all direct children of specified mimic, which are instances of class <code>MmBaseImplementation</code>, including
   * runtime children.
   *
   * @param         pType  The specified mimic.
   *
   * @return        The list of all direct children of specified mimic of type <code>MmBaseImplementation</code>, including runtime
   *                children.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  protected <T extends MmMimic> List<T> getImplementationChildrenOfType(Class<T> pType) {
    return (List<T>)Stream.concat(implementationChildren.stream(), runtimeImplementationChildren.stream()) //
    .filter(child -> pType.isAssignableFrom(child.getClass())) //
    .collect(Collectors.toList());
  }

  /**
   * Add children of type <code>MmBaseDeclaration</code> to list of declarationChildren. Name implementation part of child and add to list
   * of children.
   *
   * @param         pChildrenAndNameAndGeneric  TODOC
   *
   * @jalopy.group  group-helper
   */
  private void addChildren(List<ChildAndNameAndGeneric> pChildrenAndNameAndGeneric) {
    for (ChildAndNameAndGeneric childAndNameAndGeneric : pChildrenAndNameAndGeneric) {
      addChild(childAndNameAndGeneric.getChild(), childAndNameAndGeneric.getNameOfChild(),
        childAndNameAndGeneric.getTypeOfFirstGenericParameter());
    }
  }

  /**
   * Returns the Java type of annotation class of this mimic.
   *
   * @return        The Java type of annotation class of this mimic.
   *
   * @jalopy.group  group-helper
   */
  private final Class<ANNOTATION> getAnnotationType() {
    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseImplementation.class, GENERIC_PARAMETER_INDEX_ANNOTATION);
  }

  /**
   * Returns the Java type of configuration class of this mimic.
   *
   * @return        The Java type of configuration class of this mimic.
   *
   * @jalopy.group  group-helper
   */
  private final Class<CONFIGURATION> getConfigurationType() {
    return MmJavaHelper.findGenericsParameterType(getClass(), MmBaseImplementation.class, GENERIC_PARAMETER_INDEX_CONFIGURATION);
  }

  /**
   * Returns the list of all direct children of specified mimic, which are instances of class <code>MmBaseDeclaration</code>, including
   * runtime children.
   *
   * @param         pType  The specified mimic.
   *
   * @return        The list of all direct children of specified mimic of type <code>MmBaseDeclaration</code>, including runtime children.
   *
   * @jalopy.group  group-helper
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  private <T extends MmMimic> List<T> getDeclarationChildrenOfType(Class<T> pType) {
    return (List<T>)Stream.concat(declarationChildren.stream(), runtimeDeclarationChildren.stream()) //
    .filter(child -> pType.isAssignableFrom(child.getClass())) //
    .collect(Collectors.toList());
  }

  /**
   * Sets the generic type of this mimic. This method is called on a mimic during execution of mimic parent's initialize method.
   *
   * @param         pTypeOfFirstGenericParameter  The type to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link CONSTRUCTION_COMPLETE} or {@link INITIALIZED}.
   * @throws        IllegalArgumentException  in case of parameter pTypeOfFirstGenericParameter is null.
   *
   * @jalopy.group  group-helper
   */
  private void setTypeOfFirstGenericParameter(Type pTypeOfFirstGenericParameter) {
    if ((initialState.isNot(CONSTRUCTION_COMPLETE)) && (initialState.isNot(INITIALIZED))) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or INITIALIZED, but was " + initialState);
    }
    assert implementationParent.initialState.is(IN_INITIALIZATION) : "Initial state of parent must be IN_INITIALIZATION";
    if (pTypeOfFirstGenericParameter == null) {
      throw new IllegalArgumentException("Parameter pTypeOfFirstGenericParameter cannot be null");
    }
    assert typeOfFirstGenericParameter == null : "Instance variable genericType " + typeOfFirstGenericParameter + " cannot be changed to "
                                                 + pTypeOfFirstGenericParameter;

    typeOfFirstGenericParameter = pTypeOfFirstGenericParameter;
  }

  /**
   * Returns configuration of this mimic, specified annotation may be null.
   *
   * @param   pAnnotation  The specified annotation, may be null.
   *
   * @return  Configuration of this mimic.
   */
  protected abstract CONFIGURATION onConstructConfiguration(ANNOTATION pAnnotation);

  /**
   * TODOC.
   *
   * @param   pField  TODOC
   *
   * @return  TODOC
   */
  @SuppressWarnings("unchecked")
  private ANNOTATION onConstructAnnotation(final Field pField) {
    if (pField != null) {
      for (Annotation annotation : pField.getAnnotations()) {
        if (annotation.annotationType().isAnnotationPresent(MmMetaAnnotation.class)) {
          return (ANNOTATION)annotation;
        }
      }
    }
    return null;
  }
}
