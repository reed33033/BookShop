package com.mypack.Converter;

import com.mypack.domain.Books;
import com.mypack.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;

public class BooksConverter implements Converter<String, Books> {
    public Books convert(String s) {
        //以,拆分
        String[] arrs = s.split(",");
        if(arrs!=null&&arrs.length==14){
            Books books= null;
            try {
                books = new Books(Integer.parseInt(arrs[0]),Integer.parseInt(arrs[1]),arrs[2],arrs[3],arrs[4],arrs[5],Integer.parseInt(arrs[6]),Double.parseDouble(arrs[7]),arrs[8],arrs[9],arrs[10],Integer.parseInt(arrs[11]),arrs[12], DateUtils.string2Date(arrs[13],"yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return books;
        }
        return null;
    }
}
