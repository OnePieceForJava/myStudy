package com.wusp.lambda;

import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        Stream.of("h", "a", "Hello").map(str -> {return str.toUpperCase();});



    }
}
