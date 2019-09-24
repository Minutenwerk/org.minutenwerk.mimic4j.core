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
    controller.tabPerson.onModelChange();

    // assert
    Assert.assertEquals("John", controller.tabPerson.vorname.getMmViewModel());
    Assert.assertEquals("Doe", controller.tabPerson.nachname.getMmViewModel());
    Assert.assertEquals("MALE", controller.tabPerson.gender.getMmViewModel());
    Assert.assertEquals("02.02.2018", controller.tabPerson.birthday.getMmViewModel());
    Assert.assertNotNull(controller.tabPerson.adresse.getMmModel());
    Assert.assertEquals("Hauptstraße", controller.tabPerson.adresse.street.getMmViewModel());
    Assert.assertEquals("Hamburg", controller.tabPerson.adresse.city.getMmViewModel());
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
    controller.tabPerson.onModelChange();

    controller.tabPerson.vorname.setMmViewModel("Jane");
    controller.tabPerson.nachname.setMmViewModel("Austen");
    controller.tabPerson.gender.setMmViewModel("FEMALE");
    controller.tabPerson.birthday.setMmViewModel("16.12.1775");
    controller.tabPerson.adresse.street.setMmViewModel("Mansfield Park");
    controller.tabPerson.adresse.city.setMmViewModel("Steventon");
    controller.tabPerson.doMmValidate();

    // assert
    Assert.assertEquals("Jane", controller.person().vorname().get());
    Assert.assertEquals("Austen", controller.person().nachname().get());
    Assert.assertEquals(Gender.FEMALE, controller.person().gender().get());
    Assert.assertEquals("1775-12-16", controller.person().birthday().get().toString());
    Assert.assertEquals("Mansfield Park", controller.person().adresse().street().get());
    Assert.assertEquals("Steventon", controller.person().adresse().city().get());
  }
}
