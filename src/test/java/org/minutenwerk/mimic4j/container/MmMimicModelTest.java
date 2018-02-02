package org.minutenwerk.mimic4j.container;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.exception.MmException;
import org.minutenwerk.mimic4j.container.TestModel.Gender;

public class MmMimicModelTest {

  private final TestDialogMm testDialog = new TestDialogMm(new MmRoot());

  private TestModel testModel;

  private static final LocalDateTime BIRTHDAY = LocalDateTime.parse("2018-02-02T10:18:30");

  @Before
  public void before() {
    TestAdresse testAdresse = new TestAdresse().setStreet("Hauptstraße").setCity("Hamburg");
    testModel = new TestModel().setVorname("John").setNachname("Doe").
        setGender(Gender.MALE).setBirthday(BIRTHDAY).setAdresse(testAdresse);
  }

  @Test
  public void test1() {
    testDialog.doMmSetModelsideFromModel(testModel);
    Assert.assertEquals("vorname must be John", "John", testDialog.vorname.getMmViewsideValue());
    Assert.assertEquals("nachname must be Doe", "Doe", testDialog.nachname.getMmViewsideValue());
    Assert.assertEquals("gender must be MALE", "MALE", testDialog.gender.getMmViewsideValue());
    Assert.assertEquals("birthday must be 02.02.2018 10:18:30", "02.02.2018 10:18:30", testDialog.birthday.getMmViewsideValue());
    Assert.assertNotNull("adresse must be not null", testDialog.adresse.getMmModel());
    Assert.assertEquals("adresse.street must be Hauptstraße", "Hauptstraße", testDialog.adresse.street.getMmViewsideValue());
    Assert.assertEquals("adresse.city must be Hamburg", "Hamburg", testDialog.adresse.city.getMmViewsideValue());
  }

  @Test
  public void test2() throws MmException {
    testDialog.doMmSetModelsideFromModel(testModel);
    testDialog.vorname.setMmViewsideValue("Jane");
    testDialog.nachname.setMmViewsideValue("Austen");
    testDialog.gender.setMmViewsideValue("FEMALE");
    testDialog.birthday.setMmViewsideValue("16.12.1775 00:00:00");
    testDialog.adresse.street.setMmViewsideValue("Mansfield Park");
    testDialog.adresse.city.setMmViewsideValue("Steventon");
    testDialog.doMmValidate();
    testDialog.doMmSetModelFromModelside();
    Assert.assertEquals("vorname must be Jane", "Jane", testModel.getVorname());
    Assert.assertEquals("nachname must be Austen", "Austen", testModel.getNachname());
    Assert.assertEquals("gender must be FEMALE", Gender.FEMALE, testModel.getGender());
    Assert.assertEquals("birthday must be 1775-12-16T00:00", "1775-12-16T00:00", testModel.getBirthday().toString());
    Assert.assertEquals("adresse.street must be Mansfield Park", "Mansfield Park", testModel.getAdresse().getStreet());
    Assert.assertEquals("adresse.city must be Steventon", "Steventon", testModel.getAdresse().getCity());
  }

}
