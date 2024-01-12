//
// Simple initial ASM class reader example
//
import org.objectweb.asm.*;

public class ClassPrinter extends ClassVisitor {

int noopCount;
static int v = 0;

public ClassPrinter() {
   super(Opcodes.ASM9);
   noopCount = 0;
}

public void addNoops(int count) {
   noopCount += count;
}

public void visit(int version, int access, String name,
   String signature, String superName, String[] interfaces) 
{
   System.out.println(name + " extends " + superName + " {");
}

public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) 
{
   System.out.println("" + desc + " " + name);
   return null;
}

// Note that for your assignment you need to create a class that 
// extends MethodVisitor and return an object of your class here
public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) 
{
   MyMethodVisitor mv = new MyMethodVisitor(this);
   System.out.println("" + name + desc); 
   return mv;
}

public void visitEnd() {
   System.out.println("}");
}

public static void main(String args[])
{
   String classname;
   if (args==null || args.length==0 || args[0]==null)
      classname = "java.lang.Runnable";
   else
      classname = args[0];
   ClassPrinter cp = new ClassPrinter();
   try {
      ClassReader cr = new ClassReader(classname);
      cr.accept(cp, 0);
   } catch (Exception e) {
      System.err.println("Error: " + e);
   }
   System.out.println("Noop count: " + cp.noopCount);
}

}

