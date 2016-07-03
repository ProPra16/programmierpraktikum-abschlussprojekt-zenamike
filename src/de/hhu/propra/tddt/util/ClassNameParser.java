package de.hhu.propra.tddt.util;


import java.util.Arrays;

/**
 * Created by Subject-16 on 03.07.2016.
 */
public class ClassNameParser {

    public String getClassName(String classContent) {

        String[] codeToken = classContent.split("\\s+");
        String returnString = "";

        try{
            for(int i = 0; i < codeToken.length; i++){
                if(codeToken[i].equals("public")){


                    while(codeToken[i].equals("strictfp") ||
                            codeToken[i].equals("abstract")  ||
                            codeToken[i].equals("final")) {
                        i++;
                    }
                    i++;
                    if(codeToken[i].equals("class")){
                        returnString = codeToken[i+1];
                    }
                }
            }
            } catch ( ArrayIndexOutOfBoundsException e) {
                returnString = "could not find classname";
            } finally {
                return returnString;
            }
        }
    }
