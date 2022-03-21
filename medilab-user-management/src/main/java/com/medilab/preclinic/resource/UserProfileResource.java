/**
 * 
 */
package com.medilab.preclinic.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilab.preclinic.bean.MedilabUserBean;
import com.medilab.preclinic.service.UserProfileService;

/**
 * @author IM-LP-1763
 *
 */
@RestController
@RequestMapping("/api/v1/")
public class UserProfileResource {

	@Autowired
	private UserProfileService userService;

	@GetMapping(value = "/users")
	public List<MedilabUserBean> findAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping(value = "/users/{userId}")
	public MedilabUserBean findByUserId(@PathVariable("userId") String userId) {
		return userService.findUserById(userId);
	}

	@PostMapping(value = "/users")
	public MedilabUserBean createUser(@RequestBody MedilabUserBean user, HttpServletRequest request) {
		String callbackUrl = request.getRequestURL().toString();
		callbackUrl = callbackUrl + "/verifyUser";
		return userService.createUser(user, callbackUrl);
	}

	@PutMapping(value = "/users")
	public MedilabUserBean updateUser(@RequestBody MedilabUserBean updateUser, HttpServletRequest request) {
		String callbackUrl = request.getRequestURL().toString();
		callbackUrl = callbackUrl + "/verifyUser";
		return userService.createUser(updateUser, callbackUrl);
	}

	@DeleteMapping(value = "/users")
	public List<MedilabUserBean> deleteUser(@RequestBody MedilabUserBean userBean) {
		return userService.deleteUser(userBean);
	}

	@DeleteMapping(value = "/users/{userId}")
	public List<MedilabUserBean> deleteUserByUserId(@PathVariable("userId") String userId) {
		return userService.deleteUser(userId);
	}

	@GetMapping(value = "/users/verifyUser/{code}")
	public ResponseEntity<String> verifyUser(@PathVariable("code") String code, HttpServletRequest request) {
		System.out.println(request.getServletPath() + "\tservlet path");
		System.out.println(request.getContextPath() + "\tcontextpath");
		System.out.println(request.getRequestURL().toString() + "\trequest url");
		System.out.println(request.getScheme() + "\tscheme is");
		System.out.println(request.getServerPort() + "\tserver port");
		System.out.println(request.getServerName() + "\tserver name");
		System.out.println("verify user code is:\t" + code);
		//
		JSONObject userVerifiedMsg = new JSONObject();
		boolean isUserVerified = userService.verifyUser(code);
		userVerifiedMsg.put("isUserVerified", isUserVerified);
		return ResponseEntity.ok(userVerifiedMsg.toString());
	}

}
