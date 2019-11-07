package jp.co.sample.form;

/**
 * Administrator情報が入るフォームクラス
 * 
 * @author hashimotoshinya
 *
 */
public class InsertAdministratorForm {

	/** 管理者ID */
	private String id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password="
				+ password + "]";
	}

}
