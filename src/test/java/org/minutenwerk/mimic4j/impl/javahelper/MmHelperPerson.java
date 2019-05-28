package org.minutenwerk.mimic4j.impl.javahelper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class MmHelperPerson {

	private String vorname;

	private String nachname;

	private boolean isMember;

	private LocalDate birthday;

	private Instant instant;

	private LocalTime localTime;

	private LocalDateTime localDateTime;

	private ZonedDateTime zonedDateTime;

	private Gender gender;

	private MmHelperAdresse adresse;

	public enum Gender {
		MALE, FEMALE;
	}

	public MmHelperPerson() {
	}

	public String getVorname() {
		return this.vorname;
	}

	public MmHelperPerson setVorname(final String vorname) {
		this.vorname = vorname;
		return this;
	}

	public String getNachname() {
		return this.nachname;
	}

	public MmHelperPerson setNachname(final String nachname) {
		this.nachname = nachname;
		return this;
	}

	public boolean isMember() {
		return this.isMember;
	}

	public MmHelperPerson setMember(final boolean isMember) {
		this.isMember = isMember;
		return this;
	}

	public LocalDate getBirthday() {
		return this.birthday;
	}

	public MmHelperPerson setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	public Gender getGender() {
		return this.gender;
	}

	public MmHelperPerson setGender(final Gender gender) {
		this.gender = gender;
		return this;
	}

	public MmHelperAdresse getAdresse() {
		return this.adresse;
	}

	public MmHelperPerson setAdresse(final MmHelperAdresse adresse) {
		this.adresse = adresse;
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
