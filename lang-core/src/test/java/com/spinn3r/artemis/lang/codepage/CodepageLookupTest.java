package com.spinn3r.artemis.lang.codepage;

import com.google.common.collect.ImmutableList;
import com.spinn3r.artemis.guava.ImmutableCollectors;
import com.spinn3r.artemis.lang.Lang;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.spinn3r.artemis.lang.Lang.RU;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class CodepageLookupTest {

    @Test
    public void testLookup() throws Exception {

        ImmutableList<CodepageReference> codepageReferences = CodepageReferences.create();

        AtomicInteger idx = new AtomicInteger();
        CodepageLookup codepageLookup = new CodepageLookup(codepageReferences.stream()
                                                                             .map(current -> new Codepage(current.getStart(), current.getEnd(), current.getLang(), idx.incrementAndGet()))
                                                                             .collect(ImmutableCollectors.toImmutableList()));

        assertNull(codepageLookup.lookup(' '));
        assertNull(codepageLookup.lookup((char)Character.MAX_CODE_POINT));
        assertEquals(Lang.KO, codepageLookup.lookup((char) 0x3130).getLang());

    }

}