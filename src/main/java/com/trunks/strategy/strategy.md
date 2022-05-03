### 策略模式

在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。

策略（Strategy）模式的定义：该模式定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响使用算法的客户。策略模式属于对象行为模式，它通过对算法进行封装，把使用算法的责任和算法的实现分割开来，并委派给不同的对象对这些算法进行管理

**类型：行为类模式**

举一个便于理解的例子：

> 【旅游案例】
>
> 当你需要去旅游，根据手里的预算，你可以选择不同的出行方式，比如钱多就可选择飞机出行，钱少就可以选择火车出行，如果完全没钱就只能步行去旅游

#### 代码案例：

策略模式需要以下角色：

1. 抽象策略类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现

2. 具体策略类：实现了抽象策略定义的接口，提供具体的算法实现

3. 环境类：持有一个策略类的引用，最终给客户端调用

```java
/**
 * 抽象策略类，此处使用接口实现，定义旅游
 */
public interface TravelStrategy {
    void travel();
}
```

```java
/**
 * 具体的策略类，继承抽象策略类或者现在抽象策略的接口，此处为飞机出行
 */
public class Airplane implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("通过飞机出行");
    }
}
```

```java
/**
 * 具体的策略类，继承抽象策略类或者现在抽象策略的接口，此处为火车出行
 */
public class Train implements TravelStrategy {
    @Override
    public void travel() {
        System.out.println("通过火车出行");
    }
}
```

```java
/**
 * 环境类，用于给客户端调用
 */
public class TravelMethod {

    /**
     * 要旅游，需要选择一个旅游策略
     * get与set方案略
     */
    public TravelStrategy travelStrategy;

    public void travelMethod() {
        travelStrategy.travel();
    }
}
```

```java
/**
 * 客户端按理
 */
public class Client {
    public static void main(String[] args) {
        int money = 1000;
        Train train = new Train();
        Airplane airplane = new Airplane();
        Walk walk = new Walk();
        TravelMethod travelMethod = new TravelMethod();
        /* 
         这一步可以使用单例和简单工厂模式来做
         比如定义一个工厂TravelFactory
         其中内置一个map，定义不同的策略与具体的实现
         在系统启动时将所有的策略放置进map中，后续想要什么策略从这个map中取就好
         */
        if (money <= 0) {
            travelMethod.setTravelStrategy(walk);
        } else if (money <= 500) {
            travelMethod.setTravelStrategy(train);
        } else {
            travelMethod.setTravelStrategy(airplane);
        }
        travelMethod.travelMethod();
    }
}
```

策略模式是准备一组算法，并将这组算法封装到一系列的策略类里面，作为一个抽象策略类的子类。策略模式的重心不是如何实现算法，而是如何组织这些算法，从而让这些程序结构更加灵活，具有更好的维护性和扩展性。

#### 策略模式的使用场景

- 一个系统需要动态地在几种算法中选择一种时，可将每个算法封装到策略类中。
- 一个类定义了多种行为，并且这些行为在这个类的操作中以多个条件语句的形式出现，可将每个条件分析移入它们各自的策略类中以完全代替这些条件语句。
- 系统中各算法彼此完全独立，且要求对客户隐藏具体算法的实现细节时。
- 系统要求使用算法的客户不应该知道其操作的数据时，可使用策略模式来隐藏与算法相关的数据结构。
- 多个类只区别在表现行为不同，可以使用策略模式，在运行时动态选择具体要执行的行为。

#### 策略模式优点：

- 多重条件语句不易维护，而使用策略模式可以避免使用多重条件语句，如if…else语句、switch…case语句。

- 策略模式提供了一系列的可供重用的算法族，恰当使用继承可以把算法族的公共代码转移到父类里面，从而避免重复的代码。

- 策略模式可以提供相同行为的不同实现，客户可以根据不同时间或空间要求选择不同的。

- 策略模式提供了对开闭原则的完美支持，可以在不修改源代码的情况下，灵活增加新算法。

- 策略模式把算法的使用放到环境类中，而算法的实现移到具体策略类中，实现了二者的分离。

#### 策略模式缺点：

- 客户端必须理解所有策略算法的区别，以便适时选择恰当的算法类。

- 策略模式造成很多的策略类，增加维护难度。