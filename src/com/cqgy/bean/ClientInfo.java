

package com.cqgy.bean;

import java.net.InetAddress;

public class ClientInfo {
	// �û�Id
	private String userId;
	// �ͻ���IP��ַ
	private InetAddress address;
	// �ͻ��˶˿ں�
	private int port;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
