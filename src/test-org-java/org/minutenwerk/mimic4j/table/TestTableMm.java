package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.api.composite.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class TestTableMm extends MmTable<TestModel> {
  
  public final MmTableColumn vorname = new MmTableColumn(this); 

  public final MmTableColumn nachname = new MmTableColumn(this);

  public final MmTableColumn birthday = new MmTableColumn(this);

  public final MmTableColumn gender = new MmTableColumn(this);

  public final MmTableColumn isMember = new MmTableColumn(this);

  public TestTableMm(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }

  @Override
  public MmTableRow<TestModel> callbackMmCreateTableRow(int pRowIndex) {
    return new TestTableRowMm(this, pRowIndex);
  }
}
