package com.laioin.boot.web.base;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BootRequestWrapper extends HttpServletRequestWrapper {
    ThreadLocal<ByteArrayOutputStream> paramLocal = new ThreadLocal<ByteArrayOutputStream>();

    public BootRequestWrapper(HttpServletRequest request, ByteArrayOutputStream out) {
        super(request);
        paramLocal.set(out);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream is = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            private ByteArrayInputStream inputStream = new ByteArrayInputStream(paramLocal.get().toByteArray());

            @Override
            public int available() throws IOException {
                return inputStream.available();
            }

            @Override
            public int read() throws IOException {
                return inputStream.read();
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return inputStream.read(b, off, len);
            }
        };
        return is;
    }

}
