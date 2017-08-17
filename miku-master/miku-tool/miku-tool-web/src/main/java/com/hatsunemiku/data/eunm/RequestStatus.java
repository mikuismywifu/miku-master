
package com.hatsunemiku.data.eunm;

public enum RequestStatus implements EnumMessage {
	ok(200, "请求成功"), failure(404, "请求成功");
	private final Integer _code;
	private final String _message;

	RequestStatus(Integer code, String message) {
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
