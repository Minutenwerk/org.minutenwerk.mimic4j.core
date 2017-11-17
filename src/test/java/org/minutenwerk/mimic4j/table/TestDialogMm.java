package org.minutenwerk.mimic4j.table;

import java.util.List;

import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestDialogMm extends MmTab<List<TestModel>> {

  private final TestTableMm testTable = new TestTableMm(this);

  public TestDialogMm(MmBaseDeclaration<?,?> pParent) {
    super(pParent);
  };

  @Override
  public void callbackMmSetModelsideFromModel(List<TestModel> pModelList) {
    this.testTable.doMmSetModelsideFromModel(pModelList);
  }

  @Override
  public void callbackMmSetModelFromModelside(List<TestModel> pModelList) {
  }

}
