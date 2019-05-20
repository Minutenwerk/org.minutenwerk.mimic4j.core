package org.minutenwerk.mimic4j.impl.accessor;

import java.util.List;
import java.util.function.Supplier;

public class ChapterAccessor extends MmListEntryAccessor<List<Chapter>, Chapter> {

  public ChapterAccessor(final MmListAccessor<?, List<Chapter>, Chapter> chapterAccessor,
      final Supplier<Integer> indexSupplier) {
    super(chapterAccessor, indexSupplier);
  }

  public MmAttributeAccessor<Chapter, String> topic() {
    return new MmAttributeAccessor<>(this, Chapter::getTopic, Chapter::setTopic);
  }
}
