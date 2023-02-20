package com.magneticreason.fitnium.plugins.tokens;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import com.magneticreason.fitnium.FitniumException;

public class SpecialTokens extends FitniumToken {

    private HashMap<String, Object> tokens = new HashMap<String, Object>();
    final static String prop = "fitnium.plugins.tokens";

    public final void load(Properties properties) {

        loadInbuilt();

        boolean more = true;
        int count = 1;
        do {
            String line = prop + "." + count;

            String value = properties.getProperty(line);
            if (null != value) {
                loadToken(value);
            } else {
                more = false;
            }
            count++;
        } while (more);
    }

    protected void loadToken(String value) {
        String[] bits = value.split(":");

        try {
            Object token = Class.forName(bits[1]).newInstance();
            tokens.put(bits[0], token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FitniumException("Failed to load token ");
        }
    }

    private void loadInbuilt() {

        this.loadToken("date:com.magneticreason.fitnium.plugins.tokens.DateToken");
        this.loadToken("calendar:com.magneticreason.fitnium.plugins.tokens.CalendarToken");
        this.loadToken("rand:com.magneticreason.fitnium.plugins.tokens.RandomToken");

    }

    /**
    // date
    // date.now
    // date.before(1)
    // date.after(10)
    // rand.int(4)
    // rand.string(3)
     */
    public final String process(String var) {
        try {

            String method = null;
            String op = null;
            String value = null;

            if (var.contains(".")) {
                int d = var.indexOf(".");
                method = var.substring(0, d);
                Object token = tokens.get(method);

                if (hasParams(var)) {
                    String seg = var.substring(d + 1);
                    op = createOpName(getParamMethod(seg));
                    value = getParamValue(var);
                    Method m = token.getClass().getMethod(op, int.class);
                    String result = (String) (m.invoke(token, Integer.parseInt(value)));
                    return result;
                } else {
                    op = createOpName(var.substring(d + 1));
                    Method m = token.getClass().getMethod(op);
                    return (String) m.invoke(token);
                }
            } else {
                method = var;
                Object token = tokens.get(method);
                Method m = token.getClass().getMethod("get");
                return (String) m.invoke(token);
            }
        } catch (Exception e) {
            throw new FitniumException("Failed to call token", e);
        }

    }

    protected boolean hasParams(String data) {
        return data.contains("(");
    }

    protected String getParamMethod(String data) {
        int i = data.indexOf("(");
        return data.substring(0, i).trim();
    }

    protected String createOpName(String name) {
        String sub = name.substring(0, 1).toUpperCase();
        String rem = name.substring(1);
        return ("get" + sub + rem);
    }

    protected String getParamValue(String data) {
        int l = data.indexOf("(");
        int r = data.indexOf(")");
        return data.substring(l + 1, r).trim();
    }
}
