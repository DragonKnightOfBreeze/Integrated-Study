package com.windea.study.stringtable;

import sun.misc.Unsafe;

//只有由主类加载器加载的类才能正常调用Unsafe.getUnsafe()方法
//可以通过设置bootstrap参数正常使用，也可以通过反射的方式直接得到theUnsafe字段

public class UnsafeAccessor {
    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        var theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.trySetAccessible();
        return (Unsafe) theUnsafe.get(null);
    }
}
