"#hibernate_demo hql" 

12/02/2016 笔记

where子句

比较运算
    1. = <> < > >= <=
    
    String hql = "from Commodity c where c.price>400";
    
    2. null值判断  is [not] null
        也可以直接使用  x = null  表示 x is null
                    x <> null  表示 x is not null

    //在数据库中增加一条测试数据 description 是null值的数据
    //在sql语句中 “= null” 是错误的，但是hql语句中“= null” 是允许的
    String hql = "from Commodity c where c.description = null";
    


范围运算

    //not 取反
    1. [not] in (列表)

    String hql = "from Customer c where c.age in (20,40)";

    2. [not] between 值1 and  值2

    String hql = "from Customer c where c.age between 20 and 40";

字符串模式匹配

    1. like关键字
    2. 通配符 % _
            %匹配任意个字符， _匹配一个字符

    String hql = "from Customer c where c.name like '张_'";

    String hql = "from Customer c where c.address like '%北京%'";


逻辑运算

    1. and , or 
    2. not

    String hql ="from Commodity c where c.price between 100 and 5000 and c.category like '%电脑%'";

    String hql ="from Commodity c where c.price between 100 and 5000 or c.category like '%电脑%'";


集合运算


    一对多， 
    1. is [not] empty  ：集合[不]为空， 不包含任何元素
    2. member of       ：元素属于集合

        empty       解析为sql中的-> exits
        member of   解析为sql中的-> in


        //查询订单明细不为空的有效订单
        String hql = "from OrderForm o where o.orderItems is not empty";
    
四则运算  
    1. hql中使用+ - / × 
    2. where， select 子句中都可以使用

    //查找出5件商品总价大于3000的商品信息
    String  hql = "from Commodity c where c.price * 5 > 3000";

 查询单个对象
    1. Query接口的uniqueResult方法
    2. where子句需要设置恰当的条件，使得最后查询到的结果只有一条数据   

    //查询用户名称为“张三”的用户
     String hql = "from Customer c where c.name='张三'";
     Customer c = session.createQuery(hql).uniqueResult();


 order by 子句
    asc  升序
    desc 降序

    String hql = "from Commodity c order by c.price asc";

    设置多个排序规则

    //多个规则之间使用逗号分割
    String hql = "from Commodity c order by c.seller.id asc, c.price desc";


注意
1.hql语句形式
2.hql语句大小写敏感，特别时持久化类及其属性的大小写
3.别名的使用
4.select子句使用自定义类返回选择属性，持久化类构造方法处理， 一定要提供默认构造方法

练习1：
1.查询商品的名称，价格，所属商家名称， 类别
2.查询范围限定在类别与“书”相关，价格不低于10元的商品
3.查询结果按照商品的所属商家名称排序，价格降序和商品名称升序排序

练习2：
1.查询订单的客户姓名，交易日期，订单状态，订单金额
2.查询范围限定在交易日期为2015-05-01到2015-06-01之间。订单状态为已发货或者已付款，订单金额大于1000元的订单信息
3.查询结果按照订单状态升序，交易日期降序，订单金额升序排序



