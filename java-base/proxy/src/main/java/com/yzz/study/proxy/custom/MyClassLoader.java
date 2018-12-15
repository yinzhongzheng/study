package com.yzz.study.proxy.custom;

import java.io.*;

/**
 * author:yzz
 * date:2018/12/1
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.custom
 * 注释: 加载class文件
 */
public class MyClassLoader extends ClassLoader{

    private static String baseDir;

    public MyClassLoader(){
        baseDir = MyClassLoader.class.getResource("").getPath();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName()+"."+name;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            File classFile = new File(baseDir,name.replaceAll("\\.","/") + ".class");
            bufferedInputStream = new BufferedInputStream(new FileInputStream(classFile));
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            int len;
            while((len = bufferedInputStream.read(temp)) != -1){
                byteArrayOutputStream.write(temp,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != bufferedInputStream){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != byteArrayOutputStream){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return defineClass(className,byteArrayOutputStream.toByteArray(),0,byteArrayOutputStream.size());
    }
}
