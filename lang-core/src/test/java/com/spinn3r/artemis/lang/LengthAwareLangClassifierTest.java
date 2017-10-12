package com.spinn3r.artemis.lang;

import com.spinn3r.artemis.lang.codepage.CodepageLangClassifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LengthAwareLangClassifierTest {

    private static final int MIN_LENGTH = 6;

    private static final int TRUNCATE_LENGTH = 10;

    private LengthAwareLangClassifier classifier;

    @Before
    public void setUp() throws Exception {

        CodepageLangClassifier codepageLangClassifier = new CodepageLangClassifier();
        codepageLangClassifier.init();

        classifier = new LengthAwareLangClassifier( MIN_LENGTH, TRUNCATE_LENGTH, codepageLangClassifier );

    }

    @Test
    public void testTextForClassification() throws Exception {

        assertTrue( classifier.textForClassification( "xxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxxxxxxx" ).length() <= TRUNCATE_LENGTH );
        assertTrue( classifier.textForClassification( "xxxxxxxxxxxxxxxxxx" ).length() <= TRUNCATE_LENGTH );

    }

    @Test(expected = LangClassificationException.UnknownLangException.class)
    public void testRequireMinLength() throws Exception {

        classifier.requireMinLength( "hello" );

    }

    @Test
    public void testRequireMinLengthWithoutException() throws Exception {

        classifier.requireMinLength( "hello world this is enough text" );

    }


}