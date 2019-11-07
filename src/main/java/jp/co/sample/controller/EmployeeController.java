package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 全件検索した情報を受け取るコントローラー.
 * 
 * @author hashimotoshinya
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}

	/**
	 * 全件検索で取得した従業員リストを格納します.
	 * 
	 * @param model スコープに格納するための器???
	 * @return 従業員一覧リスト
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		model.addAttribute("employeeList", employeeService.showList());
		return "employee/list";
	}

	/**
	 * 詳細な従業員情報を表示します.
	 * 
	 * @param id    従業員ID
	 * @param model スコープに格納
	 * @return
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		int intId = Integer.parseInt(id);
		model.addAttribute("employee", employeeService.showDetail(intId));

		return "employee/detail";
	}

	/**
	 * 扶養人数を更新します.
	 * 
	 * @param form 更新したい従業員情報
	 * @return 従業員一覧リスト
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		System.out.println(form);
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));

		System.out.println(employee);
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

}
