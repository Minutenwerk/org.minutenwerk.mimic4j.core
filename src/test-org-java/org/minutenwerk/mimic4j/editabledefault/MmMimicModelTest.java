package org.minutenwerk.mimic4j.editabledefault;

import org.junit.Assert;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.editabledefault.TestModel.Gender;

public class MmMimicModelTest {

  public final TestCrudDialog testDialog = new TestCrudDialog(new MmRoot());    

  public final TestModel testModel1 = new TestModel();

  @Test
  public void testVorname() {
    Assert.assertEquals("Vorname must be Martin", "Martin", this.testDialog.vorname.getMmModelsideValue());
    Assert.assertEquals("Vorname must be Martin", "Martin", this.testDialog.vorname.getMmViewsideValue());
  }
  
  @Test
  public void testNachname() {
    Assert.assertEquals("Nachname must be Fowler", "Fowler", this.testDialog.nachname.getMmModelsideValue());
    Assert.assertEquals("Nachname must be Fowler", "Fowler", this.testDialog.nachname.getMmViewsideValue());
  }
  
  @Test
  public void testBirthday() {
    Assert.assertEquals("Birthday must be null", null, this.testDialog.birthday.getMmModelsideValue());
    Assert.assertEquals("Birthday must be empty", "", this.testDialog.birthday.getMmViewsideValue());
  }
  
  @Test
  public void testGender() {
    Assert.assertEquals("Gender must be Gender.MALE", Gender.MALE, this.testDialog.gender.getMmModelsideValue());
    Assert.assertEquals("Gender must be 'MALE'", "MALE", this.testDialog.gender.getMmViewsideValue());
  }
  
  @Test
  public void testIsMember() {
    Assert.assertEquals("isMember must be true", Boolean.TRUE, this.testDialog.isMember.getMmModelsideValue());
    Assert.assertEquals("isMember must be true", true, this.testDialog.isMember.getMmViewsideValue());
  }

}
