import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {
	String mName;
	int counter;
	int myline;

	public MethodTransformVisitor(final MethodVisitor mv, String name) {
		super(ASM5, mv);
		this.mName = name;
		counter = 0;
		myline = 0;
	}

	// method coverage collection
	@Override
	public void visitCode() {
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn(mName + " executed");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		super.visitCode();
	} // statement
	// coverage collection but not working well all the time

	@Override
	public void visitLineNumber(int line, Label start) {
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("line " + line + " executed");
		counter = 0;
		myline = line;
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		super.visitLineNumber(line, start);
	}

	// statement coverage collection
	// but not working well all the time
	@Override
	public void visitIincInsn(int var, int increment) {
		if (counter > 0) {
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitLdcInsn("line " + myline + " executed");
			counter = 0;
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
		}
		counter++;
		super.visitIincInsn(var, increment);
	}
}