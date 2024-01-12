
import org.objectweb.asm.*;

public class MyMethodVisitor extends MethodVisitor
{

private int count;
private ClassPrinter cv;

MyMethodVisitor​(ClassPrinter v)
{
   super(Opcodes.ASM9);
   count = 0;
   cv = v;
}

public void visitInsn​(int opcode)
{
   count++;
   System.out.println("No-operand instruction!" + opcode);
}

public void visitEnd() {
   cv.addNoops(count);
}
}
