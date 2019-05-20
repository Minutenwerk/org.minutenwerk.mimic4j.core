package org.minutenwerk.mimic4j.table;

import java.time.LocalDate;
import java.util.List;

import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTableRow;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.table.Person.Gender;

public class MmTableRowPersonen extends MmTableRow<Person> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this) {
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.person(rowIndex).vorname();
    }
  };

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this) {
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.person(rowIndex).nachname();
    }
  };

  @MmLocalDateAnnotation(id = "bd", formatPattern = "yyyy-MM-dd")
  public final MmLocalDate birthday = new MmLocalDate(this) {
    public MmAttributeAccessor<?, LocalDate> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.person(rowIndex).birthday();
    }
  };

  @SuppressWarnings("unchecked")
  @MmEnumAnnotation(id = "gd")
  public final MmEnum<Person.Gender> gender = new MmEnum<Person.Gender>(this) {
    public MmAttributeAccessor<?, Gender> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.person(rowIndex).gender();
    }
  };

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this) {
    public MmAttributeAccessor<?, Boolean> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
      TeamAccessor team = (TeamAccessor) pRootAccessor;
      return team.person(rowIndex).isMember();
    }
  };

  public MmTableRowPersonen(MmBaseDeclaration<?, ?> pParent, int pRowIndex) {
    super(pParent, pRowIndex);
  };

  @Override
  public MmListEntryAccessor<? extends List<Person>, Person> callbackMmGetAccessor(MmRootAccessor<?> pRootAccessor) {
    TeamAccessor team = (TeamAccessor) pRootAccessor;
    return team.person(rowIndex);
  }
}
