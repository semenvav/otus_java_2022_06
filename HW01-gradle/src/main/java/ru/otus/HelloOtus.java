package ru.otus;

import com.google.common.base.Splitter;

public class HelloOtus {
    public static void main(String... args) {
        Iterable<String> stringIterator = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("abc, bcd,, cde   ,zsa");
        for (String string : stringIterator){
            System.out.println(string);
        }
    }
}
