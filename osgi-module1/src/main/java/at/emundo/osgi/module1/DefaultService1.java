package at.emundo.osgi.module1;

public final class DefaultService1 implements Service1 {

    @Override
    public void execute() {
        System.out.println("bean chain");
    }

}
