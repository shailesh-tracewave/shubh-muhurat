package com.techmeeva.chogadiyawidgets.core.localization

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object AppLocalizer {

    fun text(key: AppTextKey, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (key) {
                AppTextKey.ACTIVE_NOW -> "Active Now"
                AppTextKey.FULL_SCHEDULE -> "Full Schedule"
                AppTextKey.TODAYS_PATH -> "Today's Path"
                AppTextKey.HOME -> "Home"
                AppTextKey.CALENDAR -> "Calendar"
                AppTextKey.SETTINGS -> "Settings"
                AppTextKey.SUNRISE -> "Sunrise"
                AppTextKey.SUNSET -> "Sunset"
                AppTextKey.ENDS_AT -> "Ends at"
                AppTextKey.COULD_NOT_LOAD_TODAY_FLOW -> "Couldn’t load today’s flow"
                AppTextKey.COULD_NOT_LOAD_MONTH -> "Couldn’t load the month"
                AppTextKey.TRY_AGAIN -> "Try Again"
                AppTextKey.PREMIUM_PREVIEW_TITLE -> "Free preview includes today and the next 7 days"
                AppTextKey.PREMIUM_PREVIEW_BODY -> "Unlock the full month view and future dates with Premium."
                AppTextKey.PREMIUM_UNLOCK_BODY -> "Unlock deeper astrological insight and every premium widget style."
                AppTextKey.UPGRADE -> "Upgrade"
                AppTextKey.ACTIVE -> "Active"
                AppTextKey.AUSPICIOUS_DAYS -> "Auspicious days"
                AppTextKey.DAY_NIGHT_FLOW -> "Daytime and nighttime Choghadiya flow"
                AppTextKey.PREFERENCES -> "Preferences"
                AppTextKey.SYSTEM -> "System"
                AppTextKey.LOCATION -> "Location"
                AppTextKey.USE_CURRENT_LOCATION -> "Use Current Location"
                AppTextKey.FINDING_CURRENT_CITY -> "Finding your current city…"
                AppTextKey.SWITCH_TO_LIVE_LOCATION -> "Switch to live location"
                AppTextKey.LANGUAGE -> "Language"
                AppTextKey.APPEARANCE -> "Appearance"
                AppTextKey.CELESTIAL_LIGHT -> "Celestial Light"
                AppTextKey.RESTORE_PURCHASES -> "Restore Purchases"
                AppTextKey.SYNC_PREMIUM_ACCESS -> "Sync your premium access"
                AppTextKey.NOTIFICATIONS -> "Notifications"
                AppTextKey.COMING_SOON -> "Coming soon"
                AppTextKey.RATE_APP -> "Rate App"
                AppTextKey.SHARE_APP -> "Share App"
                AppTextKey.SHARE_APP_SUBTITLE -> "Invite friends to Shubh Muhurat"
                AppTextKey.REPLAY_ONBOARDING -> "Replay Onboarding"
                AppTextKey.SEE_INTRODUCTION_AGAIN -> "See the introduction again"
                AppTextKey.PRIVACY_POLICY -> "Privacy Policy"
                AppTextKey.TERMS_CONDITIONS -> "Terms & Conditions"
                AppTextKey.OPEN_LEGAL_PAGE -> "Open legal page"
                AppTextKey.CURRENT_LOCATION_ACTIVE -> "Active"
                AppTextKey.PREMIUM_ACTIVE_TITLE -> "Celestial Premium Active"
                AppTextKey.PREMIUM_INACTIVE_TITLE -> "Celestial Premium"
                AppTextKey.PREMIUM_ACTIVE_BODY -> "All premium widgets and future dates are unlocked."
                AppTextKey.PREMIUM_INACTIVE_BODY -> "Unlock deeper astrological insight and every premium widget style."
                AppTextKey.SELECT_LOCATION -> "Select Location"
                AppTextKey.SEARCH_CITIES -> "Search cities"
                AppTextKey.CLOSE -> "Close"
                AppTextKey.OPEN_APP_TO_SYNC -> "Open app to sync"
                AppTextKey.CURRENT_SLOT -> "Current Slot"
                AppTextKey.REMAINING -> "Remaining"
                AppTextKey.BRAND_TAGLINE -> "Your divine companion for auspicious timing, celestial rhythm, and everyday clarity."
                AppTextKey.SPLASH_TITLE -> "Shubh Muhurat"
                AppTextKey.SPLASH_SUBTITLE -> "Aligning with cosmic rhythms"
                AppTextKey.NEXT -> "Next"
                AppTextKey.CONTINUE_ACTION -> "Continue"
                AppTextKey.START_EXPLORING -> "Start Exploring"
                AppTextKey.ONBOARDING_WELCOME_TITLE -> "Know the right time"
                AppTextKey.ONBOARDING_WELCOME_BODY -> "Align your daily actions with cosmic rhythms effortlessly."
                AppTextKey.ONBOARDING_WIDGETS_TITLE -> "Lock Screen Widgets"
                AppTextKey.ONBOARDING_WIDGETS_BODY -> "Built around lock screen and home widgets, so today's Choghadiya is always visible before you even open the app."
                AppTextKey.ONBOARDING_OFFLINE_TITLE -> "Offline Access"
                AppTextKey.ONBOARDING_OFFLINE_BODY -> "Fully functional offline cache ensures you always have guidance, even when disconnected from the world."
                AppTextKey.ONBOARDING_TIMELINE_TITLE -> "Follow the real flow, not a static table"
                AppTextKey.ONBOARDING_TIMELINE_BODY -> "The app shows the current slot plus the next six chronological slots, including night Choghadiya after midnight when it matters most."
                AppTextKey.ONBOARDING_LOCATION_TITLE -> "Choose your timing zone"
                AppTextKey.ONBOARDING_USE_CURRENT_LOCATION_BODY -> "Use your current location for more accurate timings"
                AppTextKey.DAYTIME -> "Daytime"
                AppTextKey.NIGHTTIME -> "Nighttime"
                AppTextKey.UPGRADE_IN_APP -> "Upgrade in app"
            }
            AppLanguage.HINDI -> when (key) {
                AppTextKey.ACTIVE_NOW -> "अभी सक्रिय"
                AppTextKey.FULL_SCHEDULE -> "पूरा शेड्यूल"
                AppTextKey.TODAYS_PATH -> "आज का क्रम"
                AppTextKey.HOME -> "होम"
                AppTextKey.CALENDAR -> "कैलेंडर"
                AppTextKey.SETTINGS -> "सेटिंग्स"
                AppTextKey.SUNRISE -> "सूर्योदय"
                AppTextKey.SUNSET -> "सूर्यास्त"
                AppTextKey.ENDS_AT -> "समाप्ति"
                AppTextKey.COULD_NOT_LOAD_TODAY_FLOW -> "आज का क्रम लोड नहीं हो सका"
                AppTextKey.COULD_NOT_LOAD_MONTH -> "महीना लोड नहीं हो सका"
                AppTextKey.TRY_AGAIN -> "फिर प्रयास करें"
                AppTextKey.PREMIUM_PREVIEW_TITLE -> "फ्री प्रीव्यू में आज और अगले 7 दिन शामिल हैं"
                AppTextKey.PREMIUM_PREVIEW_BODY -> "प्रीमियम के साथ पूरा महीना और आगे की तिथियाँ देखें।"
                AppTextKey.PREMIUM_UNLOCK_BODY -> "गहन ज्योतिषीय जानकारी और सभी प्रीमियम विजेट स्टाइल अनलॉक करें।"
                AppTextKey.UPGRADE -> "अपग्रेड"
                AppTextKey.ACTIVE -> "सक्रिय"
                AppTextKey.AUSPICIOUS_DAYS -> "शुभ दिन"
                AppTextKey.DAY_NIGHT_FLOW -> "दिन और रात का चोघड़िया क्रम"
                AppTextKey.PREFERENCES -> "पसंद"
                AppTextKey.SYSTEM -> "सिस्टम"
                AppTextKey.LOCATION -> "स्थान"
                AppTextKey.USE_CURRENT_LOCATION -> "वर्तमान स्थान का उपयोग करें"
                AppTextKey.FINDING_CURRENT_CITY -> "आपका वर्तमान शहर खोजा जा रहा है…"
                AppTextKey.SWITCH_TO_LIVE_LOCATION -> "लाइव लोकेशन पर जाएँ"
                AppTextKey.LANGUAGE -> "भाषा"
                AppTextKey.APPEARANCE -> "रूप"
                AppTextKey.CELESTIAL_LIGHT -> "सेलेस्टियल लाइट"
                AppTextKey.RESTORE_PURCHASES -> "खरीदारी पुनर्स्थापित करें"
                AppTextKey.SYNC_PREMIUM_ACCESS -> "अपना प्रीमियम एक्सेस सिंक करें"
                AppTextKey.NOTIFICATIONS -> "सूचनाएँ"
                AppTextKey.COMING_SOON -> "जल्द आ रहा है"
                AppTextKey.RATE_APP -> "ऐप को रेट करें"
                AppTextKey.SHARE_APP -> "ऐप शेयर करें"
                AppTextKey.SHARE_APP_SUBTITLE -> "दोस्तों को दिव्य चोघड़िया भेजें"
                AppTextKey.REPLAY_ONBOARDING -> "ऑनबोर्डिंग फिर देखें"
                AppTextKey.SEE_INTRODUCTION_AGAIN -> "परिचय फिर से देखें"
                AppTextKey.PRIVACY_POLICY -> "गोपनीयता नीति"
                AppTextKey.TERMS_CONDITIONS -> "नियम और शर्तें"
                AppTextKey.OPEN_LEGAL_PAGE -> "कानूनी पेज खोलें"
                AppTextKey.CURRENT_LOCATION_ACTIVE -> "सक्रिय"
                AppTextKey.PREMIUM_ACTIVE_TITLE -> "सेलेस्टियल प्रीमियम सक्रिय"
                AppTextKey.PREMIUM_INACTIVE_TITLE -> "सेलेस्टियल प्रीमियम"
                AppTextKey.PREMIUM_ACTIVE_BODY -> "सभी प्रीमियम विजेट और भविष्य की तिथियाँ अनलॉक हैं।"
                AppTextKey.PREMIUM_INACTIVE_BODY -> "गहरी ज्योतिषीय जानकारी और सभी प्रीमियम विजेट स्टाइल अनलॉक करें।"
                AppTextKey.SELECT_LOCATION -> "स्थान चुनें"
                AppTextKey.SEARCH_CITIES -> "शहर खोजें"
                AppTextKey.CLOSE -> "बंद करें"
                AppTextKey.OPEN_APP_TO_SYNC -> "सिंक के लिए ऐप खोलें"
                AppTextKey.CURRENT_SLOT -> "वर्तमान चोघड़िया"
                AppTextKey.REMAINING -> "शेष"
                AppTextKey.BRAND_TAGLINE -> "शुभ समय, दिव्य लय और रोज़मर्रा की स्पष्टता के लिए एक मार्गदर्शक साथी।"
                AppTextKey.SPLASH_TITLE -> "दिव्य चोघड़िया"
                AppTextKey.SPLASH_SUBTITLE -> "दिव्य लय के साथ संतुलन"
                AppTextKey.NEXT -> "आगे"
                AppTextKey.CONTINUE_ACTION -> "जारी रखें"
                AppTextKey.START_EXPLORING -> "शुरू करें"
                AppTextKey.ONBOARDING_WELCOME_TITLE -> "सही समय जानें"
                AppTextKey.ONBOARDING_WELCOME_BODY -> "अपने दैनिक कार्यों को दिव्य लय के साथ सहजता से जोड़ें।"
                AppTextKey.ONBOARDING_WIDGETS_TITLE -> "लॉक स्क्रीन विजेट्स"
                AppTextKey.ONBOARDING_WIDGETS_BODY -> "लॉक स्क्रीन और होम विजेट्स के लिए बनाया गया, ताकि आज का चोघड़िया ऐप खोले बिना भी दिखे।"
                AppTextKey.ONBOARDING_OFFLINE_TITLE -> "ऑफलाइन उपयोग"
                AppTextKey.ONBOARDING_OFFLINE_BODY -> "पूरी तरह कार्यशील ऑफलाइन कैश आपको बिना इंटरनेट के भी मार्गदर्शन देता है।"
                AppTextKey.ONBOARDING_TIMELINE_TITLE -> "स्थिर तालिका नहीं, वास्तविक प्रवाह देखें"
                AppTextKey.ONBOARDING_TIMELINE_BODY -> "ऐप वर्तमान स्लॉट के साथ अगले छह क्रमिक स्लॉट दिखाता है, जिनमें ज़रूरत पड़ने पर आधी रात के बाद का चोघड़िया भी शामिल है।"
                AppTextKey.ONBOARDING_LOCATION_TITLE -> "अपना समय क्षेत्र चुनें"
                AppTextKey.ONBOARDING_USE_CURRENT_LOCATION_BODY -> "अधिक सटीक समय के लिए अपने वर्तमान स्थान का उपयोग करें"
                AppTextKey.DAYTIME -> "दिन"
                AppTextKey.NIGHTTIME -> "रात"
                AppTextKey.UPGRADE_IN_APP -> "ऐप में अपग्रेड करें"
            }
            AppLanguage.GUJARATI -> when (key) {
                AppTextKey.ACTIVE_NOW -> "હમણાં સક્રિય"
                AppTextKey.FULL_SCHEDULE -> "પૂર્ણ સમયપત્રક"
                AppTextKey.TODAYS_PATH -> "આજનો ક્રમ"
                AppTextKey.HOME -> "હોમ"
                AppTextKey.CALENDAR -> "કેલેન્ડર"
                AppTextKey.SETTINGS -> "સેટિંગ્સ"
                AppTextKey.SUNRISE -> "સૂર્યોદય"
                AppTextKey.SUNSET -> "સૂર્યાસ્ત"
                AppTextKey.ENDS_AT -> "સમાપ્ત થાય"
                AppTextKey.COULD_NOT_LOAD_TODAY_FLOW -> "આજનો ક્રમ લોડ થઈ શક્યો નથી"
                AppTextKey.COULD_NOT_LOAD_MONTH -> "મહિનો લોડ થઈ શક્યો નથી"
                AppTextKey.TRY_AGAIN -> "ફરી પ્રયાસ કરો"
                AppTextKey.PREMIUM_PREVIEW_TITLE -> "ફ્રી પ્રિવ્યુમાં આજે અને આગામી 7 દિવસ સામેલ છે"
                AppTextKey.PREMIUM_PREVIEW_BODY -> "પ્રીમિયમ સાથે આખો મહિનો અને આગલી તારીખો જુઓ."
                AppTextKey.PREMIUM_UNLOCK_BODY -> "ઊંડી જ્યોતિષ માહિતી અને બધા પ્રીમિયમ વિજેટ શૈલીઓ અનલોક કરો."
                AppTextKey.UPGRADE -> "અપગ્રેડ"
                AppTextKey.ACTIVE -> "સક્રિય"
                AppTextKey.AUSPICIOUS_DAYS -> "શુભ દિવસો"
                AppTextKey.DAY_NIGHT_FLOW -> "દિવસ અને રાત્રિનો ચોઘડિયા ક્રમ"
                AppTextKey.PREFERENCES -> "પસંદગીઓ"
                AppTextKey.SYSTEM -> "સિસ્ટમ"
                AppTextKey.LOCATION -> "સ્થાન"
                AppTextKey.USE_CURRENT_LOCATION -> "વર્તમાન સ્થાન વાપરો"
                AppTextKey.FINDING_CURRENT_CITY -> "તમારું વર્તમાન શહેર શોધાઈ રહ્યું છે…"
                AppTextKey.SWITCH_TO_LIVE_LOCATION -> "લાઇવ સ્થાન પર સ્વિચ કરો"
                AppTextKey.LANGUAGE -> "ભાષા"
                AppTextKey.APPEARANCE -> "દેખાવ"
                AppTextKey.CELESTIAL_LIGHT -> "સેલેસ્ટિયલ લાઇટ"
                AppTextKey.RESTORE_PURCHASES -> "ખરીદી પુનઃસ્થાપિત કરો"
                AppTextKey.SYNC_PREMIUM_ACCESS -> "તમારો પ્રીમિયમ ઍક્સેસ સિંક કરો"
                AppTextKey.NOTIFICATIONS -> "સૂચનાઓ"
                AppTextKey.COMING_SOON -> "જલ્દી આવી રહ્યું છે"
                AppTextKey.RATE_APP -> "એપને રેટ કરો"
                AppTextKey.SHARE_APP -> "એપ શેર કરો"
                AppTextKey.SHARE_APP_SUBTITLE -> "મિત્રોને Shubh Muhurat મોકલો"
                AppTextKey.REPLAY_ONBOARDING -> "ઓનબોર્ડિંગ ફરી જુઓ"
                AppTextKey.SEE_INTRODUCTION_AGAIN -> "પરિચય ફરી જુઓ"
                AppTextKey.PRIVACY_POLICY -> "ગોપનીયતા નીતિ"
                AppTextKey.TERMS_CONDITIONS -> "નિયમો અને શરતો"
                AppTextKey.OPEN_LEGAL_PAGE -> "કાનૂની પેજ ખોલો"
                AppTextKey.CURRENT_LOCATION_ACTIVE -> "સક્રિય"
                AppTextKey.PREMIUM_ACTIVE_TITLE -> "સેલેસ્ટિયલ પ્રીમિયમ સક્રિય"
                AppTextKey.PREMIUM_INACTIVE_TITLE -> "સેલેસ્ટિયલ પ્રીમિયમ"
                AppTextKey.PREMIUM_ACTIVE_BODY -> "બધા પ્રીમિયમ વિજેટ્સ અને ભવિષ્યની તારીખો અનલોક છે."
                AppTextKey.PREMIUM_INACTIVE_BODY -> "ઊંડી જ્યોતિષ માહિતી અને બધા પ્રીમિયમ વિજેટ શૈલીઓ અનલોક કરો."
                AppTextKey.SELECT_LOCATION -> "સ્થાન પસંદ કરો"
                AppTextKey.SEARCH_CITIES -> "શહેરો શોધો"
                AppTextKey.CLOSE -> "બંધ કરો"
                AppTextKey.OPEN_APP_TO_SYNC -> "સિંક કરવા એપ ખોલો"
                AppTextKey.CURRENT_SLOT -> "વર્તમાન ચોઘડિયા"
                AppTextKey.REMAINING -> "બાકી"
                AppTextKey.BRAND_TAGLINE -> "શુભ સમય, દૈવી લય અને રોજિંદી સ્પષ્ટતા માટેનો એક માર્ગદર્શક સાથી."
                AppTextKey.SPLASH_TITLE -> "દિવ્ય ચોઘડિયા"
                AppTextKey.SPLASH_SUBTITLE -> "દૈવી લય સાથે સંતુલન"
                AppTextKey.NEXT -> "આગળ"
                AppTextKey.CONTINUE_ACTION -> "ચાલુ રાખો"
                AppTextKey.START_EXPLORING -> "શરૂ કરો"
                AppTextKey.ONBOARDING_WELCOME_TITLE -> "योग્ય સમય જાણો"
                AppTextKey.ONBOARDING_WELCOME_BODY -> "તમારા દૈનિક કાર્યને દૈવી લય સાથે સહેલાઈથી જોડો."
                AppTextKey.ONBOARDING_WIDGETS_TITLE -> "લોક સ્ક્રીન વિજેટ્સ"
                AppTextKey.ONBOARDING_WIDGETS_BODY -> "લોક સ્ક્રીન અને હોમ વિજેટ્સ માટે બનાવેલું, જેથી આજનું ચોઘડિયા એપ ખોલ્યા વગર પણ દેખાય."
                AppTextKey.ONBOARDING_OFFLINE_TITLE -> "ઓફલાઇન ઍક્સેસ"
                AppTextKey.ONBOARDING_OFFLINE_BODY -> "પૂર્ણ કાર્યક્ષમ ઑફલાઇન કૅશ તમને ઇન્ટરનેટ વગર પણ માર્ગદર્શન આપે છે."
                AppTextKey.ONBOARDING_TIMELINE_TITLE -> "સ્થિર કોષ્ટક નહીં, સાચો પ્રવાહ અનુસરો"
                AppTextKey.ONBOARDING_TIMELINE_BODY -> "એપ હાલનો સ્લોટ અને તેના પછીના છ ક્રમબદ્ધ સ્લોટ બતાવે છે, જેમાં જરૂરી હોય ત્યારે મધરાત પછીનું ચોઘડિયા પણ સામેલ છે."
                AppTextKey.ONBOARDING_LOCATION_TITLE -> "તમારો સમય વિસ્તાર પસંદ કરો"
                AppTextKey.ONBOARDING_USE_CURRENT_LOCATION_BODY -> "વધુ સચોટ સમય માટે તમારું વર્તમાન સ્થાન વાપરો"
                AppTextKey.DAYTIME -> "દિવસ"
                AppTextKey.NIGHTTIME -> "રાત"
                AppTextKey.UPGRADE_IN_APP -> "એપમાં અપગ્રેડ કરો"
            }
        }
    }

    fun slotName(type: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> englishSlotName(type)
            AppLanguage.HINDI -> when (type.lowercase(Locale.US)) {
                "shubh" -> "शुभ"
                "labh" -> "लाभ"
                "amrit" -> "अमृत"
                "chal" -> "चर"
                "rog" -> "रोग"
                "kal" -> "काल"
                "udveg" -> "उद्वेग"
                else -> type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }
            }
            AppLanguage.GUJARATI -> when (type.lowercase(Locale.US)) {
                "shubh" -> "શુભ"
                "labh" -> "લાભ"
                "amrit" -> "અમૃત"
                "chal" -> "ચલ"
                "rog" -> "રોગ"
                "kal" -> "કાળ"
                "udveg" -> "ઉદ્વેગ"
                else -> type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }
            }
        }
    }

    fun slotMeaning(type: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (type.lowercase(Locale.US)) {
                "amrit" -> "Highly auspicious"
                "shubh" -> "Auspicious"
                "labh" -> "Gain"
                "chal" -> "Normal"
                "udveg" -> "Tense"
                "rog" -> "Difficult"
                "kal" -> "Avoid major starts"
                else -> "Timing slot"
            }
            AppLanguage.HINDI -> when (type.lowercase(Locale.US)) {
                "amrit" -> "अत्यंत शुभ"
                "shubh" -> "शुभ"
                "labh" -> "लाभ"
                "chal" -> "सामान्य"
                "udveg" -> "तनावपूर्ण"
                "rog" -> "कठिन"
                "kal" -> "बड़े आरंभ टालें"
                else -> "समय खंड"
            }
            AppLanguage.GUJARATI -> when (type.lowercase(Locale.US)) {
                "amrit" -> "અતિ શુભ"
                "shubh" -> "શુભ"
                "labh" -> "લાભ"
                "chal" -> "સામાન્ય"
                "udveg" -> "તણાવપૂર્ણ"
                "rog" -> "કઠિન"
                "kal" -> "મોટા આરંભ ટાળો"
                else -> "સમય સ્લોટ"
            }
        }
    }

    fun slotDescription(type: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (type.lowercase(Locale.US)) {
                "amrit" -> "The most auspicious time. Ideal for new beginnings, important work, and spiritual practices."
                "shubh" -> "A beautiful window for meaningful tasks, ceremonies, and peaceful progress."
                "labh" -> "Supportive for growth, business decisions, purchases, and practical wins."
                "chal" -> "A moving, adaptable period suited for routine activity and ongoing work."
                "udveg" -> "Use extra care here and avoid starting sensitive or high-stakes tasks."
                "rog" -> "A difficult phase for major starts. Keep the moment light and avoid risks."
                "kal" -> "Best handled cautiously. Delay important beginnings until a better slot arrives."
                else -> "Live timing aligned to your location and today’s celestial rhythm."
            }
            AppLanguage.HINDI -> when (type.lowercase(Locale.US)) {
                "amrit" -> "सबसे शुभ समय। नए आरंभ, महत्वपूर्ण कार्य और आध्यात्मिक अभ्यास के लिए उत्तम।"
                "shubh" -> "महत्वपूर्ण कार्यों, अनुष्ठानों और शांत प्रगति के लिए सुंदर समय।"
                "labh" -> "विकास, व्यापारिक निर्णय, खरीदारी और व्यावहारिक लाभ के लिए सहायक।"
                "chal" -> "चर चोघड़िया गतिशील और अनुकूल समय है, नियमित कार्यों और जारी कामों के लिए उपयुक्त।"
                "udveg" -> "यहाँ अतिरिक्त सावधानी रखें और संवेदनशील या उच्च-जोखिम वाले कार्य शुरू न करें।"
                "rog" -> "बड़े आरंभों के लिए कठिन समय। क्षण को हल्का रखें और जोखिम से बचें।"
                "kal" -> "सावधानी से संभालें। बेहतर समय आने तक महत्वपूर्ण शुरुआत टालें।"
                else -> "आपके स्थान और आज की दिव्य लय के अनुरूप लाइव समय।"
            }
            AppLanguage.GUJARATI -> when (type.lowercase(Locale.US)) {
                "amrit" -> "સૌથી શુભ સમય. નવી શરૂઆત, મહત્વપૂર્ણ કામ અને આધ્યાત્મિક પ્રવૃત્તિ માટે ઉત્તમ."
                "shubh" -> "અર્થપૂર્ણ કાર્ય, વિધિ અને શાંતિપૂર્ણ પ્રગતિ માટે સુંદર અવકાશ."
                "labh" -> "વિકાસ, વ્યવસાયિક નિર્ણય, ખરીદી અને પ્રાયોગિક લાભ માટે અનુકૂળ."
                "chal" -> "ચલ ચોઘડિયા ગતિશીલ અને લવચીક સમય છે, દૈનિક કામ અને ચાલુ પ્રવૃત્તિ માટે યોગ્ય."
                "udveg" -> "અહીં વધારાની કાળજી રાખો અને સંવેદનશીલ અથવા ઊંચા જોખમના કાર્ય શરૂ ન કરો."
                "rog" -> "મોટી શરૂઆત માટે કઠિન સમય. ક્ષણ હળવી રાખો અને જોખમ ટાળો."
                "kal" -> "સાવચેતીથી સંભાળો. વધુ સારો સમય આવે ત્યાં સુધી મહત્વપૂર્ણ શરૂઆત ટાળો."
                else -> "તમારા સ્થાન અને આજના દૈવી લય સાથે જોડાયેલ લાઇવ સમય."
            }
        }
    }

    fun widgetHeadline(type: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (type.lowercase(Locale.US)) {
                "amrit" -> "HIGHLY\nAUSPICIOUS"
                "shubh" -> "AUSPICIOUS\nTIMING"
                "labh" -> "GAINFUL\nWINDOW"
                "chal" -> "BALANCED\nFLOW"
                "rog" -> "USE WITH\nCAUTION"
                "kal" -> "INAUSPICIOUS\nPERIOD"
                "udveg" -> "RESTLESS\nPERIOD"
                else -> "CURRENT\nSLOT"
            }
            AppLanguage.HINDI -> when (type.lowercase(Locale.US)) {
                "amrit" -> "अत्यंत\nशुभ"
                "shubh" -> "शुभ\nसमय"
                "labh" -> "लाभकारी\nसमय"
                "chal" -> "संतुलित\nप्रवाह"
                "rog" -> "सावधानी\nरखें"
                "kal" -> "अशुभ\nअवधि"
                "udveg" -> "चिंताजनक\nअवधि"
                else -> "वर्तमान\nस्लॉट"
            }
            AppLanguage.GUJARATI -> when (type.lowercase(Locale.US)) {
                "amrit" -> "અતિ\nશુભ"
                "shubh" -> "શુભ\nસમય"
                "labh" -> "લાભકારી\nસમય"
                "chal" -> "સંતુલિત\nપ્રવાહ"
                "rog" -> "સાવચેતી\nરાખો"
                "kal" -> "અશુભ\nઅવધિ"
                "udveg" -> "ચિંતાજનક\nઅવધિ"
                else -> "વર્તમાન\nસ્લોટ"
            }
        }
    }

    fun weekdayTitles(language: AppLanguage): List<String> {
        return when (language) {
            AppLanguage.ENGLISH -> listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
            AppLanguage.HINDI -> listOf("रवि", "सोम", "मंगल", "बुध", "गुरु", "शुक्र", "शनि")
            AppLanguage.GUJARATI -> listOf("રવિ", "સોમ", "મંગળ", "બુધ", "ગુરુ", "શુક્ર", "શનિ")
        }
    }

    private fun getLocalizedMonthName(monthIndex: Int, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (monthIndex) {
                0 -> "January"
                1 -> "February"
                2 -> "March"
                3 -> "April"
                4 -> "May"
                5 -> "June"
                6 -> "July"
                7 -> "August"
                8 -> "September"
                9 -> "October"
                10 -> "November"
                11 -> "December"
                else -> ""
            }
            AppLanguage.HINDI -> when (monthIndex) {
                0 -> "जनवरी"
                1 -> "फ़रवरी"
                2 -> "मार्च"
                3 -> "अप्रैल"
                4 -> "मई"
                5 -> "जून"
                6 -> "जुलाई"
                7 -> "अगस्त"
                8 -> "सितम्बर"
                9 -> "अक्टूबर"
                10 -> "नवम्बर"
                11 -> "दिसम्बर"
                else -> ""
            }
            AppLanguage.GUJARATI -> when (monthIndex) {
                0 -> "જાન્યુઆરી"
                1 -> "ફેબ્રુઆરી"
                2 -> "માર્ચ"
                3 -> "એપ્રિલ"
                4 -> "મે"
                5 -> "જૂન"
                6 -> "જુલાઈ"
                7 -> "ઓગસ્ટ"
                8 -> "સપ્ટેમ્બર"
                9 -> "ઓક્ટોબર"
                10 -> "નવેમ્બર"
                11 -> "ડિસેમ્બર"
                else -> ""
            }
        }
    }

    private fun getLocalizedShortMonthName(monthIndex: Int, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (monthIndex) {
                0 -> "Jan"
                1 -> "Feb"
                2 -> "Mar"
                3 -> "Apr"
                4 -> "May"
                5 -> "Jun"
                6 -> "Jul"
                7 -> "Aug"
                8 -> "Sep"
                9 -> "Oct"
                10 -> "Nov"
                11 -> "Dec"
                else -> ""
            }
            AppLanguage.HINDI -> when (monthIndex) {
                0 -> "जन."
                1 -> "फ़र."
                2 -> "मार्च"
                3 -> "अप्रै."
                4 -> "मई"
                5 -> "जून"
                6 -> "जुला."
                7 -> "अग."
                8 -> "सित."
                9 -> "अक्टू."
                10 -> "नव."
                11 -> "दिस."
                else -> ""
            }
            AppLanguage.GUJARATI -> when (monthIndex) {
                0 -> "જાન્યુ"
                1 -> "ફેબ્રુ"
                2 -> "માર્ચ"
                3 -> "એપ્રિ"
                4 -> "મે"
                5 -> "જૂન"
                6 -> "જુલા"
                7 -> "ઓગ"
                8 -> "સપ્ટે"
                9 -> "ઓક્ટો"
                10 -> "નવે"
                11 -> "ડિસે"
                else -> ""
            }
        }
    }

    fun localizedMonthYear(date: Date, language: AppLanguage, timezone: String): String {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timezone), Locale.US).apply {
            time = date
        }
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        val monthName = getLocalizedMonthName(month, language)
        return "$monthName $year"
    }

    fun localizedLongDate(dateString: String, language: AppLanguage, timezone: String): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
                timeZone = TimeZone.getTimeZone(timezone)
            }
            val date = parser.parse(dateString) ?: return dateString
            val cal = Calendar.getInstance(TimeZone.getTimeZone(timezone), Locale.US).apply {
                time = date
            }
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)
            val monthName = getLocalizedMonthName(month, language)
            "$day $monthName $year"
        } catch (e: Exception) {
            dateString
        }
    }

    fun localizedShortDate(date: Date, language: AppLanguage, timezone: String): String {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timezone), Locale.US).apply {
            time = date
        }
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val monthName = getLocalizedShortMonthName(month, language)
        return "$day $monthName"
    }

    fun localizedDayNumber(date: Date, language: AppLanguage, timezone: String): String {
        val sdf = SimpleDateFormat("d", language.locale)
        sdf.timeZone = TimeZone.getTimeZone(timezone)
        return sdf.format(date)
    }

    fun localizedTime(date: Date, language: AppLanguage, timezone: String, withMeridiem: Boolean = true): String {
        val pattern = if (withMeridiem) "h:mm a" else "HH:mm"
        val sdf = SimpleDateFormat(pattern, language.locale)
        sdf.timeZone = TimeZone.getTimeZone(timezone)
        return sdf.format(date)
    }

    fun localizedTime(raw: String, language: AppLanguage, withMeridiem: Boolean = true): String {
        return try {
            val parser = SimpleDateFormat("HH:mm", Locale.US)
            val date = parser.parse(raw) ?: return raw
            val pattern = if (withMeridiem) "h:mm a" else "HH:mm"
            val sdf = SimpleDateFormat(pattern, language.locale)
            sdf.format(date)
        } catch (e: Exception) {
            raw
        }
    }

    fun localizedRange(start: Date, end: Date, language: AppLanguage, timezone: String): String {
        return "${localizedTime(start, language, timezone)} - ${localizedTime(end, language, timezone)}"
    }

    fun astronomyWindowTitle(key: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (key.lowercase(Locale.US)) {
                "abhijit_muhurat" -> "Abhijit Muhurat"
                "amrit_choghadiya" -> "Amrit Choghadiya"
                "brahma_muhurta" -> "Brahma Muhurta"
                "rahu_kaal" -> "Rahu Kaal"
                "yamaganda" -> "Yamaganda"
                "gulika_kaal" -> "Gulika Kaal"
                else -> key.replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.titlecase(Locale.US) } }
            }
            AppLanguage.HINDI -> when (key.lowercase(Locale.US)) {
                "abhijit_muhurat" -> "अभिजीत मुहूर्त"
                "amrit_choghadiya" -> "अमृत चौघड़िया"
                "brahma_muhurta" -> "ब्रह्म मुहूर्त"
                "rahu_kaal" -> "राहु काल"
                "yamaganda" -> "यमगंड"
                "gulika_kaal" -> "गुलिक काल"
                else -> key.replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.titlecase(Locale.US) } }
            }
            AppLanguage.GUJARATI -> when (key.lowercase(Locale.US)) {
                "abhijit_muhurat" -> "અભિજિત મુહૂર્ત"
                "amrit_choghadiya" -> "અમૃત ચોઘડિયા"
                "brahma_muhurta" -> "બ્રહ્મ મુહૂર્ત"
                "rahu_kaal" -> "રાહુ કાળ"
                "yamaganda" -> "યમગંડ"
                "gulika_kaal" -> "ગુલિકા કાળ"
                else -> key.replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.titlecase(Locale.US) } }
            }
        }
    }

    fun moonPhaseName(key: String, language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (key.lowercase(Locale.US)) {
                "new_moon" -> "New Moon"
                "waxing_crescent" -> "Waxing Crescent"
                "first_quarter" -> "First Quarter"
                "waxing_gibbous" -> "Waxing Gibbous"
                "full_moon" -> "Full Moon"
                "waning_gibbous" -> "Waning Gibbous"
                "last_quarter" -> "Last Quarter"
                "waning_crescent" -> "Waning Crescent"
                else -> key.replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.titlecase(Locale.US) } }
            }
            AppLanguage.HINDI -> when (key.lowercase(Locale.US)) {
                "new_moon" -> "अमावस्या"
                "waxing_crescent" -> "शुक्ल चंद्र"
                "first_quarter" -> "प्रथम चरण"
                "waxing_gibbous" -> "शुक्ल गिबस"
                "full_moon" -> "पूर्णिमा"
                "waning_gibbous" -> "कृष्ण गिबस"
                "last_quarter" -> "अंतिम चरण"
                "waning_crescent" -> "कृष्ण चंद्र"
                else -> key.replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.titlecase(Locale.US) } }
            }
            AppLanguage.GUJARATI -> when (key.lowercase(Locale.US)) {
                "new_moon" -> "અમાસ"
                "waxing_crescent" -> "વધતો ચંદ્ર"
                "first_quarter" -> "પ્રથમ ચરણ"
                "waxing_gibbous" -> "વધતો પૂર્ણાકાર"
                "full_moon" -> "પૂર્ણિમા"
                "waning_gibbous" -> "ઘટતો પૂર્ણાકાર"
                "last_quarter" -> "અંતિમ ચરણ"
                "waning_crescent" -> "ઘટતો ચંદ્ર"
                else -> key.replace("_", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.titlecase(Locale.US) } }
            }
        }
    }

    fun todayDateKey(timezone: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(timezone)
        }
        return sdf.format(Date())
    }

    private fun englishSlotName(type: String): String {
        return when (type.lowercase(Locale.US)) {
            "shubh" -> "Shubh"
            "labh" -> "Labh"
            "amrit" -> "Amrit"
            "chal" -> "Char"
            "rog" -> "Rog"
            "kal" -> "Kaal"
            "udveg" -> "Udveg"
            else -> type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }
        }
    }
}
