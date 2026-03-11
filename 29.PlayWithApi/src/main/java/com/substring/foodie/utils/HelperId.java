package com.substring.foodie.utils;

import java.util.UUID;

public class HelperId {
    public static String generateRandomId(){
              return UUID.randomUUID().toString();
    }
}
