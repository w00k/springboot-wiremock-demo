package com.javaschool.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public Boolean validUrl(String url) {

        if (url == null ) {
            return false;
        }

        Pattern pattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

}
