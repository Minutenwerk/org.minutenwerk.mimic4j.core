package org.minutenwerk.mimic4j.table;

import org.minutenwerk.mimic4j.api.container.MmTable;
import org.minutenwerk.mimic4j.api.container.MmTableColumn;
import org.minutenwerk.mimic4j.api.container.MmTableColumnAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmTablePersonen extends MmTable<Person> {

  @MmTableColumnAnnotation(id = "colVn")
  public final MmTableColumn<?> vorname = new MmTableColumn<Person>(this);

  @MmTableColumnAnnotation(id = "colNn")
  public final MmTableColumn<?> nachname = new MmTableColumn<Person>(this);

  @MmTableColumnAnnotation(id = "colBd")
  public final MmTableColumn<?> birthday = new MmTableColumn<Person>(this);

  @MmTableColumnAnnotation(id = "colGd")
  public final MmTableColumn<?> gender = new MmTableColumn<Person>(this);

  @MmTableColumnAnnotation(id = "colMb")
  public final MmTableColumn<?> isMember = new MmTableColumn<Person>(this);

  public MmTablePersonen(MmBaseDeclaration<?, ?> pParent) {
    super(pParent);
  }

  @Override
  public MmTableRow<Person> callbackMmCreateTableRow(int pRowIndex) {
    return new MmTableRowPersonen(this, pRowIndex);
  }
}
