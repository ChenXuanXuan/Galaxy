package com.mex.GalaxyChain.view;

/**
 * name：
 * describe:
 * author: LSJ
 * time 23/4/18 下午8:10
 */
interface PasswordView {

	//void setError(String error);

	String getPassWord();

	void clearPassword();

	void setPassword(String password);

	void setPasswordVisibility(boolean visible);

	void togglePasswordVisibility();

	void setOnPasswordChangedListener(GridPasswordView.OnPasswordChangedListener listener);

	void setPasswordType(PasswordType passwordType);
}