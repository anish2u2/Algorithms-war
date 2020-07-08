package org.workholic.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class DigitalRootIterations {

    public static void main(String args[]) throws Exception{
        List<String> listOfResults=new LinkedList<>();
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        Integer testCases=Integer.parseInt(reader.readLine());
        while(testCases>0){
            String input=reader.readLine();
            char[] characters=input.toCharArray();
            listOfResults.add(findIterations(characters));
            testCases--;
        }
        listOfResults.forEach((str)->System.out.println(str));
    }

    public static String findIterations(char[] input){
        long value=0;
        StringBuilder builder=new StringBuilder();
        int counter=0;
        int iterations=1;
        if(input.length==1){
           return  builder.append(input[0]).append(" ").append(0).toString();
        }
        while(true) {
            value = value + Long.valueOf(Character.valueOf(input[counter]).toString());
            counter++;
            if (counter>=input.length ) {
                if(value >= 10l){
                    counter=0;
                    input=String.valueOf(value).toCharArray();
                    value=0;
                    iterations++;
                }else{
                    builder.append(value).append(" ").append(iterations);
                    break;
                }
            }
        }
        return builder.toString();
    }
}
