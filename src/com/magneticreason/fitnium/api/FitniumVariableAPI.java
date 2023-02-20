package com.magneticreason.fitnium.api;

import java.util.ArrayList;
import java.util.HashMap;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.plugins.tokens.SpecialTokens;
import com.magneticreason.fitnium.utils.FitniumUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FitniumVariableAPI {

    public final static SpecialTokens tokens = new SpecialTokens();
    /****************************************************************************
     * Variables
     ****************************************************************************/
    private static HashMap<String, String> fitniumVars = new HashMap<String, String>();

    /**
     * Set a named BaseFitniumFixture variable with the value passed
     * @param name Name of variable to set
     * @param value Value to set variable with
     * 
     * <br/><br/>
     * | set selenium variable called | name | with value | value | 
     */
    public final static void setSeleniumVariableCalledWithValue(FitniumFixture fitnium, String name, String value) {
        String actual = "";
        if (value != null) {
            if (value.startsWith("${")) { // Its a Selenium variable
                String var = FitniumVariableAPI.extractSeleniumVarName(value);
                actual = FitniumVariableAPI.getSeleniumVariableCalled(fitnium, var);
            } else {                       // Its either a fitnium variable or a text constant
                actual = FitniumVariableAPI.replaceAnyVars(value);
            }
        }
        fitnium.getSelenium().getEval("storedVars['" + name + "']='" + actual + "'");
    }

    protected final static String extractSeleniumVarName ( String name ) {
        int end = name.indexOf("}");
        return name.substring(2, end );
    }

    /**
     * Get the value of a BaseFitniumFixture variable
     * @param name Name of the variable to fetch
     * @return Value of the variable 
     * 
     * <br/><br/>
     * | get selenium variable called | name | 
     */
    public final static String getSeleniumVariableCalled(FitniumFixture fitnium, String name) {
        return fitnium.getSelenium().getEval("storedVars['" + name + "']");
    }

    /**
     * Check if to BaseFitniumFixture variables are the same
     * @param var1 First variable to compare
     * @param var2 Second variable to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | is Selenium variable | var1 | equal | var2 | true |
     */
    public final static boolean doesSeleniumVariableEqual(FitniumFixture fitnium, String var1, String var2) {
        String val1 = fitnium.getSelenium().getEval("storedVars['" + var1 + "']");
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        if (FitniumVariableAPI.isRegularExpression(val2)) {
            String regex = FitniumVariableAPI.getRegularExpression(val2);
            return val1.matches(FitniumVariableAPI.replaceAnyVars(regex));
        } else {
            return val1.equals(FitniumVariableAPI.replaceAnyVars(val2));
        }
    }

    /**
     * Set the value of a fitnium variable 
     * @param name Name of the variable to set
     * @param value Value of the variable to set
     * 
     * <br/><br/>
     * | set fitnium variable called | name | with value | value | 
     */
    public final static void setFitniumVariableCalledWithValue(FitniumFixture fitnium, String name, String value) {
        String actual = "";
        if ( value != null ) {
            if (value.startsWith("${")) { // Its a Selenium variable
                String var = FitniumVariableAPI.extractSeleniumVarName(value);
                actual = FitniumVariableAPI.getSeleniumVariableCalled(fitnium, var);
            } else {                       // Its either a fitnium variable or a text constant
                actual = FitniumVariableAPI.replaceAnyVars(value);
            }
        }
        fitniumVars.put(name, actual );
    }

    /**
     * Get the value of a fitnium variable
     * @param name Name of the variable to fetch
     * @return Value of fetched variable
     * 
     * <br/><br/>
     * | get firnium variable called | name | 
     */
    public final static String getFitniumVariableCalled(String name) {
        String val = fitniumVars.get(name);
        if (null == val) {
            return "";
        }
        return val;
    }

    /**
     * Check if to fitnium variables are the same
     * @param var1 First variable to compare
     * @param var2 Second variable to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | does fitnium variable | var1 | equal variable | var2 | true |
     */
    public final static boolean doesFitniumVariableEqualVariable(String var1, String var2) {
        String val1 = fitniumVars.get(var1);
        String val2 = fitniumVars.get(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        if (FitniumVariableAPI.isRegularExpression(val2)) {
            String regex = FitniumVariableAPI.getRegularExpression(val2);
            return val1.matches(FitniumVariableAPI.replaceAnyVars(regex));
        } else {
            return val1.equals(FitniumVariableAPI.replaceAnyVars(val2));
        }
    }

    /**
     * Check if to fitnium variables has a value
     * @param var1 Variable to compare
     * @param var2 Value to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | does fitnium variable | var1 | equal value | var2 | true |
     */
    public final static boolean doesFitniumVariableEqualValue(String var1, String var2) {
        String val1 = fitniumVars.get(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        if (FitniumVariableAPI.isRegularExpression(val2)) {
            String regex = FitniumVariableAPI.getRegularExpression(val2);
            return val1.matches(FitniumVariableAPI.replaceAnyVars(regex));
        } else {
            return val1.equals(FitniumVariableAPI.replaceAnyVars(val2));
        }
    }

    /**
     * Check if to values are the same ( useful for substituted parameters )
     * @param var1 First value to compare
     * @param var2 Second value to compare
     * @return true if variables are equal
     * 
     * <br/><br/>
     * | check | does | var1 | equal | var2 | true |
     * @REGEX
     */
    public final static boolean doesEqual(String var1, String var2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        if (FitniumVariableAPI.isRegularExpression(val2)) {
            String regex = FitniumVariableAPI.getRegularExpression(val2);
            return val1.matches(FitniumVariableAPI.replaceAnyVars(regex));
        } else {
            return val1.equals(FitniumVariableAPI.replaceAnyVars(val2));
        }
    }

    /**
     * Return the difference between two values by converting them to 
     * integers and subtracting one from the other
     * @param var1 First value
     * @param var2 Second value to subtract for var1
     * @return
     */
    public final static String differenceBetweenAnd(String var1, String var2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        int int1 = Integer.parseInt(val1);
        int int2 = Integer.parseInt(val2);

        return Integer.toString(int1 - int2);
    }

    /**
     * Check if first value is greater than second value
     * @param var1 First value to compare
     * @param var2 Second value to compare
     * @return true if first is greater
     * 
     * <br/><br/>
     * | check | is | var1 | greater than | var2 | true |
     */
    public final static boolean isGreaterThan(String var1, String var2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        return (Long.parseLong(val1) > Long.parseLong(val2));
    }

    /**
     * Check if first value is less than second value
     * @param var1 First value to compare
     * @param var2 Second value to compare
     * @return true if first is less
     * 
     * <br/><br/>
     * | check | is | var1 | less than | var2 | true |
     */
    public final static boolean isLessThan(String var1, String var2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        return (Long.parseLong(val1) < Long.parseLong(val2));
    }

    /**
     * Check if first value is greater than second value
     * @param var1 First value to compare
     * @param var2 Second value to compare
     * @return true if first is greater
     * 
     * <br/><br/>
     * | check | is | var1 | greater than or equal to | var2 | true |
     */
    public final static boolean isGreaterTheanOrEqualTo(String var1, String var2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        return (Long.parseLong(val1) >= Long.parseLong(val2));
    }

    /**
     * Check if first value is less than second value
     * @param var1 First value to compare
     * @param var2 Second value to compare
     * @return true if first is less
     * 
     * <br/><br/>
     * | check | is | var1 | less than or equal to | var2 | true |
     */
    public final static boolean isLessThanOrEqualTo(String var1, String var2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(var1);
        String val2 = FitniumVariableAPI.replaceAnyVars(var2);

        if (null == val1 || null == val2) {
            return false;
        }

        return (Long.parseLong(val1) <= Long.parseLong(val2));
    }

    /**
     * Does a comma seperated list equal another csl. Order is unimportant
     * @param list1 First list to compare
     * @param list2 Second list to compare
     * @return true if all elements in lists are equal
     * 
     * <br/><br/>
     * | does list | list1 | equal list | list2 |
     */
    public final static boolean doesListEqualList(String list1, String list2) {
        String val1 = FitniumVariableAPI.replaceAnyVars(list1);
        String val2 = FitniumVariableAPI.replaceAnyVars(list2);

        return FitniumUtils.compareTwoStringArrays(FitniumUtils.stringToArray(val1), FitniumUtils.stringToArray(val2));
    }

    /**
     * Checks to see if a list contains a specific value
     * @param list List to search
     * @param val Value to search for
     * @return true if value exists in list
     * 
     * <br/><br/>
     * | does list | list1 | contain value | value |
     */
    public final static boolean doesListContainValue(String list, String val) {
        String name1 = FitniumVariableAPI.replaceAnyVars(list);
        String name2 = FitniumVariableAPI.replaceAnyVars(val);

        return FitniumUtils.arrayContainsString(FitniumUtils.stringToArray(name1), name2);
    }

    /**
     * Checks if a list contains any of the values from second list
     * @param srcList List to search
     * @param list List of values to search for
     * @return true if list contains any of the values
     * 
     * <br/><br/>
     * | does list | list1 | contain any values | list2 |
     * @REGEX
     */
    public final static boolean doesListContainAnyValues(String srcList, String list) {
        String name1 = FitniumVariableAPI.replaceAnyVars(srcList);
        String name2 = FitniumVariableAPI.replaceAnyVars(list);

        return FitniumUtils.arrayContainsArray(FitniumUtils.stringToArray(name1), FitniumUtils.stringToArray(name2));
    }

    /**
     * Looks for a variable of the format !{name} or @{name} and replaces it with the text
     */
    public final static String replaceAnyVars(String str) {

        ArrayList<String> dataVars = FitniumVariableAPI.extractVars("@", str);
        if (dataVars.size() > 0) {
            str = replaceDataVars(str, dataVars);
        }

        ArrayList<String> vars = FitniumVariableAPI.extractVars("!", str);
        if (vars.size() == 0) {
            return str;
        }

        return replaceVars(str, vars, fitniumVars);
    }

    /**
     * Extracts for the string passed in the list of all vars to be substituted
     * @param str String to parse
     * @return Array of variables to replace
     */
    public final static ArrayList<String> extractVars(String ch, String str) {
        String curr = str;
        ArrayList<String> vars = new ArrayList<String>();
        boolean more = true;

        if (null == curr) {
            return vars;
        }

        while (more) {
            int start = curr.indexOf(ch + "{");
            if (-1 != start) {
                int end = curr.indexOf("}");
                String var = curr.substring(start + 2, end);
                vars.add(var);
                curr = curr.substring(end + 1);
            } else {
                more = false;
            }
        }

        return vars;
    }

    /**
     * Replace all the !{name} vars in a string with their actual values
     * @param str To replace vars in
     * @param vars Varaibles to replace
     * @param vals Values of variables to replace
     * @return String with values replaced
     */
    public final static String replaceVars(String str, ArrayList<String> vars, HashMap<String, String> vals) {

        for (String name : vars) {
            String value = vals.get(name);
            if (null == value) {
                value = "";
            }
            str = str.replaceAll("\\!\\{" + name + "\\}", value);
        }

        return str;
    }

    /**
     * Replace all the !{name} vars in a string with their actual values
     * @param str To replace vars in
     * @param vars Varaibles to replace
     * @return String with values replaced
     * @throws Throwable
     */
    public final static String replaceDataVars(String str, ArrayList<String> vars) {

        for (String var : vars) {
            String slash = slashVar(var);
            str = str.replaceAll("\\@\\{" + slash + "\\}", tokens.process(var));
        }

        return str;
    }

    public final static String slashVar(String str) {
        str = str.replaceAll("\\(", "\\\\\\(");
        str = str.replaceAll("\\)", "\\\\\\)");
        return str;
    }

    public final static boolean isRegularExpression(String str) {
        return str.startsWith("regexp:") || str.startsWith("regexpi:");
    }

    public final static String getRegularExpression(String str) {
        if (str.startsWith("regexp:")) {
            return str.substring(7);
        } else {
            return str.substring(8);
        }
    }

    public final static void replaceWithInAndStoreInFitniumVariable ( String find, String rep, String str, String var ) {
        String findFull = FitniumVariableAPI.replaceAnyVars(find);
        String repFull = FitniumVariableAPI.replaceAnyVars(rep);
        String strFull = FitniumVariableAPI.replaceAnyVars(str);
        String varFull = FitniumVariableAPI.replaceAnyVars(var);

        String newStr = strFull.replaceAll( findFull, repFull );

        FitniumVariableAPI.fitniumVars.put( varFull, newStr );
    }

    // Added by Patrick Mead - State Farm 28/6/10
    public final static boolean extractTextInVariableMatchingPatternAndStoreIn( FitniumFixture fitnium, String variableNameForOriginalText, String regex, String variableNameForExtractedText) {
        String extractedText = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =
                pattern.matcher(getFitniumVariableCalled(variableNameForOriginalText));
        boolean textWasFound = matcher.find();
        if (textWasFound) {
            extractedText = matcher.group();
        }
        setFitniumVariableCalledWithValue( fitnium, variableNameForExtractedText,
                extractedText);
        return textWasFound;
    }

}
