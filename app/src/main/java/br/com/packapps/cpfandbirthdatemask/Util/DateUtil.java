package br.com.packapps.cpfandbirthdatemask.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by paulolinhares on 19/10/17.
 */

public class DateUtil {

    public static boolean valideBirthdate(String dateStr){
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.setLenient(false);
            date = format.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }
}
