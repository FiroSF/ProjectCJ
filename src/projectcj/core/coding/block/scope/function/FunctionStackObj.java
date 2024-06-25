package projectcj.core.coding.block.scope.function;

import java.util.HashMap;

/**
 * Just store variables
 */
public class FunctionStackObj {
    public HashMap<String, Object> variables = new HashMap<>();

    // Function's return value;
    public Object returnValue;

    public FunctionStackObj() {
    }
}
