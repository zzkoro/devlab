package devlab.spring.springboot.basic.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author asus
 *
 */
public class DemoVO {
	
	@NotEmpty
	@Size(min = 1, max = 10)
	String id;
	
	@NotEmpty
	String name;

	String age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	

}

