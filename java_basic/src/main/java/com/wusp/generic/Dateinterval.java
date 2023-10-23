package com.wusp.generic;

import java.time.LocalDate;

public class Dateinterval extends Pair<LocalDate>{
    @Override
    public void setSecond(LocalDate second) {
        if(second.compareTo(getFirst())>=0){
            super.setSecond(second);
        }
    }
}
