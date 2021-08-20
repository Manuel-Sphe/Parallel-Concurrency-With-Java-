JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

BINDIR=bin
SRCDIR=src
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES = Data.class MyThread.class
CLASS_FILES = $(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(CLASS_FILES)

run3:
	java -cp bin MyThread "sampleInput/sampleInput100000.txt" "3" "out100000.txt"

