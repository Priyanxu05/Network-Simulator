package DaaPbl;

public class login {

    public static void line() {
    logs += "--------------------------------\n";
    System.out.println("--------------------------------");
}

public static void header(String msg) {
    logs += "\n===== " + msg + " =====\n";
    System.out.println("\n===== " + msg + " =====");
}

    public static String logs = "";

    public static void info(String msg) {
        logs += "[INFO] " + msg + "\n";
        System.out.println("[INFO] " + msg);
    }

    public static void success(String msg) {
        logs += "[SUCCESS] " + msg + "\n";
        System.out.println("[SUCCESS] " + msg);
    }

    public static void error(String msg) {
        logs += "[ERROR] " + msg + "\n";
        System.out.println("[ERROR] " + msg);
    }

    public static String getLogs() {
        return logs;
    }

    public static void clear() {
        logs = "";
    }
}
