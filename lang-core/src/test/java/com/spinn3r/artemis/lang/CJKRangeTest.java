package com.spinn3r.artemis.lang;

import com.google.common.collect.Maps;
import com.spinn3r.artemis.util.misc.Files;
import com.spinn3r.artemis.util.misc.Histograph;
import com.spinn3r.artemis.util.misc.HistographFormatter;
import com.spinn3r.artemis.util.misc.Histographs;
import org.junit.Test;

import java.util.Map;

import static com.spinn3r.artemis.util.misc.Histographs.*;

/**
 *
 */
public class CJKRangeTest {

    private static final int RANGE = 50;

    @Test
    public void test1() throws Exception {

        Map<String,Histograph<Integer>> profiles = Maps.newHashMap();

        profiles.put( "ja", createProfile( Files.toUTF8( getClass().getResourceAsStream( "/wikipedia/ja.txt" ) ), RANGE ) );
        //profiles.put( "ko", createProfile( Files.toUTF8( getClass().getResourceAsStream( "/wikipedia/ko.txt" ) ), RANGE ) );
        profiles.put( "zh", createProfile( Files.toUTF8( getClass().getResourceAsStream( "/wikipedia/zh.txt" ) ), RANGE ) );

        // FIXME: compute the standard jaccard score between the two
        // histographs/sets... then we can know if we can safely use range codes
        // to identify languages.

        for (String sourceProfileName : profiles.keySet()) {
            Histograph<Integer> sourceProfile = profiles.get( sourceProfileName );
            System.out.printf( "%s\n", sourceProfileName );
            //System.out.printf( "%s\n", HistographFormatter.chart( sourceProfile ) );

            for (String targetProfileName : profiles.keySet()) {
                Histograph<Integer> targetProfile = profiles.get( targetProfileName );

                if ( targetProfileName.equals( sourceProfileName ) )
                    continue;

                Histograph<Integer> union = union( sourceProfile, targetProfile );
                Histograph<Integer> intersection = intersection( sourceProfile, targetProfile );
                System.out.printf( "intersection: \n%s\n", HistographFormatter.chart( sourceProfile ) );

                long intersectionSum = sum( intersection );
                long unionSum = sum( union );
                double score = intersectionSum / (double)unionSum;

                System.out.printf( "  %s=%.2f\n", targetProfileName, score );

            }

        }

    }

    private Histograph<Integer> createProfile( String data, int range ) {

        Histograph<Integer> result = new Histograph<>();

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt( i );

            if ( c <= 0x007f )
                continue;

            int val = c / range;
            result.incr( val );
        }

        return result;

    }

}
