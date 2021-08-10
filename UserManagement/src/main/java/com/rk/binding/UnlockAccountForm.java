package com.rk.binding;

import lombok.Data;

@Data
public class UnlockAccountForm {

	private String email;
	private String tempPssword;
	private String newPassword;
	private String confirmPassword;
}
