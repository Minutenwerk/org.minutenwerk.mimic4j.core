package org.minutenwerk.mimic4j.table;

import java.time.LocalDate;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListEntryAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
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
import org.minutenwerk.mimic4j.table.Person.Gender;

public class MmTableRowPersonen extends MmTableRow<Person> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.vorname();
    }
  };

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.nachname();
    }
  };

  @MmLocalDateAnnotation(id = "bd", formatPattern = "yyyy-MM-dd")
  public final MmLocalDate birthday = new MmLocalDate(this) {
    @Override
    public MmAttributeAccessor<?, LocalDate> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.birthday();
    }
  };

  @SuppressWarnings("unchecked")
  @MmEnumAnnotation(id = "gd")
  public final MmEnum<Person.Gender> gender = new MmEnum<Person.Gender>(this) {
    @Override
    public MmAttributeAccessor<?, Gender> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.gender();
    }
  };

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this) {
    @Override
    public MmAttributeAccessor<?, Boolean> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.isMember();
    }
  };

  public MmTableRowPersonen(MmBaseDeclaration<?, ?> pParent, int pRowIndex) {
    super(pParent, pRowIndex);
  };

  @Override
  public MmListEntryAccessor<Person> callbackMmGetModelAccessor(MmModelAccessor<?, ?> pParentAccessor) {
    @SuppressWarnings("unchecked")
    MmListAccessor<Team, Person> personen = (MmListAccessor<Team, Person>) pParentAccessor;
    return new PersonAccessor(personen, () -> getMmRowIndex());
  }
}
