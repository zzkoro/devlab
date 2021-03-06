package devlab.spring.springboot.basic.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author asus
 *
 */
public class DemoRequestVO {
	
	@NotEmpty
	String id;
	
	@NotEmpty
	String name;

	String commcType;
	
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

	public String getCommcType() {
		return commcType;
	}

	public void setCommcType(String commcType) {
		this.commcType = commcType;
	}
	

}

