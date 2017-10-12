package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.io.ByteStreams;
import com.spinn3r.artemis.lang.ngramcat.matchers.Match;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

public class NGramLangClassifierRegressionTest {

    NGramLangClassifier nGramLangClassifier;

    @Before
    public void setUp() throws Exception {

        nGramLangClassifier = new NGramLangClassifier();
        nGramLangClassifier.init();

    }

    @Test
    public void test50() throws Exception {
        assertThat(regress(50), greaterThan(96.0));
    }

    @Test
    public void test100() throws Exception {
        assertThat(regress(100), greaterThan(96.0));
    }

    @Test
    public void test200() throws Exception {
        assertTrue( regress(200) > 98 );
    }

    private double regress(int width) throws Exception {

        long before = System.currentTimeMillis();

        RegressResult regressResult = testAllProfileCategories(width);

        long after = System.currentTimeMillis();

        System.out.println();

        System.out.println("success: " + regressResult.hits);
        System.out.println("total: " + regressResult.total);

        double rate = (regressResult.hits / regressResult.total) * 100.0D;

        System.out.println("Success rate: " + rate);

        //compute the performance

        long duration = after - before;

        double interval = duration / regressResult.total;

        System.out.println("interval: " + interval);
        System.out.println("duration: " + duration);

        System.out.println("totalMemory: " + Runtime.getRuntime().totalMemory());
        System.out.println("freeMemory: " + Runtime.getRuntime().freeMemory());

        return rate;

    }

    private RegressResult testAllProfileCategories(int width) throws Exception {

        RegressResult regressResult = new RegressResult();

        boolean useStdout = true;

        assertThat(nGramLangClassifier.getProfiles().size(), greaterThan(0));

        for (Profile profile : nGramLangClassifier.getProfiles()) {

            Match result = null;
            String test = "";

            if (useStdout) {
                System.out.println();
                System.out.println("Testing profile: " + profile.getProfileDataReference().getLang());
            }

            InputStream is = getInputStream(profile.getProfileDataReference().getPath());

            byte[] b = ByteStreams.toByteArray( is );

            String content = new String(b, profile.getProfileDataReference().getEncoding());

            int i = 0;

            while (i + width < content.length()) {

                ++regressResult.total;

                try {

                    test = content.substring(i, i + width);
                    i += width;

                    result = nGramLangClassifier.match(test);

                    NGramLangClassifierTest.assertLang( result, profile.getProfileDataReference().getLang() );

                    ++regressResult.hits;

                    if (useStdout)
                        System.out.print(".");

                    test = "";

                } catch (Throwable t) {

                    if (useStdout)
                        System.out.print("x");

                }

            }

        }

        return regressResult;

    }

    /**
     * Get then input stream for a given profile on disk.
     *
     * @author <a href="mailto:burton@peerfear.org">Kevin A. Burton</a>
     */
    public static InputStream getInputStream(String path) throws IOException {

        InputStream is = NGramLangClassifier.class.getResourceAsStream(path);

        if ( is == null ) {
            throw new IOException( "Could not find resource: " + path );
        }

        return is;

    }

    class RegressResult {

        protected double total;
        protected double hits;

    }

}