package org.minutenwerk.mimic4j.container;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmModelAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmRootAccessor;
import org.minutenwerk.mimic4j.api.attribute.MmBoolean;
import org.minutenwerk.mimic4j.api.attribute.MmBooleanAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmEnum;
import org.minutenwerk.mimic4j.api.attribute.MmEnumAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmInstant;
import org.minutenwerk.mimic4j.api.attribute.MmInstantAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDate;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalDateTimeAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTime;
import org.minutenwerk.mimic4j.api.attribute.MmLocalTimeAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmString;
import org.minutenwerk.mimic4j.api.attribute.MmStringAnnotation;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTime;
import org.minutenwerk.mimic4j.api.attribute.MmZonedDateTimeAnnotation;
import org.minutenwerk.mimic4j.api.container.MmTab;
import org.minutenwerk.mimic4j.api.container.MmTabAnnotation;
import org.minutenwerk.mimic4j.container.Person.Gender;
import org.minutenwerk.mimic4j.impl.MmBaseDeclaration;

public class MmTabPerson extends MmTab<Person> {

  @MmStringAnnotation(id = "vn")
  public final MmString vorname = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.vorname();
    }
  };

  @MmStringAnnotation(id = "nn")
  public final MmString nachname = new MmString(this) {
    @Override
    public MmAttributeAccessor<?, String> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.nachname();
    }
  };

  @MmLocalDateAnnotation(id = "bd")
  public final MmLocalDate birthday = new MmLocalDate(this) {
    @Override
    public MmAttributeAccessor<?, LocalDate> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.birthday();
    }
  };

  @MmInstantAnnotation(id = "in")
  public final MmInstant instant = new MmInstant(this) {
    @Override
    public MmAttributeAccessor<?, Instant> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.instant();
    }
  };

  @MmLocalTimeAnnotation(id = "lt")
  public final MmLocalTime localTime = new MmLocalTime(this) {
    @Override
    public MmAttributeAccessor<?, LocalTime> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.localTime();
    }
  };

  @MmLocalDateTimeAnnotation(id = "ldt")
  public final MmLocalDateTime localDateTime = new MmLocalDateTime(this) {
    @Override
    public MmAttributeAccessor<?, LocalDateTime> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.localDateTime();
    }
  };

  @MmZonedDateTimeAnnotation(id = "zdt")
  public final MmZonedDateTime zonedDateTime = new MmZonedDateTime(this) {
    @Override
    public MmAttributeAccessor<?, ZonedDateTime> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.zonedDateTime();
    }
  };

  @MmEnumAnnotation(id = "gd")
  @SuppressWarnings("unchecked")
  public final MmEnum<Gender> gender = new MmEnum<Gender>(this) {
    @Override
    public MmAttributeAccessor<?, Gender> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.gender();
    }
  };

  @MmBooleanAnnotation(id = "mb")
  public final MmBoolean isMember = new MmBoolean(this) {
    @Override
    public MmAttributeAccessor<?, Boolean> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.isMember();
    }
  };

  @MmTabAnnotation(id = "adr")
  public final MmTabAdresse adresse = new MmTabAdresse(this) {
    @Override
    public MmModelAccessor<?, Adress> callbackMmGetAccessor(MmModelAccessor<?, ?> pParentAccessor) {
      PersonAccessor person = (PersonAccessor) pParentAccessor;
      return person.adresse();
    }
  };

  public MmTabPerson(MmBaseDeclaration<?, ?> pParent, MmRootAccessor<Person> pParentAccessor) {
    super(pParent, pParentAccessor);
  }
}
