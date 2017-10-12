package com.spinn3r.artemis.lang;

import com.spinn3r.artemis.lang.codepage.CodepageLangClassifier;
import com.spinn3r.artemis.lang.ngramcat.NGramLangClassifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryUsageTest {

    LangClassifier classifier;

    @Before
    public void setUp() throws Exception {

        CodepageLangClassifier codepageLangClassifier = new CodepageLangClassifier();
        codepageLangClassifier.init();

        NGramLangClassifier nGramLangClassifier = new NGramLangClassifier();
        nGramLangClassifier.init();

        classifier = new CompositeLangClassifier( codepageLangClassifier, nGramLangClassifier );

    }

    @Test
    public void test1() throws Exception {

        // test that we don't have INSANE memory usage by loading profiles into
        // memory.  it's possible that loading training material could destroy
        // memory so we need a way to prevent that from happening.

        Runtime runtime = Runtime.getRuntime();

        runtime.gc();

        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        CodepageLangClassifier codepageLangClassifier = new CodepageLangClassifier();
        codepageLangClassifier.init();

        NGramLangClassifier nGramLangClassifier = new NGramLangClassifier();
        nGramLangClassifier.init();

        runtime.gc();

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        long memoryUsage = memoryAfter - memoryBefore;

        System.out.printf( "memoryUsage: %,d\n", memoryUsage );

        assertTrue( memoryUsage <= 15_000_000 );

    }

}