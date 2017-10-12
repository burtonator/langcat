package com.spinn3r.artemis.lang;

import com.spinn3r.artemis.lang.codepage.CodepageLangClassifier;
import com.spinn3r.artemis.lang.codepage.CodepageReferences;
import com.spinn3r.artemis.lang.ngramcat.NGramLangClassifier;
import com.spinn3r.artemis.lang.ngramcat.ProfileFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompositeLangClassifierTest {

    LangClassifier classifier;

    @Before
    public void setUp() throws Exception {

        CodepageLangClassifier codepageLangClassifier = new CodepageLangClassifier(CodepageReferences.create());
        codepageLangClassifier.init();

        NGramLangClassifier nGramLangClassifier = new NGramLangClassifier();
        nGramLangClassifier.init();

        classifier = new CompositeLangClassifier( codepageLangClassifier, nGramLangClassifier );

    }

    @Test
    public void testDetect() throws Exception {

        test( "en", NGramLangClassifier.class, "this is a test of the emergency broadcast system.  This is only a test." );
        test( "ko", CodepageLangClassifier.class, "의사 결정 기구인 IOC는 올림픽 개최 도시를 선정하며, 각 올림픽 대회마다 열리는 올림픽 종목도 IOC에서 결정한다. 올림픽은 " );
        test( "pt", NGramLangClassifier.class, "A alegria de uma cacheada: O PENTE! Melhor presente! ♥♥ #coisasdeumacacheada #cachos #meupente #cacheadas #negra #blackpower" );
    }

    private void test(String lang, Class classifierClazz, String text ) throws Exception {

        LangClassification result = classifier.detect( text );

        assertEquals( lang, result.getCode() );
        assertEquals( result.getLangClassifier().getClass(), classifierClazz );

    }

}