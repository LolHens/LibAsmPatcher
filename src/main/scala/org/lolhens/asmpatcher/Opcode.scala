package org.lolhens.asmpatcher

import org.lolhens.asmpatcher.Opcode._
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree._

/**
 * Created by LolHens on 14.12.2014.
 */
class Opcode(val name: String,
             val opcode: Int,
             val optype: Int) {
  opcodes(opcode) = this

  override def toString = name

  def toInsn(arg: String): AbstractInsnNode = optype match {
    case AbstractInsnNode.INSN => new InsnNode(opcode)
    case AbstractInsnNode.INT_INSN => new IntInsnNode(opcode, arg(0).toInt)
    case AbstractInsnNode.VAR_INSN => new VarInsnNode(opcode, arg(0).toInt)
    case AbstractInsnNode.TYPE_INSN => ??? //TODO
    case AbstractInsnNode.FIELD_INSN => ??? //TODO
    case AbstractInsnNode.METHOD_INSN => ??? //TODO
    case AbstractInsnNode.INVOKE_DYNAMIC_INSN => ???
    case AbstractInsnNode.JUMP_INSN => new JumpInsnNode(opcode, null) //TODO LABEL!
    case AbstractInsnNode.LDC_INSN => arg match {
      case _ if (arg == "*") => new LdcInsnNode(null)
      case _ if (arg.endsWith("\"")) => new LdcInsnNode(arg.drop(1).dropRight(1))
      case _ if (arg.endsWith("L")) => new LdcInsnNode(arg.dropRight(1).toLong)
      case _ if (arg.endsWith("F")) => new LdcInsnNode(arg.dropRight(1).toFloat)
      case _ if (arg.endsWith("D")) => new LdcInsnNode(arg.dropRight(1).toDouble)
      case _ if (arg.contains(".")) => new LdcInsnNode(arg.toDouble)
      case _ => new LdcInsnNode(arg.toInt)
    }
    case AbstractInsnNode.IINC_INSN => new IincInsnNode(opcode, arg.toInt)
    case AbstractInsnNode.LABEL => ???
    case AbstractInsnNode.TABLESWITCH_INSN => ???
    case AbstractInsnNode.LOOKUPSWITCH_INSN => throw new Exception("I don't know how to deal with this insn type")
    case AbstractInsnNode.MULTIANEWARRAY_INSN => new MultiANewArrayInsnNode(arg, "secondarg".toInt);
    case AbstractInsnNode.FRAME => ???
    case _ => null
  }
}

object Opcode {
  private val opcodes = new Array[Opcode](255)

  private def apply(name: String, opcode: Int, optype: Int): Opcode = new Opcode(name, opcode, optype)

  val NOP = Opcode("nop", Opcodes.NOP, AbstractInsnNode.INSN)
  val ACONST_NULL = Opcode("aconst_null", Opcodes.ACONST_NULL, AbstractInsnNode.INSN)
  val ICONST_M1 = Opcode("iconst_m1", Opcodes.ICONST_M1, AbstractInsnNode.INSN)
  val ICONST_0 = Opcode("iconst_0", Opcodes.ICONST_0, AbstractInsnNode.INSN)
  val ICONST_1 = Opcode("iconst_1", Opcodes.ICONST_1, AbstractInsnNode.INSN)
  val ICONST_2 = Opcode("iconst_2", Opcodes.ICONST_2, AbstractInsnNode.INSN)
  val ICONST_3 = Opcode("iconst_3", Opcodes.ICONST_3, AbstractInsnNode.INSN)
  val ICONST_4 = Opcode("iconst_4", Opcodes.ICONST_4, AbstractInsnNode.INSN)
  val ICONST_5 = Opcode("iconst_5", Opcodes.ICONST_5, AbstractInsnNode.INSN)
  val LCONST_0 = Opcode("lconst_0", Opcodes.LCONST_0, AbstractInsnNode.INSN)
  val LCONST_1 = Opcode("lconst_1", Opcodes.LCONST_1, AbstractInsnNode.INSN)
  val FCONST_0 = Opcode("fconst_0", Opcodes.FCONST_0, AbstractInsnNode.INSN)
  val FCONST_1 = Opcode("fconst_1", Opcodes.FCONST_1, AbstractInsnNode.INSN)
  val FCONST_2 = Opcode("fconst_2", Opcodes.FCONST_2, AbstractInsnNode.INSN)
  val DCONST_0 = Opcode("dconst_0", Opcodes.DCONST_0, AbstractInsnNode.INSN)
  val DCONST_1 = Opcode("dconst_1", Opcodes.DCONST_1, AbstractInsnNode.INSN)
  val BIPUSH = Opcode("bipush", Opcodes.BIPUSH, AbstractInsnNode.INT_INSN)
  val SIPUSH = Opcode("sipush", Opcodes.SIPUSH, AbstractInsnNode.INT_INSN)
  val LDC = Opcode("ldc", Opcodes.LDC, AbstractInsnNode.LDC_INSN)
  //val LDC_W = Opcode("ldc_w", 19)
  //val LDC2_W = Opcode("ldc2_w", 20)
  val ILOAD = Opcode("iload", Opcodes.ILOAD, AbstractInsnNode.VAR_INSN)
  val LLOAD = Opcode("lload", Opcodes.LLOAD, AbstractInsnNode.VAR_INSN)
  val FLOAD = Opcode("fload", Opcodes.FLOAD, AbstractInsnNode.VAR_INSN)
  val DLOAD = Opcode("dload", Opcodes.DLOAD, AbstractInsnNode.VAR_INSN)
  val ALOAD = Opcode("aload", Opcodes.ALOAD, AbstractInsnNode.VAR_INSN)
  //val ILOAD_0 = Opcode("iload_0", 26)
  //val ILOAD_1 = Opcode("iload_1", 27)
  //val ILOAD_2 = Opcode("iload_2", 28)
  //val ILOAD_3 = Opcode("iload_3", 29)
  //val LLOAD_0 = Opcode("lload_0", 30)
  //val LLOAD_1 = Opcode("lload_1", 31)
  //val LLOAD_2 = Opcode("lload_2", 32)
  //val LLOAD_3 = Opcode("lload_3", 33)
  //val FLOAD_0 = Opcode("fload_0", 34)
  //val FLOAD_1 = Opcode("fload_1", 35)
  //val FLOAD_2 = Opcode("fload_2", 36)
  //val FLOAD_3 = Opcode("fload_3", 37)
  //val DLOAD_0 = Opcode("dload_0", 38)
  //val DLOAD_1 = Opcode("dload_1", 39)
  //val DLOAD_2 = Opcode("dload_2", 40)
  //val DLOAD_3 = Opcode("dload_3", 41)
  //val ALOAD_0 = Opcode("aload_0", 42)
  //val ALOAD_1 = Opcode("aload_1", 43)
  //val ALOAD_2 = Opcode("aload_2", 44)
  //val ALOAD_3 = Opcode("aload_3", 45)
  val IALOAD = Opcode("iaload", Opcodes.IALOAD, AbstractInsnNode.INSN)
  val LALOAD = Opcode("laload", Opcodes.LALOAD, AbstractInsnNode.INSN)
  val FALOAD = Opcode("faload", Opcodes.FALOAD, AbstractInsnNode.INSN)
  val DALOAD = Opcode("daload", Opcodes.DALOAD, AbstractInsnNode.INSN)
  val AALOAD = Opcode("aaload", Opcodes.AALOAD, AbstractInsnNode.INSN)
  val BALOAD = Opcode("baload", Opcodes.BALOAD, AbstractInsnNode.INSN)
  val CALOAD = Opcode("caload", Opcodes.CALOAD, AbstractInsnNode.INSN)
  val SALOAD = Opcode("saload", Opcodes.SALOAD, AbstractInsnNode.INSN)
  val ISTORE = Opcode("istore", Opcodes.ISTORE, AbstractInsnNode.VAR_INSN)
  val LSTORE = Opcode("lstore", Opcodes.LSTORE, AbstractInsnNode.VAR_INSN)
  val FSTORE = Opcode("fstore", Opcodes.FSTORE, AbstractInsnNode.VAR_INSN)
  val DSTORE = Opcode("dstore", Opcodes.DSTORE, AbstractInsnNode.VAR_INSN)
  val ASTORE = Opcode("astore", Opcodes.ASTORE, AbstractInsnNode.VAR_INSN)
  //val ISTORE_0 = Opcode("istore_0", 59)
  //val ISTORE_1 = Opcode("istore_1", 60)
  //val ISTORE_2 = Opcode("istore_2", 61)
  //val ISTORE_3 = Opcode("istore_3", 62)
  //val LSTORE_0 = Opcode("lstore_0", 63)
  //val LSTORE_1 = Opcode("lstore_1", 64)
  //val LSTORE_2 = Opcode("lstore_2", 65)
  //val LSTORE_3 = Opcode("lstore_3", 66)
  //val FSTORE_0 = Opcode("fstore_0", 67)
  //val FSTORE_1 = Opcode("fstore_1", 68)
  //val FSTORE_2 = Opcode("fstore_2", 69)
  //val FSTORE_3 = Opcode("fstore_3", 70)
  //val DSTORE_0 = Opcode("dstore_0", 71)
  //val DSTORE_1 = Opcode("dstore_1", 72)
  //val DSTORE_2 = Opcode("dstore_2", 73)
  //val DSTORE_3 = Opcode("dstore_3", 74)
  //val ASTORE_0 = Opcode("astore_0", 75)
  //val ASTORE_1 = Opcode("astore_1", 76)
  //val ASTORE_2 = Opcode("astore_2", 77)
  //val ASTORE_3 = Opcode("astore_3", 78)
  val IASTORE = Opcode("iastore", Opcodes.IASTORE, AbstractInsnNode.INSN)
  val LASTORE = Opcode("lastore", Opcodes.LASTORE, AbstractInsnNode.INSN)
  val FASTORE = Opcode("fastore", Opcodes.FASTORE, AbstractInsnNode.INSN)
  val DASTORE = Opcode("dastore", Opcodes.DASTORE, AbstractInsnNode.INSN)
  val AASTORE = Opcode("aastore", Opcodes.AASTORE, AbstractInsnNode.INSN)
  val BASTORE = Opcode("bastore", Opcodes.BASTORE, AbstractInsnNode.INSN)
  val CASTORE = Opcode("castore", Opcodes.CASTORE, AbstractInsnNode.INSN)
  val SASTORE = Opcode("sastore", Opcodes.SASTORE, AbstractInsnNode.INSN)
  val POP = Opcode("pop", Opcodes.POP, AbstractInsnNode.INSN)
  val POP2 = Opcode("pop2", Opcodes.POP2, AbstractInsnNode.INSN)
  val DUP = Opcode("dup", Opcodes.DUP, AbstractInsnNode.INSN)
  val DUP_X1 = Opcode("dup_x1", Opcodes.DUP_X1, AbstractInsnNode.INSN)
  val DUP_X2 = Opcode("dup_x2", Opcodes.DUP_X2, AbstractInsnNode.INSN)
  val DUP2 = Opcode("dup2", Opcodes.DUP2, AbstractInsnNode.INSN)
  val DUP2_X1 = Opcode("dup2_x1", Opcodes.DUP2_X1, AbstractInsnNode.INSN)
  val DUP2_X2 = Opcode("dup2_x2", Opcodes.DUP2_X2, AbstractInsnNode.INSN)
  val SWAP = Opcode("swap", Opcodes.SWAP, AbstractInsnNode.INSN)
  val IADD = Opcode("iadd", Opcodes.IADD, AbstractInsnNode.INSN)
  val LADD = Opcode("ladd", Opcodes.LADD, AbstractInsnNode.INSN)
  val FADD = Opcode("fadd", Opcodes.FADD, AbstractInsnNode.INSN)
  val DADD = Opcode("dadd", Opcodes.DADD, AbstractInsnNode.INSN)
  val ISUB = Opcode("isub", Opcodes.ISUB, AbstractInsnNode.INSN)
  val LSUB = Opcode("lsub", Opcodes.LSUB, AbstractInsnNode.INSN)
  val FSUB = Opcode("fsub", Opcodes.FSUB, AbstractInsnNode.INSN)
  val DSUB = Opcode("dsub", Opcodes.DSUB, AbstractInsnNode.INSN)
  val IMUL = Opcode("imul", Opcodes.IMUL, AbstractInsnNode.INSN)
  val LMUL = Opcode("lmul", Opcodes.LMUL, AbstractInsnNode.INSN)
  val FMUL = Opcode("fmul", Opcodes.FMUL, AbstractInsnNode.INSN)
  val DMUL = Opcode("dmul", Opcodes.DMUL, AbstractInsnNode.INSN)
  val IDIV = Opcode("idiv", Opcodes.IDIV, AbstractInsnNode.INSN)
  val LDIV = Opcode("ldiv", Opcodes.LDIV, AbstractInsnNode.INSN)
  val FDIV = Opcode("fdiv", Opcodes.FDIV, AbstractInsnNode.INSN)
  val DDIV = Opcode("ddiv", Opcodes.DDIV, AbstractInsnNode.INSN)
  val IREM = Opcode("irem", Opcodes.IREM, AbstractInsnNode.INSN)
  val LREM = Opcode("lrem", Opcodes.LREM, AbstractInsnNode.INSN)
  val FREM = Opcode("frem", Opcodes.FREM, AbstractInsnNode.INSN)
  val DREM = Opcode("drem", Opcodes.DREM, AbstractInsnNode.INSN)
  val INEG = Opcode("ineg", Opcodes.INEG, AbstractInsnNode.INSN)
  val LNEG = Opcode("lneg", Opcodes.LNEG, AbstractInsnNode.INSN)
  val FNEG = Opcode("fneg", Opcodes.FNEG, AbstractInsnNode.INSN)
  val DNEG = Opcode("dneg", Opcodes.DNEG, AbstractInsnNode.INSN)
  val ISHL = Opcode("ishl", Opcodes.ISHL, AbstractInsnNode.INSN)
  val LSHL = Opcode("lshl", Opcodes.LSHL, AbstractInsnNode.INSN)
  val ISHR = Opcode("ishr", Opcodes.ISHR, AbstractInsnNode.INSN)
  val LSHR = Opcode("lshr", Opcodes.LSHR, AbstractInsnNode.INSN)
  val IUSHR = Opcode("iushr", Opcodes.IUSHR, AbstractInsnNode.INSN)
  val LUSHR = Opcode("lushr", Opcodes.LUSHR, AbstractInsnNode.INSN)
  val IAND = Opcode("iand", Opcodes.IAND, AbstractInsnNode.INSN)
  val LAND = Opcode("land", Opcodes.LAND, AbstractInsnNode.INSN)
  val IOR = Opcode("ior", Opcodes.IOR, AbstractInsnNode.INSN)
  val LOR = Opcode("lor", Opcodes.LOR, AbstractInsnNode.INSN)
  val IXOR = Opcode("ixor", Opcodes.IXOR, AbstractInsnNode.INSN)
  val LXOR = Opcode("lxor", Opcodes.LXOR, AbstractInsnNode.INSN)
  val IINC = Opcode("iinc", Opcodes.IINC, AbstractInsnNode.IINC_INSN)
  val I2L = Opcode("i2l", Opcodes.I2L, AbstractInsnNode.INSN)
  val I2F = Opcode("i2f", Opcodes.I2F, AbstractInsnNode.INSN)
  val I2D = Opcode("i2d", Opcodes.I2D, AbstractInsnNode.INSN)
  val L2I = Opcode("l2i", Opcodes.L2I, AbstractInsnNode.INSN)
  val L2F = Opcode("l2f", Opcodes.L2F, AbstractInsnNode.INSN)
  val L2D = Opcode("l2d", Opcodes.L2D, AbstractInsnNode.INSN)
  val F2I = Opcode("f2i", Opcodes.F2I, AbstractInsnNode.INSN)
  val F2L = Opcode("f2l", Opcodes.F2L, AbstractInsnNode.INSN)
  val F2D = Opcode("f2d", Opcodes.F2D, AbstractInsnNode.INSN)
  val D2I = Opcode("d2i", Opcodes.D2I, AbstractInsnNode.INSN)
  val D2L = Opcode("d2l", Opcodes.D2L, AbstractInsnNode.INSN)
  val D2F = Opcode("d2f", Opcodes.D2F, AbstractInsnNode.INSN)
  val I2B = Opcode("i2b", Opcodes.I2B, AbstractInsnNode.INSN)
  val I2C = Opcode("i2c", Opcodes.I2C, AbstractInsnNode.INSN)
  val I2S = Opcode("i2s", Opcodes.I2S, AbstractInsnNode.INSN)
  val LCMP = Opcode("lcmp", Opcodes.LCMP, AbstractInsnNode.INSN)
  val FCMPL = Opcode("fcmpl", Opcodes.FCMPL, AbstractInsnNode.INSN)
  val FCMPG = Opcode("fcmpg", Opcodes.FCMPG, AbstractInsnNode.INSN)
  val DCMPL = Opcode("dcmpl", Opcodes.DCMPL, AbstractInsnNode.INSN)
  val DCMPG = Opcode("dcmpg", Opcodes.DCMPG, AbstractInsnNode.INSN)
  val IFEQ = Opcode("ifeq", Opcodes.IFEQ, AbstractInsnNode.JUMP_INSN)
  val IFNE = Opcode("ifne", Opcodes.IFNE, AbstractInsnNode.JUMP_INSN)
  val IFLT = Opcode("iflt", Opcodes.IFLT, AbstractInsnNode.JUMP_INSN)
  val IFGE = Opcode("ifge", Opcodes.IFGE, AbstractInsnNode.JUMP_INSN)
  val IFGT = Opcode("ifgt", Opcodes.IFGT, AbstractInsnNode.JUMP_INSN)
  val IFLE = Opcode("ifle", Opcodes.IFLE, AbstractInsnNode.JUMP_INSN)
  val IF_ICMPEQ = Opcode("if_icmpeq", Opcodes.IF_ICMPEQ, AbstractInsnNode.JUMP_INSN)
  val IF_ICMPNE = Opcode("if_icmpne", Opcodes.IF_ICMPNE, AbstractInsnNode.JUMP_INSN)
  val IF_ICMPLT = Opcode("if_icmplt", Opcodes.IF_ICMPLT, AbstractInsnNode.JUMP_INSN)
  val IF_ICMPGE = Opcode("if_icmpge", Opcodes.IF_ICMPGE, AbstractInsnNode.JUMP_INSN)
  val IF_ICMPGT = Opcode("if_icmpgt", Opcodes.IF_ICMPGT, AbstractInsnNode.JUMP_INSN)
  val IF_ICMPLE = Opcode("if_icmple", Opcodes.IF_ICMPLE, AbstractInsnNode.JUMP_INSN)
  val IF_ACMPEQ = Opcode("if_acmpeq", Opcodes.IF_ACMPEQ, AbstractInsnNode.JUMP_INSN)
  val IF_ACMPNE = Opcode("if_acmpne", Opcodes.IF_ACMPNE, AbstractInsnNode.JUMP_INSN)
  val GOTO = Opcode("goto", Opcodes.GOTO, AbstractInsnNode.JUMP_INSN)
  val JSR = Opcode("jsr", Opcodes.JSR, AbstractInsnNode.JUMP_INSN)
  val RET = Opcode("ret", Opcodes.RET, AbstractInsnNode.VAR_INSN)
  val TABLESWITCH = Opcode("tableswitch", Opcodes.TABLESWITCH, AbstractInsnNode.TABLESWITCH_INSN)
  val LOOKUPSWITCH = Opcode("lookupswitch", Opcodes.LOOKUPSWITCH, AbstractInsnNode.LOOKUPSWITCH_INSN)
  val IRETURN = Opcode("ireturn", Opcodes.IRETURN, AbstractInsnNode.INSN)
  val LRETURN = Opcode("lreturn", Opcodes.LRETURN, AbstractInsnNode.INSN)
  val FRETURN = Opcode("freturn", Opcodes.FRETURN, AbstractInsnNode.INSN)
  val DRETURN = Opcode("dreturn", Opcodes.DRETURN, AbstractInsnNode.INSN)
  val ARETURN = Opcode("areturn", Opcodes.ARETURN, AbstractInsnNode.INSN)
  val RETURN = Opcode("return", Opcodes.RETURN, AbstractInsnNode.INSN)
  val GETSTATIC = Opcode("getstatic", Opcodes.GETSTATIC, AbstractInsnNode.FIELD_INSN)
  val PUTSTATIC = Opcode("putstatic", Opcodes.PUTSTATIC, AbstractInsnNode.FIELD_INSN)
  val GETFIELD = Opcode("getfield", Opcodes.GETFIELD, AbstractInsnNode.FIELD_INSN)
  val PUTFIELD = Opcode("putfield", Opcodes.PUTFIELD, AbstractInsnNode.FIELD_INSN)
  val INVOKEVIRTUAL = Opcode("invokevirtual", Opcodes.INVOKEVIRTUAL, AbstractInsnNode.METHOD_INSN)
  val INVOKESPECIAL = Opcode("invokespecial", Opcodes.INVOKESPECIAL, AbstractInsnNode.METHOD_INSN)
  val INVOKESTATIC = Opcode("invokestatic", Opcodes.INVOKESTATIC, AbstractInsnNode.METHOD_INSN)
  val INVOKEINTERFACE = Opcode("invokeinterface", Opcodes.INVOKEINTERFACE, AbstractInsnNode.METHOD_INSN)
  val INVOKEDYNAMIC = Opcode("invokedynamic", Opcodes.INVOKEDYNAMIC, AbstractInsnNode.INVOKE_DYNAMIC_INSN)
  val NEW = Opcode("new", Opcodes.NEW, AbstractInsnNode.TYPE_INSN)
  val NEWARRAY = Opcode("newarray", Opcodes.NEWARRAY, AbstractInsnNode.INT_INSN)
  val ANEWARRAY = Opcode("anewarray", Opcodes.ANEWARRAY, AbstractInsnNode.TYPE_INSN)
  val ARRAYLENGTH = Opcode("arraylength", Opcodes.ARRAYLENGTH, AbstractInsnNode.INSN)
  val ATHROW = Opcode("athrow", Opcodes.ATHROW, AbstractInsnNode.INSN)
  val CHECKCAST = Opcode("checkcast", Opcodes.CHECKCAST, AbstractInsnNode.TYPE_INSN)
  val INSTANCEOF = Opcode("instanceof", Opcodes.INSTANCEOF, AbstractInsnNode.TYPE_INSN)
  val MONITORENTER = Opcode("monitorenter", Opcodes.MONITORENTER, AbstractInsnNode.INSN)
  val MONITOREXIT = Opcode("monitorexit", Opcodes.MONITOREXIT, AbstractInsnNode.INSN)
  //val WIDE = Opcode("wide", 196)
  val MULTIANEWARRAY = Opcode("multianewarray", Opcodes.MULTIANEWARRAY, AbstractInsnNode.MULTIANEWARRAY_INSN)
  val IFNULL = Opcode("ifnull", Opcodes.IFNULL, AbstractInsnNode.JUMP_INSN)
  val IFNONNULL = Opcode("ifnonnull", Opcodes.IFNONNULL, AbstractInsnNode.JUMP_INSN)
  //val GOTO_W = Opcode("goto_w", 200)
  //val JSR_W = Opcode("jsr_w", 201)
  //val BREAKPOINT = Opcode("breakpoint", 202)
  //val IMPDEP1 = Opcode("impdep1", 254)
  //val IMPDEP2 = Opcode("impdep2", 255)

  def apply(opcode: Int): Opcode = opcodes(opcode)

  def apply(name: String): Opcode = {
    val opName = name.toLowerCase
    for (opcode <- opcodes) if (opcode.name == opName) return opcode
    null
  }
}
