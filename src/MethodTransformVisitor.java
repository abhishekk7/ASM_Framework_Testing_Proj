import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;



class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String mName;
    int lineNumber;
    String labelName;
 	int isnewLabel=0;

    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName=name;
    }

    // method coverage collection
    @Override
    public void visitCode(){
    	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn(mName+" executed");
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    	super.visitCode();
    }
    // statement coverage collection but not working well all the time
	@Override
	public void visitLineNumber(int line, Label start) {
    	isnewLabel=0;    
	    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    	mv.visitLdcInsn("line "+line+" executed");
        lineNumber=line;
        labelName=start.toString();
    	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
       	super.visitLineNumber(line, start);
	}

    @Override
    public void visitLabel(Label label)
    {
        
        
        if(isnewLabel>0)
        {
        	mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	mv.visitLdcInsn("line "+lineNumber+" executed");
        	mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);	
        }
        isnewLabel++;
        super.visitLabel(label);
        
    }
}