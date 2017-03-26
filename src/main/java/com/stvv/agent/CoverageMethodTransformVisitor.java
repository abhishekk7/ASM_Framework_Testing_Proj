package com.stvv.agent;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CoverageMethodTransformVisitor extends MethodVisitor implements Opcodes {
	String mName;
	int line;
	String className;

	public CoverageMethodTransformVisitor(final MethodVisitor mv, String name, String className) {
		super(ASM5, mv);
		this.mName = name;
		this.className = className;
	}

	// method coverage collection
	@Override
	public void visitCode() {

		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn(mName + " executed");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		super.visitCode();
		
		/*Label l0 = new Label();
		Label l1 = new Label();
		Label l2 = new Label();
		mv.visitTryCatchBlock(l0, l1, l2, "java/io/FileNotFoundException");
		mv.visitLabel(l0);
		mv.visitTypeInsn(NEW, "java/io/PrintWriter");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("C:/testing/stmt_cov1.txt");
		mv.visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitFieldInsn(PUTSTATIC, className, "writer", "Ljava/io/PrintWriter;");
		mv.visitLabel(l1);
		Label l3 = new Label();
		mv.visitJumpInsn(GOTO, l3);
		mv.visitLabel(l2);
		mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/io/FileNotFoundException"});
		mv.visitVarInsn(ASTORE, 1);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/FileNotFoundException", "printStackTrace", "()V", false);*/
	}

	// statement coverage collection
	@Override
	public void visitLineNumber(int line, Label start) {
		this.line = line;


		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("line " + line + " executed");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		/*mv.visitFieldInsn(GETSTATIC, className, "writer", "Ljava/io/PrintWriter;");
		mv.visitLdcInsn("line " + line + " executed");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintWriter", "println", "(Ljava/lang/String;)V", false);*/
		super.visitLineNumber(line, start);
		
	}

	// collect line coverage for each label 
	@Override
	public void visitLabel(Label label) {
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("line " + line + " executed");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		
		/*mv.visitFieldInsn(GETSTATIC, className, "writer", "Ljava/io/PrintWriter;");
		mv.visitLdcInsn("line " + line + " executed");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintWriter", "println", "(Ljava/lang/String;)V", false);
*/
		super.visitLabel(label);
	}

	@Override
	public void visitEnd() {
		/*mv.visitFieldInsn(GETSTATIC, className, "writer", "Ljava/io/PrintWriter;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintWriter", "close", "()V", false);*/
		super.visitEnd();
	}
}
