package com.spinn3r.artemis.lang.corpora;

import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.corpora.CorporaUtils;
import com.spinn3r.artemis.util.misc.Strings;
import org.junit.Test;

import java.io.InputStream;
import java.text.spi.CollatorProvider;

import static org.junit.Assert.*;

public class CorporaUtilsTest {

    @Test
    public void toText() throws Exception {

        InputStream inputStream = Strings.toInputStream("\nhello\nhttp://cnn.com\nworld");

        assertEquals("\n" +
                       "hello\n" +
                       "\n" +
                       "world",
                     CorporaUtils.toText(inputStream, "UTF-8"));

    }

    @Test
    public void testLoadProfileTrainVsTest() throws Exception {

        String train = CorporaUtils.toText(Lang.EN, CorporaType.TRAIN);
        String test = CorporaUtils.toText(Lang.EN, CorporaType.TEST);

        assertNotEquals(train, test);

        assertEquals(2000000, train.length());
        assertEquals(1002799, test.length());

    }
    
}