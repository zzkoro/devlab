package devlab.spring.springboot.basic.sms;

import org.springframework.stereotype.Service;

/**
 * Created by junemp on 2016. 2. 21..
 */
@Service("sktSmsService")
public class SKTSmsService extends SmsService {

    @Override
    public boolean sendAuthNo(String msg) {
        //TODO 삭제
        System.out.println("SKT Send:" + msg);

        return true;
    }
}
