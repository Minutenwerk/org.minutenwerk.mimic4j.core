package org.minutenwerk.mimic4j.table;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Person {

  private String vorname;

  private String nachname;

  private boolean isMember;

  private LocalDate birthday;

  private Instant instant;

  private LocalTime localTime;

  private LocalDateTime localDateTime;

  private ZonedDateTime zonedDateTime;

  private Gender gender;

  public enum Gender {
    MALE, FEMALE;
  }

  public Person() {
  }

  public String getVorname() {
    return this.vorname;
  }

  public Person setVorname(final String vorname) {
    this.vorname = vorname;
    return this;
  }

  public String getNachname() {
    return this.nachname;
  }

  public Person setNachname(final String nachname) {
    this.nachname = nachname;
    return this;
  }

  public boolean isMember() {
    return this.isMember;
  }

  public Person setMember(final boolean isMember) {
    this.isMember = isMember;
    return this;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public Person setBirthday(final LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }

  public Gender getGender() {
    return this.gender;
  }

  public Person setGender(final Gender gender) {
    this.gender = gender;
    return this;
  }

  public Instant getInstant() {
    return this.instant;
  }

  public void setInstant(final Instant instant) {
    this.instant = instant;
  }

  public LocalTime getLocalTime() {
    return this.localTime;
  }

  public void setLocalTime(final LocalTime localTime) {
    this.localTime = localTime;
  }

  public LocalDateTime getLocalDateTime() {
    return this.localDateTime;
  }

  public void setLocalDateTime(final LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public ZonedDateTime getZonedDateTime() {
    return this.zonedDateTime;
  }

  public void setZonedDateTime(final ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
  }

}
