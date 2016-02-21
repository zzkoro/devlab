package devlab.spring.springboot.basic.sms;

import org.springframework.stereotype.Service;

/**
 * Created by junemp on 2016. 2. 21..
 */
@Service
public abstract class SmsService {

    public void send(String msg) {
        userAuthBaseInfoSetProc();
        publicPaymentAuthProc();

        sendAuthNo(msg);

        autoNoCreationProc();
    }

    void userAuthBaseInfoSetProc() {
        System.out.println("userAuthBaseInfoSetProc:");
    }

    void publicPaymentAuthProc() {
        System.out.println("publicPaymentAuthProc:");
    }

    void autoNoCreationProc() {
        System.out.println("autoNoCreationProc:");
    }

    abstract boolean sendAuthNo(String msg);
}
