package com.spinn3r.artemis.lang.ngramcat;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.spinn3r.artemis.lang.Lang;
import com.spinn3r.artemis.lang.corpora.CorporaType;
import com.spinn3r.artemis.lang.corpora.CorporaUtils;
import com.spinn3r.artemis.lang.ngramcat.matchers.Match;
import com.spinn3r.artemis.lang.ngramcat.ngrams.NGram;
import com.spinn3r.artemis.util.hashcodes.Hashcodes;
import com.spinn3r.artemis.util.misc.Base64;
import com.spinn3r.artemis.util.crypto.SHA1;
import com.spinn3r.artemis.util.text.MapFormatter;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NGramLangClassifierTest {

    NGramLangClassifier NGramLangClassifier;

    @Before
    public void setUp() throws Exception {

        NGramLangClassifier = new NGramLangClassifier();
        NGramLangClassifier.init();

    }

    @Test
    public void testDanishContent() throws Exception {

        // NOTES:

        // - I suspect this is an issue with HashMap but might be wrong
        // -

        // make sure the data we're reading is correct.
        assertEquals( "-zF4hsrjGk6W9JRjUiIdrbYniZo", Hashcodes.getHashcode(CorporaUtils.toText("/train/ar-utf8.txt", "utf8", CorporaType.TRAIN ) ) );
        assertEquals( "7i9_pIVsHaAqRdTobsd_oZvb4Do", Hashcodes.getHashcode(CorporaUtils.toText("/train/da-iso-8859-1.txt", "iso-8859-1", CorporaType.TRAIN ) ) );

        // test reading the data and computing the raw ngrams from it.. .

        // look at the ngrams for a UTF8 profile.
        //assertEquals( "5eDwW4oGIvbLupCa0aqaaN8KF84", Hashcodes.getHashcode( NGramLangClassifier.profiles.get( "ar" ).nGramsAsList().toString() ) );

        // look at the ngrams for an ISO-8859-1 profile
        //assertEquals( "QCiIFpYMODqPgr29B9clMHyMsSQ", Hashcodes.getHashcode( NGramLangClassifier.profiles.get( "da" ).nGramsAsList().toString() ) );

        //String danishContent = "Skriget er et maleri af Edvard Munch. Munch udførte flere versioner af motivet, fire som malerier og et som litografi.";

        //test( "da", danishContent );

    }

    @Test
    public void testDanishContentFromDisk() throws Exception {

        String name = "da";
        String language = "danish";

        String path = "/train/da-iso-8859-1.txt";
        String encoding = "iso-8859-1";

        ProfileDataReference profileDataReference = new ProfileDataReference(Lang.DA, path, encoding, language);

        String content = CorporaUtils.toText(path, encoding, CorporaType.TRAIN);

        char[] chars = content.toCharArray();

        // READ as index seems to work.. I think it's calculate and cut.
        Map<NGram,NGram> index = ProfileFactory.readAsIndex(chars, 0, chars.length );
        assertEquals( "mhyW7g1n3FhSHyi80Gc8E1_c5JE", Hashcodes.getHashcode( MapFormatter.toString( index ) ) );

        //assertEquals( "lDRMTrKUAAgUBtv80XbypKs4ZNo", Hashcodes.getHashcode( NGrams.format( ProfileFactory.calculatePositionsAndCut( index ) ) ) );

        //assertEquals( "lDRMTrKUAAgUBtv80XbypKs4ZNo", Hashcodes.getHashcode( NGrams.format( ProfileFactory.read( chars, 0, chars.length )  ) ) );

        Profile profile =
          ProfileFactory.create(profileDataReference, true, chars );

        //assertEquals( "9dQUv8cRP567Hm6x_o8XGwkzRCM", Hashcodes.getHashcode( NGrams.format( profile.nGrams() ) ) );

    }

    @org.junit.Test
    public void testMatch() throws Exception {

        test(Lang.DA, "Skriget er et maleri af Edvard Munch. Munch udførte flere versioner af motivet, fire som malerier og et som litografi." );

        test(Lang.IT, "studio dell'uomo interiore? La scienza del cuore umano, che");
        test(Lang.EN, "this is a test of the Emergency text categorizing system.");
        test(Lang.FR, "serait d�sign� peu apr�s PDG d'Antenne 2 et de FR 3. Pas m�me lui ! Le");
        test(Lang.IT, "studio dell'uomo interiore? La scienza del cuore umano, che");

        test(Lang.RO, "taiate pe din doua, in care vezi stralucind brun  sau violet cristalele interioare");

        test(Lang.PL, "na porozumieniu, na ��czeniu si� i �rodk�w. Dlatego szukam ludzi, kt�rzy");

        test(Lang.DE, "sagt H�hsam das war bei �ber eine Annonce in einem Frankfurter der T�pfer ein. Anhand von gefundenen gut kennt, hatte ihm die wahren Tatsachen Sechzehn Adorno-Sch�ler erinnern und da� ein Weiterdenken der Theorie f�r ihre Festlegung sind drei Jahre Ersch�tterung Einblick in die Abh�ngigkeit der Bauarbeiten sei");

        test(Lang.FI, "koulun arkistoihin p�lyttym��n, vaan nuoret saavat itse vaikuttaa ajatustensa eteenp�inviemiseen esimerkiksi");

        test(Lang.HU, "es�z�seket egy kiss� t�lm�retezte, ebb�l kifoly�lag a F�ldet egy hatalmas �rv�z mosta el");

        test(Lang.FI,
              "koulun arkistoihin p�lyttym��n, vaan nuoret saavat itse vaikuttaa ajatustensa eteenp�inviemiseen esimerkiksi");

        test(Lang.NL,
              "tegen de kabinetsplannen. Een speciaal in het leven geroepen Landelijk");

        test(Lang.CS,
              "(česky Kolo do nebe) je pomník věnovaný propagátorovi městské cyklistiky Janu Bouchalovi a všem");

        test(Lang.PT, "popular. Segundo o seu bi�grafo, a Maria Adelaide auxiliava muita gente");

        testWithPath(Lang.JA, "/tests/ja-euc_jp-1.txt", "euc_jp");
        testWithPath(Lang.KO, "/tests/ko-euc_kr-1.txt", "euc_kr");
        testWithPath(Lang.PL, "/tests/pl-utf8-1.txt", "utf8");

    }

    @Test
    public void testVietnamese() throws Exception {


        test( Lang.VI, "Kaiserin di chuyển đến biển Baltic cho một lượt huấn luyện hải đội từ ngày 3 đến ngày 29 tháng 1 năm 1915. Khi quay trở lại biển Bắc, nó vào ụ tàu tại Wilhelmshaven cho một đợt đại tu định kỳ, vốn kéo dài từ ngày 31 tháng 1 đến ngày 20 tháng 2.[7] Sau khi chiếc Blücher bị mất trong Trận Dogger Bank vào tháng 1 năm 1915, Kaiser cách chức Đô đốc von Ingenohl vào ngày 2 tháng 2, được Đô đốc Hugo von Pohl thay thế trong vai trò tư lệnh hạm đội.[11] Von Pohl tiếp tục chiến lược càn quét vào biển Bắc để tiêu diệt những đơn vị Anh biệt lập. Một loạt các cuộc tiến quân hạm đội được tiến hành trong năm 1915. Kaiserin cùng phần còn lại của hạm đội đã xuất quân để hỗ trợ cho Đội Tuần tiễu 2 thực hiện một chiến dịch rải mìn vào ngày 17-18 tháng 5. Không đầy hai tuần sau, vào ngày 29-30 tháng 5, hạm đội dự định tiến hành cuộc càn quét vào biển Bắc, nhưng hoàn cảnh thời tiết khắc nghiệt đã buộc Đô đốc Pohl phải hủy bỏ chiến dịch khi chỉ còn cách 50 nmi (93 km; 58 mi) ngoài khơi Schiermonnikoog. Hạm đội đã ở lại căn cứ cho đến ngày 10 tháng 8, khi nó di chuyển đến phía Bắc Helgoland hỗ trợ cho chuyến quay về của chiếc tàu tuần dương phụ trợ Meteor. Một tháng sau, vào ngày 11-12 tháng 9, hạm đội hỗ trợ cho một hoạt động rải mìn khác ngoài khơi Swarte Bank. Hoạt động cuối cùng trong năm là vào ngày 23-24 tháng 10, khi hạm đội xuất quân về hướng Horns Reef mà không gặp sự kiện gì. Hải đội Chiến trận 3 tiến hành một lượt huấn luyện tại biển Baltic trong các ngày 5 đến 20 tháng 12.[12]\n" +
                      "\n" +
                      "Nhiệm kỳ của von Pohl dưới tư cách Tư lệnh Hạm đội khá ngắn ngủi; vào tháng 1 năm 1916, chứng bệnh ung thư gan đã khiến ông suy yếu đến mức không thể thi hành nhiệm vụ. Ông được Phó đô đốc Reinhard Scheer thay thế vào ngày 11 tháng 1.[13] Scheer đề xuất một chiến lược tích cực tấn công hơn nhằm đưa đến một cuộc đối đầu với Hạm đội Grand Anh Quốc; ông nhận được sự chuẩn thuận của Kaiser vào tháng 2.[14] Chiến dịch đầu tiên của Scheer được thực hiện trong tháng tiếp theo, vào ngày 5-7 tháng 3, với một cuộc càn quét Hoofden không mang lại kết quả.[15] Kaiserin cũng hiện diện trong một cuộc tiến quân đến Amrun Bank vào ngày 2-3 tháng 4. Một đợt tiến quân khác đến Horns Reef được tiếp nối vào ngày 21-22 tháng " );



    }

    public void testWithPath( Lang lang,
                              String path,
                              String encoding) throws Exception {

        try( InputStream is = NGramLangClassifierTest.class.getResourceAsStream( path ) ) {
            byte[] b = ByteStreams.toByteArray( is );
            String content = new String( b, encoding );

            test( lang, content );
        }

    }

    public void test( Lang lang, String content, String expectedContentSHA1 ) throws Exception {

        String actualContentSHA1 = Base64.encode( SHA1.encode( content.getBytes( Charsets.UTF_8 ) ) );

        assertEquals( expectedContentSHA1, actualContentSHA1 );

        test( lang, content );

    }

    public void test(Lang lang, String content) throws Exception {

        Match result = NGramLangClassifier.match(content);

        assertLang(result, lang);
        System.out.println("result: " + result);

    }

    public static void assertLang(Match match, Lang lang) throws Exception {

        if ( ! match.getLang().equals(lang) ) {

            throw new Exception("Returned the incorrect result. Expected " + lang +
                                  " but result " + match.getLang() + " : ");// +

        }

    }

}