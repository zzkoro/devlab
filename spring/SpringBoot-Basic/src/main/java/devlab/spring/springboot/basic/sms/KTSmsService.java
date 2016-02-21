package devlab.spring.springboot.basic.sms;

import org.springframework.stereotype.Service;

/**
 * Created by junemp on 2016. 2. 21..
 */
@Service("ktSmsService")
public class KTSmsService extends SmsService {

    @Override
    public boolean sendAuthNo(String msg) {
        userAuthBaseInfoSetProc();
        publicPaymentAuthProc();

        //TODO 삭제
        System.out.println("KT Send:" + msg);

        return true;
    }
}
