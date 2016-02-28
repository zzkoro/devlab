package devlab.spring.springboot.basic.api;

import devlab.spring.springboot.basic.code.CommcType;
import devlab.spring.springboot.basic.sms.SmsService;
import devlab.spring.springboot.basic.vo.DemoRequestVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Configuration
@ComponentScan
@EnableAutoConfiguration
/**
 * 
 * @author asus
 *
 */
@RestController
public class DemoController {

	@Autowired @Qualifier("ktSmsService")
	private SmsService ktSmsService;

	@Autowired @Qualifier("sktSmsService")
	private SmsService sktSmsService;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RequestMapping("/getName")
	DemoRequestVO getName(@RequestBody @Valid DemoRequestVO req) {

		
		return req;
	}

	@RequestMapping("/sendSms")
	DemoRequestVO sendSms(@RequestBody @Valid DemoRequestVO req) {

		SmsService smsService = getSmsService(req.getCommcType());

		smsService.send("Hello");

		return req;
	}


	@RequestMapping("/sendQ")
	DemoRequestVO sendQ(@RequestBody DemoRequestVO req) {

		rabbitTemplate.convertAndSend(req);

		System.out.println("send Q");

		return req;
	}


	private SmsService getSmsService(String commcName) {
		CommcType commcType = CommcType.valueOf(commcName);
		SmsService smsService;

		if (commcType == CommcType.SKT)
			smsService = sktSmsService;
		else if (commcType == CommcType.KT)
			smsService = ktSmsService;
		else
			smsService = null;

		return smsService;


	}

}

