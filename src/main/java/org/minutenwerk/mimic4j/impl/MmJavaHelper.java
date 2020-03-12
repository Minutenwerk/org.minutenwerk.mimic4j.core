package org.minutenwerk.mimic4j.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Static helper methods on Java Generics and Annotations.
 *
 * @author  Olaf Kossak
 */
public class MmJavaHelper {

  /**
   * Not for use.
   */
  private MmJavaHelper() {
  }

  /**
   * Reads the Java-Generics parameter having the given index position (starting at ONE) from the given {@link Class}.
   *
   * @param   pClassToAnalyze  The class to analyze.
   * @param   pGenericRawType  The class containing the generic.
   * @param   pParameterIndex  The parameter position index. Starts by 1 for the first parameter.
   *
   * @return  The found Java-Generics parameter. <code>null</code> if none was found.
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
   * Provides all fields within the given class and its super-classes, which are public, final, NOT static, and instances of {@link MmBaseDeclaration}.
   *
   * <p>The fields of the super classes appear as first list items.</p>
   *
   * @param   pClassToAnalyze  The class to analyze.
   *
   * @return  All found fields.
   */
  public static List<Field> findPublicStaticFinalBaseDeclarationFields(Class<?> pClassToAnalyze) {
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
    return allFields.stream() //
    .filter(field -> ((field.getModifiers() & Modifier.PUBLIC) != 0)) //
    .filter(field -> ((field.getModifiers() & Modifier.STATIC) == 0)) //
    .filter(field -> ((field.getModifiers() & Modifier.FINAL) != 0)) //
    .filter(field -> MmBaseDeclaration.class.isAssignableFrom(field.getType())) //
    .peek(field -> field.setAccessible(true)) //
    .collect(Collectors.toList());
  }

  /**
   * TODOC.
   *
   * @param   pField  TODOC
   *
   * @return  TODOC
   */
  public static Type getFirstGenericType(final Field pField) {
    if (pField != null) {
      Type genericSuperClass = pField.getGenericType();
      if (genericSuperClass instanceof ParameterizedType) {
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperClass;
        Type[]            typeArray         = parameterizedType.getActualTypeArguments();
        if (typeArray.length >= 1) {
          return typeArray[0];
        }
      }
    }
    return null;
  }
}
