package org.minutenwerk.mimic4j.table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.minutenwerk.mimic4j.api.composite.MmRoot;
import org.minutenwerk.mimic4j.table.TestModel.Gender;

public class MmMimicModelTest {

  private final TestTableMm testTable = new TestTableMm(new MmRoot());
  
  private List<TestModel> testModelList;
  
  @Before public void before() {
    testModelList = new ArrayList<>();
    TestModel testModel1 = new TestModel().setVorname("John").setNachname("Doe").
        setGender(Gender.MALE).setMember(true).setBirthday(LocalDateTime.now());
    testModelList.add(testModel1);
    TestModel testModel2 = new TestModel().setVorname("Jane").setNachname("Doe").
        setGender(Gender.FEMALE).setMember(false).setBirthday(LocalDateTime.now());
    testModelList.add(testModel2);
  }

  @Test
  public void test1() {
    testTable.doMmSetModelsideFromModel(testModelList);
  }
}
