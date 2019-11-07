package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 検索処理を行うサービスクラス.
 * 
 * @author hashimotoshinya
 *
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 全従業員リストを検索します.
	 * 
	 * @return 従業員リスト
	 */
	public List<Employee> showList() {
		return employeeRepository.findAll();
	}

	/**
	 * 詳しい従業員情報を検索します.
	 * 
	 * @param id 従業員ID
	 * @return 詳しい従業員情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}

	/**
	 * 扶養人数を更新します. 従業員情報を更新します.(どっちのほうがいいかな？？)
	 * 
	 * @param employee 更新したい従業員情報
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}

}
