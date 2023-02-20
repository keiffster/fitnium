package com.magneticreason.fitnium.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumUtils {

    /****************************************************************************
     * Internal methods
    
     ****************************************************************************/
    public final static void outputStringArray(String[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array has 0 elements");
        } else {
            System.out.println("Array has " + arr.length + " elements");
            for (String el : arr) {
                System.out.println("\t[" + el + "]");
            }
        }
    }

    /**
     * Creates a command seperated list as a String from an array of strongs
     * @param arr Array of string objects
     * @return Comma seperated string
     */
    public final static String createCommaSeperatedList(String[] arr) {

        if (null == arr) {
            return "";
        }

        boolean first = true;
        StringBuffer buffer = new StringBuffer();
        for (String id : arr) {
            if (!first) {
                buffer.append(",");
            }
            first = false;
            buffer.append(id);
        }
        return buffer.toString();
    }

    /**
     * Convert a command seperated string into an arraye of String objects
     * @param str Comma seperated string
     * @return Array of string objects
     */
    public final static String[] stringToArray(String str) {
        return FitniumUtils.stringToArray(",", str);
    }

    /**
     * Convert a command seperated string into an arraye of String objects
     * @param seperator Character to seperator strings
     * @param str Comma seperated string
     * @return Array of string objects
     */
    public final static String[] stringToArray(String seperator, String str) {

        if (str == null || str.length() == 0) {
            return new String[0];
        }

        String[] arr = str.split(seperator);
        for (int count = 0; count < arr.length; count++) {
            arr[count] = arr[count].trim();
        }
        return arr;
    }

    /**
     * Compares 2 arrays of String objects. Doesn't worry about order of array.
     * Searches for each element from one array in the whole of the second array
     * @param arr First array of String objects to search
     * @param arrToCheck Second array of String objects to compare
     * @return true if all the elements in first array are contained in second array
     */
    public final static boolean compareTwoStringArrays(String[] arr, String[] arrToCheck) {

        // Preflight checks to speed up the checkings
        if (arr == null || arrToCheck == null) {
            return false;
        }

        if (arr.length == 0 || arrToCheck.length == 0) {
            return false;
        }

        if (arr.length != arrToCheck.length) {
            return false;
        }

        for (int x = 0; x < arr.length; x++) {
            boolean found = false;
            for (int y = 0; y < arrToCheck.length; y++) {

                if (FitniumVariableAPI.isRegularExpression(arrToCheck[y])) {
                    String regex = FitniumVariableAPI.getRegularExpression(arrToCheck[y]);
                    if (arr[x].matches(regex)) {
                        found = true;
                        break;
                    }
                } else {
                    if (arr[x].equals(arrToCheck[y])) {
                        found = true;
                        break;
                    }
                }

            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if an array contains a specific string
     * @param arr Array to search
     * @param str Text string to find
     * @return True if array contains text string
     */
    public final static boolean arrayContainsString(String[] arr, String str) {

        if (null == arr || arr.length == 0) {
            return false;
        }
        if (null == str) {
            return false;
        }

        for (String item : arr) {
            if (FitniumVariableAPI.isRegularExpression(str)) {
                String regex = FitniumVariableAPI.getRegularExpression(str);
                if (item.matches(regex)) {
                    return true;
                }
            } else {
                if (item.equals(str)) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * Checks to see if one array contains all the elements of another array
     * The array searched could have more than the other array, but we don't care
     * @param arr1 Array to search
     * @param arr2 Set of values to search for
     * @return true as soon as one of the values is found
     */
    public final static boolean arrayContainsArray(String[] arr1, String[] arr2) {

        if (null == arr1 || arr1.length == 0) {
            return false;
        }
        if (null == arr2 || arr2.length == 0) {
            return false;
        }

        for (String val1 : arr1) {
            for (String val2 : arr2) {
                if (FitniumVariableAPI.isRegularExpression(val2)) {
                    String regex = FitniumVariableAPI.getRegularExpression(val2);
                    if (val1.matches(regex)) {
                        return true;
                    }
                } else {
                    if (val1.equals(val2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Create a Cell Locator String, specifically for BaseFitniumFixture table look up api
     * @param loc	Cell Locator
     * @param x		X Coordinate of cell
     * @param y		Y Coordinate of cell
     * @return Cell locator string of format "xxx.x.y"
     */
    public final static String createCellLocatorString(String loc, String x, String y) {
        StringBuffer query = new StringBuffer();

        query.append(loc);
        query.append(".");
        query.append(x);
        query.append(".");
        query.append(y);

        return query.toString();
    }

    /**
     * Create an "x,y" coordinate string
     * @param x X Coordinate
     * @param y Y Coordinate
     * @return "x,y" string
     */
    public final static String createCoordString(String x, String y) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(x);
        buffer.append(",");
        buffer.append(y);

        return buffer.toString();
    }

    /**
     * Create a drag and drop offset string for use by dragElementHorizontallyPixelsAndVerticallyPixels ()
     * @param x X offset
     * @param y Y Offset
     * @return X Offset and Y Offset combined with a comma
     */
    public final static String getDragAndDropString(String x, String y) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(x);
        buffer.append(",");
        buffer.append(y);
        return buffer.toString();
    }

    /**
     * 
     * @param val
     * @param pattern
     * @return
     */
    public final static boolean regexContains(String val, String pattern) {
        String regex = FitniumVariableAPI.getRegularExpression(pattern);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(val);
        return m.find();
    }

    /**
     * 
     * @param val
     * @param pattern
     * @return
     */
    public final static boolean regexEquals(String val, String pattern) {
        if (FitniumVariableAPI.isRegularExpression(pattern)) {
            String regex = FitniumVariableAPI.getRegularExpression(pattern);
            return val.matches(regex);
        } else {
            return val.matches(pattern);
        }
    }
}
