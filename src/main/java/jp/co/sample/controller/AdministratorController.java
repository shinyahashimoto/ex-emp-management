package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 登録情報を受け取るコントローラー.
 * 
 * @author hashimotoshinya
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService service;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * 初期画面（入力画面）（追加画面？？）.
	 * 
	 * @return 初期画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 従業員情報を追加します.
	 * 
	 * @param form 管理者情報が入ったフォーム
	 * @return 初期画面
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);

		service.insert(administrator);

		return "redirect:/";

	}

	/**
	 * ログインの初期画面.
	 * 
	 * @return ログインの初期画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	/**
	 * メールアドレスとパスワードでログイン処理を行います. <br>
	 * ログイン失敗の場合再度ログイン画面を表示。
	 * 
	 * @param form 管理者情報
	 * @return ログインに成功した管理者情報
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {

		Administrator administrator = service.login(form.getMailAddress(), form.getPassword());

		if (administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です");
			System.out.println(0);
			return "administrator/login";
		} else {
			System.out.println(1);
			session.setAttribute("administrator", administrator);
			System.out.println(2);
			return "forward:/employee/showList";
		}

	}
	
	/**ログアウトをします.
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}
