/*
 * Copyright 2010 "Tailrank, Inc (Spinn3r)"
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.spinn3r.artemis.lang.corpora;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.ngramcat.NGramLangClassifier;
import com.spinn3r.artemis.util.misc.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import static com.spinn3r.artemis.lang.corpora.CorporaType.*;
import static com.spinn3r.artemis.lang.ngramcat.ProfileFactory.*;

public class CorporaUtils {

    private static InputStream toInputStream(String path) throws IOException {

        InputStream inputStream = NGramLangClassifier.class.getResourceAsStream(path);

        if (inputStream == null) {
            throw new IOException("Could not find resource at path: " + path);
        }

        if(path.endsWith(".gz")) {
            inputStream = new GZIPInputStream(inputStream);
        }

        return inputStream;

    }
    
    protected static String toText(InputStream inputStream, String encoding) throws IOException {

        byte[] bytes = ByteStreams.toByteArray(inputStream);

        String content;
        if (encoding == null) {
            content = new String(bytes);
        } else {
            content = new String(bytes, encoding);
        }

        // remove URL separators from the input text
        content = content.replaceAll("(?m)^https?://.*$", "");

        return content;

    }

    public static String toText(String path, String encoding, CorporaType corporaType) throws IOException {

        try (InputStream inputStream = CorporaUtils.toInputStream(path)) {
            return fitToType(toText(inputStream, encoding), corporaType);
        }

    }

    /**
     * Get text for a given lang based on the corpora type.
     */
    public static String toText(Lang lang, CorporaType corporaType) throws IOException {

        String path = String.format("/corpora/%s.dat.gz", lang.getCode().toLowerCase());

        return toText(path, Charsets.UTF_8.name(), corporaType);

    }

    public static String fitToType(String text, CorporaType corporaType) {

        // there may be an issue here as we're using the TEXT length not the
        // raw byte length..

        if(TRAIN.equals(corporaType)) {
            return Text.head(text, 0, TRAIN_PROFILE_LENGTH);
        } else if(TEST.equals(corporaType)) {
            return Text.head(text, TRAIN_PROFILE_LENGTH, TRAIN_PROFILE_LENGTH);
        } else {
            throw new RuntimeException("Wrong corpora type: " + corporaType);
        }

    }

}
