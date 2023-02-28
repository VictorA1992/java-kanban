package LastAndStatus;

public enum TaskStatus {
    NEW(0), IN_PROGRESS(1), DONE(2);
    private final int value;

    TaskStatus(int value) {
        this.value = value;
    }

    public static TaskStatus getType (int id) {
        for (TaskStatus type : values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
