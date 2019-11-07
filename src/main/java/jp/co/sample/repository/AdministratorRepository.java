package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するレポジトリクラス.
 * 
 * @author hashimotoshinya
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * administratorsに対応したローマッパー.
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));

		return administrator;
	};

	/**
	 * 管理者情報を新規に追加します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String sql = "insert into administrators (name, mail_address, password) values(:name, :mailAddress, :password)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得する.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 取得した管理者情報(１件も検索されなかった場合はnullを返す)
	 */
	public Administrator findByMailAddressPassword(String mailAddress, String password) {
		// 複数条件の時って「or」「and」どっち
		String sql = "select id,name,mail_address,password from administrators where mail_address=:mailAddress and password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);

		// 検索がヒットしなかったときにnullを返します
		if (administratorList.size() == 0) {
			return null;
		}

		return administratorList.get(0);
	}

}
