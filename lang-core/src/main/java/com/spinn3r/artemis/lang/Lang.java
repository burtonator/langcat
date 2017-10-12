package com.spinn3r.artemis.lang;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a language code returned for classifications. These languages
 * are represented in ISO639 as alpha2 and alpha3 codes.  At all times we prefer
 * to use alpha2 codes and only fall back to alpha3 codes when no alpha2 code
 * is available for that language.  We are using the alpha3B codes (bibliographic)
 * when using alpha3.
 *
 * From: https://en.wikipedia.org/wiki/ISO_639-2
 *
 * "While most languages are given one code by the standard, twenty of the
 * languages described have two three-letter codes, a "bibliographic" code (ISO
 * 639-2/B), which is derived from the English name for the language and was a
 * necessary legacy feature, and a "terminological" code (ISO 639-2/T), which is
 * derived from the native name for the language and resembles the language's
 * two-letter code in ISO 639-1. (There were originally 22 B codes; scc and scr
 * are now deprecated.) In general the T codes are favored; ISO 639-3 uses ISO
 * 639-2/T. However, ISO 15924 derives its codes when possible from ISO 639-2/B."
 *
 */
public enum Lang {

    U("U", null, null, false),
    CJK("cjk", null, null, false),
    MAL("mal", null, null, false),

    IN("in", null, null, false), // twitter lang code (no idea what it means)
    IW("iw", null, null, false), // twitter lang code (no idea what it means)

    AB("ab", "Abkhazian", "Аҧсшәа"),
    AF("af", "Afrikaans", "Afrikaans"),
    AK("ak", "Akan", "Akan"),
    AM("am", "Amharic", "አማርኛ"),
    AN("an", "Aragonese", "aragonés"),
    AR("ar", "Arabic", "العربية"),
    AS("as", "Assamese", "অসমীয়া"),
    AV("av", "Avar", "авар"),
    AY("ay", "Aymara", "Aymar aru"),
    AZ("az", "Azerbaijani", "azərbaycanca"),
    BA("ba", "Bashkir", "башҡортса"),
    BE("be", "Belarusian", "беларуская"),
    BG("bg", "Bulgarian", "български"),
    BH("bh", "Bihari", "भोजपुरी"),
    BI("bi", "Bislama", "Bislama"),
    BM("bm", "Bambara", "bamanankan"),
    BN("bn", "Bengali", "বাংলা"),
    BO("bo", "Tibetan", "བོད་ཡིག"),
    BR("br", "Breton", "brezhoneg"),
    BS("bs", "Bosnian", "bosanski"),
    CA("ca", "Catalan", "català"),
    CE("ce", "Chechen", "нохчийн"),
    CH("ch", "Chamorro", "Chamoru"),
    CO("co", "Corsican", "corsu"),
    CR("cr", "Cree", "Nēhiyawēwin / ᓀᐦᐃᔭᐍᐏᐣ"),
    CS("cs", "Czech", "čeština"),
    CU("cu", "Old Church Slavonic", "словѣньскъ / ⰔⰎⰑⰂⰡⰐⰠⰔⰍⰟ"),
    CV("cv", "Chuvash", "Чӑвашла"),
    CY("cy", "Welsh", "Cymraeg"),
    DA("da", "Danish", "dansk"),
    DE("de", "German", "Deutsch"),
    DV("dv", "Divehi", "ދިވެހިބަސް"),
    DZ("dz", "Dzongkha", "ཇོང་ཁ"),
    EE("ee", "Ewe", "eʋegbe"),
    EL("el", "Greek", "Ελληνικά"),
    EN("en", "English", "English"),
    EO("eo", "Esperanto", "Esperanto"),
    ES("es", "Spanish", "español"),
    ET("et", "Estonian", "eesti"),
    EU("eu", "Basque", "euskara"),
    FA("fa", "Persian", "فارسی"),
    FF("ff", "Fula", "Fulfulde"),
    FI("fi", "Finnish", "suomi"),
    FJ("fj", "Fijian", "Na Vosa Vakaviti"),
    FO("fo", "Faroese", "føroyskt"),
    FR("fr", "French", "français"),
    FY("fy", "West Frisian", "Frysk"),
    GA("ga", "Irish", "Gaeilge"),
    GD("gd", "Scottish Gaelic", "Gàidhlig"),
    GL("gl", "Galician", "galego"),
    GN("gn", "Guarani", "Avañe'ẽ"),
    GU("gu", "Gujarati", "ગુજરાતી"),
    GV("gv", "Manx", "Gaelg"),
    HA("ha", "Hausa", "Hausa"),
    HE("he", "Hebrew", "עברית"),
    HI("hi", "Hindi", "हिन्दी"),
    HR("hr", "Croatian", "hrvatski"),
    HT("ht", "Haitian", "Kreyòl ayisyen"),
    HU("hu", "Hungarian", "magyar"),
    HY("hy", "Armenian", "Հայերեն"),
    IA("ia", "Interlingua", "interlingua"),
    ID("id", "Indonesian", "Bahasa Indonesia"),
    IE("ie", "Interlingue", "Interlingue"),
    IG("ig", "Igbo", "Igbo"),
    IK("ik", "Inupiak", "Iñupiak"),
    IO("io", "Ido", "Ido"),
    IS("is", "Icelandic", "íslenska"),
    IT("it", "Italian", "italiano"),
    IU("iu", "Inuktitut", "ᐃᓄᒃᑎᑐᑦ/inuktitut"),
    JA("ja", "Japanese", "日本語"),
    JV("jv", "Javanese", "Basa Jawa"),
    KA("ka", "Georgian", "ქართული"),
    KG("kg", "Kongo", "Kongo"),
    KI("ki", "Kikuyu", "Gĩkũyũ"),
    KK("kk", "Kazakh", "қазақша"),
    KL("kl", "Greenlandic", "kalaallisut"),
    KM("km", "Khmer", "ភាសាខ្មែរ"),
    KN("kn", "Kannada", "ಕನ್ನಡ"),
    KO("ko", "Korean", "한국어"),
    KS("ks", "Kashmiri", "कॉशुर / کٲشُر"),
    KU("ku", "Kurdish", "Kurdî"),
    KV("kv", "Komi", "коми"),
    KW("kw", "Cornish", "kernowek"),
    KY("ky", "Kirghiz", "Кыргызча"),
    LA("la", "Latin", "Latina"),
    LB("lb", "Luxembourgish", "Lëtzebuergesch"),
    LG("lg", "Luganda", "Luganda"),
    LI("li", "Limburgish", "Limburgs"),
    LN("ln", "Lingala", "lingála"),
    LO("lo", "Lao", "ລາວ"),
    LT("lt", "Lithuanian", "lietuvių"),
    LV("lv", "Latvian", "latviešu"),
    MG("mg", "Malagasy", "Malagasy"),
    MI("mi", "Maori", "Māori"),
    MK("mk", "Macedonian", "македонски"),
    ML("ml", "Malayalam", "മലയാളം"),
    MN("mn", "Mongolian", "монгол"),
    MR("mr", "Marathi", "मराठी"),
    MS("ms", "Malay", "Bahasa Melayu"),
    MT("mt", "Maltese", "Malti"),
    MY("my", "Burmese", "မြန်မာဘာသာ"),
    NA("na", "Nauruan", "Dorerin Naoero"),
    NE("ne", "Nepali", "नेपाली"),
    NL("nl", "Dutch", "Nederlands"),
    NN("nn", "Norwegian", "norsk nynorsk"),
    NO("no", "Norwegian", "norsk bokmål"),
    NV("nv", "Navajo", "Diné bizaad"),
    NY("ny", "Chichewa", "Chi-Chewa"),
    OC("oc", "Occitan", "occitan"),
    OM("om", "Oromo", "Oromoo"),
    OR("or", "Oriya", "ଓଡ଼ିଆ"),
    OS("os", "Ossetian", "Ирон"),
    PA("pa", "Punjabi", "ਪੰਜਾਬੀ"),
    PI("pi", "Pali", "पालि"),
    PL("pl", "Polish", "polski"),
    PS("ps", "Pashto", "پښتو"),
    PT("pt", "Portuguese", "português"),
    QU("qu", "Quechua", "Runa Simi"),
    RM("rm", "Romansh", "rumantsch"),
    RN("rn", "Kirundi", "Kirundi"),
    RO("ro", "Romanian", "română"),
    RU("ru", "Russian", "русский"),
    RW("rw", "Kinyarwanda", "Kinyarwanda"),
    SA("sa", "Sanskrit", "संस्कृतम्"),
    SC("sc", "Sardinian", "sardu"),
    SD("sd", "Sindhi", "سنڌي"),
    SE("se", "Northern Sami", "sámegiella"),
    SG("sg", "Sango", "Sängö"),
    SH("sh", "Serbo-Croatian", "srpskohrvatski / српскохрватски"),
    SI("si", "Sinhalese", "සිංහල"),
    SK("sk", "Slovak", "slovenčina"),
    SL("sl", "Slovenian", "slovenščina"),
    SM("sm", "Samoan", "Gagana Samoa"),
    SN("sn", "Shona", "chiShona"),
    SO("so", "Somali", "Soomaaliga"),
    SQ("sq", "Albanian", "shqip"),
    SR("sr", "Serbian", "српски / srpski"),
    SS("ss", "Swati", "SiSwati"),
    ST("st", "Sesotho", "Sesotho"),
    SU("su", "Sundanese", "Basa Sunda"),
    SV("sv", "Swedish", "svenska"),
    SW("sw", "Swahili", "Kiswahili"),
    TA("ta", "Tamil", "தமிழ்"),
    TE("te", "Telugu", "తెలుగు"),
    TG("tg", "Tajik", "тоҷикӣ"),
    TH("th", "Thai", "ไทย"),
    TI("ti", "Tigrinya", "ትግርኛ"),
    TK("tk", "Turkmen", "Türkmençe"),
    TL("tl", "Tagalog", "Tagalog"),
    TN("tn", "Tswana", "Setswana"),
    TO("to", "Tongan", "lea faka-Tonga"),
    TR("tr", "Turkish", "Türkçe"),
    TS("ts", "Tsonga", "Xitsonga"),
    TT("tt", "Tatar", "татарча/tatarça"),
    TW("tw", "Twi", "Twi"),
    TY("ty", "Tahitian", "reo tahiti"),
    UG("ug", "Uyghur", "ئۇيغۇرچە / Uyghurche"),
    UK("uk", "Ukrainian", "українська"),
    UR("ur", "Urdu", "اردو"),
    UZ("uz", "Uzbek", "oʻzbekcha/ўзбекча"),
    VE("ve", "Venda", "Tshivenda"),
    VI("vi", "Vietnamese", "Tiếng Việt"),
    VO("vo", "Volapük", "Volapük"),
    WA("wa", "Walloon", "walon"),
    WO("wo", "Wolof", "Wolof"),
    XH("xh", "Xhosa", "isiXhosa"),
    YI("yi", "Yiddish", "ייִדיש"),
    YO("yo", "Yoruba", "Yorùbá"),
    ZA("za", "Zhuang", "Vahcuengh"),
    ZH("zh", "Chinese", "中文"),
    ZU("zu", "Zulu", "isiZulu"),
    ACE("ace", "Acehnese", "Acèh"),
    ALS("als", "Alemannic", "Alemannisch"),
    ANG("ang", "Anglo-Saxon", "Ænglisc"),
    ARC("arc", "Aramaic", "ܐܪܡܝܐ"),
    ARZ("arz", "Egyptian Arabic", "مصرى"),
    AST("ast", "Asturian", "asturianu"),
    AZB("azb", "Southern Azerbaijani", "تۆرکجه"),
    BAR("bar", "Bavarian", "Boarisch"),
    BCL("bcl", "Central Bicolano", "Bikol Central"),
    BJN("bjn", "Banjar", "Bahasa Banjar"),
    BPY("bpy", "Bishnupriya Manipuri", "বিষ্ণুপ্রিয়া মণিপুরী"),
    BUG("bug", "Buginese", "ᨅᨔ ᨕᨘᨁᨗ"),
    BXR("bxr", "Buryat", "буряад"),
    CDO("cdo", "Min Dong", "Mìng-dĕ̤ng-ngṳ̄"),
    CEB("ceb", "Cebuano", "Cebuano"),
    CHR("chr", "Cherokee", "ᏣᎳᎩ"),
    CHY("chy", "Cheyenne", "Tsetsêhestâhese"),
    CKB("ckb", "Sorani", "کوردیی ناوەندی"),
    CRH("crh", "Crimean Tatar", "qırımtatarca"),
    CSB("csb", "Kashubian", "kaszëbsczi"),
    DIQ("diq", "Zazaki", "Zazaki"),
    DSB("dsb", "Lower Sorbian", "dolnoserbski"),
    EXT("ext", "Extremaduran", "estremeñu"),
    FRP("frp", "Franco-Provençal", "arpetan"),
    FRR("frr", "North Frisian", "Nordfriisk"),
    FUR("fur", "Friulian", "furlan"),
    GAG("gag", "Gagauz", "Gagauz"),
    GAN("gan", "Gan", "贛語"),
    GLK("glk", "Gilaki", "گیلکی"),
    GOM("gom", "Goan Konkani", "गोवा कोंकणी / Gova Konknni"),
    GOT("got", "Gothic", "𐌲𐌿𐍄𐌹𐍃𐌺"),
    HAK("hak", "Hakka", "客家語/Hak-kâ-ngî"),
    HAW("haw", "Hawaiian", "Hawai`i"),
    HIF("hif", "Fiji Hindi", "Fiji Hindi"),
    HSB("hsb", "Upper Sorbian", "hornjoserbsce"),
    ILO("ilo", "Ilokano", "Ilokano"),
    JBO("jbo", "Lojban", "Lojban"),
    KAA("kaa", "Karakalpak", "Qaraqalpaqsha"),
    KAB("kab", "Kabyle", "Taqbaylit"),
    KBD("kbd", "Kabardian", "Адыгэбзэ"),
    KOI("koi", "Komi-Permyak", "Перем Коми"),
    KRC("krc", "Karachay-Balkar", "къарачай-малкъар"),
    KSH("ksh", "Ripuarian", "Ripoarisch"),
    LAD("lad", "Ladino", "Ladino"),
    LBE("lbe", "Lak", "лакку"),
    LEZ("lez", "Lezgian", "лезги"),
    LIJ("lij", "Ligurian", "Ligure"),
    LMO("lmo", "Lombard", "lumbaart"),
    LRC("lrc", "Northern Luri", "لۊری شومالی"),
    LTG("ltg", "Latgalian", "latgaļu"),
    MAI("mai", "Maithili", "मैथिली"),
    MDF("mdf", "Moksha", "мокшень"),
    MHR("mhr", "Meadow Mari", "олык марий"),
    MIN("min", "Minangkabau", "Baso Minangkabau"),
    MRJ("mrj", "Hill Mari", "кырык мары"),
    MWL("mwl", "Mirandese", "Mirandés"),
    MYV("myv", "Erzya", "эрзянь"),
    MZN("mzn", "Mazandarani", "مازِرونی"),
    NAP("nap", "Neapolitan", "Napulitano"),
    NDS("nds", "Low Saxon", "Plattdüütsch"),
    NEW("new", "Newar", "नेपाल भाषा"),
    NOV("nov", "Novial", "Novial"),
    NRM("nrm", "Norman", "Nouormand"),
    NSO("nso", "Northern Sotho", "Sesotho sa Leboa"),
    PAG("pag", "Pangasinan", "Pangasinan"),
    PAM("pam", "Kapampangan", "Kapampangan"),
    PAP("pap", "Papiamentu", "Papiamentu"),
    PCD("pcd", "Picard", "Picard"),
    PDC("pdc", "Pennsylvania German", "Deitsch"),
    PFL("pfl", "Palatinate German", "Pälzisch"),
    PIH("pih", "Norfolk", "Norfuk / Pitkern"),
    PMS("pms", "Piedmontese", "Piemontèis"),
    PNB("pnb", "Western Punjabi", "پنجابی"),
    PNT("pnt", "Pontic", "Ποντιακά"),
    RMY("rmy", "Romani", "Romani"),
    RUE("rue", "Rusyn", "русиньскый"),
    SAH("sah", "Sakha", "саха тыла"),
    SCN("scn", "Sicilian", "sicilianu"),
    SRN("srn", "Sranan", "Sranantongo"),
    STQ("stq", "Saterland Frisian", "Seeltersk"),
    SZL("szl", "Silesian", "ślůnski"),
    TET("tet", "Tetum", "tetun"),
    TPI("tpi", "Tok Pisin", "Tok Pisin"),
    TUM("tum", "Tumbuka", "chiTumbuka"),
    TYV("tyv", "Tuvan", "тыва дыл"),
    UDM("udm", "Udmurt", "удмурт"),
    VEC("vec", "Venetian", "vèneto"),
    VEP("vep", "Vepsian", "vepsän kel’"),
    VLS("vls", "West Flemish", "West-Vlams"),
    WAR("war", "Waray-Waray", "Winaray"),
    WUU("wuu", "Wu", "吴语"),
    XAL("xal", "Kalmyk", "хальмг"),
    XMF("xmf", "Mingrelian", "მარგალური"),
    ZEA("zea", "Zeelandic", "Zeêuws")
    ;

    private final String code;

    private final String name;

    private final String nameLocalized;

    private final boolean standardized;

    Lang(String code) {
        this(code, null, null);
    }

    Lang(String code, String name, String nameLocalized) {
        this(code, name, nameLocalized, true);
    }

    Lang(String code, String name, String nameLocalized, boolean standardized) {
        this.code = code;
        this.name = name;
        this.nameLocalized = nameLocalized;
        this.standardized = standardized;
    }

    /**
     * Get the ISO code for this language.
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getNameLocalized() {
        return nameLocalized;
    }

    /**
     * Return true if this is a real ISO standardized language.
     */
    public boolean getStandardized() {
        return standardized;
    }

    @Override
    public String toString() {
        return code;
    }

    public static Lang create(@NotNull String lang) {
        Preconditions.checkNotNull(lang);
        return Lang.valueOf(lang.toUpperCase());
    }

}
