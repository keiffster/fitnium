package com.magneticreason.fitnium.wait;

import com.magneticreason.fitnium.FitniumFixture;
import com.magneticreason.fitnium.api.FitniumVariableAPI;

public class FitniumWaitForEval extends FitniumWait {

    protected static final String MSG = "Expression never evaluated : ";
    protected String expression;
    protected String value;

    public FitniumWaitForEval(FitniumFixture fitnium, String expression, String value) {
        super(fitnium);
        this.expression = expression;
        this.value = value;
    }

    public void waitWithMessage() {
        super.wait(MSG + expression + " = " + value);
    }

    public void waitWithMessage(long millis) {
        super.wait(MSG + expression + " = " + value, millis);
    }

    @Override
    public boolean until() {
        String result = this.fitnium.getSelenium().getEval(expression);

        if (FitniumVariableAPI.isRegularExpression(value)) {
            String regex = FitniumVariableAPI.getRegularExpression(value);
            return (test == (result.matches(regex)));
        } else {
            return (test == (result.equals(value)));
        }
    }
}
