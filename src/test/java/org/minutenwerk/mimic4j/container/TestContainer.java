package org.minutenwerk.mimic4j.container;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.exception.MmException;
import org.minutenwerk.mimic4j.container.Person.Gender;

public class TestContainer {

  private static final Logger LOGGER = LogManager.getLogger(TestContainer.class);
  private static final String BIRTHDAY = "2018-02-02";
  private static final LocalDate BIRTHDAY_DATE = LocalDate.parse(BIRTHDAY);

  @Test
  public void test1() {
    LOGGER.debug("test1");
    // setup
    Adress testAdresse = new Adress().setStreet("Hauptstraße").setCity("Hamburg");
    Person testPerson = new Person().setVorname("John").setNachname("Doe").setGender(Gender.MALE)
        .setBirthday(BIRTHDAY_DATE).setAdresse(testAdresse);
    PersonController controller = new PersonController();

    // test
    controller.person().set(testPerson);
    controller.pagePerson.onModelChange();

    // assert
    Assert.assertEquals("John", controller.pagePerson.vorname.getMmViewValue());
    Assert.assertEquals("Doe", controller.pagePerson.nachname.getMmViewValue());
    Assert.assertEquals("MALE", controller.pagePerson.gender.getMmViewValue());
    Assert.assertEquals("02.02.2018", controller.pagePerson.birthday.getMmViewValue());
    Assert.assertNotNull(controller.pagePerson.adresse.getMmModel());
    Assert.assertEquals("Hauptstraße", controller.pagePerson.adresse.street.getMmViewValue());
    Assert.assertEquals("Hamburg", controller.pagePerson.adresse.city.getMmViewValue());
  }

  @Test
  public void test2() throws MmException {
    LOGGER.debug("test2");
    // setup
    Adress testAdresse = new Adress().setStreet("Hauptstraße").setCity("Hamburg");
    Person testPerson = new Person().setVorname("John").setNachname("Doe").setGender(Gender.MALE)
        .setBirthday(BIRTHDAY_DATE).setAdresse(testAdresse);
    PersonController controller = new PersonController();

    // test
    controller.person().set(testPerson);
    controller.pagePerson.onModelChange();

    controller.pagePerson.vorname.setMmViewValue("Jane");
    controller.pagePerson.nachname.setMmViewValue("Austen");
    controller.pagePerson.gender.setMmViewValue("FEMALE");
    controller.pagePerson.birthday.setMmViewValue("16.12.1775");
    controller.pagePerson.adresse.street.setMmViewValue("Mansfield Park");
    controller.pagePerson.adresse.city.setMmViewValue("Steventon");
    controller.pagePerson.doMmValidate();

    // assert
    Assert.assertEquals("Jane", controller.person().vorname().get());
    Assert.assertEquals("Austen", controller.person().nachname().get());
    Assert.assertEquals(Gender.FEMALE, controller.person().gender().get());
    Assert.assertEquals("1775-12-16", controller.person().birthday().get().toString());
    Assert.assertEquals("Mansfield Park", controller.person().adresse().street().get());
    Assert.assertEquals("Steventon", controller.person().adresse().city().get());
  }
}
