package bestwaiting.controller;

import bestwaiting.model.UserBean;
import bestwaiting.service.ILoginService;
import com.geetest.sdk.java.demo.demo1.GeetestConfig;
import com.geetest.sdk.java.sdk.GeetestLib;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired(required=true)
	private ILoginService loginService;

	@RequestMapping("/login1")
	public ModelAndView login(HttpServletRequest request,UserBean user) {
		logger.info(user);
		ModelAndView mv=new ModelAndView();
		System.out.println(user.toString());
		UserBean userBean=loginService.Login(user.getUsername(), user.getUserpwd());
		if(userBean!=null){
			request.getSession().setAttribute("user", userBean);
			mv.addObject("id", userBean.getId());
			mv.addObject("name", userBean.getUsername());
			mv.addObject("pwd", userBean.getUserpwd());
			mv.addObject("phone", userBean.getUser_phone());
			mv.addObject("email", userBean.getUser_email());
			mv.addObject("note", userBean.getUser_note());
			System.out.println(userBean.toString());
		}
		mv.setViewName("index");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/login")
	public Map<String ,Object> login1(HttpServletRequest request, UserBean user) {
		Map<String ,Object> map = new HashMap();
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
				GeetestConfig.isnewfailback());

		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		//从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

		//从session中获取userid
		String userid = (String)request.getSession().getAttribute("userid");

		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证

			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
			System.out.println(gtResult);
		} else {
			// gt-server非正常情况下，进行failback模式验证

			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
			System.out.println(gtResult);
		}


		if (gtResult == 1) {
			// 验证成功
			UserBean userBean=loginService.Login(user.getUsername(), user.getUserpwd());
			if(userBean!=null){
				map.put("status","success");
				map.put("user",userBean);
				map.put("version", gtSdk.getVersionInfo());
				return map;
			}else {
				map.put("status","fail");
				map.put("version", gtSdk.getVersionInfo());
				return map;
			}
		} else {
			// 验证失败
			map.put("status", "fail");
			map.put("version", gtSdk.getVersionInfo());
			return map;
		}


	}
}
