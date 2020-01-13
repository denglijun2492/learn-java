[Java 序列化 JDK序列化总结](https://www.cnblogs.com/ixenos/p/5832736.html)

## serialVersionUID
Java序列化允许java类中的一些变化，如果他们可以被忽略的话。一些不会影响到反序列化处理的变化有：
- 在类中添加一些新的变量。
- 将变量从transient转变为非tansient，对于序列化来说，就像是新加入了一个变量而已。
- 将变量从静态的转变为非静态的，对于序列化来说，就也像是新加入了一个变量而已。