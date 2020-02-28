package com.windea.study.stringtable;

import jdk.internal.org.objectweb.asm.*;

import java.lang.invoke.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class StringConcatDemo2 {
	public static void main(String[] args) throws Throwable {
		String x = "hello";
		String y = "world";

		//在运行期间生成匿名内部类
		//注意这里不能直接调用Unsafe.getUnsafe()来得到Unsafe单例。
		Class<?> innerClass = UnsafeAccessor.getUnsafe().defineAnonymousClass(StringConcatDemo1.class, dump(), null);
		UnsafeAccessor.getUnsafe().ensureClassInitialized(innerClass);

		//MethodHandle可用来放射调用一个已知信息的方法
		MethodHandle methodHandle = MethodHandles.lookup().findStatic(StringConcatDemo1.class, "concat",
			MethodType.methodType(String.class, String.class, String.class));
		String s = (String) methodHandle.invoke(x, y);
		System.out.println(s);
	}

	//使用java代码生成字节码的方法
	//可以生成匿名类的字节码，包括concat(String x, String y)方法
	//NOTE 可以使用IDEA插件ASM Bytecode Outline生成这些代码
	public static byte[] dump() throws Exception {
		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;
		cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/windea/study/stringtable/StringConcatDemo2", null, "java/lang" +
				"/Object",
			null);
		cw.visitSource("Test5.java", null);
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(3, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lcom/windea/study/stringtable/StringConcatDemo2;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "concat",
				"(Ljava/lang/String;Ljava/langString;)Ljava/lang/String;", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(5, l0);
			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "append",
				"(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "append",
				"(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/StringBuilder;",
				false);
			mv.visitInsn(ARETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("x", "Ljava/lang/String;", null, l0, l1, 0);
			mv.visitLocalVariable("y", "Ljava/lang/String;", null, l0, l1, 1);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		cw.visitEnd();
		return cw.toByteArray();
	}
}
