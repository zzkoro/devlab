package devlab.spring.springboot.basic.api;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devlab.spring.springboot.basic.vo.DemoRequestVO;

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

	@RequestMapping("/getName")
	DemoRequestVO getName(@RequestBody @Valid DemoRequestVO req) {
		
		org.springframework.web.bind.MethodArgumentNotValidException me;
		
		
		return req;
	}
}

