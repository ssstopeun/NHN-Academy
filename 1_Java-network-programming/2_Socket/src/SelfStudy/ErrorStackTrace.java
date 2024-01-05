package SelfStudy;

public class ErrorStackTrace implements AutoCloseable{
    @Override
    public void close() throws RuntimeException{
        System.out.println("close");
    }

    public void hello() {
        System.out.println("hello");
        throw new IllegalStateException();
    }
    public void bye() {
        System.out.println("bye");
        throw new IllegalStateException();
    }


    public static void main(String[] args) {
        ErrorStackTrace errorStackTrace  = null;

        try {
            errorStackTrace = new ErrorStackTrace();
            errorStackTrace.hello();
            errorStackTrace.bye();

        } finally {
            if (errorStackTrace != null) {
                errorStackTrace.close();
            }
        }

//        try (ErrorStackTrace errorStackTrace = new ErrorStackTrace()) {
//            errorStackTrace.hello();
//            errorStackTrace.bye();
//        }
    }
}
