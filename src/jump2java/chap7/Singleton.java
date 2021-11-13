// src: https://javaplant.tistory.com/21

package jump2java.chap7;


class ClassicSingleton {

    private static ClassicSingleton instance;
    private ClassicSingleton() {}

    public static ClassicSingleton getInstance() {
        if(instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }
}


class LockingSigneton {

    private static LockingSigneton instance;
    private LockingSigneton() {}

    public static synchronized LockingSigneton getInstance() {
        if(instance == null) {
            instance = new LockingSigneton();
        }
        return instance;
    }

}


// synchronize class only when creating instance.
class DoubleCheckedLockingSingleton {

    // volatile keyword: refer from main memory, not separated cpu cache.
    private volatile static DoubleCheckedLockingSingleton instance;
    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if(instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if(instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}


public class Singleton {  // LazyHolderSingleton: Best way

    private Singleton() {}

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    // Initialize LazyHolder class when evaluated.
    // being initialized: thread-safe
    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

}
