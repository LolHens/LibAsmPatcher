package org.lolhens.inject.test.test1

import java.io.PipedOutputStream

import org.lolhens.inject.asm.AsmBlockParser

/**
 * Created by LolHens on 17.12.2014.
 */
object Main {
  def main(args: Array[String]): Unit = {
    //println(AsmBlockParser.parseAsmBlock("L0\n    ICONST_2\n    ISTORE 2\n   L1\n    ALOAD 0\n    ICONST_1\n    PUTFIELD org/lolhens/asmpatcher/test/testclasses/Testclass1.test : I\n   L2\n    ICONST_1\n    PUTSTATIC org/lolhens/asmpatcher/test/testclasses/Testclass1.test2 : I\n   L3\n    ICONST_0\n    ISTORE 3\n   L4\n       ILOAD 3\n    ICONST_3\n    IF_ICMPGE L5\n   L6\n    ALOAD 0\n    ILOAD 3\n    INVOKESPECIAL org/lolhens/asmpatcher/test/testclasses/Testclass1.test2 (I)V\n   L7\n    IINC 3 1\n    GOTO L4\n   L5\n    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;\n    LDC \"test\"\n    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V\n   L8\n    ICONST_3\n    LOOKUPSWITCH\n      1: L9\n      10000: L10\n      default: L10\n   L9\n    GOTO L10\n   L10\n    ICONST_3\n    TABLESWITCH\n      0: L11\n      1: L12\n      2: L13\n      3: L14\n      default: L14\n   L11\n    GOTO L14\n   L12\n    GOTO L14\n   L13\n    GOTO L14\n   L14\n    ICONST_0\n    IRETURN\n   L15"))
    println(AsmBlockParser.parseAsmBlock("L0\n    GETSTATIC org/lolhens/asmpatcher/asmblock/AsmBlockParser$.MODULE$ : Lorg/lolhens/asmpatcher/asmblock/AsmBlockParser$;\n    ALOAD 1\n    INVOKEVIRTUAL org/lolhens/asmpatcher/asmblock/AsmBlockParser$.org$lolhens$asmpatcher$asmblock$AsmBlockParser$$reformatAsm (Ljava/lang/String;)Ljava/lang/String;\n    ASTORE 2\n   L1\n    NEW java/util/ArrayList\n    DUP\n    INVOKESPECIAL java/util/ArrayList.<init> ()V\n    ASTORE 3\n   L2\n    ALOAD 2\n    LDC \"\"\n    ASTORE 4\n    DUP\n    IFNONNULL L3\n    POP\n    ALOAD 4\n    IFNULL L4\n    GOTO L5\n   L3\n    ALOAD 4\n    INVOKEVIRTUAL java/lang/Object.equals (Ljava/lang/Object;)Z\n    IFEQ L5\n   L4\n    ALOAD 3\n   L6\n    ARETURN\n   L5\n    ALOAD 0\n    ALOAD 2\n    INVOKEVIRTUAL org/lolhens/asmpatcher/asmblock/AsmBlockParser.parseInsn (Ljava/lang/String;)Lorg/objectweb/asm/tree/AbstractInsnNode;\n    ASTORE 5\n   L7\n    ALOAD 5\n    IFNONNULL L8\n    NEW java/lang/IllegalArgumentException\n    DUP\n    NEW scala/StringContext\n    DUP\n    GETSTATIC scala/Predef$.MODULE$ : Lscala/Predef$;\n    ICONST_2\n    ANEWARRAY java/lang/String\n    DUP\n    ICONST_0\n    LDC \"Unable to parse intruction: \"\n    AASTORE\n    DUP\n    ICONST_1\n    LDC \"\"\n    AASTORE\n    CHECKCAST [Ljava/lang/Object;\n    INVOKEVIRTUAL scala/Predef$.wrapRefArray ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;\n    INVOKESPECIAL scala/StringContext.<init> (Lscala/collection/Seq;)V\n    GETSTATIC scala/Predef$.MODULE$ : Lscala/Predef$;\n    ICONST_1\n    ANEWARRAY java/lang/Object\n    DUP\n    ICONST_0\n    ALOAD 2\n    AASTORE\n    INVOKEVIRTUAL scala/Predef$.genericWrapArray (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;\n    INVOKEVIRTUAL scala/StringContext.s (Lscala/collection/Seq;)Ljava/lang/String;\n    INVOKESPECIAL java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V\n    ATHROW\n   L8\n    ALOAD 3\n    ALOAD 5\n    INVOKEVIRTUAL java/util/ArrayList.add (Ljava/lang/Object;)Z\n    POP\n   L9\n    GETSTATIC org/lolhens/asmpatcher/asmblock/AsmBlockParser$.MODULE$ : Lorg/lolhens/asmpatcher/asmblock/AsmBlockParser$;\n    ALOAD 2\n    LDC \" \"\n    ICONST_2\n    INVOKEVIRTUAL org/lolhens/asmpatcher/asmblock/AsmBlockParser$.org$lolhens$asmpatcher$asmblock$AsmBlockParser$$forceSplit (Ljava/lang/String;Ljava/lang/String;I)[Ljava/lang/String;\n    ASTORE 6\n   L10\n    GETSTATIC org/lolhens/asmpatcher/asmblock/AsmBlockParser$.MODULE$ : Lorg/lolhens/asmpatcher/asmblock/AsmBlockParser$;\n    ALOAD 5\n    INVOKEVIRTUAL org/objectweb/asm/tree/AbstractInsnNode.getOpcode ()I\n    ALOAD 6\n    ICONST_1\n    AALOAD\n    INVOKEVIRTUAL org/lolhens/asmpatcher/asmblock/AsmBlockParser$.numArgs (ILjava/lang/String;)I\n    ISTORE 7\n   L11\n    GETSTATIC scala/Predef$.MODULE$ : Lscala/Predef$;\n    GETSTATIC org/lolhens/asmpatcher/asmblock/AsmBlockParser$.MODULE$ : Lorg/lolhens/asmpatcher/asmblock/AsmBlockParser$;\n    ALOAD 6\n    ICONST_1\n    AALOAD\n    LDC \" \"\n    ICONST_1\n    ILOAD 7\n    IADD\n    INVOKEVIRTUAL org/lolhens/asmpatcher/asmblock/AsmBlockParser$.org$lolhens$asmpatcher$asmblock$AsmBlockParser$$forceSplit (Ljava/lang/String;Ljava/lang/String;I)[Ljava/lang/String;\n    CHECKCAST [Ljava/lang/Object;\n    INVOKEVIRTUAL scala/Predef$.refArrayOps ([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;\n    INVOKEINTERFACE scala/collection/mutable/ArrayOps.last ()Ljava/lang/Object;\n    CHECKCAST java/lang/String\n    ASTORE 2\n   L12\n    GOTO L2\n   L13"))

  }

/*
  def cur(x: Int) = {
    object cur {
      def apply(y: Int = 3) = x + y
    }
    cur
  }

  def cur(x: String) = {
    object cur {
      def apply(y: String = "test") = x.length + y.length
    }
    cur
  }
*/
  //def cur(x:Int)(y:Int = 3) = x + y

  //def cur(x:String)(y:String = "test") = x.length + y.length

  //cur(_)(3)
}
