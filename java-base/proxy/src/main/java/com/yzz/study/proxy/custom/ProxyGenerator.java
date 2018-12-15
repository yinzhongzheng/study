package com.yzz.study.proxy.custom;

import javax.sound.sampled.Line;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * author:yzz
 * date:2018/12/1
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.custom
 * 注释:创建proxy class 文件
 */
class ProxyGenerator {

    private static final String LINE = "\r\n";

    public static final String LEFT_CLOSE = "}";

    public static final String RIGHT_CLOSE = "{";

    public static final String INVOCATIONHANDLER = "InvocationHandler";

    public static final String PARAMATER = "var";

    public static String createProxyJavaFile(ClassLoader classLoader, String className, Class ince) {
        String filePath = classLoader.getClass().getResource("").getPath() + "/" + className + ".java";
        String packgeNmae = classLoader.getClass().getPackage().getName();
        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(packgeNmae).append(";").append(LINE);
        sb.append("import ").append("java.lang.reflect.InvocationHandler;").append(LINE);
        sb.append("import java.lang.reflect.Method;").append(LINE);
        sb.append("import com.yzz.study.proxy.custom.Proxy;").append(LINE);
        createHead(sb, className,ince.getName());
        Map<String, String> methodNames = createField(sb, ince);
        createConstruct(sb, className);
        createMethods(sb, ince, methodNames);
        createStaticBlock(ince,methodNames,sb);
        sb.append(LEFT_CLOSE);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static void createHead(StringBuffer sb, String className,String inceName) {
        sb.append("public class ")
                .append(className)
                .append(" extends Proxy implements ")
                .append(inceName)
                .append(" ")
                .append(RIGHT_CLOSE)
                .append(" ")
                .append(LINE);
    }

    public static Map<String, String> createField(StringBuffer sb, Class ince) {
        Method[] ms = ince.getMethods();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < ms.length; i++) {
            Method method = ms[i];
            sb.append("private static Method ")
                    .append("m")
                    .append(i + 1)
                    .append(";").append(LINE);
            map.put(method.getName(), "m" + (i + 1));
        }
        return map;
    }

    public static void createConstruct(StringBuffer sb, String className) {
        sb.append("public ")
                .append(className)
                .append(" (")
                .append(INVOCATIONHANDLER)
                .append(" h )")
                .append(RIGHT_CLOSE)
                .append("super(h);")
                .append(LEFT_CLOSE)
                .append(LINE);
    }

    public static void createMethods(StringBuffer sb, Class ince, Map<String, String> methodNmaes) {
        Method[] ms = ince.getMethods();
        for (Method m : ms) {
            String methodName = m.getName();
            Class returnType = m.getReturnType();
            Class[] pts = m.getParameterTypes();
            createMethod(methodName, returnType, pts, sb, ince, methodNmaes.get(m.getName()));
        }
    }

    public static void createMethod(String methodName, Class returnType, Class[] pts, StringBuffer sb, Class ince, String mName) {
        sb.append(" public ")
                .append(returnType)
                .append(" ")
                .append(methodName)
                .append("(");
        String[] args = new String[pts.length];
        for (int i = 0; i < pts.length; i++) {
            Class c = pts[i];
            sb.append(c.getName())
                    .append(" ")
                    .append(PARAMATER)
                    .append(i);
            if (i != pts.length - 1) {
                sb.append(",");
            }
            args[i] = PARAMATER + i;
        }
        sb.append(")");
        sb.append(RIGHT_CLOSE);
        createMethodBody(mName, args, sb);
        sb.append(LEFT_CLOSE)
                .append(LINE);

    }

    /***
     *  public Object invoke(Object proxy, Method method, Object[] args)
     *  throws Throwable;
     * @return
     */
    public static void createMethodBody(String methodName, String[] args, StringBuffer sb) {
        sb.append("try ")
                .append(LINE)
                .append(RIGHT_CLOSE)
                .append(LINE)
                .append("super.h.invoke(this,")
                .append(methodName)
                .append(",")
                .append("new Object[]{");
        //这里需要构造参数
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i != args.length - 1) {
                sb.append(",");
            }
        }
        sb.append("});")
                .append(LINE)
                .append("}catch(Throwable e){")
                .append(LINE)
                .append("e.printStackTrace();")
                .append(LINE)
                .append(LEFT_CLOSE)
                .append(LINE);
    }

    private static void createStaticBlock(Class ince,Map<String,String> classMethodMap,StringBuffer sb){
        sb.append("static ")
                .append(RIGHT_CLOSE)
                .append("try ")
                .append(RIGHT_CLOSE);
        Method[] ms = ince.getMethods();
        for (Method method:ms){
            Class[] ps = method.getParameterTypes();
            String pcs = "";
            for (int i = 0; i < ps.length; i++) {
                pcs += "Class.forName(\""+ps[i].getName() + "\")";
                if (i != ps.length-1){
                    pcs += ",";
                }
            }
            sb.append(classMethodMap.get(method.getName()))
                    .append("=")
                    .append("Class.forName(\"")
                    .append(ince.getName())
                    .append("\")")
                    .append(".getMethod(")
                    .append("\""+method.getName()+"\"")
                    .append(",")
                    .append("new Class[]{")
                    .append(pcs)
                    .append("});")
                    .append(LINE);
        }
        sb.append("}catch(Exception e)")
                .append(RIGHT_CLOSE)
                .append(LINE)
                .append("e.printStackTrace();")
                .append(LINE)
                .append(LEFT_CLOSE);
        sb.append(LEFT_CLOSE);
        sb.append(LINE);
    }


}
