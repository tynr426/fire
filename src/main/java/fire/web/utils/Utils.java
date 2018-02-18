package fire.web.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	public static String getHost(){
		try {
			ServletRequestAttributes attri=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attri.getRequest();
			return request.getScheme()+"://"+ request.getServerName();
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	  public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
		    T t = null;
		    try {
		      ObjectMapper objectMapper = new ObjectMapper();
		      t = objectMapper.readValue(jsonStr,
		          obj);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return t;
		  }
		  /**
		   * 将实体POJO转化为JSON
		   * @param obj
		   * @return
		   * @throws JSONException
		   * @throws IOException
		   */
	  public static<T> String objectToJson(T obj) {
		    ObjectMapper mapper = new ObjectMapper(); 
		    // Convert object to JSON string 
		    String jsonStr = "";
		    try {
		       jsonStr = mapper.writeValueAsString(obj);
		    } catch (IOException e) {
		     //System.out.println(e.getMessage());
		    }
		   return jsonStr;
		  }
}
