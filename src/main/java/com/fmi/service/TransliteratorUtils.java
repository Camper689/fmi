package com.fmi.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TransliteratorUtils {

    private static Map<String, String> transliterations = new HashMap<>() {{
        put(" ", "_"); put("а", "a"); put("б", "b"); put("в", "v"); put("г", "g"); put("ґ", "g"); put("д", "d"); put("е", "e"); 
        put("є", "e"); put("ж", "zh"); put("з", "z"); put("и", "y"); put("і", "i"); put("ї", "yi"); put("й", "y"); put("к", "k"); 
        put("л", "l"); put("м", "m"); put("н", "n"); put("о", "o"); put("п", "p"); put("р", "r"); put("с", "s"); put("т", "t"); 
        put("у", "u"); put("ф", "f"); put("х", "h"); put("ц", "ts"); put("ч", "ch"); put("ш", "sh"); put("щ", "shch"); put("ь", ""); put("ю", "u");
        put("я", "ya"); put("э", "e"); put("ы", "u"); put("ъ", ""); put("ё", "yo");
    }};

    public static String transliterate(String data) {
        data = data.trim().toLowerCase();

        for (Map.Entry<String, String> entry : transliterations.entrySet()) {
            String key = entry.getKey();
            String replace = entry.getValue();
            data = data.replace(key, replace);
        }

        return URLEncoder.encode(data, StandardCharsets.UTF_8);
    }
}
