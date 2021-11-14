### 单例模式

所谓单例，就是整个程序有且仅有一个实例。该类负责创建自己的对象，同时确保只有一个对象被创建。在Java，一般常用在工具类的实现或创建对象需要消耗资源。

**特点**

- 类构造器私有
- 持有自己类型的属性
- 对外提供获取实例的静态方法

**单例的实现**主要是通过以下两个步骤：

1、将该类的构造方法定义为私有方法，这样其他地方的代码无法通过调用该类的构造方法来实例化该类的对象，只有通过该类提供的静态方法来得到该类的唯一实例

2、在该类内提供一个静态方法，当我们调用这个方法时，如果类持有的引用不为空就返回这个引用，如果类保持的引用为空就创建该类的实例并将实例的引用赋予该类保持的引用。

**单例模式的适用场景**

- **1.需要生成唯一序列的环境**
- **2.需要频繁实例化然后销毁的对象。**
- **3.创建对象时耗时过多或者耗资源过多，但又经常用到的对象。**
- **4.方便资源相互通信的环境**

**单例模式的优缺点**

**优点**：

- 在内存中只有一个对象，节省内存空间；
- 避免频繁的创建销毁对象，可以提高性能；
- 避免对共享资源的多重占用，简化访问；
- 为整个系统提供一个全局访问点。

**缺点**：

-  不适用于变化频繁的对象；
-  滥用单例将带来一些负面问题，如为了节省资源将数据库连接池对象设计为的单例类，可能会导致共享连接池对象的程序过多而出现连接池溢出；
-  如果实例化的对象长时间不被利用，系统会认为该对象是垃圾而被回收，这可能会导致对象状态的丢失；

**单例模式的实现**

**1、饿汉式**

```Java
// 饿汉式单例
public class SingletonOne {
    private static SingletonOne singleton = new SingletonOne();

    private SingletonOne() {
    }

    private static SingletonOne getInstance(){
        return singleton;
    }

}
```

我们知道，**类加载的方式是按需加载，且加载一次**。。因此，在上述单例类被加载时，就会实例化一个对象并交给自己的引用，供系统使用；

而且，由于这个类在整个生命周期中只会被加载一次，因此只会创建一个实例，即能够充分保证单例。

优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题。

缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则**会造成内存的浪费**。

**2、懒汉式**

```Java
// 懒汉式单例
public class SingletonTwo {
    private static SingletonTwo singleton;

    private SingletonTwo() {
    }

    private SingletonTwo getInstance() {
        if (singleton == null)
            singleton = new SingletonTwo();
        return singleton;
    }

}
```

我们从懒汉式单例可以看到，单例实例被**延迟加载**，即只有在真正使用的时候才会实例化一个对象并交给自己的引用。

这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。如果在多线程下，一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式。

```Java
// 懒汉式单例（线程安全）
public class SingletonThree {
    private static SingletonThree singleton;
    private SingletonThree(){

    }
    private static synchronized SingletonThree getInstance(){
        if (singleton == null)
            singleton = new SingletonThree();
        return singleton;
    }
}
```

虽然解决了线程安全问题，但是还会有新的问题：效率低，每个线程想获得类的实例的时候，执行getInstance（）方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想要后的该类实例，直接return就行了。**方法进行同步效率太低**。

**3、双重检测加锁机制**

```java
public class SingletonFour {
    // 此处必须要加volatile，加了以后可以避免指令重排序出现问题
    private volatile static SingletonFour singleton;

    private SingletonFour(){

    }
    private static SingletonFour getInstance(){
        if (singleton == null){
            synchronized (SingletonFour.class){
                if (singleton == null)
                    singleton = new SingletonFour();
            }
        }
        return singleton;
    }
}
```

Double-Check概念对于多线程开发者来说不会陌生，如代码中所示，我们进行了两次if (singleton == null)检查，这样就可以保证线程安全了。这样，实例化代码只用执行一次，后面再次访问时，判断if (singleton == null)，直接return实例化对象。

使用双重检测同步延迟加载去创建单例的做法是一个非常优秀的做法，**其不但保证了单例，而且切实提高了程序运行效率**

优点：线程安全；延迟加载；效率较高。

**4、静态内部类式**

```Java
public class SingletonFive {
    private static class SingletonFiveInstance {
        private static final SingletonFive sigleton = new SingletonFive();
    }

    private SingletonFive() {
    }

    private static SingletonFive getInstance() {
        return SingletonFiveInstance.sigleton;
    }
}
```

这种方式跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程。不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。

类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。

优点：避免了线程不安全，延迟加载，效率高。

**总结**

当然，单例模式的实现方法还有很多。但是，这四种是比较经典的实现，也是我们应该掌握的几种实现方式。

从这四种实现中，我们可以总结出，要想实现效率高的线程安全的单例，我们必须注意以下两点：

- **尽量减少同步块的作用域；**
- **尽量使用细粒度的锁。**
