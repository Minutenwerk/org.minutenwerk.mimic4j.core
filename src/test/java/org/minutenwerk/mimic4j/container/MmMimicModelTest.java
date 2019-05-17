package org.minutenwerk.mimic4j.container;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.exception.MmException;
import org.minutenwerk.mimic4j.container.TestModel.Gender;

public class MmMimicModelTest {

  private static final LocalDate BIRTHDAY = LocalDate.parse("2018-02-02");

  private final TestModelAccessor testModel = new TestModelAccessor();
  
  private final TestDialogMm testDialog = new TestDialogMm(new MmRoot(), testModel());

  public TestModelAccessor testModel() {
    return testModel;
  }
  
  @Test
  public void test1() {
    // setup
    TestAdresse testAdresse = new TestAdresse().setStreet("Hauptstraße").setCity("Hamburg");
    TestModel model = new TestModel().setVorname("John").setNachname("Doe").setGender(Gender.MALE).setBirthday(BIRTHDAY).setAdresse(testAdresse);
    
    // test
    testModel().set(model);
    
    // assert
    Assert.assertEquals("John", testDialog.vorname.getMmViewsideValue());
    Assert.assertEquals("Doe", testDialog.nachname.getMmViewsideValue());
    Assert.assertEquals("MALE", testDialog.gender.getMmViewsideValue());
    Assert.assertEquals("02.02.2018", testDialog.birthday.getMmViewsideValue());
    Assert.assertNotNull(testDialog.adresse.getMmModel());
    Assert.assertEquals("Hauptstraße", testDialog.adresse.street.getMmViewsideValue());
    Assert.assertEquals("Hamburg", testDialog.adresse.city.getMmViewsideValue());
  }

  @Test
  public void test2() throws MmException {
    TestAdresse testAdresse = new TestAdresse().setStreet("Hauptstraße").setCity("Hamburg");
    TestModel model = new TestModel().setVorname("John").setNachname("Doe").setGender(Gender.MALE).setBirthday(BIRTHDAY).setAdresse(testAdresse);
    
    testModel().set(model);

    testDialog.vorname.setMmViewsideValue("Jane");
    testDialog.nachname.setMmViewsideValue("Austen");
    testDialog.gender.setMmViewsideValue("FEMALE");
    testDialog.birthday.setMmViewsideValue("16.12.1775");
    testDialog.adresse.street.setMmViewsideValue("Mansfield Park");
    testDialog.adresse.city.setMmViewsideValue("Steventon");
    testDialog.doMmValidate();

    Assert.assertEquals("Jane", testModel().vorname().get());
    Assert.assertEquals("Austen", testModel().nachname().get());
    Assert.assertEquals(Gender.FEMALE, testModel().gender().get());
    Assert.assertEquals("1775-12-16", testModel().birthday().get().toString());
    Assert.assertEquals("Mansfield Park", testModel().adresse().street().get());
    Assert.assertEquals("Steventon", testModel().adresse().city().get());
  }

}
