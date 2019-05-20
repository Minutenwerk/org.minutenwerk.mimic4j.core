package org.minutenwerk.mimic4j.table;

import java.util.List;

import org.minutenwerk.mimic4j.impl.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.impl.accessor.MmModelAccessor;

public class PersonenAccessor extends MmListAccessor<Team, List<Person>, Person> {

	public PersonenAccessor(final MmModelAccessor<?, Team> rootAccessor) {
		super(rootAccessor, Team::getPersonen, Team::setPersonen);
	}
}
