class PrintVisitor implements Visitor {
  public Object visit(Exp e) {
  	return e.accept(this);
  }
  public Object visit(Plus e) {
  	String a = (String)e.e1.accept(this);
  	String b = (String)e.e2.accept(this);
  	return "(" + a + ")+(" + b + ")";
  }
  public Object visit(Minus e) {
  	String a = (String)e.e1.accept(this);
  	String b = (String)e.e2.accept(this);
  	return "(" + a + ")-(" + b + ")";
  }
  public Object visit(Times e) {
  	String a = (String)e.e1.accept(this);
  	String b = (String)e.e2.accept(this);
  	return "(" + a + ")*(" + b + ")";
  }
  public Object visit(Divide e) {
  	String a = (String)e.e1.accept(this);
  	String b = (String)e.e2.accept(this);
  	return "(" + a + ")/(" + b + ")";
  }
  public Object visit(Power e) {
  	String a = (String)e.e1.accept(this);
  	String b = (String)e.e2.accept(this);
  	return "(" + a + ")^(" + b + ")";
  }
  public Object visit(Var e) {
  	return e.id;
  }
  public Object visit(Num e) {
  	return e.n + "";
  }
  public Object visit(Sin e) {
  	String a = (String)e.e.accept(this);
  	return "sin(" + a + ")";
  }
  public Object visit(Cos e) {
  	String a = (String)e.e.accept(this);
  	return "cos(" + a + ")";
  }
  public Object visit(Tan e) {
  	String a = (String)e.e.accept(this);
  	return "tan(" + a + ")";
  }
  public Object visit(Ln e) {
  	String a = (String)e.e.accept(this);
  	return "ln(" + a + ")";
  }
  public Object visit(Log e) {
  	String a = (String)e.e.accept(this);
  	return "log(" + a + ")";
  }
  public Object visit(Euler e) {
  	String a = (String)e.e.accept(this);
  	return "exp(" + a + ")";
  }
  public Object visit(Abs e) {
  	String a = (String)e.e.accept(this);
  	return "abs(" + a + ")";
  }
}

