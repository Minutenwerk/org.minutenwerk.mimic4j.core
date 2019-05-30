package org.minutenwerk.mimic4j.impl.javahelper;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.javahelper.MmHelperPerson.Gender;

public class TestJavaHelper {

  @Test
  public void testJavaHelperGetFields() {
    // test
    List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);

    // assert
    Assert.assertNotNull(fields);
    Assert.assertEquals(10, fields.size());
  }

  @Test
  public void testJavaHelperGetEnumGeneric() {
    // test
    List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
    Type type = MmJavaHelper.getFirstGenericType(fields.get(7));

    // assert
    Assert.assertNotNull(type);
    Assert.assertEquals(Gender.class, type);
  }

  @Ignore
  public void testJavaHelperGetTabGeneric() {
    // test
    List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
    Type type = MmJavaHelper.getFirstGenericType(fields.get(9));

    // assert
    Assert.assertNotNull(type);
    Assert.assertEquals(MmHelperAdresse.class, type);
  }

}
