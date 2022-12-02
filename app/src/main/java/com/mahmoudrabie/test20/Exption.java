package com.mahmoudrabie.test20;

public class Exption {

    private static void name(String n) throws Exception {
        if(n==null||n.isEmpty()){
            throw new Exception();
        }
    }
    private static void password(String a,String b) throws Exception {
        if(a.compareTo(b)!=0){
            throw new Exception();
        }

    }


}
