package com.spinn3r.artemis.lang.iso639;

import com.google.common.collect.ImmutableList;
import com.spinn3r.artemis.corpus.test.CorporaAsserter;
import com.spinn3r.artemis.corpus.test.CorporaCache;
import com.spinn3r.artemis.util.text.CollectionFormatter;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ISOLangCodesTest {

    CorporaAsserter corporaAsserter = new CorporaAsserter(getClass());

    @Test
    public void parse() throws Exception {

        ImmutableList<ISOLangCode> isoLangCodes = ISOLangCodes.parse();

        assertEquals(7850, isoLangCodes.size());

        corporaAsserter.assertEquals("parse", CollectionFormatter.table(isoLangCodes));

    }

}