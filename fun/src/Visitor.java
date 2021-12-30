interface Visitor {
  public Object visit(Exp e);
  public Object visit(Plus e);
  public Object visit(Minus e);
  public Object visit(Times e);
  public Object visit(Divide e);
  public Object visit(Power e);
  public Object visit(Var e);
  public Object visit(Num e);
  public Object visit(Sin e);
  public Object visit(Cos e);
  public Object visit(Tan e);
  public Object visit(Ln e);
  public Object visit(Log e);
  public Object visit(Euler e);
  public Object visit(Abs e);
}
