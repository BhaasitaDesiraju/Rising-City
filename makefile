:%s/^[ ]\+/^I/
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES =	\
	Building.java \
	MinHeap.java \
	RedBlackTree.java \
	InputFileParser.java \
	MinHeapTest.java \
	RedBlackTreeTest.java \
	RedBlackNode.java \
	WayneConstructions.java
default:classes

classes:$(CLASSES:.java=.class)

clean:
	$(RM) *.class
