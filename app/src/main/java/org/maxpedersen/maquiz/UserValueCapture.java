package org.maxpedersen.maquiz;



// This class enables easier access for activities and fragments to key attributes of the active user*/
public class UserValueCapture {
    public static int zIDGlobal;
    public static String nameGlobal;
    public static int quizActivityState;
    public static String userMsg;

    public static void setUserMsg(String userMsg) {
        UserValueCapture.userMsg = userMsg;
    }

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
