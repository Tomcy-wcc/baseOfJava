package thread;

import sun.misc.Unsafe;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @auther wcc
 * @create 2019-09-20 16:03
 */
public class MyLock {

    /*private static final Unsafe unsafe = Unsafe.getUnsafe();

    private static final long stateOffset;

    private Thread exclusiveOwnerThread;


    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (MyLock.class.getDeclaredField("state"));
        } catch (Exception ex) { throw new Error(ex); }
    }


    final void lock() {
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());

    }

    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    protected final void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }
*/

    public static void main(String[] args) {
        //0x61c88647
        for(int i = 1; i <= 16; i++){
            String r = Integer.toBinaryString(1640531527*i);
            int length = r.length();
            System.out.println(Integer.valueOf(r.substring(length-4), 2) );
        }
    }

}
