package com.spinn3r.artemis.lang.iso639;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.spinn3r.artemis.util.misc.Strings;
import com.spinn3r.artemis.util.misc.TextFiles;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 */
public class ISOLangCodes {

    public static ImmutableList<ISOLangCode> parse() throws IOException {

        String path = "/iso-codes-639-3.tsv";

        URL resource = ISOLangCodes.class.getResource(path);

        if (resource == null) {
            throw new IOException("Could not load resource: " + path);
        }

        List<ISOLangCode> result = Lists.newArrayList();

        try(InputStream inputStream = resource.openStream()) {

            String text = TextFiles.toUTF8(inputStream);

            List<String> lines = Strings.toLines(text);

            for (String line : lines) {

                //List<String> row = Splitter.on(Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))
                //                         .splitToList(line);

                List<String> row = Splitter.on(Pattern.compile("\t"))
                                         .splitToList(line);

                if (row.size() > 8)
                    throw new IOException("Data parsed incorrectly (Wrong number of columns): " + row.size());

                result.add(new ISOLangCode(row.get(0),
                                           row.get(1),
                                           row.get(2),
                                           row.get(3),
                                           row.get(6)));

            }
            
            return ImmutableList.copyOf(result);

        }

    }

    private static String stripQuotes(String text) {
        return text.substring(1, text.length() - 1);
    }

}
