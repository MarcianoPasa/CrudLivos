package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
   
    public static Date formatarDataParaBanco(String campoData) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
            return formato.parse(campoData);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
