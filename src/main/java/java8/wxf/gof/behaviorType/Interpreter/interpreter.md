解释器模式（Interpreter）

效果
1. 不常用的设计模式。

2. 用于描述如何构成一个简单的语言解释器，主要用于使用面向对象语言开发的编译器和解释器设计。

3. 尽量不要使用解释器模式，后期维护非常麻烦。在项目中，可以使用JRuby、Groovy、Java的JavaScript引擎来代替解释器，弥补Java语言不足。


应用场景
1. EL表达式的处理。

2. 正则表达式解释器。

3. SQL语法解释器。

4. 数学表达式解析器，如工具包Math Expression String Parser、Expression4J。