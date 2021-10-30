package expressions

class ExprEvaluator {
    def simplifyTop(expr: Expr): Expr = {
        expr match {
            case UnOp("-", UnOp("-", e: Expr)) => e
            case BinOp("+", e: Expr, Number(0)) => e
            case BinOp("+", Number(0), e: Expr) => e
            case BinOp("*", e: Expr, Number(1)) => e
            case BinOp("*", Number(1), e: Expr) => e
            case UnOp("abs", UnOp("abs", e: Expr)) => UnOp("abs", e)
            case BinOp("+", x: Expr, y: Expr) if (x == y) => BinOp("*", x, Number(2))
            case UnOp("+", e: Expr) => e
            case _ => expr
        }
    }

    def simplifyAll(expr: Expr): Expr = {

        expr match {
            case UnOp(op, e) => simplifyTop(UnOp(op, simplifyAll(e)))
            case BinOp(op, left, right) => simplifyTop(BinOp(op, simplifyAll(left), simplifyAll(right)))
            case Var(_) => expr
            case Number(p) => Number(p)
        }
    }
}
