package com.spinn3r.artemis.lang.codepage;

import com.google.common.collect.ImmutableList;
import com.spinn3r.artemis.util.primitives.Integers;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class CodepageReferencesTest {

    @Test
    public void testMaxRange() throws Exception {

        ImmutableList<CodepageReference> codepageReferences = CodepageReferences.create();

        Optional<Integer> max = codepageReferences
                                  .stream()
                                  .map(CodepageReference::getEnd)
                                  .max(Integer::compareTo);

        assertTrue(max.isPresent());

        // max: 195103

        // assertEquals(-1L, max.get().longValue());

    }

}