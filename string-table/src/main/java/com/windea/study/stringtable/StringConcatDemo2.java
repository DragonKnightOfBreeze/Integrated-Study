package com.windea.study.stringtable;

import jdk.internal.org.objectweb.asm.*;

import java.lang.invoke.*;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

//需要添加的编译参数：--add-exports=java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED

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
	//NOTE 可以使用IDEA插件ASM Bytecode Viewer生成这些代码
	public static byte[] dump() {
		ClassWriter classWriter = new ClassWriter(0);
		FieldVisitor fieldVisitor;
		MethodVisitor methodVisitor;
		AnnotationVisitor annotationVisitor0;

		classWriter.visit(V13, ACC_PUBLIC | ACC_SUPER, "com/windea/study/stringtable/StringConcatDemo1", null,
			"java/lang/Object", null);

		classWriter.visitSource("StringConcatDemo1.java", null);

		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			methodVisitor.visitCode();
			Label label0 = new Label();
			methodVisitor.visitLabel(label0);
			methodVisitor.visitLineNumber(6, label0);
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			methodVisitor.visitInsn(RETURN);
			Label label1 = new Label();
			methodVisitor.visitLabel(label1);
			methodVisitor
				.visitLocalVariable("this", "Lcom/windea/study/stringtable/StringConcatDemo1;", null, label0, label1,
					0);
			methodVisitor.visitMaxs(1, 1);
			methodVisitor.visitEnd();
		}
		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "concat",
				"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", null, null);
			methodVisitor.visitParameter("a", 0);
			methodVisitor.visitParameter("b", 0);
			methodVisitor.visitCode();
			Label label0 = new Label();
			methodVisitor.visitLabel(label0);
			methodVisitor.visitLineNumber(20, label0);
			methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
			methodVisitor.visitInsn(DUP);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
				"(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
			methodVisitor.visitVarInsn(ALOAD, 1);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
				"(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
			methodVisitor
				.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
			methodVisitor.visitInsn(ARETURN);
			Label label1 = new Label();
			methodVisitor.visitLabel(label1);
			methodVisitor.visitLocalVariable("a", "Ljava/lang/String;", null, label0, label1, 0);
			methodVisitor.visitLocalVariable("b", "Ljava/lang/String;", null, label0, label1, 1);
			methodVisitor.visitMaxs(2, 2);
			methodVisitor.visitEnd();
		}
		classWriter.visitEnd();

		return classWriter.toByteArray();
	}
}
