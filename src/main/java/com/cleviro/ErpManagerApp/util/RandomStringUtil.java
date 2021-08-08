package com.cleviro.ErpManagerApp.util;

import com.cleviro.ErpManagerApp.exception.EntityType;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

/*
 * Created by Julius kimathi.
 */
public class RandomStringUtil {
    // function to generate a random string of length n
    public static String getAlphaNumericString(int n, EntityType entityType) {

        String inputString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String prefix = "";
        String inputStringUcase = inputString.trim().toUpperCase().replaceAll(" ", "").concat("123456789");

        // create StringBuffer size of inputString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to inputString variable length
            int index
                    = (int) (inputStringUcase.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(inputStringUcase
                    .charAt(index));
        }
        if (entityType.equals(EntityType.CUSTOMER))
             prefix = "CSTM-";
        else if (entityType.equals(EntityType.EMPLOYEE))
            prefix="EMPL";
        else if (entityType.equals(EntityType.PAYERACCOUNT))
            prefix="PACC";
        else if (entityType.equals(EntityType.SUPPLIER))
            prefix="SPLR";
        else if (entityType.equals(EntityType.VISIT))
            prefix="VSIT";
        else if (entityType.equals(EntityType.COMPANY))
            prefix="CMPY";
        else if (entityType.equals(EntityType.LOCATION))
            prefix="LCTN";
        else if (entityType.equals(EntityType.PAYER))
            prefix="PYER";
        else if (entityType.equals(EntityType.SCHEME))
            prefix="SCHM";
        else prefix="PLAN";

        int year = ZonedDateTime.now(  ZoneId.of( "Africa/Nairobi" )  ).getYear() ;

        String suffix = String.valueOf(year);
        String randomString = prefix.concat(sb.toString()).concat("-"+suffix);
        return randomString;
    }

    public static String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public void givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

    //In one line:
    public static void generateInOneLine(){
       String random_string = Long.toHexString(Double.doubleToLongBits(Math.random()));
        System.out.println(random_string);
      }


}