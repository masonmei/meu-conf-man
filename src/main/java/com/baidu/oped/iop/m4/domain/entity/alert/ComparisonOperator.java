package com.baidu.oped.iop.m4.domain.entity.alert;

/**
 * @author mason
 */
public enum ComparisonOperator {
    EQ {
        public String rendered() {
            return "=";
        }
    },
    NEQ {
        public String rendered() {
            return "<>";
        }
    },
    LT {
        public String rendered() {
            return "<";
        }
    },
    LET {
        public String rendered() {
            return "<=";
        }
    },
    GT {
        public String rendered() {
            return ">";
        }
    },
    GET {
        public String rendered() {
            return ">=";
        }
    };

    public abstract String rendered();
}
