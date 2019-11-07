package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * administratorの処理を実行するサービスクラス
 * 
 * @author hashimotoshinya
 *
 */
@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository repository;

	/**
	 * 追加処理を行います.
	 * 
	 * @param administrator 追加したい管理者情報.
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}

	/**
	 * ログイン処理を行います.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報
	 */
	public Administrator login(String mailAddress, String password) {
		return repository.findByMailAddressPassword(mailAddress, password);
	}

}
