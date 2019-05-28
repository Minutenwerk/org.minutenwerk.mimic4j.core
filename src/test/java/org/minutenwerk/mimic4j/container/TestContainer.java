package org.minutenwerk.mimic4j.container;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.exception.MmException;
import org.minutenwerk.mimic4j.container.Person.Gender;
import org.minutenwerk.mimic4j.impl.MmJavaHelper;

public class TestContainer {

  private static final Logger LOGGER = LogManager.getLogger(TestContainer.class);
  private static final String BIRTHDAY = "2018-02-02";
  private static final LocalDate BIRTHDAY_DATE = LocalDate.parse(BIRTHDAY);

  @Test
  public void test1() {
	LOGGER.debug("test1");
    // setup
    Adresse testAdresse = new Adresse().setStreet("Hauptstraße").setCity("Hamburg");
    Person testPerson = new Person().setVorname("John").setNachname("Doe").setGender(Gender.MALE)
        .setBirthday(BIRTHDAY_DATE).setAdresse(testAdresse);
    PersonController controller = new PersonController();

    // test
    controller.person().set(testPerson);

    // assert
    Assert.assertEquals("John", controller.tabPerson.vorname.getMmViewsideValue());
    Assert.assertEquals("Doe", controller.tabPerson.nachname.getMmViewsideValue());
    Assert.assertEquals("MALE", controller.tabPerson.gender.getMmViewsideValue());
    Assert.assertEquals("02.02.2018", controller.tabPerson.birthday.getMmViewsideValue());
    Assert.assertNotNull(controller.tabPerson.adresse.getMmModel());
    Assert.assertEquals("Hauptstraße", controller.tabPerson.adresse.street.getMmViewsideValue());
    Assert.assertEquals("Hamburg", controller.tabPerson.adresse.city.getMmViewsideValue());
  }

  @Test
  public void test2() throws MmException {
    LOGGER.debug("test2");
    // setup
    Adresse testAdresse = new Adresse().setStreet("Hauptstraße").setCity("Hamburg");
    Person testPerson = new Person().setVorname("John").setNachname("Doe").setGender(Gender.MALE)
        .setBirthday(BIRTHDAY_DATE).setAdresse(testAdresse);
    PersonController controller = new PersonController();

    // test
    controller.person().set(testPerson);

    controller.tabPerson.vorname.setMmViewsideValue("Jane");
    controller.tabPerson.nachname.setMmViewsideValue("Austen");
    controller.tabPerson.gender.setMmViewsideValue("FEMALE");
    controller.tabPerson.birthday.setMmViewsideValue("16.12.1775");
    controller.tabPerson.adresse.street.setMmViewsideValue("Mansfield Park");
    controller.tabPerson.adresse.city.setMmViewsideValue("Steventon");
    controller.tabPerson.doMmValidate();

    // assert
    Assert.assertEquals("Jane", controller.person().vorname().get());
    Assert.assertEquals("Austen", controller.person().nachname().get());
    Assert.assertEquals(Gender.FEMALE, controller.person().gender().get());
    Assert.assertEquals("1775-12-16", controller.person().birthday().get().toString());
    Assert.assertEquals("Mansfield Park", controller.person().adresse().street().get());
    Assert.assertEquals("Steventon", controller.person().adresse().city().get());
  }

	@Test
	public void testJavaHelper(){
	  LOGGER.debug("testJavaHelper");
	  // setup
	  List<Field> fields = MmJavaHelper.findPublicStaticFinalBaseDeclarationFields(MmTabPerson.class);
	  Assert.assertNotNull(fields);
	  Assert.assertEquals(10, fields.size());
	  MmStringAnnotation stringAnnotation = MmJavaHelper.findAnnotation(fields.get(0), MmStringAnnotation.class);
	  Assert.assertNotNull(stringAnnotation);
	  Assert.assertEquals("vn", stringAnnotation.id());
	}
}
