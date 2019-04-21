package org.maxpedersen.maquiz;

public class UserValueCapture {
    public static int zIDGlobal;
    public static String nameGlobal;
    public static int quizActivityState;

    public static void setQuizActivityState(int quizActivityState) {
        UserValueCapture.quizActivityState = quizActivityState;
    }

    public static void setzIDGlobal(int zIDGlobal) {
        UserValueCapture.zIDGlobal = zIDGlobal;
    }

    public static void setNameGlobal(String nameGlobal) {
        UserValueCapture.nameGlobal = nameGlobal;
    }
}
