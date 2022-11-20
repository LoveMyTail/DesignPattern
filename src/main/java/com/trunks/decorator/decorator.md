### 装饰者模式

装饰者（Decorator Pattern）指的是在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象

动态的将新功能附加到对象上。在对象功能扩展方面，他比继承更有弹性，装饰者模式也体现了开闭原则（OCP）



**类型：结构类模式**

**装饰者模式与代理模式区别**

装饰者模式关注的是对象的动态添加功能。代理模式关注的是对对象的控制访问，对它的用户隐藏对象的具体信息

**举例**

创建一个煎饼果类

```java
public class Battercake {
    protected String getDesc(){
        return "煎饼果子";
    }
    protected int cost(){
        return 8;
    }
}
```

加鸡蛋类，我们让加鸡蛋类继承煎饼果子类

```javascript
public class BattercakeWithEgg extends Battercake {
    @Override
    public String getDesc() {
        return super.getDesc()+" 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost()+1;
    }
}
```

加香肠类，同样的继承煎饼果子类。

```Java
public class BattercakeWithEggSausage extends BattercakeWithEgg {
    @Override
    public String getDesc() {
        return super.getDesc()+ " 加一根香肠";
    }

    @Override
    public int cost() {
        return super.cost()+2;
    }
}

```

测试类

```Java
public class DecoratorV1Test {
    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getDesc()+" 销售价格:"+battercake.cost());

        Battercake battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getDesc()+" 销售价格:"+battercakeWithEgg.cost());

        Battercake battercakeWithEggSausage = new BattercakeWithEggSausage();
        System.out.println(battercakeWithEggSausage.getDesc()+" 销售价格:"+battercakeWithEggSausage.cost());

    }
}

```

```
输出结果：
煎饼 销售价格:8
煎饼 加一个鸡蛋 销售价格:9
煎饼 加一个鸡蛋 加一根香肠 销售价格:11
```

这样做有个问题，什么问题呢？假设我们现在要加2个鸡蛋呢？糟糕我们没写加2个鸡蛋的类，如果还有3个4个什么的那是不是就要类爆炸了。

**下面我们用装饰者模式实现一下**

首先我们定义一个抽象的煎饼果子

```Java
public abstract class ABatterCake {
    protected abstract String getDesc();
    protected abstract int cost();

}
```

实体煎饼果子类，实体煎饼果子继承了抽象煎饼果子类

```Java
public class BatterCake extends ABatterCake {
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
```

装饰父类，这里也是可以使用抽象类，等会儿我们再说什么时候使用抽象类什么时候使用实体类。注意构造器和这个里面的花费、描述方法的写法。这里注入一个抽象煎饼类的对象。我们的获取描述花费的操作**都委托抽象煎饼类来执行**，为什么要这么做可以去看看我之前的文章依赖倒置原则。

```java
public class AbstractDecorator extends ABatterCake {
    private ABatterCake aBatterCake;

    public AbstractDecorator (ABatterCake aBatterCake) {
        this.aBatterCake = aBatterCake;
    }

    @Override
    protected String getDesc() {
        return this.aBatterCake.getDesc();
    }

    @Override
    protected int cost() {
        return this.aBatterCake.cost();
    }
}
```

鸡蛋的装饰类，这里注意他的构造器，参数是父类的对象抽象煎饼类对象，这里获取描述和花费方法都是调用了父类的方法。

```java
public class EggDecorator extends AbstractDecorator {
    public EggDecorator(ABatterCake aBatterCake) {
        super(aBatterCake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc()+"加一个鸡蛋";
    }

    @Override
    protected int cost() {
        return super.cost()+1;
    }
}
```

香肠装饰类

```java
public class SausageDecorator extends AbstractDecorator{
    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected String getDesc() {
        return super.getDesc()+" 加一根香肠";
    }

    @Override
    protected int cost() {
        return super.cost()+2;
    }
}
```

最后是测试类，创建一个实体煎饼果子类并赋值给抽象煎饼果子类，然后将这个父类对象注入装饰类，再把得到的对象赋值给创建的抽象对象。

```Java
public class DecoratorTest {
    public static void main(String[] args) {
        ABatterCake aBatterCake;
        aBatterCake = new BatterCake();
        aBatterCake = new EggDecorator(aBatterCake);
        aBatterCake = new EggDecorator(aBatterCake);
        aBatterCake = new SausageDecorator(aBatterCake);

        System.out.println(aBatterCake.getDesc()+" 销售价格:"+aBatterCake.cost());
    }
}
输出结果：
煎饼加一个鸡蛋加一个鸡蛋 加一根香肠 销售价格:12
```

最后我们来说说装饰父类什么时候使用抽象类。一般当我们需要在具体的类中都需涛执行一些特定的操作时。我们一般就会使用抽象类，并定义抽象方法。

```Java
public abstract class AbstractDecorator extends ABatterCake {
    private ABatterCake aBatterCake;

    public AbstractDecorator(ABatterCake aBatterCake) {
        this.aBatterCake = aBatterCake;
    }
	//定义每个抽象类的独特方法
    protected abstract void doSomething();

    @Override
    protected String getDesc() {
        return this.aBatterCake.getDesc();
    }

    @Override
    protected int cost() {
        return this.aBatterCake.cost();
    }
}

```


#### 装饰者模式的使用场景

- 扩展一个类的功能或者给一个类添加附加职责 

- 给一个对象动态的添加功能，或动态撤销功能

在以下情况下可以使用装饰模式：

• 在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。

• 需要动态地给一个对象增加功能，这些功能也可以动态地被撤销。

• 当不能采用继承的方式对系统进行扩充或者采用继承不利于系统扩展和维护时。不能采用继承的情况主要有两类：第一类是系统中存在大量独立的扩展，为支持每一种组合将产生大量的子类，使得子类数目呈爆炸性增长；第二类是因为类定义不能继承（如final类）。

#### 装饰者模式优点：

- 装饰类和被装饰类是可以**独立**的，低耦合的。互相都不用知道对方的存在

- 装饰模式是继承的一种**替代**方案，**无论包装多少层，返回的对象都是is-a的关系**(上面的例子：包装完还是Phone类型)。

- 实现动态扩展，只要**继承了装饰器**就可以**动态**扩展想要的功能了。

#### 装饰者模式缺点：

- 会出现更多的代码，更多的类，增加程序的复杂性。

- 动态装饰时，多层装饰时会更复杂。（使用继承来拓展功能会增加类的数量，使用装饰者模式不会像继承那样增加那么多类的数量但是会增加对象的数量，当对象的数量增加到一定的级别时，无疑会大大增加我们代码调试的难度）

