package thread;

/**
 * 按照某个顺序调用线程
 */
public class SequentialPrinting {
    public static void main(String[] args) {
        int[] flags = {3, 2, 1};
        Foo foo = new Foo(flags, 0);
        foo.setFlag(foo.getFlags()[foo.get_index()]);
        new Thread(new PrintOne(foo)).start();
        new Thread(new PrintTwo(foo)).start();
        new Thread(new PrintThree(foo)).start();
    }
}

class Foo {

    private int[] flags;

    private int _index;

    private int flag;

    public Foo(int[] flags, int _index) {
        this.flags = flags;
        this._index = _index;
    }

    public int[] getFlags() {
        return flags;
    }

    public int get_index() {
        return _index;
    }

    public void set_index(int _index) {
        this._index = _index;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void printOne() {
        System.out.println("one");
    }

    public void printTwo() {
        System.out.println("two");
    }

    public void printThree() {
        System.out.println("three");
    }
}

class PrintOne implements Runnable {

    private Foo foo;

    public PrintOne(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        synchronized (foo) {
            while (foo.getFlag() != 1) {
                try {
                    foo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            foo.printOne();
            if (foo.get_index() < 2) {
                foo.set_index(foo.get_index() + 1);
                foo.setFlag(foo.getFlags()[foo.get_index()]);
                foo.notifyAll();
            }
        }
    }
}

class PrintTwo implements Runnable {

    private Foo foo;

    public PrintTwo(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        synchronized (foo) {
            while (foo.getFlag() != 2) {
                try {
                    foo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            foo.printTwo();
            if (foo.get_index() < 2) {
                foo.set_index(foo.get_index() + 1);
                foo.setFlag(foo.getFlags()[foo.get_index()]);
                foo.notifyAll();
            }
        }
    }
}

class PrintThree implements Runnable {

    private Foo foo;

    public PrintThree(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        synchronized (foo) {
            while (foo.getFlag() != 3) {
                try {
                    foo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            foo.printThree();
            if (foo.get_index() < 2) {
                foo.set_index(foo.get_index() + 1);
                foo.setFlag(foo.getFlags()[foo.get_index()]);
                foo.notifyAll();
            }
        }
    }
}
