package com.viscu.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Create by ostreamBaba on 18-3-30
 * @����
 */
public class FileDownloadAction extends ActionSupport{

    private static final long serialVersionUID=1L;
    private String file;
    private String contentType;

    public FileInputStream getDownload() throws FileNotFoundException{
        System.out.println(ServletActionContext.getServletContext().getMimeType(file));
        return new FileInputStream( ServletActionContext.getServletContext().getRealPath("/upload")
                +"/"+file);
    }

    public String getFile() throws UnsupportedOperationException, UnsupportedEncodingException {
        return URLEncoder.encode(file, "utf-8");
    }

    public void setFile(String file) throws UnsupportedEncodingException {
/*       * �� tomcat7 �д�get�����ȡ���������������ģ�
         * ��ᷢ��������������
         * ��Ϊ���������������������utf-8����ģ��ڷ�����tomcat����iso-8859-1����
         * ���������룬���������Խ��������
         * new String(f.getBytes("ISO-8859-1"),"UTF-8");
         * */
        this.file = new String(file.getBytes("ISO-8859-1"),"utf-8");
    }



    public String getContentType() {
        return ServletActionContext.getServletContext().getMimeType(file);
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}



