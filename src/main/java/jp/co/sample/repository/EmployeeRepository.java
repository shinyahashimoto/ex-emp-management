package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesテーブルを操作するレポジトリクラス.
 * 
 * @author hashimotoshinya
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * employeesに対応したローマッパー.
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setDependentsCount(rs.getInt("dependents_count"));

		return employee;
	};

	/**
	 * 従業員一覧を取得します.
	 * 
	 * @return 従業員一覧情報
	 */
	public List<Employee> findAll() {
		String sql = "select id,name,image,gender,hire_date,mail_address,zip_code,telephone,salary,dependents_count "
				+ "from employees order by hire_date";
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}

	/**
	 * 検索されたidと合致した従業員情報を取得します.
	 * 
	 * @param id 検索したID
	 * @return 検索されたidと合致した従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "select id,name,image,gender,hire_date,mail_address,zip_code,telephone,salary,dependents_count"
				+ " from employees where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}

	/**
	 * 従業員情報を更新します.
	 * 
	 * @param employee 更新したい従業員情報
	 */
	public void update(Employee employee) {
		String sql = "update employees set dependents_count=:dependentsCount where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("dependentsCount", employee.getDependentsCount()).addValue("id", employee.getId());
		template.update(sql, param);
	}

}
