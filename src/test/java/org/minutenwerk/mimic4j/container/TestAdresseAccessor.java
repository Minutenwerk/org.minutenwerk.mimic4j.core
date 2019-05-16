package org.minutenwerk.mimic4j.container;

import org.minutenwerk.mimic4j.impl.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmComponentAccessor;

public class TestAdresseAccessor extends MmComponentAccessor<TestModel, TestAdresse> {

	public TestAdresseAccessor(final TestModelAccessor testModelAccessor) {
		super(testModelAccessor, TestModel::getAdresse, TestModel::setAdresse);
	}

	public MmAttributeAccessor<TestAdresse, String> street() {
		return new MmAttributeAccessor<>(this, TestAdresse::getStreet, TestAdresse::setStreet);
	}

	public MmAttributeAccessor<TestAdresse, String> city() {
		return new MmAttributeAccessor<>(this, TestAdresse::getCity, TestAdresse::setCity);
	}
}
