package com.rotek.controller.front;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cta.utils.RandomUtil;
import com.cta.utils.Utils;
import com.rotek.constant.SessionParams;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 验证码
 */
@Controller
@RequestMapping("/certcode")
public class RCertCodeController {
    
	static final private int CERT_CODE_LENGTH = 4;
	static final private int IMAGE_WITH = 55;
	static final private int IMAGE_HEIGHT = 20;
	static final private int IMAGE_START_X = 5;
	static final private int IMAGE_START_Y = 17;
	static final private String FONT_NAME = "TimesRoman";
	static final private int FONT_STYLE = Font.PLAIN;
	static final private int FONT_SIZE = 18;
	
    /**
     * 得到验证码，并保存到session中
     * @return 验证码图片
     */
    @RequestMapping("")
    public void getCertCode(final HttpServletRequest request,
	        final HttpServletResponse response) throws ServletException,
	        IOException {
		//设置response不缓存
    	response.setContentType("image/jpeg; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("P3P", "CP=CAO PSA OUR");
		//得到随机数字
		String certCodeString = RandomUtil.randomNum(CERT_CODE_LENGTH);
		//绘制图片
		final BufferedImage image = new BufferedImage(IMAGE_WITH,IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		final Graphics g = image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, IMAGE_WITH,IMAGE_HEIGHT);
		g.setColor(Color.black);
		g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
		g.drawString(certCodeString, IMAGE_START_X,IMAGE_START_Y);
		final ServletOutputStream out = response.getOutputStream();
		final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		//保存session
		Utils.setSession(request, SessionParams.SESSION_CERT_CODE, certCodeString);
		
	}
    
    /**
     * 校验验证码
     * @return 验证结果 expired:过期，true:一致，false:不一致
     */
    @RequestMapping("/check")
    public @ResponseBody JSONObject chkCertCode(
    		@RequestParam(value = "certCode", defaultValue = "") String certCode,
    		final HttpServletRequest request,
	        final HttpServletResponse response) throws ServletException,
	        IOException {
    	
    	JSONObject json = new JSONObject();
    	String sessionCertCode = Utils.getSession(request, SessionParams.SESSION_CERT_CODE);
    	if (StringUtils.isEmpty(sessionCertCode)) {
    		json.put("result", "expired");
    	}else if (certCode.equals(sessionCertCode)){
    		json.put("result", true);
    	}else{
    		json.put("result", false);
    	}
    	return json;
    	
    }
    
}
