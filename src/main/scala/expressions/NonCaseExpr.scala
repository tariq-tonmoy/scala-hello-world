package expressions

abstract class NonCaseExpr

class NonCaseVar(name: String) extends NonCaseExpr

class NonCaseNumber(num: Double) extends NonCaseExpr

class NonCaseUnOp(operator: String, arg: NonCaseExpr) extends NonCaseExpr

class NonCaseBinOp(operator: String, leftArg: NonCaseExpr, rightArg: NonCaseExpr) extends NonCaseExpr


