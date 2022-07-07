/// HTTPS版加密短信发送DEMO
/// 开发环境 ：JSE1.8,httpclient4.5.2,windows 10 专业版
/// 联系方式 ：346910917@qq.com,18611729367
/// 版本：1.0
/// 最近修订：2017-01-19

package com.cui.user.comm;

import com.alibaba.fastjson.JSON;
import com.cui.common.utils.BaseResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnsmsSend {
	
	private static HttpClient client;
	
	
	public static BaseResult send(String url,String userId,String userName,String password,String mobile,String content) {
		//扩展号，没有请留空
		String ext="";
		//即时短信请留空，定时短信请指定，格式为：yyyy-MM-dd HH:mm:ss
		String sendTime="";
		// new了一个当前的时间并且转化为字符串
		String stamp =new SimpleDateFormat("MMddHHmmss").format(new Date());
		String secret=MD5.GetMD5Code(password+stamp).toUpperCase();
		try {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("UserName", userName);
			jsonObj.put("Stamp", stamp);
			jsonObj.put("Secret", secret);
			jsonObj.put("Moblie", mobile);
			jsonObj.put("Text", content);
			jsonObj.put("Ext", ext);
			jsonObj.put("SendTime", sendTime);
			//Des加密，base64转码
			String text64=DesHelper.Encrypt(jsonObj.toString(), password); 
			client = new SSLClient();
//			httpPost其实在服务端模拟浏览器向其它接口发送服务的，一般情况下和httpclient，或者jsonp联合使用，可以把它理解为浏览器就行了
			HttpPost post=new HttpPost(url);
			post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("UserId",userId));
			nvps.add(new BasicNameValuePair("Text64",text64));
			post.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = client.execute(post);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String returnString=EntityUtils.toString(entity, "UTF-8");
//			{"StatusCode":1010,"Description":"StampError","MsgId":"","Amount":0,"SuccessCounts":0,"Errors":null
			com.alibaba.fastjson.JSONObject result = JSON.parseObject(returnString);
			Integer statusCode = result.getInteger("StatusCode");
			if(statusCode==1){
				return BaseResult.success();
			}
//			System.out.println(returnString);
//			EntityUtils.consume(entity);
			return BaseResult.fail(statusCode+"", returnString);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return BaseResult.fail("500", "系统异常");
	}
}
