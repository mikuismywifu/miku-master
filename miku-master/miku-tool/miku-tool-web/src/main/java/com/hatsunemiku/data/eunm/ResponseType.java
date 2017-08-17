
package com.hatsunemiku.data.eunm;

public enum ResponseType implements EnumMessage {
	forward(203, "转发"), redirect(307, "重定向");
	private final Integer _code;
	private final String _message;

	ResponseType(Integer code, String message) {
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
