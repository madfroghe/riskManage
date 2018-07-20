package duomi.com.http.converter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

public class MyStringHttpMessageConverter extends StringHttpMessageConverter {

	public MyStringHttpMessageConverter(Charset defaultCharset) {
		List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		mediaTypeList.add(new MediaType("text", "plain", defaultCharset));
		mediaTypeList.add(MediaType.ALL);
		super.setSupportedMediaTypes(mediaTypeList);
	}

}