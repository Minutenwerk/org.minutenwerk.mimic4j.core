package org.minutenwerk.mimic4j.container;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.minutenwerk.mimic4j.container.TestModel.Gender;
import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmRootAccessor;

public class TestModelAccessor extends MmRootAccessor<TestModel> {

	public MmAttributeAccessor<TestModel, String> vorname() {
	    return new MmAttributeAccessor<>(this, TestModel::getVorname, TestModel::setVorname);
	}

	public MmAttributeAccessor<TestModel, String> nachname() {
		return new MmAttributeAccessor<>(this, TestModel::getNachname, TestModel::setNachname);
	}

	public MmAttributeAccessor<TestModel, Boolean> isMember() {
		return new MmAttributeAccessor<>(this, TestModel::isMember, TestModel::setMember);
	}

	public MmAttributeAccessor<TestModel, LocalDate> birthday() {
		return new MmAttributeAccessor<>(this, TestModel::getBirthday, TestModel::setBirthday);
	}

	public MmAttributeAccessor<TestModel, Gender> gender() {
		return new MmAttributeAccessor<>(this, TestModel::getGender, TestModel::setGender);
	}

	public TestAdresseAccessor adresse() {
		return new TestAdresseAccessor(this);
	}

	public MmAttributeAccessor<TestModel, Instant> instant() {
		return new MmAttributeAccessor<>(this, TestModel::getInstant, TestModel::setInstant);
	}

	public MmAttributeAccessor<TestModel, LocalTime> localTime() {
		return new MmAttributeAccessor<>(this, TestModel::getLocalTime, TestModel::setLocalTime);
	}

	public MmAttributeAccessor<TestModel, LocalDateTime> localDateTime() {
		return new MmAttributeAccessor<>(this, TestModel::getLocalDateTime, TestModel::setLocalDateTime);
	}

	public MmAttributeAccessor<TestModel, ZonedDateTime> zonedDateTime() {
		return new MmAttributeAccessor<>(this, TestModel::getZonedDateTime, TestModel::setZonedDateTime);
	}
}
