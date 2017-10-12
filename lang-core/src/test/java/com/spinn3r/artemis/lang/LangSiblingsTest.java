package com.spinn3r.artemis.lang;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class LangSiblingsTest {

    @Test
    public void createMap() throws Exception {

        ImmutableMap<Lang, ImmutableSet<Lang>> langSiblingsMap = LangSiblings.createMap();

        assertEquals("[en]", langSiblingsMap.get(Lang.EN).toString());

        assertEquals("[bh, hi]", langSiblingsMap.get(Lang.HI).toString());

    }

}