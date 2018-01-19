使用步骤：
1.新建类实现IProcess接口并实现onReceive方法，
如果是string或boolean可以不用传泛型，如果不是，
则需要传入对应的实例，
例如：implements IProcess<TestVO>,TestVO为自定义实体。
2.实例化QueFactory,QueFactory包含两个构造函数，
public QueFactory(IProcess<T> process)，
和public QueFactory(Executor pool, Integer queDepth,IProcess<T> process)，
如果使用第一个构造函数，则默认使用内置的线程池和ringbuffer的大小，
默认线程池核心10线程，最大200线程，ringbuffersize默认大小1048576，
一般使用单参数的构造函数。
3.调用QueFactory的start方法，
4.使用QueFactory生成QueEventProducer,例：QueEventProducer<TestVO> producer = factory.createProducer();
5.使用QueEventProducer的onData方法往队列放数据，参数根据需要传，不需要的参数可传null，
6.在实现了IProcess接口的类中的onReceive处理消息。