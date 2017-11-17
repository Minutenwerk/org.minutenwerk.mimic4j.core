package org.minutenwerk.mimic4j.editable;

import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.api.composite.MmRootAnnotation;
import org.minutenwerk.mimic4j.api.exception.MmException;
import org.minutenwerk.mimic4j.api.exception.MmValidatorException;
import org.minutenwerk.mimic4j.editable.TestModel.Gender;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MmMimicModelTest {

  @MmRootAnnotation(id="root")
  public class TestRoot extends MmRoot {
  }

  public final TestCrudDialog testDialog = new TestCrudDialog(new TestRoot());

  public final TestModel testModel1 = new TestModel().setVorname("John").setNachname("Doe").setMember(true).setBirthday(LocalDate.parse("2013-09-18")).setGender(Gender.FEMALE);

  private static final Logger LOGGER = LoggerFactory.getLogger(MmMimicModelTest.class);

  @Test
  public void testVorname() {
    testDialog.doMmSetModelsideFromModel(testModel1);
    Assert.assertEquals("Vorname must be John", "John", this.testDialog.vorname.getMmViewsideValue());
  }

  @Test
  public void testNachname() {
    testDialog.doMmSetModelsideFromModel(testModel1);
    Assert.assertEquals("Nachname must be Doe", "Doe", this.testDialog.nachname.getMmViewsideValue());
  }

  @Test
  public void testBirthday() {
    testDialog.doMmSetModelsideFromModel(testModel1);
    Assert.assertEquals("Birthday must be 18.09.2013", "18.09.2013", this.testDialog.birthday.getMmViewsideValue());
  }

  @Test
  public void testGender() {
    testDialog.doMmSetModelsideFromModel(testModel1);
    Assert.assertEquals("Gender must be Gender.FEMALE", Gender.FEMALE, this.testDialog.gender.getMmModelsideValue());
    Assert.assertEquals("Gender must be 'FEMALE'", "FEMALE", this.testDialog.gender.getMmViewsideValue());
  }

  @Test
  public void testIsMember() {
    testDialog.doMmSetModelsideFromModel(testModel1);
    Assert.assertEquals("IsMember must be false", true, this.testDialog.isMember.getMmViewsideValue());
    LOGGER.debug(testDialog.isMember.toString());
  }
  
  @Test
  public void testSetFromView() throws MmException {
    testDialog.vorname.setMmViewsideValue("Susanne");
    testDialog.nachname.setMmViewsideValue("Mustermann");
    testDialog.isMember.setMmViewsideValue(false);
    testDialog.birthday.setMmViewsideValue("03.02.2001");
    testDialog.doMmValidate();
    Assert.assertEquals("Birthday must be 03.02.2001", "2001-02-03", this.testDialog.birthday.getMmModelsideValue().toString());
  }
  
  @Test
  public void testSetModelEditFromViewsideValidateAndSetToModel() throws MmException {
    testDialog.doMmSetModelsideFromModel(testModel1);
    testDialog.vorname.setMmViewsideValue("Susanne");
    testDialog.doMmValidate();
    testDialog.doMmSetModelFromModelside();
    Assert.assertEquals("vorname must be Susanne", "Susanne", testModel1.getVorname());
  }

  @Test(expected=MmValidatorException.class)
  public void testIsRequired() throws MmException {
    try {
    testDialog.doMmValidate();
    } catch (ClassCastException e) {
      LOGGER.error("", e); 
    }
  }

  @Test(expected=MmValidatorException.class)
  public void testErrorInConversion() throws MmException {
    testDialog.birthday.setMmViewsideValue("huhu");
    testDialog.doMmValidate();
  }

}
