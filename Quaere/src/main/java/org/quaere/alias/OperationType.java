package org.quaere.alias;

public enum OperationType {
    PLUS("+") {
        public Object calculate(Object a, Object b) {
            return ((Number) a).doubleValue() + ((Number) b).doubleValue();
        }
    },
    MODULO("%") {
        public Object calculate(Object a, Object b) {
            return ((Number) a).longValue() % ((Number) b).longValue();
        }
    };    
    
    OperationType(String text) {
        this.text = text;
    }
    
    private String text;
    
    public String toString() {
        return text;
    }
    
    public abstract Object calculate(Object a, Object b);
}
