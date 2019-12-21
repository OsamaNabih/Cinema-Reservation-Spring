package application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Response {
	String msg;
	
	Response(String msg) {
		this.msg = msg;
	}
	
	Response(){}
}
