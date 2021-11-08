package com.carservice.carservice.oibValidator;

import org.springframework.stereotype.Service;

@Service
public class OibValidator {

    public static boolean oibValidation (String oib) {

        if (oib.length() != 11) {
            return false;
        }

        try {
            Long.parseLong(oib);
        } catch (NumberFormatException e) {
            return false;
        }

        int a = 10;
        for (int i = 0; i < 10; i++) {
            a = a + Integer.parseInt(oib.substring(i, i + 1));
            a = a % 10;
            if (a == 0) {
                a = 10;
            }
            a *= 2;
            a = a % 11;
        }
        int control = 11 - a;
        if (control == 10) {
            control = 0;
        }
        return control == Integer.parseInt(oib.substring(10));
    }

}