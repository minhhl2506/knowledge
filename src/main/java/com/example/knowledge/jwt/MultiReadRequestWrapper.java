package com.example.knowledge.jwt;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import net.snowflake.client.jdbc.internal.apache.commons.io.input.BoundedInputStream;

public class MultiReadRequestWrapper extends HttpServletRequestWrapper {

	public static final Integer MAX_BYTE_SIZE = 1_048_576; // 1 MB

	private StringBuilder body;

	public MultiReadRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		body = new StringBuilder("");

		try (InputStream bounded = new BoundedInputStream(request.getInputStream(), MAX_BYTE_SIZE);

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bounded));) {

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				body.append(line);
			}
		} catch (Exception e) {

		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.toString().getBytes());

		return new ServletInputStream() {

			public int read() throws IOException {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				return byteArrayInputStream.available() == 0;
			}

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// do nothing
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
}
