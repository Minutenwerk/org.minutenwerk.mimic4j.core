package org.minutenwerk.mimic4j.impl.javahelper;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationString;
import org.minutenwerk.mimic4j.impl.javahelper.MmHelperPerson.Gender;

public class TestJavaHelper {

	@Test
	public void testJavaHelperGetFields(){
	  // test
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);

	  // assert
	  Assert.assertNotNull(fields);
	  Assert.assertEquals(10, fields.size());
	}

	@Test
	public void testJavaHelperGetEnumGeneric(){
	  // test
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
	  Type type = MmJavaHelper.getFirstGenericType(fields.get(7));

	  // assert
	  Assert.assertNotNull(type);
	  Assert.assertEquals(Gender.class, type);
	}

	@Ignore
	public void testJavaHelperGetTabGeneric(){
	  // test
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
	  Type type = MmJavaHelper.getFirstGenericType(fields.get(9));

	  // assert
	  Assert.assertNotNull(type);
	  Assert.assertEquals(MmHelperAdresse.class, type);
	}

	@Test
	public void testJavaHelperGetStringAnnotation(){
	  // test
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
	  MmStringAnnotation stringAnnotation = MmJavaHelper.findAnnotation(fields.get(0), MmStringAnnotation.class);

	  // assert
	  Assert.assertNotNull(stringAnnotation);
	  Assert.assertEquals("vn", stringAnnotation.id());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_IS_VISIBLE, stringAnnotation.visible());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_IS_ENABLED, stringAnnotation.enabled());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_IS_READONLY, stringAnnotation.readOnly());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_IS_REQUIRED, stringAnnotation.required());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_JSF_TAG, stringAnnotation.jsfTag());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_JSF_TAG_DISABLED, stringAnnotation.jsfTagDisabled());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_COLS, stringAnnotation.cols());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_ROWS, stringAnnotation.rows());
	  Assert.assertEquals(MmConfigurationString.DEFAULT_FORMAT_MAX_LENGTH, stringAnnotation.formatMaxLength());
	}
}
