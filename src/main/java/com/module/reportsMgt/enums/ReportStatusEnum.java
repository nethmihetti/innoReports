package com.module.reportsMgt.enums;

public enum ReportStatusEnum {
    IN_PROGRESS {
        @Override
        public String toString() {
            return "In Progress";
        }
    },
    RECEIVED {
        @Override
        public String toString() {
            return "Received";
        }
    },
    SOLVED {
        @Override
        public String toString() {
            return "Solved";
        }
    },
    DECLINED {
        @Override
        public String toString() {
            return "Declined";
        }
    }
}
