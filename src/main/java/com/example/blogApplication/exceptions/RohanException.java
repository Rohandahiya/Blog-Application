package com.example.blogApplication.exceptions;

public class RohanException extends Exception{

        public RohanException(String resourceName, String fieldName, Integer fieldValue){

            super("Cannot find " + fieldName + " : " + fieldValue + " in " + resourceName);

        }

}
