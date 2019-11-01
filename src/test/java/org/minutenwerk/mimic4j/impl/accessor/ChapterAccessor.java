package org.minutenwerk.mimic4j.impl.accessor;

import java.util.function.Supplier;

import org.minutenwerk.mimic4j.api.accessor.MmAttributeAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListAccessor;
import org.minutenwerk.mimic4j.api.accessor.MmListEntryAccessor;

public class ChapterAccessor extends MmListEntryAccessor<Chapter> {

  public ChapterAccessor(final MmListAccessor<?, Chapter> chapterAccessor,
      final Supplier<Integer> indexSupplier) {
    super(chapterAccessor, indexSupplier);
  }

  public MmAttributeAccessor<Chapter, String> topic() {
    return new MmAttributeAccessor<>(this, Chapter::getTopic, Chapter::setTopic);
  }
}
