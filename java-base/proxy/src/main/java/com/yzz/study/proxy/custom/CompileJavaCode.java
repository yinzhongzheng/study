package com.yzz.study.proxy.custom;

import javax.tools.*;
import java.nio.charset.Charset;

/**
 * author:yzz
 * date:2018/12/1
 * E-mail:yzzstyle@163.com
 * com.yzz.study.proxy.custom
 * 注释:
 */

public class CompileJavaCode {

    public static void compile(String javaFileName){
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, Charset.defaultCharset());
            Iterable iterable = manager.getJavaFileObjects(javaFileName);
            JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, manager, null, null, null, iterable);
            compilationTask.call();
            manager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
