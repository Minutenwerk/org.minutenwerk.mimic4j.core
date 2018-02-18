package org.minutenwerk.mimic4j.table;

import java.time.LocalDate;

public class TestModel {

  private String vorname;

  private String nachname;

  private boolean isMember;

  private LocalDate birthday;

  private Gender gender;
  
  public enum Gender { MALE, FEMALE; }
  
  public TestModel() {
  }

  public String getVorname() {
    return this.vorname;
  }

  public TestModel setVorname(String vorname) {
    this.vorname = vorname;
    return this;
  }

  public String getNachname() {
    return this.nachname;
  }

  public TestModel setNachname(String nachname) {
    this.nachname = nachname;
    return this;
  }

  public boolean isMember() {
    return this.isMember;
  }

  public TestModel setMember(boolean isMember) {
    this.isMember = isMember;
    return this;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public TestModel setBirthday(LocalDate birthday) {
    this.birthday = birthday;
    return this;
  }
  
  public final Gender getGender() {
    return this.gender;
  }

  public final TestModel setGender(Gender pGender) {
    this.gender = pGender;
    return this;
  }

}
