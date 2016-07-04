package de.hhu.propra.tddt.util.classnameparser;


/**
 * Created by kevin 03.07.2016.
 */
public class ClassNameParser {

    /**
     * Method: getClassName
     * <p>
     * Task: Parsing/Filerting the name of the class ouf of the source code as a
     * String.
     *
     * @param classContent The java program, all what you would write in a .java
     *                     file.
     *
     * @return The name of the class, which stands after public [someModifiers]
     * class [theClassName] { ...
     *
     * @throws ClassNameParserException If there is no valid class name, then
     *                                  this exception will be thrown.
     */
    public static String getClassName(String classContent) throws ClassNameParserException {

        String[] codeToken = classContent.split("\\s+");
        String returnString = "";

        try {
            for (int i = 0; i < codeToken.length; i++) {
                if (codeToken[i].equals("public")) {


                    while (codeToken[i].equals("strictfp") ||
                            codeToken[i].equals("abstract") ||
                            codeToken[i].equals("final")) {
                        i++;
                    }
                    i++;
                    if (codeToken[i].equals("class")) {
                        returnString = codeToken[i + 1];
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ClassNameParserException();
        }

        return returnString;

    }
}
