package org.minutenwerk.mimic4j.impl.javahelper;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;
import org.minutenwerk.mimic4j.impl.attribute.MmConfigurationString;

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
	public void testJavaHelperGetStringGeneric(){
	  // test
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
	  Optional<Type> optType = MmJavaHelper.getFirstGenericType(fields.get(0));

	  // assert
	  Assert.assertTrue(optType.isPresent());
	  // TODO Assert.assertEquals(String.class, optType.get());
	}

	@Test
	public void testJavaHelperGetTabGeneric(){
	  // test
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabHelperPerson.class);
	  Optional<Type> optType = MmJavaHelper.getFirstGenericType(fields.get(9));

	  // assert
	  Assert.assertTrue(optType.isPresent());
	  Assert.assertEquals(MmHelperAdresse.class, optType.get());
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
