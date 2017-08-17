package com.hatsunemiku.data.eunm;

//枚举类
public enum AccountStatus implements EnumMessage {
	ok(0, "校验通过"), incorrectPassword(1001, "账号密码不正确"), AccountNotExist(1002, "账号不存在"), Frozen(1003,
			"账户冻结"), Disabled(1004, "账户禁用"), NotService(0000, "无服务"),error(400,"系统繁忙、稍后重试")
	,accountExist(1004,"邮箱已注册");
	private final Integer _code;
	private final String _message;

	AccountStatus(Integer code, String message) {
		_code = code;
		_message = message;
	}

	@Override
	public Integer getValue() {
		return _code;
	}

	@Override
	public String getMessage() {
		return _message;
	}
}