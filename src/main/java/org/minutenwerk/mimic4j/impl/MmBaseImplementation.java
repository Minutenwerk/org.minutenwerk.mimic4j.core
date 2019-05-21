package org.minutenwerk.mimic4j.impl;

import java.io.StringWriter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.minutenwerk.mimic4j.impl.composite.MmImplementationRoot;
import org.minutenwerk.mimic4j.impl.message.MmMessageType;
import org.minutenwerk.mimic4j.impl.referencable.MmReferenceImplementation;
import org.minutenwerk.mimic4j.impl.view.MmJsfBridge;

/**
 * MmBaseImplementation is the abstract base class for the implementation part of all mimic classes.
 *
 * @author              Olaf Kossak
 *
 * @jalopy.group-order  group-construction, group-naming, group-initialization, group-override, group-helper
 */
public abstract class MmBaseImplementation<DECLARATION extends MmBaseDeclaration<?, ?>, CONFIGURATION extends MmBaseConfiguration>
  implements MmMimic {

  /** Constant for index of generic type of configuration. */
  public static final int     GENERIC_PARAMETER_INDEX_CONFIGURATION = 2;

  /** End of line characters of operating system in use. */
  public static final String  NL                                    = System.getProperty("line.separator");

  /** Logger of this class. */
  private static final Logger LOGGER                                = LogManager.getLogger(MmBaseImplementation.class);

  /**
   * Enumeration of states during initialization phase.
   *
   * @author  Olaf Kossak
   */
  protected enum MmInitialState {

    /** Mimic instance constructors are in execution. */
    IN_CONSTRUCTION,

    /** Mimic instance constructors are executed. */
    CONSTRUCTION_COMPLETE,

    /** Mimic initialize method is in execution. */
    IN_INITIALIZATION,

    /** Mimic is initialized. */
    INITIALIZED
  }

  /** The state of initialization during initialization phase. */
  protected MmInitialState                         initialState                  = MmInitialState.IN_CONSTRUCTION;

  /** The name of this mimic is evaluated during initialization phase of its parent mimic. */
  protected String                                 name;

  /** The type of first optional generic parameter of this mimic is evaluated during initialization phase of its parent mimic. */
  protected Type                                   typeOfFirstGenericParameter;

  /** The parentPath of this mimic is evaluated during initialization phase of its parent mimic. */
  protected String                                 parentPath;

  /** The configuration of fixed values. */
  protected CONFIGURATION                          configuration;

  /** The root ancestor of this mimic, is set in constructor phase. */
  protected final MmImplementationRoot             root;

  /** The declaration part of this implementation is the declaration. */
  protected DECLARATION                            declaration;

  /** This or an ancestor mimic, which delivers a reference path, file and params. May be null. */
  protected MmReferencableMimic<?>                 referencableAncestor;

  /** The implementation parent of this mimic, is set in constructor phase. */
  protected final MmBaseImplementation<?, ?>       implementationParent;

  /** The declaration parent of this mimic, is set in constructor phase. */
  protected final MmBaseDeclaration<?, ?>          declarationParent;

  /** All direct children are of type <code>MmBaseImplementation</code>. */
  protected final List<MmBaseImplementation<?, ?>> implementationChildren;

  /** All direct children of the declaration are of type <code>MmMimic</code>. */
  protected final List<MmMimic>                    declarationChildren;

  /** All runtime children are of type <code>MmBaseImplementation</code>. */
  protected final List<MmBaseImplementation<?, ?>> runtimeImplementationChildren;

  /** All runtime declaration children are of type <code>MmMimic</code>. */
  protected final List<MmMimic>                    runtimeDeclarationChildren;

  /** The MmJsfBridge of this mimic, which connects it to a JSF view component, is set in constructor phase. */
  protected final MmJsfBridge<?, ?, ?>             mmJsfBridge;

  /**
   * Creates a new MmCompositeImplementation instance. After this constructor assigned values are:
   *
   * <ul>
   *   <li>initialState is IN_CONSTRUCTION</li>
   *   <li>root is assigned, except for root itself</li>
   *   <li>implementationParent is assigned, except for root</li>
   *   <li>declarationParent is assigned, except for root</li>
   *   <li>isRuntimeChild is false, may be changed in method addChild()</li>
   *   <li>mmJsfBridge is assigned</li>
   * </ul>
   *
   * <p>This constructor has been called by constructor of declaration part. Declaration constructor calls method setCallback().</p>
   *
   * <ul>
   *   <li>declaration is assigned</li>
   *   <li>initialState is CONSTRUCTION_COMPLETE</li>
   * </ul>
   *
   * <p>At first call of mimic method assureInitialization() calls method initialize(). After initialization:</p>
   *
   * <ul>
   *   <li>referencableAncestor is assigned</li>
   * </ul>
   *
   * <p>Method initialize() calls method addFieldOfTypeMmToChildren() calls method addChild(), which assigns:</p>
   *
   * <ul>
   *   <li>name</li>
   *   <li>parentPath</li>
   *   <li>implementationChildren</li>
   *   <li>declarationChildren</li>
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
   *
   * @throws  IllegalStateException  in case of parameter pDeclarationParent does not have an implementation part, or root ancestor of
   *                                 subtree is not of type {@link MmImplementationRoot}.
   */
  public MmBaseImplementation(MmDeclarationMimic pDeclarationParent) {
    name       = "";
    parentPath = "";

    if (pDeclarationParent == null) {
      // if this is a MmRoot, there is no parent

      // set reference to declaration part of parent mimic to null
      declarationParent    = null;

      // set reference to implementation part of parent mimic to null
      implementationParent = null;

      // set reference to root to null
      root                 = null;

    } else {

      if (!(pDeclarationParent instanceof MmBaseDeclaration<?, ?>)) {
        throw new IllegalStateException("Parameter pDeclarationParent " + pDeclarationParent + " must be of type MmBaseDeclaration<?,?>");
      }

      // set reference to declaration part of parent mimic
      declarationParent = (MmBaseDeclaration<?, ?>)pDeclarationParent;

      // set reference to implementation part of parent
      if (declarationParent.implementation == null) {
        throw new IllegalStateException("Parameter pDeclarationParent " + pDeclarationParent + " must have an implementation");
      }
      implementationParent = declarationParent.implementation;

      // evaluate reference to root ancestor
      MmBaseImplementation<?, ?> temp = implementationParent;
      while (temp.implementationParent != null) {
        temp = temp.implementationParent;
      }

      // if declaration part of root ancestor is of type MmRoot
      if (MmImplementationRoot.class.isAssignableFrom(temp.getClass())) {

        // set reference to implementation part of MmRoot
        root = (MmImplementationRoot)temp;
      } else {
        throw new IllegalStateException("root ancestor of subtree must be of type MmImplementationRoot");
      }
    }
    
    // evaluate is runtime
	if (isRuntimeMimic == null) {
		isRuntimeMimic = implementationParent != null && implementationParent.isMmRuntimeMimic();
	}

    // create lists for child mimics
    implementationChildren        = new ArrayList<>();
    declarationChildren           = new ArrayList<>();
    runtimeImplementationChildren = new ArrayList<>();
    runtimeDeclarationChildren    = new ArrayList<>();

    // create bridge for jsf tags
    mmJsfBridge                   = createMmJsfBridge();
  }

  /**
   * Logs debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param         pMm           The specific mimic to log.
   * @param         pIndentation  Level of indentation in log.
   *
   * @jalopy.group  group-initialization
   */
  protected static void logSubtree(MmBaseImplementation<?, ?> pMm, String pIndentation) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug(pIndentation + pMm.toString());
      for (MmBaseImplementation<?, ?> child : pMm.implementationChildren) {
        logSubtree(child, pIndentation + "  ");
      }
      for (MmBaseImplementation<?, ?> child : pMm.runtimeImplementationChildren) {
        logSubtree(child, pIndentation + "  ");
      }
    }
  }

  /**
   * Reads the Java-Generics parameter having the given index position (starting at ONE) from the given {@link Class}.
   *
   * @param         pClassToAnalyze  The class to analyze.
   * @param         pGenericRawType  The class containing the generic.
   * @param         pParameterIndex  The parameter position index. Starts with One for the first parameter.
   *
   * @return        The found Java-Generics parameter. <code>null</code> if none was found.
   *
   * @jalopy.group  group-helper
   */
  @SuppressWarnings("unchecked")
  public static <T extends Type> T findGenericsParameterType(Class<?> pClassToAnalyze, Class<?> pGenericRawType, int pParameterIndex) {
    final int  parameterIndex    = pParameterIndex - 1;
    final Type genericSuperclass = pClassToAnalyze.getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      final ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
      final Type              rawType           = parameterizedType.getRawType();
      if (rawType.equals(pGenericRawType)) {
        Type[] typeArray = parameterizedType.getActualTypeArguments();
        if (typeArray.length > parameterIndex) {
          return (T)typeArray[parameterIndex];
        } else {
          return null;
        }
      } else {
        Class<?> superClass = pClassToAnalyze.getSuperclass();
        if (superClass != null) {
          return (T)findGenericsParameterType(superClass, pGenericRawType, parameterIndex);
        } else {
          return null;
        }
      }
    } else {
      return null;
    }
  }

  /**
   * Returns debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @param         pMm           The specific mimic to log.
   * @param         pIndentation  Level of indentation in log.
   *
   * @return        Debug information about a specified mimic and subtree of all its children and runtime children.
   *
   * @jalopy.group  group-helper
   */
  public static String toStringSubtree(MmBaseImplementation<?, ?> pMm, String pIndentation) {
    StringWriter writer = new StringWriter();
    if (pMm != null) {
      writer.append(pIndentation + pMm.toString());
      writer.append(NL);
      for (MmBaseImplementation<?, ?> child : pMm.implementationChildren) {
        writer.append(toStringSubtree(child, pIndentation + "  "));
      }
      for (MmBaseImplementation<?, ?> child : pMm.runtimeImplementationChildren) {
        writer.append(toStringSubtree(child, pIndentation + "  "));
      }
    }
    return writer.toString();
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
  protected static <T extends MmBaseImplementation<?, ?>> T getImplementation(MmBaseDeclaration<?, ?> pDeclaration) {
    return (T)pDeclaration.implementation;
  }

  /**
   * Set declaration part of this mimic.
   *
   * @param         pDeclaration  The declaration part to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.IN_CONSTRUCTION}, or reference to declaration
   *                                          part is NOT null.
   * @throws        IllegalArgumentException  in case of parameter pDeclaration is null.
   *
   * @jalopy.group  group-construction
   */
  @SuppressWarnings("unchecked")
  protected void setCallback(MmBaseDeclaration<?, ?> pDeclaration) {
    if (initialState != MmInitialState.IN_CONSTRUCTION) {
      throw new IllegalStateException("Initial state must be IN_CONSTRUCTION");
    }
    if (declaration != null) {
      throw new IllegalStateException("Reference to declaration part must be null");
    }
    if (pDeclaration == null) {
      throw new IllegalArgumentException("Parameter pDeclaration cannot be null");
    }

    declaration  = (DECLARATION)pDeclaration;
    initialState = MmInitialState.CONSTRUCTION_COMPLETE;

    // if there is a parent mimic, add this mimic as child of parent mimic
    if (implementationParent != null) {
      implementationParent.addChild(declaration, null, null);
    }

    assert parentPath.isEmpty() : "Instance variable parentPath must be still empty";
  }

  /**
   * Sets the name of this mimic. A mimic gets its name from its parent mimic, because its name is the field name in its parent class
   * declaration. This method is called on a mimic during execution of mimic parent's initialize method.
   *
   * @param         pName  The name to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.CONSTRUCTION_COMPLETE},
   *                                          {@link MmInitialState.IN_INITIALIZATION} or {@link MmInitialState.INITIALIZED}.
   * @throws        IllegalArgumentException  in case of parameter pName is null.
   *
   * @jalopy.group  group-naming
   */
  protected void setName(String pName) {
    if ((initialState != MmInitialState.IN_CONSTRUCTION) && (initialState != MmInitialState.CONSTRUCTION_COMPLETE)
        && (initialState != MmInitialState.INITIALIZED)) {
      throw new IllegalStateException("Initial state must be IN_CONSTRUCTION or CONSTRUCTION_COMPLETE or INITIALIZED, but is "
        + initialState);
    }
    assert ((implementationParent.initialState == MmInitialState.IN_INITIALIZATION)
        || (implementationParent.initialState == MmInitialState.INITIALIZED)) : "Initial state of parent must be IN_INITIALIZATION or INITIALIZED";
    if (pName == null) {
      throw new IllegalArgumentException("Parameter pName cannot be null");
    }
    assert name.isEmpty() : "Instance variable name " + name + " cannot be changed to " + pName;
    assert parentPath.isEmpty() : "Instance variable parentPath " + parentPath + " cannot be set twice";

    name = pName.trim();

    if (implementationParent.name.isEmpty()) {
      parentPath = "";
    } else if (implementationParent.parentPath.isEmpty()) {
      parentPath = implementationParent.name;
    } else {
      parentPath = implementationParent.parentPath + "." + implementationParent.name;
    }
  }

  /**
   * Sets the generic type of this mimic. This method is called on a mimic during execution of mimic parent's initialize method.
   *
   * @param         pTypeOfFirstGenericParameter  The type to be set.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.CONSTRUCTION_COMPLETE} or
   *                                          {@link MmInitialState.INITIALIZED}.
   * @throws        IllegalArgumentException  in case of parameter pTypeOfFirstGenericParameter is null.
   *
   * @jalopy.group  group-naming
   */
  protected void setTypeOfFirstGenericParameter(Type pTypeOfFirstGenericParameter) {
    if ((initialState != MmInitialState.CONSTRUCTION_COMPLETE) && (initialState != MmInitialState.INITIALIZED)) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or INITIALIZED, but was " + initialState);
    }
    assert implementationParent.initialState == MmInitialState.IN_INITIALIZATION : "Initial state of parent must be IN_INITIALIZATION";
    if (pTypeOfFirstGenericParameter == null) {
      throw new IllegalArgumentException("Parameter pTypeOfFirstGenericParameter cannot be null");
    }
    assert typeOfFirstGenericParameter == null : "Instance variable genericType " + typeOfFirstGenericParameter + " cannot be changed to "
                                                 + pTypeOfFirstGenericParameter;

    typeOfFirstGenericParameter = pTypeOfFirstGenericParameter;
  }

  /**
   * Add child of type <code>MmBaseDeclaration</code> to list of declarationChildren. Name implementation part of child and add to list of
   * children.
   *
   * @param         pChild                        The child to add (cannot be null).
   * @param         pNameOfChild                  The name of the child (may be null).
   * @param         pTypeOfFirstGenericParameter  The type of first generic parameter of the child.
   *
   * @throws        IllegalStateException     in case of state is not in {@link MmInitialState.CONSTRUCTION_COMPLETE},
   *                                          {@link MmInitialState.IN_INITIALIZATION} or {@link MmInitialState.INITIALIZED}, or parameter
   *                                          pChild does not have an implementation part.
   * @throws        IllegalArgumentException  in case of parameter pChild is null.
   *
   * @jalopy.group  group-initialization
   */
  protected void addChild(MmBaseDeclaration<?, ?> pChild, String pNameOfChild, Type pTypeOfFirstGenericParameter) {
    if ((initialState != MmInitialState.CONSTRUCTION_COMPLETE) && (initialState != MmInitialState.IN_INITIALIZATION)
        && (initialState != MmInitialState.INITIALIZED)) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE or IN_INITIALIZATION or INITIALIZED");
    }
    if (pChild == null) {
      throw new IllegalArgumentException("Parameter pChild cannot be null");
    }
    if (pChild.implementation == null) {
      throw new IllegalStateException("Parameter pChild " + pChild + " must have an implementation");
    }

    MmBaseImplementation<?, ?> childImplementation = pChild.implementation;

    // name implementation part of child
    if (pNameOfChild != null) {
      childImplementation.setName(pNameOfChild);
    }

    // generic type implementation part of child
    if (pTypeOfFirstGenericParameter != null) {
      childImplementation.setTypeOfFirstGenericParameter(pTypeOfFirstGenericParameter);
    }

    if (pChild.isMmRuntimeMimic()) {

	    // if child not in list yet, add child to list of
	    // runtime declarationChildren and list of runtime implementationChildren
		if (LOGGER.isDebugEnabled()) {
			if (runtimeDeclarationChildren.contains(pChild)) {
			      throw new IllegalStateException("Runtime child " + pChild + " is already registered");
			}
		}
      runtimeDeclarationChildren.add(pChild);
      runtimeImplementationChildren.add(childImplementation);

    } else {

        // if child not in list yet, add child to list of
        // declarationChildren and list of implementationChildren
		if (LOGGER.isDebugEnabled()) {
			if (!declarationChildren.contains(pChild)) {
			      throw new IllegalStateException("Compiletime child " + pChild + " is already registered");
			}
		}
          declarationChildren.add(pChild);
          implementationChildren.add(childImplementation);
    }
  }

  /**
   * Assures that mimic will be initialized, if it is not initialized yet.
   *
   * @jalopy.group  group-initialization
   */
  protected void assureInitialization() {
    if (initialState != MmInitialState.INITIALIZED) {
      if (root == null) {

        // if this mimic is not initialized, and this mimic is the root
        // then initialize this mimic, and log sub tree of this mimic
        initialize();
        if (LOGGER.isDebugEnabled()) {
          logSubtree(this, "");
        }

      } else if (root.initialState == MmInitialState.INITIALIZED) {

        // if this mimic is not initialized, and is not the root, and the root is initialized
        // then initialize this mimic
        initialize();

      } else {

        // if this mimic is not initialized, and is not the root, and the root is not initialized
        // then initialize the root, and log sub tree of root
        root.initialize();
        if (LOGGER.isDebugEnabled()) {
          logSubtree(root, "");
        }
      }
    }
  }

  /**
   * Searches for an annotation within the inheritance tree of a class.
   *
   * @param         pDeclaration      pObjectToAnalyze <ANNOTATION> The annotation type.
   * @param         pAnnotationClass  The runtime class of the annotation.
   *
   * @throws        IllegalArgumentException  in case othe annotation is not allowed for this type of mimic.
   *
   * @jalopy.group  group-initialization
   */
  protected <ANNOTATION extends Annotation> void checkForIllegalAnnotationsOtherThan(MmBaseDeclaration<?, ?> pDeclaration,
    Class<ANNOTATION> pAnnotationClass) {
    // if implementation part has a name
    // search for all annotations annotated by MmMetaAnnotation in field declaration of parent mimic
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

    // search for all annotations annotated by MmMetaAnnotation in class tree of pDeclaration
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
   * Returns a new MmJsfBridge for this mimic, which connects it to a JSF view component.
   *
   * @return        A new MmJsfBridge for this mimic.
   *
   * @jalopy.group  group-initialization
   */
  protected abstract MmJsfBridge<?, ?, ?> createMmJsfBridge();

  /**
   * Searches for an annotation within the inheritance tree of a class.
   *
   * @param         pDeclaration      pObjectToAnalyze <ANNOTATION> The annotation type.
   * @param         pAnnotationClass  The runtime class of the annotation.
   *
   * @return        The found annotation or <code>null</code>.
   *
   * @jalopy.group  group-initialization
   */
  protected <ANNOTATION extends Annotation> ANNOTATION findAnnotation(MmBaseDeclaration<?, ?> pDeclaration,
    Class<ANNOTATION> pAnnotationClass) {
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
   * Provides all fields (inclusive private ones) within the given class and its super-classes.
   *
   * <p>The fields of the super classes appear as first list items.</p>
   *
   * @param         pClassToAnalyze  The class to analyze.
   *
   * @return        All found fields.
   *
   * @jalopy.group  group-initialization
   */
  protected List<Field> findFields(Class<?> pClassToAnalyze) {
    List<Field>    allFields = new ArrayList<>();
    List<Class<?>> classes   = new ArrayList<>();
    Class<?>       c         = pClassToAnalyze;

    while (c != null) {
      classes.add(c);
      c = c.getSuperclass();
    }

    for (int i = classes.size() - 1; i >= 0; --i) {
      allFields.addAll(Arrays.asList(classes.get(i).getDeclaredFields()));
    }
    return allFields;
  }

  /**
   * Returns an ancestor of this mimic of specified type, if exists, otherwise null.
   *
   * @param         pType  The specified type.
   *
   * @return        An ancestor of this mimic of specified type, if exists, otherwise null.
   *
   * @jalopy.group  group-initialization
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
   * Initializes this mimic after constructor phase, calls super.initialize(), if you override this method, you must call
   * super.initialize()!
   *
   * @throws        IllegalStateException  in case of initial state is not {@link MmInitialState.CONSTRUCTION_COMPLETE} or reference to
   *                                       declaration part of mimic is not defined.
   *
   * @jalopy.group  group-initialization
   */
  protected void initialize() {
    if (initialState != MmInitialState.CONSTRUCTION_COMPLETE) {
      throw new IllegalStateException("Initial state must be CONSTRUCTION_COMPLETE");
    }
    if (declaration == null) {
      throw new IllegalStateException("Reference to declaration part must be defined");
    }

    // set state to IN_INITIALIZATION
    initialState         = MmInitialState.IN_INITIALIZATION;

    // evaluate ancestor for reference path, file and params
    referencableAncestor = getImplementationAncestorOfType(MmReferencableMimic.class);

    // evaluate children
    for (Field field : findFields(declaration.getClass())) {

      // add all public not static fields of type MmBaseDeclaration as children
      addFieldOfTypeMmToChildren(field);
    }

    // evaluate configuration
    initializeConfiguration();

    // if id is not defined yet, set id to full name
    if (configuration.getId().equals(MmBaseConfiguration.UNDEFINED_ID) && !name.isEmpty()) {
      String newId = name;
      if (!parentPath.isEmpty()) {
        newId = parentPath.replaceAll("\\.", "-") + "-" + name;
      }
      configuration.setId(newId);
    }

    // set state to INITIALIZED
    initialState = MmInitialState.INITIALIZED;

    // initialize children
    for (MmBaseImplementation<?, ?> implementationChild : implementationChildren) {
      implementationChild.initialize();
    }
  }

  /**
   * Initialize this mimic after constructor phase.
   *
   * @jalopy.group  group-initialization
   */
  protected abstract void initializeConfiguration();

  /**
   * Add field to children, if field is public and not static and of type MmBaseDeclaration.
   *
   * @param         pField  The specific field to analyze.
   *
   * @jalopy.group  group-initialization
   */
  private void addFieldOfTypeMmToChildren(Field pField) {
    // if field is public but not static
    if (((pField.getModifiers() & Modifier.PUBLIC) != 0) && ((pField.getModifiers() & Modifier.STATIC) == 0)) {

      // if field is descendant of MmBaseDeclaration
      if (MmBaseDeclaration.class.isAssignableFrom(pField.getType())) {

        // if field is not final log warning
        if ((pField.getModifiers() & Modifier.FINAL) == 0) {
          LOGGER.warn("Field: {}.{} is not declared final!", pField.getDeclaringClass(), pField.getName());
        }
        if (!pField.isAccessible()) {
          pField.setAccessible(true);
        }

        try {
          MmBaseDeclaration<?, ?> child                              = (MmBaseDeclaration<?, ?>)pField.get(declaration);
          Type                    typeOfFirstGenericParameterOfField = null;
          if (pField.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType)pField.getGenericType();
            Type[]            typeArray         = parameterizedType.getActualTypeArguments();
            if (typeArray.length >= 1) {
              typeOfFirstGenericParameterOfField = typeArray[0];
            }
          }
          addChild(child, pField.getName(), typeOfFirstGenericParameterOfField);

          // check parent child relation
          if (child.implementation.implementationParent != this) {
            throw new IllegalStateException("this mimic must be parent of child to add");
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
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

      // create an own reference from anchestor reference for specified model, and own anchor
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
	assureInitialization();
	
	return isRuntimeMimic;
  }

  /** <code>True</code>, if the mimic has been created at runtime, e.g. a {@link MmTableRow}. */
  private Boolean isRuntimeMimic = null;

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
   * Returns the Java type of configuration class of this mimic.
   *
   * @return        The Java type of configuration class of this mimic.
   *
   * @jalopy.group  group-helper
   */
  protected Class<?> getConfigurationType() {
    return findGenericsParameterType(getClass(), MmBaseImplementation.class, GENERIC_PARAMETER_INDEX_CONFIGURATION);
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
  protected <T extends MmMimic> List<T> getDeclarationChildrenOfType(Class<T> pType) {
    return (List<T>)Stream.concat(declarationChildren.stream(), runtimeDeclarationChildren.stream()) //
    .filter(child -> pType.isAssignableFrom(child.getClass())) //
    .collect(Collectors.toList());
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

}
