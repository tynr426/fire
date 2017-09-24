package fire.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import fire.web.utils.JsonResult;

public abstract class ExceptionController {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object exp(Exception e){
		e.printStackTrace();		
		return new JsonResult(e);	
	}
}
