package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.collect.Lists;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGram;
import com.spinn3r.artemis.util.text.CollectionFormatter;

import java.util.List;

/**
 *
 */
public class NGrams {

    public static List<NGram> toList(NGram...  nGrams ) {

        List<NGram> result = Lists.newArrayList();

        if (nGrams != null) {

            for (NGram nGram : nGrams) {
                result.add( nGram );
            }

        }

        return result;

    }

    public static String format( NGram... nGrams ) {
        return format(Lists.newArrayList(nGrams));
    }

    public static String format( List<NGram> nGrams ) {

        List<String> list = Lists.newArrayList();

        if ( nGrams == null )
            return "";

        for (NGram nGram : nGrams) {
            String formatted = String.format ( "<%s, length=%,d, position=%,d>", nGram.toString(), nGram.length(), nGram.position() );
            list.add( formatted );
        }

        return CollectionFormatter.table( list );

    }

}
